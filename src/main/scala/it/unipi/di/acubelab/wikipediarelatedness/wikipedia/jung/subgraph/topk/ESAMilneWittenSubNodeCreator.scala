package it.unipi.di.acubelab.wikipediarelatedness.wikipedia.jung.subgraph.topk

import it.unimi.dsi.fastutil.ints.IntOpenHashSet
import it.unipi.di.acubelab.wikipediarelatedness.wikipedia.topk.{ESATopK, MilneWittenTopK, TopK}
import org.slf4j.{Logger, LoggerFactory}

import collection.JavaConverters._


class ESAMilneWittenSubNodeCreator(graph: String, size: Int) extends TopKSubNodeCreator(size) {
  override protected def logger: Logger = LoggerFactory.getLogger(getClass)

  protected def mw = new MilneWittenTopK(graph)
  protected def esa = new ESATopK


  override protected def topK: TopK = new TopK {

    override def topKScoredEntities(wikiID: Int, k: Int): Seq[(Int, Float)] = {
        val mwTopK = mw.topKEntities(wikiID, k)
        val dstTopK = esa.topKEntities(wikiID, k)

        val nodes = new IntOpenHashSet()

        var (i, j) = (0, 0)
        var n = nodes.size()

        while(i < mwTopK.length && j < dstTopK.length && nodes.size() < size) {

          while (i < mwTopK.length && n == nodes.size() && nodes.size() < size) {
            nodes.add( mwTopK(i) )
            i += 1
          }
          if (n != nodes.size()) n += 1

          while (j < dstTopK.length && n == nodes.size() && nodes.size() < size) {
            nodes.add( dstTopK(j) )
            j += 1
          }
          if (n != nodes.size()) n += 1

        }

        while (i < mwTopK.length && nodes.size() < size) { nodes.add( mwTopK(i) ) ; i += 1 }
        while (j < dstTopK.length && nodes.size() < size) { nodes.add( dstTopK(j) ) ; j += 1 }


        nodes.toIntArray.map((_, 1f))  // 1f is a fake score
    }

  }
}
