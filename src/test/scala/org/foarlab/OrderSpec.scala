package org.foarlab

import org.foarlab.libiada._
import org.scalatest.FunSpec

/**
 * Created by goruha on 23.09.15.
 */
class OrderSpec extends FunSpec {
  describe("Order") {
    describe("Intervals") {
      it("should distinct values") {
        val sequence: List[(Int, Int)] = List((0, 0), (1, 0), (2, 1))
        assertResult(3)(sequence.intervalsSequence(Link.End).length)
        assertResult((0, 1))(sequence.intervalsSequence(Link.End).head)
        assertResult((1, 2))(sequence.intervalsSequence(Link.End).tail.head)
        assertResult((2, 1))(sequence.intervalsSequence(Link.End).last)
      }
    }
  }
}