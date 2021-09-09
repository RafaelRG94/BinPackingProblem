package BinPackingD1.Solvers.AVLTreeAlgorithm

import BinPackingD1.Bin.{Bin, ProblemInstance, Solution}
import BinPackingD1.Solvers.Solver

import scala.collection.mutable.ArrayBuffer

class WFDAVLTree(instance: ProblemInstance) extends Solver {
  def name: String = "Worst Fit Decreasing AVL Tree Algorithm"
  val worstFitDecTree = new WFAVLTree(instance.sortDescendingInstance())

  def addAll(): Unit = {
    worstFitDecTree.addAll()
  }

  def solve(): Solution = {
    addAll()
    new Solution(inOrder.toArray)
  }

  def inOrder: ArrayBuffer[Bin] = {
    worstFitDecTree.inOrder
  }

  override def toString: String = {
    worstFitDecTree.toString()
  }

}
