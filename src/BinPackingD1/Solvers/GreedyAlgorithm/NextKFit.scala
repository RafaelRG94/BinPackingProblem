package BinPackingD1.Solvers.GreedyAlgorithm

import BinPackingD1.Bin.{Bin, ProblemInstance, Solution}
import BinPackingD1.Solvers.Solver

import scala.collection.mutable.ArrayBuffer

class NextKFit(val k: Int, val instance: ProblemInstance) extends Solver {
  def name: String = "NextKFit Algorithm"

  def solve(): Solution = {
    val solution = new ArrayBuffer[Bin]()
    var current = new Bin(instance.capacity)
    var itemIsAdded: Boolean = false
    for (item <- instance.items) { //for(item <- instance.items)
      if (solution.isEmpty){
        current = new Bin(instance.capacity)
        current.add(item)
        solution += current
      }
      else if (solution.nonEmpty && solution.length < k) {
        var iterator: Int = 0
        while (!itemIsAdded && iterator <= solution.length - 1) {
          if (solution(iterator).canAdd(item)) {
            solution(iterator).add(item)
            itemIsAdded = true
          } else {
            iterator += 1
          }
        }
        if(!itemIsAdded){
          current = new Bin(instance.capacity)
          current.add(item)
          solution += current
        }
      } else {
        itemIsAdded = false
        var j = k
        while (!itemIsAdded && j >= 1) {
          if (solution(solution.length - j).canAdd(item)) {
            solution(solution.length - j).add(item)
            itemIsAdded = true
          } else {
            j -= 1
          }
        }
        if (!itemIsAdded) {
          current = new Bin(instance.capacity)
          current.add(item)
          solution += current
        }
      }
    }
    new Solution(solution.toArray)
  }

}
