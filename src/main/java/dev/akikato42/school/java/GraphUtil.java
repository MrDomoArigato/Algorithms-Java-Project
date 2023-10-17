package dev.akikato42.school.java;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedSet;

public class GraphUtil {
    public static Node[] newRandomGraph(){
        // Determines Size of graph. 
        int nodes = new Random().nextInt(26 - 0 + 1) + 0;

        // TODO change back to random node count
        return randomGraphBySize(10);
    }

    private static Node[] randomGraphBySize(int graphSize){
        Random rand = new Random();
        Node[] graph = new Node[graphSize];
        for(int n = 0; n < graphSize; n++){
            // TODO fix graph labels to be unique when size > 26
            graph[n] = new Node(n, Character.toString((char) ((n % 65) + 65)));
            System.out.println(graph[n].nodeName + " Node Created.");
        }

        for(int n = 0; n < graphSize; n++){
            System.out.println("Adding edges to Node " + graph[n].nodeName);
            int edgeNum = (int) Math.floor(rand.nextGaussian()*1+3);

            for(int e = 0; e < edgeNum; e++){
                int dest = rand.nextInt(graphSize - 0) + 0;
                Integer weight = Integer.valueOf((int) Math.floor(rand.nextGaussian()*5+10));

                graph[n].addEdge(graph[dest], weight);
            }
        }

        return graph;
    }

    public static Integer Dijkstra(Node s, Node e){
        Set<Node> filter = new HashSet<>();
        s.addDistance(new Node[]{s}, 0);
        s.setDistance(0);
        Dijkstra(s, filter);
        if(e.getDistance() == Integer.MAX_VALUE)
            System.out.println("No Connections to end node.");
        return e.getDistance();
    }

    private static void Dijkstra(Node n, Set<Node> filter){
        System.out.println(n.nodeName);
        filter.add(n);
        n.getEdges().entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(entry -> {
            if(entry.getKey().getDistance() > (n.getDistance() + entry.getValue())){
                entry.getKey().setDistance(n.getDistance() + entry.getValue());
            }
            if(!filter.contains(entry.getKey()) && n != entry.getKey()){
                Dijkstra(entry.getKey(), filter);
            }
        });            
    }

    /*
     * Graphing code below please ignore
     */

    /*
     * Easy Code for making graphs 
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

    /*
     * Writes non-highlighted edges
     */
    private static void makeGraph(Set<String> lines, Node[] s, Set<String> filters){
        for(Node n : s){
            for(Node o : n.getEdges().keySet()){
                System.out.println(n.nodeName + " -> " + o.nodeName);
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
