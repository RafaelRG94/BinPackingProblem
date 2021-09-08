package BinPackingD1

object Main {
  def main(args: Array[String]): Unit = {
    val instance = new ProblemInstance(10, Array(2, 9, 3, 1, 1, 9, 8, 3, 7, 6, 1, 5, 4, 3, 3, 4, 5, 7, 6, 2, 4, 1))


    val solvers = List(new NextFit(instance), new FirstFit(instance), new BestFit(instance), new WorstFit(instance)
                      , new FirstFitDecreasing(instance), new BestFitDecreasing(instance), new WorstFitDecreasing(instance)
                      , new NextKFit(2, instance), new AlmostWorstFit(instance), new FFAVLTree(instance), new BFAVLTree(instance)
                      , new BFDAVLTree(instance), new WFAVLTree(instance), new WFDAVLTree(instance))

    for (solver <- solvers) {
      println(s"Solving instance with solver ${solver.name}")
      val sol = solver.solve()
      println(s"Solution is: $sol")
      println("\n")
    }

    /*
    val tree = new FFAVLTree(instance)
    tree.addAll()
    println(tree)
    println(tree.inOrder)
    println(tree.inOrder.length)

    val treeFD = new FFDAVLTree(instance)
    treeFD.addAll()
    println(treeFD)
    println(treeFD.inOrder)
    println(treeFD.inOrder.length)

    val treeB = new BFAVLTree(instance)
    treeB.addAll()
    println(treeB)
    println(treeB.inOrder)
    println(treeB.inOrder.length)

    val treeBFD = new BFDAVLTree(instance)
    treeBFD.addAll()
    println(treeBFD)
    println(treeBFD.inOrder.length)

    val treeWF = new WFAVLTree(instance)
    treeWF.addAll()
    println(treeWF)
    println(treeWF.inOrder)
    println(treeWF.inOrder.length)

    val treeWFD = new WFDAVLTree(instance)
    treeWFD.addAll()
    println(treeWFD)
    println(treeWFD.inOrder.length)
    */

    val aWEvolutionaryAlgorithm = new AWEvolutionaryAlgorithm(20, instance, 0.9, 10, 2)
    aWEvolutionaryAlgorithm.evolve()

    val fFEvolutionaryAlgorithm = new FFEvolutionaryAlgorithm(20, instance, 0.9, 10, 2)
    fFEvolutionaryAlgorithm.evolve()

    val bFEvolutionaryAlgorithm = new BFEvolutionaryAlgorithm(20, instance, 0.9, 10, 2)
    bFEvolutionaryAlgorithm.evolve()

    val wFEvolutionaryAlgorithm = new WFEvolutionaryAlgorithm(20, instance, 0.9, 10, 2)
    wFEvolutionaryAlgorithm.evolve()

    val treeFFEvolutionaryAlgorithm = new FFAVLEvolutionaryAlgorithm(20, instance, 0.9, 10, 2)
    treeFFEvolutionaryAlgorithm.evolve()

    val treeBFEvolutionaryAlgorithm = new BFAVLEvolutionaryAlgorithm(20, instance, 0.9, 10, 2)
    treeBFEvolutionaryAlgorithm.evolve()

    val treeWFEvolutionaryAlgorithm = new WFAVLEvolutionaryAlgorithm(20, instance, 0.9, 10, 2)
    treeWFEvolutionaryAlgorithm.evolve()

  }
}
