package BinPackingD1

import scala.collection.mutable.ArrayBuffer

object BFAVLTree {

  def apply(): BFAVLTree =
    new BFAVLTree()

  private class Node(var h: Int, var bin: Bin, var left: Node, var right: Node) {
    // Comprueba si este nodo se inclina a la derecha
    def rightLeaning: Boolean =
      height(right) >= height(left)

    // Comprueba si este nodo se inclina a la izquierda
    def leftLeaning: Boolean =
      height(left) >= height(right)

    // Recalcula la altura de este nodo
    def setHeight(): Unit = {
      h = 1 + (height(left) max height(right))
    }

    // Rota a la derecha este nodo
    def rotateRight(): Node = {
      val lt = this.left
      val lrt = lt.right

      this.left = lrt
      this.setHeight()

      lt.right = this
      lt.setHeight()

      lt
    }

    // Rota a la izquierda este nodo
    def rotateLeft(): Node = {
      val rt = this.right
      val rlt = rt.left

      this.right = rlt
      this.setHeight()

      rt.left = this
      rt.setHeight()

      rt
    }

    // Balancéa este nodo
    def balance(): Node = {
      val lh = height(left)
      val rh = height(right)

      if (lh - rh > 1) {
        if (!left.leftLeaning)
          left = left.rotateLeft() // double rotation
        this.rotateRight()
      } else if (rh - lh > 1) {
        if (!right.rightLeaning)
          right = right.rotateRight() // double rotation
        this.rotateLeft()
      } else { // already balanced
        this.setHeight()
        this
      }
    }

  }

  // Altura de un nodo en el árbol
  private def height(node: Node): Int =
    if (node==null) 0 else node.h

  // Elimina cubo mínimo del árbol node, copiando previamente su contenido al nodo temp
  private def split(node: Node, temp: Node): Node = {
    if(node.left == null) {
      temp.bin = node.bin
      node.right
    } else {
      node.left = split(node.left, temp)
      node.balance()
    }
  }

  // método auxiliar para implementar toString
  private def buildStringRec(sb: StringBuilder, node: Node, indent: Int, tab: Int = 2): Unit = {
    val spaces = "".padTo(indent, ' ')
    sb.append(spaces)
    if(node==null)
      sb.append("null")
    else {
      sb.append("Node(")
      sb.append(node.h)
      sb.append(", ")
      sb.append(node.bin.toString)
      sb.append(",")
      if(tab>0)
        sb.append("\n")
      buildStringRec(sb, node.left, indent+tab, tab)
      sb.append(",")
      if(tab>0)
        sb.append("\n")
      buildStringRec(sb, node.right, indent+tab, tab)
      if(tab>0)
        sb.append("\n")
      sb.append(spaces)
      sb.append(")")
    }
  }

  private def checkAVLBalancedRec(node: Node): Boolean =
    (node==null) ||
      ((height(node.left) - height(node.right)).abs <= 1 &&
        checkAVLBalancedRec(node.left) &&
        checkAVLBalancedRec(node.right))

}

class BFAVLTree {
  import BFAVLTree._

  // referencia al nodo raíz del arbol
  private var root: Node = null

  // true si el árbol está vacío
  def isEmpty: Boolean =
    root == null

  // inserta bin en el árbol
  def insert(bin: Bin): Unit = {

    def insertRec(node: Node): Node = {
      if (node == null)
        new Node(1, bin: Bin,null, null)
      else {
        val cmp = bin.getLeftCapacity - node.bin.getLeftCapacity
        if (cmp < 0)
          node.left = insertRec(node.left)
        else // en esta implementación almacenamos elementos repetidos a la derecha
          node.right = insertRec(node.right)
        node.balance()
      }
    }

    root = insertRec(root) // inserta empezando por la raíz
  }

  // Elimina y devuelve el menor elemento que es mayor o igual que elem
  def deleteLargestEq(elem: Int): Option[Bin] = {
    var deleted: Option[Bin] = None // Acabará almacenando el elemento borrado

    def deleteRec(node: Node): Node = {
      deleted = None // Empezamos asumiendo que no vamos a encontrar el elemento

      // método auxiliar que elimina el nodo node
      def deleteThisNode(): Node = {
        deleted = Some(node.bin)
        if (node.left == null)
          node.right
        else if (node.right == null)
          node.left
        else {
          node.right = split(node.right, node)
          node.balance()
        }
      }


      if (node == null)
        null // no encontrado
      else {
        val cmp = node.bin.getLeftCapacity - elem

        if (cmp >= 0) {
          // El elemento en el nodo node cumpliría el criterio

          // Ver si hay otro en la rama izquierda que también cumpla.
          // Si lo hubiese, sería el menor que cumpliría el criterio
          if (node.left != null) {
            val left = deleteRec(node.left)
            deleted match {
              case None =>
                // No hay ninguno en la izquierda que cumpla el criterio
                deleteThisNode() // borrar el elemento en el nodo node
              case Some(deletedElem) =>
                // Hemos borrado uno en la izquierda que cumple el criterio.
                node.left = left
                node.balance()
            }
          } else {
            // No hay hijo izquierdo, así que el menor que cumple es el del nodo node; borrarlo.
            deleteThisNode()
          }
        } else {
          // Si hay alguno que cumpla el criterio, estará en la rama derecha
          node.right = deleteRec(node.right)
          node.balance()
        }
      }
    }

    root = deleteRec(root)
    deleted
  }

  def addBest(initialCapacity: Int, weight: Int): Unit = {/*
    if(root == null) {
      val bin = new Bin(initialCapacity)
      bin.add(weight)
      insert(bin)
    } else {*/
      val auxBin: Option[Bin] = deleteLargestEq(weight)
      if(auxBin.isEmpty){
        val bin = new Bin(initialCapacity)
        bin.add(weight)
        insert(bin)
      } else {
        val bin = auxBin.get
        bin.add(weight)
        insert(bin)/*
      }*/
    }
  }

  def addAll(instance: ProblemInstance): Unit = {
    for (item <- instance.items) {
      addBest(instance.capacity, item)/*
      // uncomment next two lines to debug
      println(toIndentedString)
      println(checkAVLBalanced)
      println()*/
    }
  }

  override def toString: String = {
    val sb = new StringBuilder()
    buildStringRec(sb, root,0, 0)
    sb.toString()
  }

  def toIndentedString: String = {
    val sb = new StringBuilder()
    buildStringRec(sb, root, 0, 2)
    sb.toString()
  }

  // Realiza un recorrido del árbol en orden, almacenando los elementos en un ArrayBuffer
  def inOrder: ArrayBuffer[Bin] = {
    val ab = new ArrayBuffer[Bin]()

    def inOrderRec(node: Node): Unit = {
      if(node!=null) {
        inOrderRec(node.left)
        ab.append(node.bin)
        inOrderRec(node.right)
      }
    }
    inOrderRec(root)
    ab
  }

  def checkAVLBalanced: Boolean =
    checkAVLBalancedRec(root)

}
