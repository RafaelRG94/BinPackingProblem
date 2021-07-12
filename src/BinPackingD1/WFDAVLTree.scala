package BinPackingD1

import scala.collection.mutable.ArrayBuffer

class WFDAVLTree {

  val worstFitDecTree = new WFAVLTree

  def addAll(instance: ProblemInstance): Unit = {
    worstFitDecTree.addAll(instance.sortDescendingInstance())
  }

  def inOrder: ArrayBuffer[Bin] = {
    worstFitDecTree.inOrder
  }

  override def toString: String = {
    worstFitDecTree.toString()
  }

}
