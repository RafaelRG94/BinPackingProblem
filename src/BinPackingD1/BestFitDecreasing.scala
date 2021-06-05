package BinPackingD1

import BinPackingD1.Utils.decreaseSortedArray

class BestFitDecreasing(val instance: ProblemInstance) {

  def solve(): Solution = {
    decreaseSortedArray(instance.items)
    val solution = new BestFit(instance)
    solution.solve()
  }

}
