package it.unipi.di.acubelab.wikipediarelatedness.wikipedia.processing.webgraph.subgraph.topk

import it.unipi.di.acubelab.wikipediarelatedness.wikipedia.processing.embeddings.TopKEmbeddings
import it.unipi.di.acubelab.wikipediarelatedness.wikipedia.processing.webgraph.graph.WikiGraph
import org.slf4j.LoggerFactory


class W2VSubWikiGraph(srcWikiID: Int, dstWikiID: Int, wikiGraph: WikiGraph, threshold: Int)
  extends TopKSubWikiGraph(srcWikiID, dstWikiID, wikiGraph, threshold) {


  override def getLogger() = LoggerFactory.getLogger(classOf[W2VSubWikiGraph])


  override def neighborhood(wikiID: Int) : Array[Int] = TopKEmbeddings.corpusSG.getTopKWikiIDs(wikiID).toArray

}
