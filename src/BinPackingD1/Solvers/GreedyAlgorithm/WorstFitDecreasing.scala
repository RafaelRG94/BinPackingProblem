package BinPackingD1.Solvers.GreedyAlgorithm

import BinPackingD1.Bin.{ProblemInstance, Solution}
import BinPackingD1.Solvers.Solver

class WorstFitDecreasing(val instance: ProblemInstance) extends Solver{
  def name: String = "Worst Fit Decreasing Algorithm"

  def solve(): Solution = {
    val solution = new WorstFit(instance.sortDescendingInstance())
    solution.solve()
  }

}
