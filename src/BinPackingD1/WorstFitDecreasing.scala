package BinPackingD1

class WorstFitDecreasing(val instance: ProblemInstance) extends Solver{
  def name: String = "Worst Fit Decreasing Algorithm"

  def solve(): Solution = {
    val solution = new WorstFit(instance.sortDescendingInstance())
    solution.solve()
  }

}
