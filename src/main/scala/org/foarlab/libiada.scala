package org.foarlab

package object libiada {

  implicit class Builder[A](val l: List[A]) {

    def order: List[(Int, Int)] = build.order

    def build: List[(Int, A)] = {
      l.zipWithIndex // make array as tuple (pos, word)
        .map({ case (value, pos: Int) => (pos, value)})
    }
  }

  implicit class Sequence[A](val l: List[(Int, A)]) {

    def alphabet: IndexedSeq[A] = {
      l.map({case (pos: Int, value) => value}).distinct.toIndexedSeq
    }

    def order:List[(Int, Int)] = {
      l.map({case (pos: Int, value) => (pos, l.alphabet.indexOf(value))})
    }
  }

  implicit class Order(val l: List[(Int, Int)]) {

    def intervalsSequence(link: (List[(Int, Int)]) => ((Int, Int)) => (Int, Int)): List[(Int, Int)] = {
      l.map(link(l))
    }
  }

  implicit class Distriution(val l: List[(Int, Int)]) {

    def distribution: List[(Int, Int)] = {
      l.alphabet.map((value: Int) => {(value, l.count(_._2 == value))})
        .toSeq.sortWith(_._1 < _._1).toList
    }

    def frequency: List[(Int, Double)] = {
      l.distribution.map({case (item: Int, count: Int) => (item, count / l.map(_._2).sum.toDouble)})
    }
  }
}

