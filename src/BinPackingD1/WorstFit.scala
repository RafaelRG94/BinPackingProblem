package BinPackingD1

import scala.collection.mutable.ArrayBuffer
import BinPackingD1.Utils.{reorderBufferArrays, smallerThanTarget}

class WorstFit(val instance: ProblemInstance) extends Solver {
  def name: String = "Worst Fit Algorithm"

  def solve(): Solution = {
    val solution = new ArrayBuffer[Bin]()
    var current = new Bin(instance.capacity)
    for (item <- instance.items) {
      val targetBin = smallerThanTarget(item, solution)
      if (targetBin < solution.length - 1) {
        solution(solution.length - 1).add(item)
        reorderBufferArrays(solution.length - 1, solution)
      } else {
        solution += current
        current = new Bin(instance.capacity)
        current.add(item)
        reorderBufferArrays(targetBin + 1, solution)
      }
    }
    new Solution(solution.toArray)
  }

}
