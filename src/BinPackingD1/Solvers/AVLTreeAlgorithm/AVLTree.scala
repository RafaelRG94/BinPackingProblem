package BinPackingD1.Solvers.AVLTreeAlgorithm

import BinPackingD1.Bin.{Bin, ProblemInstance, Solution}
import BinPackingD1.Solvers.Solver

import scala.collection.mutable.ArrayBuffer

abstract class AVLTree(instance: ProblemInstance) extends Solver {

  def delete(elem: Int): Option[Bin]
  def name: String

  // referencia al nodo raíz del arbol
  protected var root: Node = null

  protected class Node(var h: Int, var bin: Bin, var left: Node, var right: Node) {
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
  protected def split(node: Node, temp: Node): Node = {
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

  def add(initialCapacity: Int, weight: Int): Unit = {
    val auxBin: Option[Bin] = delete(weight)
    if(auxBin.isEmpty){
      val bin = new Bin(initialCapacity)
      bin.add(weight)
      insert(bin)
    } else {
      val bin = auxBin.get
      bin.add(weight)
      insert(bin)
    }
  }

  def addAll(): Unit = {
    for (item <- instance.items) {
      add(instance.capacity, item)/*
      // uncomment next two lines to debug
      println(toIndentedString)
      println(checkAVLBalanced)
      println()*/
    }
  }

  def solve(): Solution = {
    addAll()
    new Solution(inOrder.toArray)
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
