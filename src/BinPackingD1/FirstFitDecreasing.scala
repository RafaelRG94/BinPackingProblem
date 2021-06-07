package BinPackingD1

class FirstFitDecreasing(val instance: ProblemInstance) extends Solver {
  def name: String = "First Fit Decreasing Algorithm"

  def solve(): Solution = {
    val solution = new FirstFit(instance.sortDescendingInstance())
    solution.solve()
  }

}
