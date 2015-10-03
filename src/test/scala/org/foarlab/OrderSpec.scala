package org.foarlab.libiada

import org.foarlab.libiada._
import org.scalatest.FunSpec

/**
 * Created by goruha on 23.09.15.
 */
class OrderSpec extends FunSpec {
  describe("Order") {
    describe("Intervals") {
      it("should distinct values") {
        val sequence: List[(Int, String)] = List((0, "A"), (1, "A"), (2, "B"))
        assertResult(3)(sequence.order.intervalsSequence(Link.End).length)
        assertResult((0, 1))(sequence.order.intervalsSequence(Link.End).head)
        assertResult((1, 2))(sequence.order.intervalsSequence(Link.End).tail.head)
        assertResult((2, 1))(sequence.order.intervalsSequence(Link.End).last)
      }
    }
  }
}
