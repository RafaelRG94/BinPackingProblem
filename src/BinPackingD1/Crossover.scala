package BinPackingD1

import scala.Array.copyOf
import scala.util.Random

object Crossover {
  def pmx(p1: Array[Int], p2: Array[Int], rnd: Random, child: Array[Int]): Unit = {
    val s: Int = p1.length // Assumed both parents have the same size.

    // Left and right will determine the crossover extremes.
    val left = rnd.nextInt(s) // s excluded.
    val width = rnd.nextInt(s-left) // width+1 gives the number of elements in the crossover swath.
    val right = left + width
    //child is filled in with the cross of the two parents
    def pmxOffspring(sender: Array[Int], receiver: Array[Int]): Unit = { // Sender is the parent who donates the swath, whereas receiver is the one adapted.
      val child = copyOf(receiver, s)
      //val child = receiver.clone()
      //val child = receiver map identity
      for (i <- left to right) if (child(i) != sender(i)) {
        //val j = child.find(sender(i)) //en que posición de child se encuentra el objeto sender(i)
        val j = child.indexWhere(_ == sender(i))
        val temp = child(i)
        child(i) = child(j)
        child(j) = temp
      }
    }
    pmxOffspring(p1, p2)
  }


  def inversionCrossover(p1: Array[Int], p2: Array[Int], rnd: Random, child: Array[Int]): Unit = { // Method proposed by Göktürk Üçoluk.

    def fromPermToInv(perm: Array[Int]): Array[Int] = {
      val inv: Array[Int] = new Array[Int](perm.length)
      for (i <- perm.indices) {
        inv(i) = 0
        for (m <- 0 until i) { // This looks at the items on the left of the ith item.
          if (perm(m) > i) inv(i) += 1
        }
      }
      inv
    }

    def fromInvToPerm(inv: Array[Int]): Unit = {
      val n = inv.length
      val pos: Array[Int] = new Array[Int](n)

      for (i <- n-1 to 0 by -1) {
        for (m <- i + 1 until n) {
          if (pos(m) >= inv(i)) pos(m) += 1
          pos(i) = inv(i)
        }
      }
      for (i <- 0 until n) {
        child(pos(i)) = i
      }
    }

    val (inv1, inv2) = (fromPermToInv(p1), fromPermToInv(p2))
    val s: Int = p1.length // Assumed both parents have the same size.

    val crossoverPoint: Int = rnd.nextInt(s)
    for (i <- 0 until crossoverPoint) inv1(i) = inv2(i)
    fromInvToPerm(inv1)
  }

}
