package org.foarlab

import org.foarlab.libiada._
import org.scalatest.FunSpec

/**
 * Created by goruha on 23.09.15.
 */
class BuilderSpec extends FunSpec {
  describe("Builder") {
    it("should convert any list[A] to List[(Int, A)]") {
      val sequence: List[String] = List( "A", "A")
      assertResult((0, "A"))(sequence.build.head)
      assertResult((1, "A"))(sequence.build.last)
    }

    describe("order") {
      it("should return order") {
        val sequence: List[String] = List("A", "A", "B")
        assertResult(3)(sequence.order.length)
        assertResult((0, 0))(sequence.order.head)
        assertResult((1, 0))(sequence.order.tail.head)
        assertResult((2, 1))(sequence.order.last)
      }

      it("should order") {
        val sequence: List[String] = List("B", "A", "B")
        assertResult(3)(sequence.order.length)
        assertResult((0, 0))(sequence.order.head)
        assertResult((1, 1))(sequence.order.tail.head)
        assertResult((2, 0))(sequence.order.last)
      }
    }

  }
}
