package BinPackingD1.Solvers.AVLTreeAlgorithm

import BinPackingD1.Bin
import BinPackingD1.Bin.{Bin, ProblemInstance}

class BFAVLTree(instance: ProblemInstance) extends AVLTree(instance)  {

  def name: String = "Best Fit AVL Tree Algorithm"

  // Elimina y devuelve el menor elemento que es mayor o igual que elem
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

        if (cmp >= 0) {
          // El elemento en el nodo node cumpliría el criterio

          // Ver si hay otro en la rama izquierda que también cumpla.
          // Si lo hubiese, sería el menor que cumpliría el criterio
          if (node.left != null) {
            val left = deleteRec(node.left)
            deleted match {
              case None =>
                // No hay ninguno en la izquierda que cumpla el criterio
                deleteThisNode() // borrar el elemento en el nodo node
              case Some(deletedElem) =>
                // Hemos borrado uno en la izquierda que cumple el criterio.
                node.left = left
                node.balance()
            }
          } else {
            // No hay hijo izquierdo, así que el menor que cumple es el del nodo node; borrarlo.
            deleteThisNode()
          }
        } else {
          // Si hay alguno que cumpla el criterio, estará en la rama derecha
          node.right = deleteRec(node.right)
          node.balance()
        }
      }
    }

    root = deleteRec(root)
    deleted
  }

}
