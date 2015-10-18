package org.foarlab.libiada

import com.github.mauricio.async.db.{RowData, QueryResult, Connection}
import com.github.mauricio.async.db.postgresql.PostgreSQLConnection
import com.github.mauricio.async.db.postgresql.util.URLParser
import org.foarlab.libiada._
import org.scalatest.FunSpec
import scala.concurrent.duration._
import scala.concurrent.{Future, Await}
import scala.concurrent.ExecutionContext.Implicits.global

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


/*    describe("Fun") {
      val configuration = URLParser.parse("jdbc:postgresql://92.126.202.54:5433/Libiada?user=postgres&password=4784")
      val connection: Connection = new PostgreSQLConnection(configuration)

      Await.result(connection.connect, 180 seconds)

      val future: Future[QueryResult] = connection.sendQuery("SELECT ARRAY_TO_STRING(building[1:100000], '') FROM chain WHERE id = 1000969")

      val mapResult: Future[Any] = future.map(queryResult => queryResult.rows match {
        case Some(resultSet) => {
          val row : RowData = resultSet.head
          row(0)
        }
        case None => -1
      }
      )

      val result = Await.result( mapResult, 180 seconds ).asInstanceOf[String]


      info("Length " + result.length)
      connection.disconnect
      val t1 = System.currentTimeMillis
      val list= result.toList
      val t2 = System.currentTimeMillis
      println((t2 - t1) + " msecs - toList")
      val build = list.build
      val t3 = System.currentTimeMillis
      println((t3 - t2) + " msecs - build")

      val intervals = build.intervalsSequence(Link.End)
      val t4 = System.currentTimeMillis
      println((t4 - t3) + " msecs - order")

      println((t4 - t1) + " msecs")

    }*/
  }
}
