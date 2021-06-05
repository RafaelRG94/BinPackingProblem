package BinPackingD1

class AVL {

  case class Node() {
    var bin: Option[Bin] = None
    var height = 0
    var maxRemainingCapacity = 0
    var left, right: Option[Node] = None

    def setHeight(height: Int): Unit = {this.height = height}

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
