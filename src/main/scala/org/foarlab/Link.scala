package org.foarlab.libiada


object Link {

   def End[A](sequence: List[(Int,A)]): List[(Int, Int)] = {
     //internalEnd(sequence, List())
     //oldEnd(sequence)
     mutableEnd(sequence)
   }

  def Start[A](sequence: List[(Int,A)]): List[(Int, Int)] = {
    val last = sequence.lastOption
    if (last.isDefined) {
      val pos = last.get._1
      val value = last.get._2
      val init = sequence.init

      Start(init) ++ List(
        (pos,
          pos - init.reverse
                    .collectFirst[Int]({ case (n_pos: Int, n_value: Int) if n_value == value => n_pos})
                    .getOrElse(0)
          )
      )
    }
    else {
      List()
    }
  }

  private

  def oldEnd[A](sequence: List[(Int,A)]): List[(Int, Int)] = {
    val head = sequence.headOption
    if (head.isDefined) {
      val pos = head.get._1
      val value = head.get._2
      val tail = sequence.tail
      List(
        (pos,
          tail
            .collectFirst[Int]({ case (n_pos: Int, n_value: Int) if n_value == value => n_pos})
            .getOrElse(pos + tail.length + 1) - pos
          )
      ) ++ End(tail)
    }
    else {
      List()
    }
  }

  def mutableEnd[A](sequence: List[(Int,A)]): List[(Int, Int)] = {
    sequence.map({
      case (pos: Int, value) =>
        val next_pos = sequence
          .collectFirst[Int]({ case (n_pos: Int, n_value: Int) if ((n_pos > pos) && (n_value == value)) => n_pos})
          .getOrElse(sequence.last._1 + 1)
        (pos, next_pos - pos)
    })
  }

  def internalEnd[A](sequence: List[(Int,A)], result: List[(Int,Int)]): List[(Int, Int)] = {
    val head = sequence.headOption
    if (head.isDefined) {
      val pos = head.get._1
      val value = head.get._2
      val tail = sequence.tail
      internalEnd(tail, List(
        (pos,
          tail
            .collectFirst[Int]({ case (n_pos: Int, n_value: Int) if n_value == value => n_pos})
            .getOrElse(pos + tail.length + 1) - pos
          )
      ) ++ result)
    }
    else {
      result
    }
  }
 }

