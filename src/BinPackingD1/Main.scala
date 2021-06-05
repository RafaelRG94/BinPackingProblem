package BinPackingD1

object Main {
  def main(args: Array[String]): Unit = {
    val instance = new ProblemInstance(10, Array(2, 9, 3, 1, 1, 9, 8, 3, 7, 6, 1, 5, 4, 3, 3, 4, 5, 7, 6, 2, 4, 1))
    val nextFit = new NextFit(instance)
    val firstFit = new FirstFit(instance)
    val bestFit = new BestFit(instance)
    val worstFit = new WorstFit(instance)
    val firstFitDecreasing = new FirstFitDecreasing(instance)
    val bestFitDecreasing = new BestFitDecreasing(instance)
    val nextKFit = new NextKFit(2,instance)
    val almostWorstFit = new AlmostWorstFit(instance)
    println(nextFit.solve())
    println("\n")
    println(nextKFit.solve())
    println("\n")
    println(firstFit.solve())
    println("\n")
    println(bestFit.solve())
    println("\n")
    println(worstFit.solve())
    println("\n")
    println(almostWorstFit.solve())
    println("\n")
    println(firstFitDecreasing.solve())
    println("\n")
    println(bestFitDecreasing.solve())
  }
}
