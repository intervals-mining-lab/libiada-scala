package org.foarlab.libiada


object Link {

   def End(sequence: List[(Int,Int)])(item: Tuple2[Int,Int]): (Int, Int) = {
     val pos = item._1
     val value = item._2
     pos -> (
        sequence.slice(pos + 1, sequence.length)
         .find(
           { case (n_pos: Int, n_value) => n_value == value}
         ).getOrElse((sequence.length, value))._1 - pos
       )
   }
 }

