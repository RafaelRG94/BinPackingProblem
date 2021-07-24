package BinPackingD1

import scala.util.Random

object Populations {
  //Crear una clase individuo para que estos métodos me devuelvan un array de individuos
  def initPopulation(rnd: Random, popSize: Int, instance: ProblemInstance, fSolver: ProblemInstance => Solver): Array[Individual] = {
    val populations = new Array[Individual](popSize)
    for(i <- 0 until popSize){
      val population = rnd.shuffle(instance.items)
      val reorderedInstance = new ProblemInstance(instance.capacity, population.toArray)
      val solver = fSolver(reorderedInstance)
      populations(i) = new Individual(reorderedInstance.items, solver.solve().length)
    }
    populations.sortBy(_.bins)
  }

  def fFPopulation(rnd: Random, popSize: Int, instance: ProblemInstance): Array[Individual] = {
    initPopulation(rnd, popSize, instance, problemInstance => new FirstFit(problemInstance))
  }

  def bFPopulation(rnd: Random, popSize: Int, instance: ProblemInstance): Array[Individual] = {
    initPopulation(rnd, popSize, instance, problemInstance => new BestFit(problemInstance))
  }

  def wFPopulation(rnd: Random, popSize: Int, instance: ProblemInstance): Array[Individual] = {
    initPopulation(rnd, popSize, instance, problemInstance => new WorstFit(problemInstance))
  }

  def aWFPopulation(rnd: Random, popSize: Int, instance: ProblemInstance): Array[Individual] = {
    initPopulation(rnd, popSize, instance, problemInstance => new AlmostWorstFit(problemInstance))
  }

  def fFAVLTreePopulation(rnd: Random, popSize: Int, instance: ProblemInstance): Array[Individual] = {
    initPopulation(rnd, popSize, instance, problemInstance => new FFAVLTree(problemInstance))
  }

  def bFAVLTreePopulation(rnd: Random, popSize: Int, instance: ProblemInstance): Array[Individual] = {
    initPopulation(rnd, popSize, instance, problemInstance => new BFAVLTree(problemInstance))
  }

  def wFAVLTreePopulation(rnd: Random, popSize: Int, instance: ProblemInstance): Array[Individual] = {
    initPopulation(rnd, popSize, instance, problemInstance => new WFAVLTree(problemInstance))
  }

}