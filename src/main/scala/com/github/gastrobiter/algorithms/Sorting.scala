package com.github.gastrobiter.algorithms

import scala.annotation.tailrec

object Sorting {
  /** Implements insertion sort algorithm in Scala
   *
   * @param input an input list
   * @param ord   an implicit instance representing a strategy for sorting instances of a type A
   * @tparam A a type parameter of a method. It must have a way to be compared
   * @return a sorted list of A's
   */
  def insertionSort[A](input: List[A])(implicit ord: Ordering[A]): List[A] = {
    def swap(x: A, xs: List[A]): List[A] = xs match {
      case Nil => List(x)
      case _ if ord.lt(x, xs.head) => x :: xs
      case _ => xs.head :: swap(x, xs.tail)
    }

    input.foldLeft(List[A]()) {(xs, x) => swap(x, xs)}
  }

  /**Implements merge sort algorithm in Scala
   *
   * @param input an input list
   * @param ord an implicit instance representing a strategy for sorting instances of a type A
   * @tparam A a type parameter of a method. It must have a way to be compared
   * @return a sorted list of A's
   */
  def mergeSort[A](input: List[A])(implicit ord: Ordering[A]): List[A] = {
    def merge(left: List[A], right: List[A]): List[A] = (left, right) match {
      case (_, Nil) => left
      case (Nil, _) => right
      case _ if ord.lt(left.head, right.head) => left.head :: merge(left.tail, right)
      case _ => right.head :: merge(left, right.tail)
    }

    input match {
      case Nil => Nil
      case x :: Nil => List[A](x)
      case _ =>
        val (left, right) = input.splitAt(input.length / 2)

        merge(mergeSort(left), mergeSort(right))
    }
  }

  /**Implements bubble sort algorithm in Scala
   *
   * @param input an input list
   * @param ord an implicit instance representing a strategy for sorting instances of a type A
   * @tparam A a type of elements of input list. It must have a way to be compared
   * @return a sorted given list
   */
  def bubbleSort[A](input: List[A])(implicit ord: Ordering[A]): List[A] = {
    def sort(xs: List[A]): List[A] = xs match {
      case Nil      => List[A]()
      case y :: ys  => ys match {
        case Nil                     => List(y)
        case _ if ord.lt(ys.head, y) => ys.head :: sort(y :: ys.tail)
        case _                       => y :: sort(ys)
      }
    }

    @tailrec
    def iter(oldList: List[A], newList: List[A]): List[A] = {
      if (oldList.equals(newList)) newList
      else iter(newList, sort(newList))
    }

    iter(List[A](), input)
  }
}
