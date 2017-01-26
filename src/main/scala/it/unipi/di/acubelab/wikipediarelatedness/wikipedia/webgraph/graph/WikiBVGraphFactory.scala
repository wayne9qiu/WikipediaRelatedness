package it.unipi.di.acubelab.wikipediarelatedness.wikipedia.webgraph.graph

import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap
import it.unimi.dsi.fastutil.io.BinIO
import it.unimi.dsi.webgraph.{BVGraph, ImmutableGraph}
import it.unipi.di.acubelab.wikipediarelatedness.utils.Config
import org.slf4j.LoggerFactory


/**
  * Factory object to have the wikiBVGraph from its name.
  *
  */
object WikiBVGraphFactory {
  val logger = LoggerFactory.getLogger("WikiBVGraphFactory")

  lazy val immOutGraph = loadImmutableGraph(Config.getString("webgraph.out"))
  lazy val immInGraph = loadImmutableGraph(Config.getString("webgraph.in"))
  lazy val immSymGraph = loadImmutableGraph(Config.getString("webgraph.sym"))
  lazy val immSymNoLoopGraph = loadImmutableGraph(Config.getString("webgraph.sym_no_loop"))

  // WikiID -> NodeID mapping
  lazy val wiki2node = BinIO.loadObject(Config.getString("webgraph.mapping")).asInstanceOf[Int2IntOpenHashMap]


  /**
    * Loads an immutable graph from path.
    *
    * @param path
    */
  protected def loadImmutableGraph(path: String): ImmutableGraph = {
      logger.info("Loading BVGraph from %s".format(path))
      val graph = BVGraph.load(path)
      logger.info("BVGraph loaded. |Nodes| = %d and |Edges| = %d".format(graph.numNodes, graph.numArcs))

      graph
  }


  /**
    * Returns a lightweight copy WikiBVGraph from its name.
    * If threadSafe is true it returns a lightweight copy of the graph which can be parallel processed.
    *
    * @param graphName
    * @return
    */
  def makeWikiBVGraph(graphName: String, threadSafe: Boolean = false) : WikiBVGraph = {

      val immGraph = graphName match {
        case "out" => immOutGraph
        case "in" => immInGraph
        case "sym" => immSymGraph
        case "sym_no_loop" =>  immSymNoLoopGraph
      }

      val threadedImmGraph = if (threadSafe) immOutGraph.copy() else immOutGraph

      new WikiBVGraph(threadedImmGraph, wiki2node)
  }

}