package BinPackingD1

import BinPackingD1.Crossover.pmx
import BinPackingD1.Mutation.swapMutation

import scala.util.Random

class BFEvolutionaryAlgorithm(val popLength: Int, instance: ProblemInstance, val probCross: Double, val seed: Int, val maxTime: Double) {

  val rnd = new Random(seed)
  val probMut: Double = 1/popLength
  val popSize: Int = popLength + 1

  val population = new Population
  val algorithmPopulation = new population.BestFitPopulation(rnd, popSize, instance) //Save child in the array last position
  val timer: Timer = Timer()

  def evolve(): Unit = {
    timer.reset
    while(timer.elapsedTime < maxTime) {
      if (rnd.nextDouble() < probCross) {
        pmx(algorithmPopulation.binaryTournament.perm, algorithmPopulation.binaryTournament.perm, rnd,  algorithmPopulation.individuals(popLength).perm) // Child is stored in popSize-1 = popLength
      }
      else algorithmPopulation.individuals(popLength) = algorithmPopulation.individuals(rnd.nextInt(popLength)) // The child is chosen randomly.
      if (rnd.nextDouble() < probMut) swapMutation(algorithmPopulation.individuals(popLength).perm, rnd)

      val currentInstance = new ProblemInstance(instance.capacity, algorithmPopulation.individuals(popLength).perm) // Child is stored in popSize-1 = popLength
      val bestFit = new BestFit(currentInstance)

      algorithmPopulation.individuals(popLength).bins = bestFit.solve().length // Finds the solution for child
      algorithmPopulation.binaryInsertion(algorithmPopulation.individuals(popSize-1)) // Inserts child in the population
    }
    println("FINAL SOLUTION: " + algorithmPopulation.individuals(0).toString)
    println("Algorithm time: " + maxTime)
  }

}
