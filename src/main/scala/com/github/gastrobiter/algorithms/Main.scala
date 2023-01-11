package com.github.gastrobiter.algorithms

object Main {
  def main(args: Array[String]): Unit = {
//    val a = List(6, 5, 3, 1, 8, 7, 2, 4)
    val sortingList = List(8, 7, 6, 5, 4, 3, 2, 1)
    val subarrayList = List(13, -3, -25, 20, -3, -16, -23, 18, 20, -7, 12, -5, -22, 15, -4, 7)
//    val subarrayList = List(1, -1, -2)
    val start = System.nanoTime()

    println(Sorting.mergeSort(sortingList))
    println(Sorting.findMaxCrossingSubarray(subarrayList))
    println((System.nanoTime() - start) / 1e9d)
  }
}
