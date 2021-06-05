package BinPackingD1

import scala.collection.mutable.ArrayBuffer
import scala.util.Sorting.quickSort

object Utils {

  def smallerThanTarget(target: Int, elements: ArrayBuffer[Bin]): Int = {
    var start = 0
    var end = elements.length - 1
    var ans = -1
    while (start <= end) {
      val mid = (start + end) / 2
      // Move to the left side if the target is smaller
      if (elements(mid).getLeftCapacity >= target) {
        end = mid - 1
      }
      // Move right side
      else {
        ans = mid
        start = mid + 1
      }
    }
    ans
  }

  def reorderBufferArrays(position: Int, binArrayToBeSorted: ArrayBuffer[Bin]): Unit = {
    val auxBin = binArrayToBeSorted(position)
    val newPosition = smallerThanTarget(auxBin.getLeftCapacity, binArrayToBeSorted)
    if (newPosition < binArrayToBeSorted.length - 1) {
      binArrayToBeSorted.remove(position)
      binArrayToBeSorted.insert(newPosition + 1, auxBin)
    }
  }

  def decreaseSortedArray(sortedArray: Array[Int]): Unit = {
    quickSort(sortedArray)
    var aux1 = 0
    var aux2 = 0
    var indexFirstPosition = 0
    var indexLastPosition = sortedArray.length - 1
    while (indexFirstPosition < indexLastPosition) {
      aux1 = sortedArray(indexFirstPosition)
      aux2 = sortedArray(indexLastPosition)
      sortedArray(indexFirstPosition) = aux2
      sortedArray(indexLastPosition) = aux1
      indexFirstPosition += 1
      indexLastPosition -= 1
    }
  }

}