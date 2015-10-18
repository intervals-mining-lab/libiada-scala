package org.foarlab

import org.foarlab.libiada._
import scala.collection.mutable.ListBuffer

object Generator {

  def Sequence(frequence: List[(Int, Double)], length: Int): List[(Int, Int)] = frequence.flatMap({ case (value: Int, freq: Double) =>
    List.fill((freq * length).round.toInt)(value)
  }).sortWith(_ < _).build

  def Uniform(alphabet: IndexedSeq[Int]): List[(Int, Double)] = alphabet.map((x: Int) => (x, 1.toDouble / alphabet.size)).toList

  def Alphabet(size: Int): IndexedSeq[Int] = IndexedSeq.range(1, size + 1)

  def Permutations(source: List[(Int, Int)]): List[List[(Int, Int)]] = {
    source.map({case (pos: Int, value: Int) => value}).toSeq.permutations.map((i: Seq[Int]) => i.toList.build).toList
  }

  def Orders(n: Int, m: Int):  List[List[(Int, Int)]] = {
    ordersGenerationTree(ListBuffer[List[(Int, Int)]](), n, m).toList
  }

  def ordersGenerationTree(result: ListBuffer[List[(Int, Int)]], n: Int, m: Int): ListBuffer[List[(Int, Int)]] = {
    var VertexesToProcess = scala.collection.mutable.Stack[(List[(Int, Int)], Int, Int, Int)]()

    VertexesToProcess.push((List(), 1, 1, 1))

    while (!VertexesToProcess.isEmpty) {
      val vertex = VertexesToProcess.pop()
      val list = vertex._1
      val k = vertex._2
      val i = vertex._3
      val j = vertex._4

      val new_list = List((i, k))
      if (n == i) {
        result+= list ++ new_list
      } else {
        // (n - i) > m - j
        val end = Math.min(m, j + 1)
        val start = if (m == j || (n - i + j - m ) > 0) {1} else { j + 1 }
        for (x <- start to end) {
          val alphabet_used = if ( x > Math.max(k, j) ) {Math.min(m, j + 1)} else { j }
          VertexesToProcess.push((list ++ new_list, x, i + 1, alphabet_used))
        }
      }
    }
    result
  }
}

