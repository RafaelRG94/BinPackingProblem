package BinPackingD1.Population

import BinPackingD1.Bin.ProblemInstance

import scala.util.Random

class Population {

  abstract class Population(rnd: Random, val popSize: Int, instance: ProblemInstance) {
    val individuals: Array[Individual] = init(rnd: Random, instance: ProblemInstance)

    def init(rnd: Random, instance: ProblemInstance): Array[Individual]

    def binaryTournament: Individual = {
      var p1: Int = (rnd.nextDouble * (popSize + 0.5)).toInt //Round and then trunc to int
      var p2 = (rnd.nextDouble * (popSize + 0.5)).toInt
      if(p1 > popSize-1) p1 = popSize-1
      do {
        p2 = (rnd.nextDouble * (popSize + 0.5)).toInt
        if(p2 > popSize-1) p2 = popSize-1
      } while(p1 == p2)
      if(individuals(p1).bins > individuals(p2).bins) individuals(p1)
      else individuals(p2)
    }

    def binarySorting(): Unit = {
      for (k <- 1 until individuals.length-1) {
        val aux: Individual = individuals(k)
        var left: Int = 0
        var right: Int = k-1

        while (left <= right) {
          val middle = (left + right) / 2
          if (aux.bins <= individuals(middle).bins) right = middle - 1
          else left = middle + 1
        }
        for(i <- k-1 to left by -1) individuals(i+1) = individuals(i)
        individuals(left) = aux
      }
    }

    def binaryInsertion(child: Individual): Unit = { // It inserts child in the Population
      var left: Int = 0
      var right: Int = popSize-2 // Last position is reserved for storing child; otherwise popSize-1

      while (left <= right) {
        val middle = (left + right) / 2
        if (child.bins <= individuals(middle).bins) right = middle - 1
        else left = middle + 1
      }
      for(i <- popSize-2 to left by -1) individuals(i+1) = individuals(i) //As in the second statement of the function
      individuals(left) = child
    }

  }


  class FirstFitPopulation(rnd: Random, popSize: Int, instance: ProblemInstance) extends Population(rnd, popSize, instance){
    def init(rnd: Random, instance: ProblemInstance): Array[Individual] = Populations.fFPopulation(rnd, popSize, instance)
  }

  class BestFitPopulation(rnd: Random, popSize: Int, instance: ProblemInstance) extends Population(rnd, popSize, instance){
    def init(rnd: Random, instance: ProblemInstance): Array[Individual] = Populations.bFPopulation(rnd, popSize, instance)
  }

  class WorstFitPopulation(rnd: Random, popSize: Int, instance: ProblemInstance) extends Population(rnd, popSize, instance){
    def init(rnd: Random, instance: ProblemInstance): Array[Individual] = Populations.wFPopulation(rnd, popSize, instance)
  }

  class AlmostWorstFitPopulation(rnd: Random, popSize: Int, instance: ProblemInstance) extends Population(rnd, popSize, instance){
    def init(rnd: Random, instance: ProblemInstance): Array[Individual] = Populations.aWFPopulation(rnd, popSize, instance)
  }

  class FirstFitAVLTreePopulation(rnd: Random, popSize: Int, instance: ProblemInstance) extends Population(rnd, popSize, instance){
    def init(rnd: Random, instance: ProblemInstance): Array[Individual] = Populations.fFAVLTreePopulation(rnd, popSize, instance)
  }

  class BestFitAVLTreePopulation(rnd: Random, popSize: Int, instance: ProblemInstance) extends Population(rnd, popSize, instance){
    def init(rnd: Random, instance: ProblemInstance): Array[Individual] = Populations.bFAVLTreePopulation(rnd, popSize, instance)
  }

  class WorstFitAVLTreePopulation(rnd: Random, popSize: Int, instance: ProblemInstance) extends Population(rnd, popSize, instance){
    def init(rnd: Random, instance: ProblemInstance): Array[Individual] = Populations.wFAVLTreePopulation(rnd, popSize, instance)
  }

}
