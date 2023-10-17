package dev.akikato42.school.java;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Node[] graph = GraphUtil.newRandomGraph();
        //System.out.println(GraphUtil.newRandomGraph().getEdges().values().isEmpty());
        GraphUtil.outputGraph(graph, null);
        System.out.println(GraphUtil.Dijkstra(graph[0], graph[graph.length - 1]));
        System.out.println(graph[3].getDistance());
    }
}
