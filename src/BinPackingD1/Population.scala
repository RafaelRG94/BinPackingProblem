package BinPackingD1

import scala.util.Random

class Population {
  //Add here selection method; Binary Tournament done
  //Add here add new individual method
  //Add here remove new individual method
  //Crear un método que haga uso de los dos métodos anteriores para reemplazar el peor individuo por uno nuevo
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

    def binarySorting(): Unit = { //Quiero que Array[Individual] sea de tipo Population
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

    def binaryInsertion(child: Individual): Int = { // It returns the position of the Individual and inserts it in the Population
      var left: Int = 0
      var right: Int = popSize-1

      while (left <= right) {
        val middle = (left + right) / 2
        if (child.bins <= individuals(middle).bins) right = middle - 1
        else left = middle + 1
      }
      for(i <- popSize-1 to left by -1) individuals(i+1) = individuals(i)
      individuals(left) = child
      left
    }
    /*
    def replacement(child: Individual): Unit = {
      /*def wellSorted(): Boolean = {
        var i: Int = 0
        var soFarSoGood: Boolean = true
        while (soFarSoGood && i < popSize) {
          if (individuals(i).bins > individuals(i+1).bins) soFarSoGood = false
          i += 1
        }
        soFarSoGood
      } // Function to check if the population is well sorted.*/

      val i = binaryInsertion(child)

      counter += 1
      print("Generation: %d, Time: %8f.".format(counter, timer.elapsedTime()))
      //print(popul(0).fitness)
      //println("; " + i)
      //println(wellSorted())
      //for (j <- 0 to popSize) println(popul(j))
      if (i == 0) println(" Better solution just found: %s".format(popul(0).toString)) else println()
    }*/

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
