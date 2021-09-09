package BinPackingD1.Solvers

import BinPackingD1.Bin.Solution

trait Solver {
  def name: String
  def solve(): Solution
}
