package it.unipi.di.acubelab.wikipediarelatedness.wikipedia.fast.relatedness

import it.unipi.di.acubelab.wikipediarelatedness.wikipedia.relatedness.Relatedness

class FastMWDWRelatedness extends Relatedness{
  /**
    * Computes the relatedness between two Wikipedia entities uniquely identified by their ID.
    *
    * @param srcWikiID
    * @param dstWikiID
    * @return
    */
  override def computeRelatedness(srcWikiID: Int, dstWikiID: Int): Float = 1f
}
