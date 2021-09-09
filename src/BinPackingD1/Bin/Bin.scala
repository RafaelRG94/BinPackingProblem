package BinPackingD1.Bin

import scala.collection.mutable.ArrayBuffer

class Bin(capacity: Int) {

  private val items = new ArrayBuffer[Int]()
  private var leftCapacity = capacity

  def add(item: Int): Unit = {
    require(item <= leftCapacity, "Item cannot be fit in bin")
    items += item
    leftCapacity -= item
  }

  def canAdd(item: Int): Boolean = {
    item <= leftCapacity
  }

  def getLeftCapacity: Int = leftCapacity

  override def toString: String =
    s"Bin(capacityLeft = $leftCapacity, items = ${items.mkString("(", ",", ")")})"

  //items += 5 Si tuviera un arraybuffer con tres elementos, con esto le añadiría un cuarto elemento (el 5)
  //val i = items(0)
  //val array = items.toArray Método para convertir el arraybuffer en un array si me hiciera falta

}

/*object Example extends App {

  val b = new Bin(10)

  b.add(2)
  b.add(3)

  println(b)

}*/