package BinPackingD1

class AVL {

  case class Node {
    var bin: Bin
    var height: Int
    var maxRemainingCapacity: Int
    var left, right: Node
  }
  val root: Node
  
}
