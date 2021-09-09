package BinPackingD1.Solvers.GreedyAlgorithm

import BinPackingD1.Bin.{ProblemInstance, Solution}
import BinPackingD1.Solvers.Solver

class FirstFitDecreasing(val instance: ProblemInstance) extends Solver {
  def name: String = "First Fit Decreasing Algorithm"

  def solve(): Solution = {
    val solution = new FirstFit(instance.sortDescendingInstance())
    solution.solve()
  }

}
