package dev.akikato42.school.java;

import java.util.Random;

public class GraphUtil {
    public static Node newRandomGraph(){
        // Determines Size of graph. 
        int nodes = new Random().nextInt(26 - 0 + 1) + 0;

        return randomGraphBySize(nodes);
    }

    private static Node randomGraphBySize(int graphSize){
        Node[] graph = new Node[graphSize];
        for(int n = 0; n < graphSize; n++){
            // TODO fix graph labels to be unique when size > 26
            graph[n] = new Node(n, Character.toString((char) ((n % 65) + 65)));
            System.out.println(graph[n].nodeName + " Node Created.");
        }

        for(int n = 0; n < graphSize; n++){
            System.out.println("Adding edges to Node " + graph[n].nodeName);
            int edgeNum = (int) Math.floor(new Random().nextGaussian()*2+3);

            for(int e = 0; e < edgeNum; e++){
                int dest = new Random().nextInt(graphSize - 0) + 0;
                int weight = (int) Math.floor( new Random().nextGaussian()*5+10 );

                graph[n].addEdge(graph[dest], weight);
            }
        }

        return graph[0];
    }

    
}
