package BinPackingD1

class BestFitDecreasing(val instance: ProblemInstance) extends Solver{
  def name: String = "Best Fit Decreasing Algorithm"

  def solve(): Solution = {
    val solution = new BestFit(instance.sortDescendingInstance())
    solution.solve()
  }

}
