package org.foarlab

import org.foarlab.libiada._

/**
 * Created by goruha on 25.09.15.
 */
object usage {

  private val input: String = "NLYFQHMRHFARTHAIGQIVPGKVTKLVPFGAFVRVEEGIEGLVHISELAERHVEVPDQVVAVGDDAMVKVIDIDLERRRISLSLKQANEDYTEEFDPAKYGMADSYDEQGNYIFPEGFDAETNEWLEGFEKQRAEWEARYAEAERRHKMHTAQMEKFAAA"

  input.toList.build.order.intervalsSequence(Link.End)
    .order.intervalsSequence(Link.End)
    .distribution
    .foldLeft[Double](1)((m: Double, x: (Int, Int)) =>  m * x._1)
    //.foreach({case ((interval: Int, count: Int)) => println(interval + "\t=>\t" + count)})
}