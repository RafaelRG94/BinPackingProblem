package BinPackingD1

import scala.util.Random

object Mutation {

  def leftRight(length: Int, rnd: Random) : (Int, Int) = {
    val left: Int = rnd.nextInt(length)
    (left, left+rnd.nextInt(length-left))
  }

  def swapMutation(x: Array[Int], rnd: Random) : Unit = {
    val (left, right): (Int, Int) = leftRight(x.length, rnd)
    val temp: Int = x(left)
    x(left) = x(right)
    x(right) = temp
  }

  def insertMutation(x: Array[Int], rnd: Random) : Unit = {
    val (left, right): (Int, Int) = leftRight(x.length, rnd)
    println((left,right))
    if (left < x.length-2) {
      val temp: Int = x(right)
      for (i <- right until left + 1 by -1) x(i) = x(i - 1)
      if (right > left) x(left + 1) = temp
    }
  }

  def scrambleMutation(x: Array[Int], rnd: Random) : Unit = {
    val (left, right): (Int, Int) = leftRight(x.length, rnd)
    var temp: Int = x(0)
    for (i <- right until left by -1) {
      val j: Int = left + rnd.nextInt(i-left)
      temp = x(i); x(i) = x(j); x(j) = temp
    }
  }

  def inversionMutation(x: Array[Int], rnd: Random) : Unit = {
    val (left, right): (Int, Int) = leftRight(x.length, rnd)
    val half: Int = (right - left + 1)/2 // It works fine with odd and even width.
    var temp: Int = x(0)
    for (i <- 0 until half) {
      temp = x(left+i)
      x(left+i) = x(right-i)
      x(right-i) = temp
    }
  }

}
