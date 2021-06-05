package BinPackingD1

import scala.collection.mutable.ArrayBuffer

class NextKFit(val k: Int, val instance: ProblemInstance) {

  def solve(): Solution = {
    val solution = new ArrayBuffer[Bin]()
    val current = new Bin(instance.capacity)
    solution += current
    for (i <- instance.items.indices) {
      if (solution.length < k) {
        if (solution(solution.length-1).canAdd(instance.items(i))) {
          solution(solution.length-1).add(instance.items(i))
        } else {
          val current = new Bin(instance.capacity)
          current.add(instance.items(i))
          solution += current
        }
      } else {
        var itemIsAdded: Boolean = false
        var j = 1
        while (!itemIsAdded && j <= k) {
          if (solution(solution.length-(k-j+1)).canAdd(instance.items(i))) {
            solution(solution.length-(k-j+1)).add(instance.items(i))
            itemIsAdded = true
          } else {
            j += 1
          }
        }
        if (!itemIsAdded) {
          val current = new Bin(instance.capacity)
          current.add(instance.items(i))
          solution += current
        }
      }
    }
    new Solution(solution.toArray)
  }

}
