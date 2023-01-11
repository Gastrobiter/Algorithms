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
      case Nil                     => List(x)
      case _ if ord.lt(x, xs.head) => x :: xs
      case _                       => xs.head :: swap(x, xs.tail)
    }

    input.foldLeft(List[A]()) { (xs, x) => swap(x, xs) }
  }

  /** Implements merge sort algorithm in Scala
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
      case _ if ord.lt(left.head, right.head) =>
        left.head :: merge(left.tail, right)
      case _ => right.head :: merge(left, right.tail)
    }

    input match {
      case Nil      => Nil
      case x :: Nil => List[A](x)
      case _ =>
        val (left, right) = input.splitAt(input.length / 2)

        merge(mergeSort(left), mergeSort(right))
    }
  }

  /** Implements bubble sort algorithm in Scala
    *
    * @param input an input list
    * @param ord an implicit instance representing a strategy for sorting instances of a type A
    * @tparam A a type of elements of input list. It must have a way to be compared
    * @return a sorted given list
    */
  def bubbleSort[A](input: List[A])(implicit ord: Ordering[A]): List[A] = {
    def sort(xs: List[A]): List[A] = xs match {
      case Nil => List[A]()
      case y :: ys =>
        ys match {
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

  /** *
    * This help method returns indices of crossing subarray and sum between these indices
    * @param input a List of Integers in which we're looking for the most efficient sum
    * @return a Tuple of (start index, end index, sum of Ints between these indices)
    */
  def findMaxCrossingSubarray(input: List[Int]): (Int, Int, Int) = {

    /** @param a       a List to check
      * @param acc     this parameter contains accumulated sum of elements of the given list
      * @param leftSum the most efficient sum of elements of the given list
      * @param i       an index where leftSum starts
      * @return a Tuple of most efficient sum of elements and the index where it starts
      */
    @tailrec
    def checkLeft(a: List[Int], acc: Int, leftSum: Int, i: Int): (Int, Int) =
      a match {
        //  leave the function if there's nothing in the list
        case Nil => (leftSum, i)
        // store the most efficient sum if edge value makes it higher
        case _ :: _ if a.last + acc > leftSum =>
          checkLeft(a.dropRight(1), a.last + acc, a.last + acc, a.length)
        // just keep on going in other cases
        case _ => checkLeft(a.dropRight(1), a.last + acc, leftSum, i)
      }

    @tailrec
    def checkRight(a: List[Int], acc: Int, rightSum: Int, i: Int): (Int, Int) =
      a match {
        case Nil => (rightSum, i)
        case _ :: _ if a.head + acc > rightSum =>
          checkRight(a.tail, a.head + acc, a.head + acc, a.length)
        case _ => checkRight(a.tail, a.head + acc, rightSum, i)
      }

    input match {
      case Nil      => (Int.MinValue, Int.MinValue, Int.MinValue)
      case x :: Nil => (x, x, x)
      case _ =>
        val mid = (input.length / 2).floor.toInt
        val left = checkLeft(input.take(mid), 0, Int.MinValue, mid)
        val right = checkRight(
          input.drop(mid),
          0,
          Int.MinValue,
          input.length - (input.length / 2).floor.toInt
        )

        (left._2 - 1, input.length - right._2, left._1 + right._1)
    }
  }
}
