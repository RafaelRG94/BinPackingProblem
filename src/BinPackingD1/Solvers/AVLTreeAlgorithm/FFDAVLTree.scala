package BinPackingD1.Solvers.AVLTreeAlgorithm

import BinPackingD1.Bin.{Bin, ProblemInstance, Solution}
import BinPackingD1.Solvers.Solver

import scala.collection.mutable.ArrayBuffer

class FFDAVLTree(instance: ProblemInstance) extends Solver {
  def name: String = "First Fit Decreasing AVL Tree Algorithm"
  val firstFitDecTree = new FFAVLTree(instance.sortDescendingInstance())

  def addAll(): Unit = {
    firstFitDecTree.addAll()
  }

  def solve(): Solution = {
    addAll()
    new Solution(inOrder.toArray)
  }

  def inOrder: ArrayBuffer[Bin] = {
    firstFitDecTree.inOrder
  }

  override def toString: String = {
    firstFitDecTree.toString()
  }

}
