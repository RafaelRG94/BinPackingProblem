package BinPackingD1.Bin

class Solution(val solution: Array[Bin]){

  override def toString: String = {
    var str = s"Total bins: ${solution.length}\n"
    str += solution.mkString("\n")
    str
  }

  def length: Int = solution.length
}

//Usar el toString de Bins para hacer el toString de Solution

/*Solución:
La capacidad de los cubos es ProblemInstance.capacity y los elementos son ARRAY(elem1, elem2, elem3, elem4,...)
El número total de bins es TOTALBINS
Bin1(leftCapacity, items)
Bin2(leftCapacity, items)
Bin3(leftCapacity, items)
...
*/



