package BinPackingD1.Solvers.GreedyAlgorithm

import BinPackingD1.Bin.{Bin, ProblemInstance, Solution}
import BinPackingD1.Solvers.Solver

import scala.collection.mutable.ArrayBuffer

class FirstFit(val instance: ProblemInstance) extends Solver {
  def name: String = "First Fit Algorithm"

  def solve(): Solution = {
    val solution = new ArrayBuffer[Bin]()
    var current = new Bin(instance.capacity)
    solution += current
    for (item <- instance.items) {
      var itemIsAdded: Boolean = false
      var i = 0
      while (!itemIsAdded && i <= solution.length - 1) {
        if (solution(i).canAdd(item)) {
          solution(i).add(item)
          itemIsAdded = true
        } else {
          i += 1
        }
      }
      if (!itemIsAdded) {
        current = new Bin(instance.capacity)
        current.add(item)
        solution += current
      }
    }
    new Solution(solution.toArray)
  }

}

