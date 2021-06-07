package BinPackingD1

import scala.collection.mutable.ArrayBuffer
import BinPackingD1.Utils.{reorderBufferArrays, smallerThanTarget}

class BestFit(val instance: ProblemInstance) extends Solver {
  def name: String = "Best Fit Algorithm"

  def solve(): Solution = {
    val solution = new ArrayBuffer[Bin]()
    for (item <- instance.items) {
      val targetBin = 1 + smallerThanTarget(item, solution)
      if (targetBin > 0 && targetBin <= solution.length - 1) {
        solution(targetBin).add(item)
        reorderBufferArrays(targetBin, solution)
      } else {
        val current = new Bin(instance.capacity)
        solution += current
        current.add(item)
        reorderBufferArrays(solution.length - 1, solution)
      }
    }
    new Solution(solution.toArray)
  }

}

/*object asd extends App {

  val a = ArrayBuffer(1,2,3)
  a.insert(1,8)
  a.remove(2)
  println(a)

  val b = ArrayBuffer(1,2)
  b.insert(0,3) // (3,1,2)
  b.insert(3,7)
  println(b)
}*/