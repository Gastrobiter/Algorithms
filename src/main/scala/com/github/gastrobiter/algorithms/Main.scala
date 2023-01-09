package com.github.gastrobiter.algorithms

object Main {
  def main(args: Array[String]): Unit = {
//    val a = List(6, 5, 3, 1, 8, 7, 2, 4)
    val a = List(8, 7, 6, 5, 4, 3, 2, 1)
    val start = System.nanoTime()

    println(Sorting.bubbleSort(a))
    println((System.nanoTime() - start) / 1e9d)
  }
}
