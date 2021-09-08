package BinPackingD1

import scala.collection.mutable.ArrayBuffer
import BinPackingD1.Utils.reorderBufferArrays

class AlmostWorstFit(val instance: ProblemInstance) extends Solver {
  def name: String = "AlmostWorstFit Algorithm"

  def solve(): Solution = {
    val solution = new ArrayBuffer[Bin]()
    for (item <- instance.items) {
      var targetBin: Int = 0
      var found = false
      if (!solution.isEmpty){
        if (solution.length == 1 && solution(solution.length - 1).canAdd(item)) {
          found = true
          targetBin = solution.length - 1
        } else if (solution.length > 1) {
          if (solution(solution.length - 2).canAdd(item)) {
            found = true
            targetBin = solution.length - 2
          } else if (solution(solution.length - 1).canAdd(item)) {
            found = true
            targetBin = solution.length - 1
          }
        }
      }
      if (found) {
        solution(targetBin).add(item)
        reorderBufferArrays(targetBin, solution)
      } else if (solution.isEmpty || !found) {
        val current = new Bin(instance.capacity)
        current.add(item)
        solution += current
        reorderBufferArrays(solution.length - 1, solution)
      }
    }
    new Solution(solution.toArray)
  }

}
