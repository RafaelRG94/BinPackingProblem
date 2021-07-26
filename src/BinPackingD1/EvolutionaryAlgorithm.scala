package BinPackingD1

import scala.util.Random

abstract class EvolutionaryAlgorithm(val popLength: Int, instance: ProblemInstance , val probCross: Double, val seed: Int, val maxTime: Double) {
  def name: String

  val rnd = new Random(seed)
  val probMut: Double = 1/popLength
  val popSize: Int = popLength + 1

  val population = new Population
  val algorithmPopulation = new population.FirstFitPopulation(rnd, popSize, instance) //guardo child en la ultima posicion del array

  // val shuffled: Array[Int] = Array.range(0,popSize)
  val timer: Timer = Timer()
  /*
  var tempToShuffle: Int = 0
  var tempToReplace: Individual = algorithmPopulation.individuals(0)

  def shuffle(xs: Array[Int]): Unit = {
    for (i <- 0 until 4) {
      val j: Int = i + rnd.nextInt(popSize-i)
      tempToShuffle = xs(i); xs(i) = xs(j); xs(j) = tempToShuffle
    }
  }
  */
  def crossover(parent1: Individual, parent2: Individual, rnd: Random, child: Individual): Unit
  def mut(): Unit

  def evolve(): Unit = {
    timer.reset
    while(timer.elapsedTime < maxTime) {
      if (rnd.nextDouble() < probCross) {
        // shuffle(shuffled)
        crossover(algorithmPopulation.binaryTournament, algorithmPopulation.binaryTournament, rnd,  algorithmPopulation.individuals(popLength)) // Child is stored in popSize-1 = popLength
      }
      else algorithmPopulation.individuals(popLength) = algorithmPopulation.individuals(rnd.nextInt(popLength)) // The child is chosen randomly.
      if (rnd.nextDouble() < probMut) mut()
      val currentInstance = new ProblemInstance(instance.capacity, algorithmPopulation.individuals(popLength).perm) // Child is stored in popSize-1 = popLength
      val firstFit = new FirstFit(currentInstance)
      algorithmPopulation.individuals(popLength).bins = firstFit.solve().length // Finds the solution for child
      algorithmPopulation.binaryInsertion(algorithmPopulation.individuals(popSize)) // Inserts child in the population
    }
    println("FINAL SOLUTION: " + algorithmPopulation.individuals(0).toString)
    println("Algorithm time: " + maxTime)
  }

}
