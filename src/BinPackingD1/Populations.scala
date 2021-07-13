package BinPackingD1

import scala.util.Random

object Populations {

  def firstFitPopulation(rnd: Random, popSize: Int, instance: ProblemInstance): Array[(Array[Int], Int)] = {
    val populations = new Array[(Array[Int], Int)](popSize)
    for(i <- 0 until popSize){
      val population = rnd.shuffle(instance.items)
      val reorderedInstance = new ProblemInstance(instance.capacity, population.toArray)
      val firstFit = new FirstFit(reorderedInstance)
      populations(i) = (reorderedInstance.items, firstFit.solve().length)
    }
    populations.sortBy(_._2)
  }

  def bestFitPopulation(rnd: Random, popSize: Int, instance: ProblemInstance): Array[(Array[Int], Int)] = {
    val populations = new Array[(Array[Int], Int)](popSize)
    for(i <- 0 until popSize){
      val population = rnd.shuffle(instance.items)
      val reorderedInstance = new ProblemInstance(instance.capacity, population.toArray)
      val bestFit = new BestFit(reorderedInstance)
      populations(i) = (reorderedInstance.items, bestFit.solve().length)
    }
    populations.sortBy(_._2)
  }

  def worstFitPopulation(rnd: Random, popSize: Int, instance: ProblemInstance): Array[(Array[Int], Int)] = {
    val populations = new Array[(Array[Int], Int)](popSize)
    for(i <- 0 until popSize){
      val population = rnd.shuffle(instance.items)
      val reorderedInstance = new ProblemInstance(instance.capacity, population.toArray)
      val worstFit = new WorstFit(reorderedInstance)
      populations(i) = (reorderedInstance.items, worstFit.solve().length)
    }
    populations.sortBy(_._2)
  }

  def almostWorstFitPopulation(rnd: Random, popSize: Int, instance: ProblemInstance): Array[(Array[Int], Int)] = {
    val populations = new Array[(Array[Int], Int)](popSize)
    for(i <- 0 until popSize){
      val population = rnd.shuffle(instance.items)
      val reorderedInstance = new ProblemInstance(instance.capacity, population.toArray)
      val almostWorstFit = new AlmostWorstFit(reorderedInstance)
      populations(i) = (reorderedInstance.items, almostWorstFit.solve().length)
    }
    populations.sortBy(_._2)
  }

  def firstFitAVLTreePopulation(rnd: Random, popSize: Int, instance: ProblemInstance): Array[(Array[Int], Int)] = {
    val populations = new Array[(Array[Int], Int)](popSize)
    for(i <- 0 until popSize){
      val population = rnd.shuffle(instance.items)
      val reorderedInstance = new ProblemInstance(instance.capacity, population.toArray)
      val firstFitTree = new FFAVLTree
      firstFitTree.addAll(reorderedInstance)
      populations(i) = (reorderedInstance.items, firstFitTree.inOrder.length)
    }
    populations.sortBy(_._2)
  }

  def bestFitAVLTreePopulation(rnd: Random, popSize: Int, instance: ProblemInstance): Array[(Array[Int], Int)] = {
    val populations = new Array[(Array[Int], Int)](popSize)
    for(i <- 0 until popSize){
      val population = rnd.shuffle(instance.items)
      val reorderedInstance = new ProblemInstance(instance.capacity, population.toArray)
      val bestFitTree = new BFAVLTree
      bestFitTree.addAll(reorderedInstance)
      populations(i) = (reorderedInstance.items, bestFitTree.inOrder.length)
    }
    populations.sortBy(_._2)
  }

  def worstFitAVLTreePopulation(rnd: Random, popSize: Int, instance: ProblemInstance): Array[(Array[Int], Int)] = {
    val populations = new Array[(Array[Int], Int)](popSize)
    for(i <- 0 until popSize){
      val population = rnd.shuffle(instance.items)
      val reorderedInstance = new ProblemInstance(instance.capacity, population.toArray)
      val worstFitTree = new WFAVLTree
      worstFitTree.addAll(reorderedInstance)
      populations(i) = (reorderedInstance.items, worstFitTree.inOrder.length)
    }
    populations.sortBy(_._2)
  }

}
