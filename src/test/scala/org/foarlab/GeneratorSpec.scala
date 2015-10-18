package org.foarlab

import org.apache.commons.math3.util.{CombinatoricsUtils, ArithmeticUtils}
import org.foarlab.libiada._
import org.scalatest.FunSpec

/**
 * Created by goruha on 23.09.15.
 */
class GeneratorSpec extends FunSpec {
  describe("Generator") {
    it("should generate alphabet IndexedSeq[Int] based on alphabet size") {
      val alphabet: IndexedSeq[Int] = Generator.Alphabet(2)
      assertResult(2)(alphabet.size)

      assertResult(1)(alphabet.head)
      assertResult(2)(alphabet.last)
    }

    it("should create frequency  List[(A, Double)] from alphabet IndexedSeq[Int] with uniform distribution") {
      val alphabet: IndexedSeq[Int] =  IndexedSeq.range(1, 3)
      val frequency: List[(Int, Double)] = Generator.Uniform(alphabet)
      assertResult((1, 0.5))(frequency.head)
      assertResult((2, 0.5))(frequency.last)
    }


    it("should create base sequence List[(Int, A)] from frequency  List[(A, Double)] based on length") {
      val frequency: List[(Int, Double)] =  List((1, 0.5), (2, 0.5))
      val seq: List[(Int, Int)] = Generator.Sequence(frequency, 10)
      assertResult((0,  1))(seq.head)
      assertResult((1,  1))(seq.tail.head)
      assertResult((2,  1))(seq.tail.tail.head)
      assertResult((3,  1))(seq.tail.tail.tail.head)
      assertResult((4,  1))(seq.tail.tail.tail.tail.head)
      assertResult((5,  2))(seq.tail.tail.tail.tail.tail.head)
      assertResult((6,  2))(seq.tail.tail.tail.tail.tail.tail.head)
      assertResult((7,  2))(seq.tail.tail.tail.tail.tail.tail.tail.head)
      assertResult((8,  2))(seq.tail.tail.tail.tail.tail.tail.tail.tail.head)
      assertResult((9,  2))(seq.last)
    }

    it("should create list of all premutation of string") {
      val n = 6 // We have 5 elements in seq
      val m = 2 // We have alphabet size 2
      val k1 = 3 // We have 3 times element 1
      val k2 = 3 // We have 2 times element 2
      val seq: List[(Int, Int)] = List((0,1), (1,1), (2, 1), (3, 2), (4, 2), (5, 2))
      val sequences = Generator.Permutations(seq)
      val count = CombinatoricsUtils.factorial(n) /
                  (CombinatoricsUtils.factorial(k1) * CombinatoricsUtils.factorial(k2))

      assertResult(count)(sequences.size)

      val textHash = sequences
        .map((s: List[(Int, Int)]) => s.map(_._2).mkString)
        .sortWith((s1, s2) => s1.compareTo(s2) < 0)
        .map((s: String) => s -> s)
        .toMap

      textHash.values.foreach((s: String) => {
        info(s + " is unique")
        assertResult(1)(textHash.count(_._2 == s))
        }
      )
     }

    it("should create list of all orders of string") {
      val n = 6 // We have 5 elements in seq
      val m = 2 // We have alphabet size 2
      val k1 = 3 // We have 3 times element 1
      val k2 = 3 // We have 2 times element 2
      val seq: List[(Int, Int)] = List((0,1), (1,1), (2, 1), (3, 2), (4, 2), (5, 2))
      val orders = Generator.Permutations(seq)
          .map(_.order.map(_._2).mkString)
          .sortWith((s1, s2) => s1.compareTo(s2) < 0)
          .distinct
          .sortWith((s1, s2) => s1.compareTo(s2) < 0)
          .map(_.toList.build.order)

      val count = CombinatoricsUtils.factorial(n) /
        (CombinatoricsUtils.factorial(k1) * CombinatoricsUtils.factorial(k2) * CombinatoricsUtils.factorial(m))


      val textHash = orders.map((s: List[(Int, Int)]) => s.map(_._2).mkString).map((s: String) => s -> s).toMap

      textHash.values.foreach((s: String) => {
        info(s + " is unique")
        assertResult(1)(textHash.count(_._2 == s))
      }
      )

      assertResult(count)(orders.size)

    }


    it("should generate list of orders length 5 alphabet 3. Size of list should be equal stibling number of second (5, 3)") {
      val n = 7
      val m = 4
      val orders = Generator.Orders(n, m)
      val textHash = orders.map((s: List[(Int, Int)]) => s.map(_._2).mkString).map((s: String) => s -> s).toMap

      textHash.values.foreach((s: String) => {
        info(s + " is unique")
        assertResult(1)(textHash.count(_._2 == s))
      }
      )

      info("Should be " + CombinatoricsUtils.stirlingS2(n, m) + " orders")
      assertResult( CombinatoricsUtils.stirlingS2(n, m) ) (orders.size )



    }

/*
    it("should create list of all orders 2 of string") {
      val n = 5 // We have 5 elements in seq
      val m = 2 // We have alphabet size 2
      val k1 = 3 // We have 3 times element 1
      val k2 = 2 // We have 2 times element 2
      val seq: List[(Int, Int)] = List((0,1), (1,1), (2, 1), (3, 2), (4, 2))
      val orders = Generator.Permutations(seq)
        .map(_.order.map(_._2).mkString)
        .sortWith((s1, s2) => s1.compareTo(s2) < 0)
        .distinct
        .sortWith((s1, s2) => s1.compareTo(s2) < 0)
        .map(_.toList.build.order)

      val count = CombinatoricsUtils.factorial(n) /
        (CombinatoricsUtils.factorial(k1) * CombinatoricsUtils.factorial(k2) * CombinatoricsUtils.factorial(m))


      val textHash = orders.map((s: List[(Int, Int)]) => s.map(_._2).mkString).map((s: String) => s -> s).toMap

      textHash.values.foreach((s: String) => {
        info(s + " is unique")
        assertResult(1)(textHash.count(_._2 == s))
      }
      )

      assertResult(count)(orders.size)

    }
*/




  }
}
