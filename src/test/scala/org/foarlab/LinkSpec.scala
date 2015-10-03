package org.foarlab.libiada

import org.scalatest.FunSpec

class LinkSpec extends FunSpec {
  describe("Link") {
    describe("End") {
      it("should have 1 for neighbor elements") {
        val sequence: List[(Int, Int)] = List( (1, 1) , (2, 1))
        assertResult(1)(Link.End(sequence).head._2)
      }

      it("should have 2 for elements") {
        val sequence: List[(Int, Int)] = List( (1, 1) , (2, 2), (3, 1))
        assertResult(2)(Link.End(sequence).head._2)
      }

      it("should have 1 for last 1 element") {
        val sequence: List[(Int, Int)] = List( (1, 1) , (2, 2), (3, 1))
        assertResult(1)(Link.End(sequence).last._2)
      }

      it("should have 2 for last 2 element") {
        val sequence: List[(Int, Int)] = List( (1, 1) , (2, 2), (3, 1))
        assertResult(2)(Link.End(sequence).tail.head._2)
      }
    }

    describe("Start") {
      it("should have 1 for neighbor elements") {
        val sequence: List[(Int, Int)] = List( (1, 1) , (2, 1))
        assertResult(1)(Link.Start(sequence).head._2)
      }

      it("should have 2 for elements") {
        val sequence: List[(Int, Int)] = List( (1, 1) , (2, 2), (3, 1))
        assertResult(1)(Link.Start(sequence).head._2)
      }

      it("should have 1 for last 1 element") {
        val sequence: List[(Int, Int)] = List( (1, 1) , (2, 2), (3, 1))
        assertResult(2)(Link.Start(sequence).last._2)
      }

      it("should have 2 for last 2 element") {
        val sequence: List[(Int, Int)] = List( (1, 1) , (2, 2), (3, 1))
        assertResult(2)(Link.Start(sequence).tail.head._2)
      }
    }

  }
}
