package BinPackingD1

import BinPackingD1.Utils.{reorderBufferArrays, smallerThanTarget}

import scala.collection.mutable.ArrayBuffer

class WorstFit(val instance: ProblemInstance) extends Solver {
  def name: String = "Worst Fit Algorithm"

  def solve(): Solution = {
    val solution = new ArrayBuffer[Bin]()
    for (item <- instance.items) {
      val targetBin = 1 + smallerThanTarget(item, solution)
      if (targetBin <= solution.length - 1) {
        solution(solution.length - 1).add(item)
        reorderBufferArrays(solution.length - 1, solution)
      } else {
        val current = new Bin(instance.capacity)
        current.add(item)
        solution += current
        reorderBufferArrays(solution.length - 1, solution)
      }
    }
    new Solution(solution.toArray)
  }

}
