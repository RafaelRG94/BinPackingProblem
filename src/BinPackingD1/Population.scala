package BinPackingD1

import scala.util.Random

class Population {
  //Añadir una clase individuo en otro fichero, que sera Array[(Array[Int], Int)] class Individuals (val genome: Array[Int], val value: Int) y adaptar los algoritmos
  //Añadir aquí métodos de selección (Binary Tour y más si puedo)
  //Añadir método para insertar nuevo individuo
  //Añadir método para quitar al peor individuo
  //Crear un método que haga uso de los dos métodos anteriores para reemplazar el peor individuo por uno nuevo
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
}
