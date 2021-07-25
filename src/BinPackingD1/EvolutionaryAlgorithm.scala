package BinPackingD1

import scala.util.Random

class EvolutionaryAlgorithm(val popSize: Int, instance: ProblemInstance , val probCross: Double, val seed: Int, val maxTime: Double) {

  val rnd = new Random(seed)
  val probMut: Double = 1/popSize

  val population: Array[Individual] = Populations.fFPopulation(rnd, popSize, instance)
  val timer: Timer = Timer()
  // any time-consuming calculation
  val elapsed: timer.Seconds = timer.elapsedTime
  println(s"Han pasado $elapsed segundos")

}
