package org.foarlab.libiada

import org.foarlab.libiada._
import org.scalatest.FunSpec

/**
 * Created by goruha on 23.09.15.
 */
class SequenceSpec extends FunSpec {
  describe("Sequence") {
    describe("alphabet") {
      it("should distinct values") {
        val sequence: List[(Int, String)] = List((0, "A"), (1, "A"), (2, "B"))
        assertResult(2)(sequence.alphabet.length)
        assertResult("A")(sequence.alphabet.head)
        assertResult("B")(sequence.alphabet.last)
      }

      it("should return values in order it appears in list") {
        val sequence: List[(Int, String)] = List((0, "B"), (1, "A"), (2, "A"))
        assertResult(2)(sequence.alphabet.length)
        assertResult("B")(sequence.alphabet.head)
        assertResult("A")(sequence.alphabet.last)
      }
    }

    describe("order") {
      it("should return order") {
        val sequence: List[(Int, String)] = List((0, "A"), (1, "A"), (2, "B"))
        assertResult(3)(sequence.order.length)
        assertResult((0, 0))(sequence.order.head)
        assertResult((1, 0))(sequence.order.tail.head)
        assertResult((2, 1))(sequence.order.last)
      }

      it("should order") {
        val sequence: List[(Int, String)] = List((0, "B"), (1, "A"), (2, "B"))
        assertResult(3)(sequence.order.length)
        assertResult((0, 0))(sequence.order.head)
        assertResult((1, 1))(sequence.order.tail.head)
        assertResult((2, 0))(sequence.order.last)
      }
    }

  }
}
