package BinPackingD1

import scala.util.Random

abstract class EvolutionaryAlgorithm(val popSize: Int, instance: ProblemInstance , val probCross: Double, val seed: Int, val maxTime: Double) extends Solver {
  def name: String

  val rnd = new Random(seed)
  val probMut: Double = 1/popSize

  val population: Array[Individual] = Populations.fFPopulation(rnd, popSize, instance)
  val shuffled: Array[Int] = Array.range(0,popSize)
  val timer: Timer = Timer()
  // any time-consuming calculation
  //val elapsed: timer.Seconds = timer.elapsedTime
  //println(s"Han pasado $elapsed segundos")

  var tempToShuffle: Int = 0
  var tempToReplace: Individual = population(0)

  def shuffle(xs: Array[Int]): Unit = {
    for (i <- 0 until 4) {
      val j: Int = i + rnd.nextInt(popSize-i)
      tempToShuffle = xs(i); xs(i) = xs(j); xs(j) = tempToShuffle
    }
  }

  def crossover(parent1: Individual, parent2: Individual, rnd: Random,  child: Individual): Unit
  def mut(): Unit
  /*
  def solve(): Unit = {
    timer.reset
    while(timer.elapsedTime < maxTime) {
      if (rnd.nextDouble() < probCross) {
        shuffle(shuffled)
        crossover(population.binaryTournament, population.binaryTournament, rnd,  population(popSize))
      }
      else population(popSize) = population(rnd.nextInt(popSize)) // The child is chosen randomly.
      if (rnd.nextDouble() < probMut) mut()
      population(popSize).bins = fitness(popul(popSize).chromosome)
    }
    println("FINAL SOLUTION: " + population(0).toString)
    println("Algorithm time: " + maxTime)
  }*/

}
