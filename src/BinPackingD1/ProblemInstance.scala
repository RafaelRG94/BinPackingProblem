package BinPackingD1

import BinPackingD1.Utils.sortDescending

class ProblemInstance(val capacity: Int, val items: Array[Int]) {
  override def toString: String = s"ProblemInstance with capacity: $capacity and items: ${items.mkString(",")}"

  def sortDescendingInstance(): ProblemInstance = {
    val sortedItems = items.clone()
    sortDescending(sortedItems)
    val sortedInstance = new ProblemInstance(capacity, sortedItems)
    sortedInstance
  }
}
