package dev.akikato42.school.java;

import java.util.HashMap;

public class Node {
    public final int nodeId;
    public final String nodeName;
    private HashMap<Node, Integer> edges = new HashMap<>();

    public Node(int nodeId, String nodeName){
        this.nodeId = nodeId;
        this.nodeName = nodeName;
    }

    public void addEdge(Node endNode, int weight){
        this.edges.put(endNode, weight);
    }

    public void addEdge(HashMap<Node, Integer> edges){
        this.edges.putAll(edges);
    }

    public void removeEdge(Node endNode){
        this.edges.remove(endNode);
    }

    public HashMap<Node, Integer> getEdges(){
        return this.edges;
    }

    public boolean equals(Node n){
        if(this.nodeId == n.nodeId)
            return true;
        return false;
    }
}