package BinPackingD1.Solvers.EvolutionaryAlgorithm

import BinPackingD1.Bin.ProblemInstance
import BinPackingD1.Solvers.GreedyAlgorithm.FirstFit
import BinPackingD1.Population.Population
import BinPackingD1.Utils.Timer

import scala.util.Random

abstract class EvolutionaryAlgorithm(val popLength: Int, instance: ProblemInstance , val probCross: Double, val seed: Int, val maxTime: Double) {

  def crossover(parent1: Array[Int], parent2: Array[Int], rnd: Random, child: Array[Int]): Unit
  def mut(child: Array[Int], rnd: Random): Unit

  val rnd = new Random(seed)
  val probMut: Double = 1/popLength
  val popSize: Int = popLength + 1

  val population = new Population
  val algorithmPopulation = new population.FirstFitPopulation(rnd, popSize, instance) //Save child in the array last position
  val timer: Timer = Timer()

  def evolve(): Unit = {
    timer.reset
    while(timer.elapsedTime < maxTime) {
      if (rnd.nextDouble() < probCross) {
        crossover(algorithmPopulation.binaryTournament.perm, algorithmPopulation.binaryTournament.perm, rnd,  algorithmPopulation.individuals(popLength).perm) // Child is stored in popSize-1 = popLength
      }
      else algorithmPopulation.individuals(popLength) = algorithmPopulation.individuals(rnd.nextInt(popLength)) // The child is chosen randomly.
      if (rnd.nextDouble() < probMut) mut(algorithmPopulation.individuals(popLength).perm, rnd)

      val currentInstance = new ProblemInstance(instance.capacity, algorithmPopulation.individuals(popLength).perm) // Child is stored in popSize-1 = popLength
      val firstFit = new FirstFit(currentInstance)

      algorithmPopulation.individuals(popLength).bins = firstFit.solve().length // Finds the solution for child
      algorithmPopulation.binaryInsertion(algorithmPopulation.individuals(popSize-1)) // Inserts child in the population
    }
    println("FINAL SOLUTION: " + algorithmPopulation.individuals(0).toString)
    println("Algorithm time: " + maxTime)
  }

}
