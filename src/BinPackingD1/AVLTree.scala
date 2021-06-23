package BinPackingD1

import scala.collection.mutable.ArrayBuffer

object AVLTree {

  def apply(): AVLTree =
    new AVLTree()

  private class Node(var h: Int, var maxRemainingCapacity: Int, var bin: Bin, var left: Node, var right: Node) {
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

    def setMaxRemainingCapacity(): Unit = {
      this.maxRemainingCapacity = calculateMaxRemainingCapacity(this)
    }

  }

  //Capacidad restante máxima de un nodo
  private def calculateMaxRemainingCapacity(node: Node): Int = {
    if (node==null) 0
    else {
      val max = node.bin.getLeftCapacity

      if (node.left != null) {
        val leftMax = calculateMaxRemainingCapacity(node.left)
        val max = Math.max(max, leftMax)
      }

      if (node.right != null) {
        val rightMax = calculateMaxRemainingCapacity(node.right)
        val max = Math.max(max, rightMax)
      }
      max
    }
  }

  private def maxRemainingCapacity(node: Node): Int =
    if (node==null) 0 else node.maxRemainingCapacity

  // Altura de un nodo en el árbol
  private def height(node: Node): Int =
    if (node==null) 0 else node.h

  /*
  // método auxiliar para implementar toString
  private def buildStringRec[A](sb: StringBuilder, node: Node[A]): Unit = {
    if(node==null)
      sb.append("null")
    else {
      sb.append("Node(")
      sb.append(node.elem)
      sb.append(", ")
      sb.append(node.h)
      sb.append(", ")
      buildStringRec(sb, node.left)
      sb.append(", ")
      buildStringRec(sb, node.right)
      sb.append(")")
    }
  }*/
}

class AVLTree() {
  import AVLTree.{Node, maxRemainingCapacity}

  // referencia al nodo raíz del arbol
  private var root: Node = null

  // true si el árbol está vacío
  def isEmpty: Boolean =
    root == null

  //Capacidad restante máxima del árbol
  private def treeMaxRemainingCapacity(): Unit = {
    def treeMaxRemainingCapacityRec(node: Node): Unit = {
      if(node != null) {
        node.setMaxRemainingCapacity()
        treeMaxRemainingCapacityRec(node.left)
        treeMaxRemainingCapacityRec(node.right)
      }
    }
    treeMaxRemainingCapacityRec(root)
  }

  //Añade un cubo al final de la espina derecha
  def addNewBin(bin: Bin): Unit = {

    def addBinToNode(node: Node): Node = {
      if(node == null){
        new Node(0, bin.getLeftCapacity, bin,null,null)
      } else {
        node.right = addBinToNode(node.right)
        node.setHeight()
        node.setMaxRemainingCapacity()
        node.balance()
      }
    }

    root = addBinToNode(root)
    treeMaxRemainingCapacity()
  }

  def addFirst(initialCapacity: Int, weight: Int): Unit = {
    if(root == null || root.maxRemainingCapacity < weight) {
      val bin = new Bin(initialCapacity)
      bin.add(weight)
      addNewBin(bin)
    } else {
      def addFirstRec(node: Node): Unit = {
        if(maxRemainingCapacity(node.left) >= weight){
          if(node.left.bin.canAdd(weight)) {
            node.left.bin.add(weight)
          } else {
            addFirstRec(node.left.left)
          }
        } else if(node.bin.canAdd(weight)) {
          node.bin.add(weight)
        } else {
          if(node.right.bin.canAdd(weight)) {
            node.right.bin.add(weight)
          } else {
            addFirstRec(node.right.right)
          }
        }
      }
      addFirstRec(root)
      treeMaxRemainingCapacity()
    }
  }

  def addAll(instance: ProblemInstance): Unit = {
    val tree = new AVLTree
    for (item <- instance.items)
      tree.addFirst(instance.capacity, item)
  }

/*
  override def toString: String = {
    val sb = new StringBuilder()
    buildStringRec(sb, root)
    sb.toString()
  }
*/
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
}
