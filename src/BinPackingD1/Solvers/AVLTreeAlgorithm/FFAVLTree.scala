package BinPackingD1.Solvers.AVLTreeAlgorithm

import BinPackingD1.Bin.{Bin, ProblemInstance, Solution}
import BinPackingD1.Solvers.Solver

import scala.collection.mutable.ArrayBuffer

object FFAVLTree {

  def apply(instance: ProblemInstance): FFAVLTree =
    new FFAVLTree(instance)

  private class Node(var h: Int, var maxRemainingCapacity: Int, var bin: Bin, var left: Node, var right: Node) {

    // Recalcula la altura de este nodo
    def setHeight(): Unit = {
      h = 1 + (height(left) max height(right))
    }

    // Rota a la izquierda este nodo
    def rotateLeft(): Node = {
      val rt = this.right
      val rlt = rt.left

      this.right = rlt
      this.setHeight()
      this.setMaxRemainingCapacity()

      rt.left = this
      rt.setHeight()
      rt.setMaxRemainingCapacity()

      rt
    }

    def setMaxRemainingCapacity(): Unit = {
      this.maxRemainingCapacity = Math.max(Math.max(FFAVLTree.maxRemainingCapacity(left), FFAVLTree.maxRemainingCapacity(right)), bin.getLeftCapacity)
    }

  }

  private def maxRemainingCapacity(node: Node): Int =
    if (node==null) 0 else node.maxRemainingCapacity

  // Altura de un nodo en el árbol
  private def height(node: Node): Int =
    if (node==null) 0 else node.h


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
      sb.append(node.maxRemainingCapacity)
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

  private def checkRemainingCapacitiesRec(node: Node): Boolean = {
    (node==null) ||
      node.maxRemainingCapacity ==
        (maxRemainingCapacity(node.left) max
          maxRemainingCapacity(node.right) max
          node.bin.getLeftCapacity)
  }
}

class FFAVLTree(instance: ProblemInstance) extends Solver {
  import FFAVLTree._

  def name: String = "First Fit AVL Tree Algorithm"

  // referencia al nodo raíz del arbol
  private var root: Node = null

  // true si el árbol está vacío
  def isEmpty: Boolean =
    root == null

  //Añade un cubo al final de la espina derecha
  def addNewBin(bin: Bin): Unit = {
    def addBinToNode(node: Node): Node = {
      if(node.right == null){
        node.right = new Node(1, bin.getLeftCapacity, bin,null,null)
        node.setHeight()
        node.setMaxRemainingCapacity()
        node
      } else {
        node.right = addBinToNode(node.right)
        if (height(node.right) - height(node.left) > 1)
          node.rotateLeft()
        else {
          node.setHeight()
          node.setMaxRemainingCapacity()
          node
        }
      }
    }
    if (root == null)
      root = new Node(1, bin.getLeftCapacity, bin,null,null)
    else {
      root = addBinToNode(root)
    }
  }

  def addFirst(initialCapacity: Int, weight: Int): Unit = {
    if(root == null || root.maxRemainingCapacity < weight) {
      val bin = new Bin(initialCapacity)
      bin.add(weight)
      addNewBin(bin)
    } else {
      def addFirstRec(node: Node): Unit = {
        if(maxRemainingCapacity(node.left) >= weight){
          addFirstRec(node.left)
        } else if(node.bin.canAdd(weight)) {
          node.bin.add(weight)
        } else {
          addFirstRec(node.right)
        }
        node.setMaxRemainingCapacity()
      }
      addFirstRec(root)
    }
  }

  def addAll(): Unit = {
    for (item <- instance.items) {
      addFirst(instance.capacity, item)/*
      // uncomment next two lines to debug
      println(toIndentedString)
      println(checkAVLBalanced)
      println(checkMaxRemainingCapacities)
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

  def checkMaxRemainingCapacities: Boolean =
    checkRemainingCapacitiesRec(root)
}
