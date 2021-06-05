package BinPackingD1

import BinPackingD1.Utils.decreaseSortedArray

class FirstFitDecreasing(val instance: ProblemInstance) {

  def solve(): Solution = {
    decreaseSortedArray(instance.items)
    val solution = new FirstFit(instance)
    solution.solve()
  }

}
