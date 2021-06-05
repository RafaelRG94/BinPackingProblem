package BinPackingD1

import scala.collection.mutable.ArrayBuffer
import BinPackingD1.Utils.{reorderBufferArrays, smallerThanTarget}

class WorstFit(val instance: ProblemInstance) {

  def solve(): Solution = {
    val solution = new ArrayBuffer[Bin]()
    val current = new Bin(instance.capacity)
    solution += current
    for (item <- instance.items) {
      val targetBin = smallerThanTarget(item, solution)
      if (targetBin < solution.length - 1) {
        solution(solution.length - 1).add(item)
        reorderBufferArrays(solution.length - 1, solution)
      } else {
        val current = new Bin(instance.capacity)
        current.add(item)
        solution += current
        reorderBufferArrays(targetBin + 1, solution)
      }
    }
    new Solution(solution.toArray)
  }

}
