package org.foarlab

package object libiada {

  implicit class Builder[A](val l: List[A]) {

    def order: List[(Int, Int)] = build.order

    def build: List[(Int, A)] = {
      var i = -1
      l.map((value) => {
        i +=1
        (i, value)
      })
    }
  }

  implicit class Sequence[A](val l: List[(Int, A)]) {

    def alphabet: IndexedSeq[A] = {
      l.map({case (pos: Int, value) => value}).distinct.toIndexedSeq
    }

    def order:List[(Int, Int)] = {
      l.map({case (pos: Int, value) => (pos, l.alphabet.indexOf(value))})
    }

    def distribution: List[(A, Int)] = {
      l.alphabet.map((value) => {(value, l.count(_._2 == value))}).toList
    }

    def frequency: List[(A, Double)] = {
      val distribution = l.distribution
      distribution.map({case (item, count: Int) => (item, count / distribution.map(_._2).sum.toDouble)})
    }


    def intervalsSequence(link: (List[(Int, A)]) => List[(Int, Int)]): List[(Int, Int)] = {
      link(l)
    }
  }

  implicit class Order(val l: List[(Int, Int)]) {

  }
}