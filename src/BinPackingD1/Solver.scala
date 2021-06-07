package BinPackingD1

trait Solver {
  def name: String
  def solve(): Solution
}
