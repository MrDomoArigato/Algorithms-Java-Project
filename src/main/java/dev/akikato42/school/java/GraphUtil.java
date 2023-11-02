package dev.akikato42.school.java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class GraphUtil {

    /**
     * Creates a new random graph of Nodes with a random size
     * @return Node Array containing all generated Nodes
     */
    public static Node[] newRandomGraph(){
        // Determines Size of graph. 
        int nodes = new Random().nextInt(26 - 0 + 1) + 0;

        // TODO change back to random node count
        return randomGraphBySize(nodes);
    }

    /**
     * Creates a randomized graph based on a number of resulting nodes
     * @param graphSize Number of nodes to create
     * @return Node Array containing all generated Nodes
     */
    public static Node[] randomGraphBySize(int graphSize){
        Random rand = new Random();
        Node[] graph = new Node[graphSize];
        for(int n = 0; n < graphSize; n++){
            // TODO fix graph labels to be unique when size > 26
            graph[n] = new Node(n, Character.toString((char) ((n % 65) + 65)));
        }

        for(int n = 0; n < graphSize; n++){
            int edgeNum = (int) Math.floor(rand.nextGaussian()*1+3);

            for(int e = 0; e < edgeNum; e++){
                int dest = rand.nextInt(graphSize - 0) + 0;
                Integer weight = Integer.valueOf((int) Math.floor(rand.nextGaussian()*5+10));

                graph[n].addEdge(graph[dest], weight);
            }
        }

        return graph;
    }

    /**
     * Out facing Dijkstra's Algorithm call
     * @param s Start node for algorithm
     * @param e End node
     * @return Distance from Node s to Node e
     */
    public static Integer Dijkstra(Node s, Node e){
        long start = System.nanoTime();
        Set<Node> filter = new HashSet<>();
        Map<Node, Integer> next = new HashMap<>();
        s.addDistance(new Node[]{s}, 0);
        s.setDistance(0);
        next.put(s, 0);
        Dijkstra(next, filter);
        long stop = System.nanoTime();
        System.out.println("Dijkstra :: Time - " + (stop - start));
        if(e.getDistance() == Integer.MAX_VALUE)
            System.out.println("No Connections to end node.");
        return e.getDistance();
    }

    /**
     * Dijkstra's Algorithm
     * @param next Map of next avaliable Nodes to visit
     * @param filter Already visited nodes to prevent loops
     */
    private static void Dijkstra(Map<Node, Integer> next, Set<Node> filter){
        if(next.isEmpty())
            return;
        Node n = Collections.min(next.entrySet(), Map.Entry.comparingByValue()).getKey();
        n.getEdges().entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(entry -> {
            if(entry.getKey().getDistance() > (n.getDistance() + entry.getValue()))
                entry.getKey().setDistance(n.getDistance() + entry.getValue());
            if(!filter.contains(entry.getKey()) && entry.getKey() != n)
                next.put(entry.getKey(), entry.getValue());
        });
        next.remove(n);
        filter.add(n);
        Dijkstra(next, filter);      
    }

    /*
     * Graphing code below please ignore
    */

    /**
     * Easy Code for making graphs
     * @param graph Array of Nodes to be used in resulting File
     * @param hghlgtPath Path of Nodes to be highlighted
     */
    public static void outputGraph(Node[] graph, Node[] hghlgtPath){
        Set<String> filters = new HashSet<>();
        LinkedHashSet<String> lines = new LinkedHashSet<>();
        lines.add("digraph \"Graph\" {");
        if(hghlgtPath != null && hghlgtPath.length != 0){
            lines.add("node [color=red]");
            lines.add("edge [color=red]");
            for(int i = 0; i < hghlgtPath.length; i++){
                filters.add(hghlgtPath[i].nodeName + " -> " + hghlgtPath[i + 1].nodeName);
                lines.add(hghlgtPath[i].nodeName + " -> " + hghlgtPath[i + 1].nodeName);
            }
        }

        lines.add("node [color=black]");
        lines.add("edge [color=black]");
        makeGraph(lines, graph, filters);
        lines.add("}");

        String filename = newOutputGraph();
        System.out.println("Creating graph: " + filename);

        try {
            FileWriter myWriter = new FileWriter(filename);
            for(String l : lines)
                myWriter.write(l + "\n");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("Unable to write to file.");
        }
    }

    /**
     * Writes Nodes and Edges for a non-highlighted path
     * @param lines The lines that will be written out to the file
     * @param s The nodes that need to be written
     * @param filters A filter to hold highlighted Nodes and Edges to prevent duplication
     */
    private static void makeGraph(Set<String> lines, Node[] s, Set<String> filters){
        for(Node n : s){
            for(Node o : n.getEdges().keySet()){
                if(!filters.contains(n.nodeName + " -> " + o.nodeName)){
                    lines.add(n.nodeName + " -> " + o.nodeName + " [label="+ n.getWeight(o) + "]");
                }
            }
        }
    }

    /*
     * Makes new file for each graph
     */
    private static String newOutputGraph(){
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmss");
        String filename = "graph-" + sdf1.format(new Date()) + ".gv.text";
        try {
            File file = new File(filename);
            file.createNewFile();
        } catch (IOException e) {
            System.out.println("Unable to create new file.");
        }

        return filename;
    }
    
}
