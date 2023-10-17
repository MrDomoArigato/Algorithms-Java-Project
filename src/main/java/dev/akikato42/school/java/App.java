package dev.akikato42.school.java;

/**
 * Hello world!
 *
 */
public class App 
{
    /**
     * Main function for running program
     * @param args
     */
    public static void main( String[] args )
    {
        Node[] graph = GraphUtil.randomGraphBySize(10);
        GraphUtil.outputGraph(graph, null);
        System.out.println(GraphUtil.Dijkstra(graph[0], graph[graph.length - 1]));
    }
}
