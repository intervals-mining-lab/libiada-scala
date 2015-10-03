package org.foarlab

import org.foarlab.libiada._
import org.scalatest.FunSpec

/**
 * Created by goruha on 23.09.15.
 */
class DistributionSpec extends FunSpec {
  describe("Distribution") {
    describe("order") {
      it("should distinct values") {
        val sequence: List[(Int, Int)] = List((0, 0), (1, 0), (2, 1))
        assertResult(2)(sequence.distribution.length)
        assertResult((0, 2))(sequence.distribution.head)
        assertResult((1, 1))(sequence.distribution.last)
      }

      it("should be sorted by default") {
        val sequence: List[(Int, Int)] = List((0, 0), (1, 0), (2, 1), (3, 1), (4, 2))
        assertResult(3)(sequence.distribution.length)
        assertResult((0, 2))(sequence.distribution.head)
        assertResult((1, 2))(sequence.distribution.tail.head)
        assertResult((2, 1))(sequence.distribution.last)
      }
    }

    describe("sequence") {
      it("should distinct values") {
        val sequence: List[(Int, String)] = List((0, "A"), (1, "A"), (2, "B"))
        assertResult(2)(sequence.distribution.length)
        assertResult(("A", 2))(sequence.distribution.head)
        assertResult(("B", 1))(sequence.distribution.last)
      }

      it("should be sorted by default") {
        val sequence: List[(Int, String)] = List((0, "A"), (1, "A"), (2, "B"), (3, "B"), (4, "C"))
        assertResult(3)(sequence.distribution.length)
        assertResult(("A", 2))(sequence.distribution.head)
        assertResult(("B", 2))(sequence.distribution.tail.head)
        assertResult(("C", 1))(sequence.distribution.last)
      }
    }
  }
}
