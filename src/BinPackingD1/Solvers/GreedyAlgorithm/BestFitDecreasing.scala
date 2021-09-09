package BinPackingD1.Solvers.GreedyAlgorithm

import BinPackingD1.Bin.{ProblemInstance, Solution}
import BinPackingD1.Solvers.Solver

class BestFitDecreasing(val instance: ProblemInstance) extends Solver{
  def name: String = "Best Fit Decreasing Algorithm"

  def solve(): Solution = {
    val solution = new BestFit(instance.sortDescendingInstance())
    solution.solve()
  }

}
