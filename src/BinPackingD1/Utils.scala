package BinPackingD1

import scala.collection.mutable.ArrayBuffer

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

  def sortDescending(array: Array[Int]): Unit = {
    val ordering = Ordering.by[Int, Int](x => -x)
    util.Sorting.quickSort[Int](array)(ordering)
  }

}