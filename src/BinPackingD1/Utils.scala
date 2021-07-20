package BinPackingD1

import scala.collection.mutable.ArrayBuffer

object Utils {

  def smallerThanTarget(target: Int, elements: ArrayBuffer[Bin]): Int = {
    var start = 0
    var end = elements.length - 1
    var ans = -1

    // Minimum size of the array should be 1
    if (end == 0) return -1
    // If target lies beyond the max element, than the index of strictly smaller
    // Value than target should be (end - 1)
    if (target > elements(end).getLeftCapacity) return end

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