package BinPackingD1.Solvers.GreedyAlgorithm

import BinPackingD1.Bin.{Bin, ProblemInstance, Solution}
import BinPackingD1.Solvers.Solver
import BinPackingD1.Utils.Utils.reorderBufferArrays

import scala.collection.mutable.ArrayBuffer

class WorstFit(val instance: ProblemInstance) extends Solver {
  def name: String = "Worst Fit Algorithm"

  def solve(): Solution = {
    val solution = new ArrayBuffer[Bin]()
    for (item <- instance.items) {
      //val targetBin = 1 + greaterThanTarget(item, solution)
      if (!solution.isEmpty && solution(solution.length - 1).canAdd(item)) {
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
