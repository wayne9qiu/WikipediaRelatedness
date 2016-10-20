package it.unipi.di.acubelab.wikipediarelatedness.wikipedia.relatedness

import it.unimi.dsi.fastutil.doubles.DoubleArrayList
import it.unipi.di.acubelab.wikipediarelatedness.dataset.WikiRelateTask
import it.unipi.di.acubelab.wikipediarelatedness.latent.GraphSVD
import it.unipi.di.acubelab.wikipediarelatedness.options.GraphSVDOptions
import it.unipi.di.acubelab.wikipediarelatedness.utils.Configuration
import org.slf4j.LoggerFactory

/**
  *
  * @param options
  *                {
  *                   "relatedness":    "graphSVD"
  *                   "eigen":   "right/left/left,right"
  *                   "length":
  *                }
  */
/*class GraphSVDRelatedness(options: GraphSVDOptions) extends Relatedness  {
  val logger = LoggerFactory.getLogger(classOf[GraphSVDRelatedness])

  val svds = eigenNames.map(name => new GraphSVD(Configuration.graphSVD(name))).toList

  override def computeRelatedness(wikiRelTask: WikiRelateTask): Double = {
    val srcWikiID = wikiRelTask.src.wikiID
    val dstWikiID = wikiRelTask.dst.wikiID

    val srcVector = mergedEigenVector(srcWikiID)
    val dstVector = mergedEigenVector(dstWikiID)

    Similarity.cosineSimilarity(srcVector, dstVector)
  }

  /**
    * @return One single eigenvector of wikiID. If eigenNames == [left,right], then
    *         it returns the concatenation between left and right eigenvectors.
    */
  def mergedEigenVector(wikiID: Int) : DoubleArrayList = {
    val eigenVectors = svds.map(_.eigenEmbeddingVector(wikiID))

    eigenVectors.foldLeft(new DoubleArrayList()) {
      (merger, vec) =>
        merger.addAll(vec)
        merger
    }
  }

  override def toString(): String = {
    "GraphSVD_%s".format(eigenNames.mkString("_"))
  }
}*/