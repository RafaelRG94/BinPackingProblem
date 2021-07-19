package BinPackingD1

import BinPackingD1.Utils.{reorderBufferArrays, smallerThanTarget}

import scala.collection.mutable.ArrayBuffer

class AlmostWorstFit(val instance: ProblemInstance) extends Solver {
  def name: String = "AlmostWorstFit Algorithm"

  def solve(): Solution = {
    val solution = new ArrayBuffer[Bin]()
    val current = new Bin(instance.capacity)
    solution += current
    for (item <- instance.items) {
      val targetBin = smallerThanTarget(item, solution)
      if (targetBin < solution.length - 1) {
        if (solution.length <= 2) {
          solution(solution.length - 1).add(item)
          reorderBufferArrays(solution.length - 1, solution)
        } else if (solution(solution.length - 2).canAdd(item)) {
          solution(solution.length - 2).add(item)
          reorderBufferArrays(solution.length - 2, solution)
        }
        else {
          solution(solution.length - 1).add(item)
          reorderBufferArrays(solution.length - 1, solution)
        }
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
