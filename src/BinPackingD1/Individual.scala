package BinPackingD1

abstract class Individual {
  var perm: Array[Int]
  var bins: Int

  def setPerm(xs: Array[Int]): Unit = { perm = xs }

  def setBins(nbins: Int): Unit = { bins = nbins }

  def getPerm(): Array[Int] = { perm }

  def getBins(): Int = { bins }
}
