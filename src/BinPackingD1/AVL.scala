package BinPackingD1

class AVL {

  case class Node() {
    var bin: Option[Bin] = None
    var height = 0
    var maxRemainingCapacity = 0
    var left, right: Option[Node] = None

    ///////////////////
    //def getBalance(): Int = {right.height + left.height}
    ///////////////////

    //def setHeight(): Unit = {height = 1 + max(left.height, right.height)} //arreglar

    def setMaxRemainingCapacity(maxRemainingCapacity: Int): Unit = {this.maxRemainingCapacity = maxRemainingCapacity}

    def rotateLeft(): Node = {
      val newRoot = right
      right = newRoot.get.left
      newRoot.get.left = Option(this)
      newRoot.get
    }
  }
  private var root: Option[Node] = None

  def maxRemainingCapacity(n: Node): Int = n.maxRemainingCapacity

  def height(n: Node): Int = n.height

  def addNewBin(bin: Bin): Unit = {
    val newNode = Node()
    newNode.bin = Option(bin)
    var current = root
    while (current.get.right.isDefined){
      current = current.get.right
    }
    current.get.right = Option(newNode)
  }

}
