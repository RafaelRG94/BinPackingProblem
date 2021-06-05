package BinPackingD1

import scala.collection.mutable.ArrayBuffer

class NextFit(val instance: ProblemInstance) {

  def solve(): Solution = {
    val solution = new ArrayBuffer[Bin]()
    var current = new Bin(instance.capacity)
    for (item <- instance.items) {
      if (current.canAdd(item)) {
        current.add(item)
      } else {
        solution += current
        current = new Bin(instance.capacity)
        current.add(item)
      }
    }
    solution += current
    new Solution(solution.toArray)
  }

}
