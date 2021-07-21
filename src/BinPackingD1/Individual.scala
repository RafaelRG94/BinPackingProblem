package BinPackingD1

class Individual(private var _perm: Array[Int], private var _bins: Int) {
  def perm: Array[Int] = _perm
  def perm_=(perm: Array[Int]): Unit = _perm = perm

  def length: Int = perm.length

  def apply(i: Int): Int =
    _perm(i)

  def bins: Int = _bins
  def bins_=(bins: Int): Unit = _bins = bins

  def copyFrom(individual: Individual): Unit = {
    Array.copy(individual._perm, 0, this._perm, 0, length)
    this._bins = individual._bins
  }

  override def toString: String =
    s"Individual(perm = ${_perm.mkString("(", ",", ")")}, bins = ${_bins})"

}
