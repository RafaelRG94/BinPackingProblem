package BinPackingD1

class WFAVLTree(instance: ProblemInstance) extends AVLTree(instance) {

  def name: String = "Worst Fit AVL Tree Algorithm"

  def delete(elem: Int): Option[Bin] = {
    var deleted: Option[Bin] = None // Acabará almacenando el elemento borrado

    def deleteRec(node: Node): Node = {
      deleted = None // Empezamos asumiendo que no vamos a encontrar el elemento

      // método auxiliar que elimina el nodo node
      def deleteThisNode(): Node = {
        deleted = Some(node.bin)
        if (node.left == null)
          node.right
        else if (node.right == null)
          node.left
        else {
          node.right = split(node.right, node)
          node.balance()
        }
      }

      if (node == null)
        null // no encontrado
      else {
        val cmp = node.bin.getLeftCapacity - elem
        if (node.right == null && cmp >= 0) {
          deleteThisNode()
        } else {
          node.right = deleteRec(node.right)
          node.balance()
        }
      }
    }

    root = deleteRec(root)
    deleted
  }

}