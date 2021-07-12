package BinPackingD1

import scala.collection.mutable.ArrayBuffer

class BFDAVLTree() {

  val bestFitDecTree = new BFAVLTree

  def addAll(instance: ProblemInstance): Unit = {
    bestFitDecTree.addAll(instance.sortDescendingInstance())
  }

  def inOrder: ArrayBuffer[Bin] = {
    bestFitDecTree.inOrder
  }

  override def toString: String = {
    bestFitDecTree.toString()
  }

}
