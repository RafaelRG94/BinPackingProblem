package BinPackingD1

import scala.collection.mutable.ArrayBuffer

class NextKFit(val k: Int, val instance: ProblemInstance) extends Solver {
  def name: String = "NextKFit Algorithm"

  def solve(): Solution = {
    val solution = new ArrayBuffer[Bin]()
    var current = new Bin(instance.capacity)
    for (i <- instance.items.indices) {
      if (solution.isEmpty){
        if (current.canAdd(instance.items(i))){
          current.add(instance.items(i))
        } else {
          solution += current
          current = new Bin(instance.capacity)
          current.add(instance.items(i))
          solution += current
        }
      }
      else if (solution.nonEmpty && solution.length < k) {
        if (solution(solution.length-1).canAdd(instance.items(i))) {
          solution(solution.length-1).add(instance.items(i))
        } else {
          current = new Bin(instance.capacity)
          current.add(instance.items(i))
          solution += current
        }
      } else {
        var itemIsAdded: Boolean = false
        var j = k
        while (!itemIsAdded && j >= 1) {
          if (solution(solution.length - j).canAdd(instance.items(i))) {
            solution(solution.length - j).add(instance.items(i))
            itemIsAdded = true
          } else {
            j -= 1
          }
        }
        if (!itemIsAdded) {
          current = new Bin(instance.capacity)
          current.add(instance.items(i))
          solution += current
        }
      }
    }
    new Solution(solution.toArray)
  }

}
