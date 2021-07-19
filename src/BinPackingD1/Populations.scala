package BinPackingD1

import scala.util.Random

object Populations {
  //Crear una clase individuo para que estos métodos me devuelvan un array de individuos
  def initPopulation(rnd: Random, popSize: Int, instance: ProblemInstance, fSolver: ProblemInstance => Solver): Array[(Array[Int], Int)] = {
    val populations = new Array[(Array[Int], Int)](popSize)
    for(i <- 0 until popSize){
      val population = rnd.shuffle(instance.items)
      val reorderedInstance = new ProblemInstance(instance.capacity, population.toArray)
      val solver = fSolver(reorderedInstance)
      populations(i) = (reorderedInstance.items, solver.solve().length)
    }
    populations.sortBy(_._2)
  }

  def fFPopulation(rnd: Random, popSize: Int, instance: ProblemInstance): Array[(Array[Int], Int)] = {
    initPopulation(rnd, popSize, instance, ProblemInstance => new FirstFit(ProblemInstance))
  }

  def bFPopulation(rnd: Random, popSize: Int, instance: ProblemInstance): Array[(Array[Int], Int)] = {
    initPopulation(rnd, popSize, instance, ProblemInstance => new BestFit(ProblemInstance))
  }

  def wFPopulation(rnd: Random, popSize: Int, instance: ProblemInstance): Array[(Array[Int], Int)] = {
    initPopulation(rnd, popSize, instance, ProblemInstance => new WorstFit(ProblemInstance))
  }

  def aWFPopulation(rnd: Random, popSize: Int, instance: ProblemInstance): Array[(Array[Int], Int)] = {
    initPopulation(rnd, popSize, instance, ProblemInstance => new AlmostWorstFit(ProblemInstance))
  }

  def fFAVLTreePopulation(rnd: Random, popSize: Int, instance: ProblemInstance): Array[(Array[Int], Int)] = {
    initPopulation(rnd, popSize, instance, ProblemInstance => new FFAVLTree(ProblemInstance))
  }

  def bFAVLTreePopulation(rnd: Random, popSize: Int, instance: ProblemInstance): Array[(Array[Int], Int)] = {
    initPopulation(rnd, popSize, instance, ProblemInstance => new BFAVLTree(ProblemInstance))
  }

  def wFAVLTreePopulation(rnd: Random, popSize: Int, instance: ProblemInstance): Array[(Array[Int], Int)] = {
    initPopulation(rnd, popSize, instance, ProblemInstance => new WFAVLTree(ProblemInstance))
  }

  //Añadir aquí métodos de selección (Binary Tour y más si puedo)
  //Añadir método para insertar nuevo individuo
  //Añadir método para quitar al peor individuo
  //Crear un método que haga uso de los dos métodos anteriores para reemplazar el peor individuo por uno nuevo
}

//Añadir una clase individuo en otro fichero, que sera Array[(Array[Int], Int)] class Individuals (val genome: Array[Int], val value: Int) y adaptar los algoritmos
//¿Tengo que introducir estas clases dentro del object?
abstract class Population(rnd: Random, val popSize: Int, instance: ProblemInstance) {
  val individuals: Array[(Array[Int], Int)] = init(rnd: Random, instance: ProblemInstance)

  def init(rnd: Random, instance: ProblemInstance): Array[(Array[Int], Int)]
}

class FirstFitPopulation(rnd: Random, popSize: Int, instance: ProblemInstance) extends Population(rnd, popSize, instance){
  def init(rnd: Random, instance: ProblemInstance): Array[(Array[Int], Int)] = Populations.fFPopulation(rnd, popSize, instance)
}

class BestFitPopulation(rnd: Random, popSize: Int, instance: ProblemInstance) extends Population(rnd, popSize, instance){
  def init(rnd: Random, instance: ProblemInstance): Array[(Array[Int], Int)] = Populations.bFPopulation(rnd, popSize, instance)
}

class WorstFitPopulation(rnd: Random, popSize: Int, instance: ProblemInstance) extends Population(rnd, popSize, instance){
  def init(rnd: Random, instance: ProblemInstance): Array[(Array[Int], Int)] = Populations.wFPopulation(rnd, popSize, instance)
}

class AlmostWorstFitPopulation(rnd: Random, popSize: Int, instance: ProblemInstance) extends Population(rnd, popSize, instance){
  def init(rnd: Random, instance: ProblemInstance): Array[(Array[Int], Int)] = Populations.aWFPopulation(rnd, popSize, instance)
}

class FirstFitAVLTreePopulation(rnd: Random, popSize: Int, instance: ProblemInstance) extends Population(rnd, popSize, instance){
  def init(rnd: Random, instance: ProblemInstance): Array[(Array[Int], Int)] = Populations.fFAVLTreePopulation(rnd, popSize, instance)
}

class BestFitAVLTreePopulation(rnd: Random, popSize: Int, instance: ProblemInstance) extends Population(rnd, popSize, instance){
  def init(rnd: Random, instance: ProblemInstance): Array[(Array[Int], Int)] = Populations.bFAVLTreePopulation(rnd, popSize, instance)
}

class WorstFitAVLTreePopulation(rnd: Random, popSize: Int, instance: ProblemInstance) extends Population(rnd, popSize, instance){
  def init(rnd: Random, instance: ProblemInstance): Array[(Array[Int], Int)] = Populations.wFAVLTreePopulation(rnd, popSize, instance)
}