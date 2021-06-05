package BinPackingD1

class ProblemInstance(val capacity: Int, val items: Array[Int]) {
  override def toString: String = s"ProblemInstance with capacity: $capacity and items: ${items.mkString(",")}"
}
