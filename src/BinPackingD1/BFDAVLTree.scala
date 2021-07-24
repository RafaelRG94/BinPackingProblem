package BinPackingD1

import scala.collection.mutable.ArrayBuffer

class BFDAVLTree(instance: ProblemInstance) extends Solver {
  def name: String = "Best Fit Decreasing AVL Tree Algorithm"
  val bestFitDecTree = new BFAVLTree(instance.sortDescendingInstance())

  def addAll(): Unit = {
    bestFitDecTree.addAll()
  }

  def solve(): Solution = {
    addAll()
    new Solution(inOrder.toArray)
  }

  def inOrder: ArrayBuffer[Bin] = {
    bestFitDecTree.inOrder
  }

  override def toString: String = {
    bestFitDecTree.toString()
  }

}