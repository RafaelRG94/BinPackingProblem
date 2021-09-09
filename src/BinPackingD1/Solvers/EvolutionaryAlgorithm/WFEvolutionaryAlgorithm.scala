package BinPackingD1.Solvers.EvolutionaryAlgorithm

import BinPackingD1.Bin.ProblemInstance
import BinPackingD1.Crossover.Crossover.pmx
import BinPackingD1.Solvers.GreedyAlgorithm.WorstFit
import BinPackingD1.Mutation.Mutation.swapMutation
import BinPackingD1.Population.Population
import BinPackingD1.Utils.Timer

import scala.util.Random

class WFEvolutionaryAlgorithm(val popLength: Int, instance: ProblemInstance, val probCross: Double, val seed: Int, val maxTime: Double) {

  val rnd = new Random(seed)
  val probMut: Double = 1/popLength
  val popSize: Int = popLength + 1

  val population = new Population
  val algorithmPopulation = new population.WorstFitPopulation(rnd, popSize, instance) //Save child in the array last position
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
      val worstFit = new WorstFit(currentInstance)

      algorithmPopulation.individuals(popLength).bins = worstFit.solve().length // Finds the solution for child
      algorithmPopulation.binaryInsertion(algorithmPopulation.individuals(popSize-1)) // Inserts child in the population
    }
    println("FINAL SOLUTION: " + algorithmPopulation.individuals(0).toString)
    println("Algorithm time: " + maxTime)
  }

}
