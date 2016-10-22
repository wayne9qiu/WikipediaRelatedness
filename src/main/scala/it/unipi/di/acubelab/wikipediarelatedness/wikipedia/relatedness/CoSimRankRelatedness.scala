package it.unipi.di.acubelab.wikipediarelatedness.wikipedia.relatedness

import it.unipi.di.acubelab.wikipediarelatedness.options.CoSimRankOptions
import it.unipi.di.acubelab.wikipediarelatedness.wikipedia.WikiGraph
import it.unipi.di.acubelab.wikipediarelatedness.wikipedia.processing.webgraph.CoSimRank
import org.slf4j.LoggerFactory

class CoSimRankRelatedness(options: CoSimRankOptions) extends Relatedness {
  val logger = LoggerFactory.getLogger(classOf[CoSimRankRelatedness])
  val csr = new CoSimRank(WikiGraph.outGraph, options.iterations, options.pprDecay, options.csrDecay)


  def computeRelatedness(srcWikiID: Int, dstWikiID: Int) : Float = {

    val s = csr.similarity(srcWikiID, dstWikiID)
    println("%d %d %1.5f".formatLocal(java.util.Locale.US, srcWikiID, dstWikiID, s))
    s
  }

  override def toString(): String = {
    "CoSimRank_%s".format(options)
  }

}
