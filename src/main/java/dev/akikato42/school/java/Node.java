package dev.akikato42.school.java;

import java.util.HashMap;

public class Node {
    public final int nodeId;
    public final String nodeName;
    private HashMap<Node, Integer> edges = new HashMap<>();
    private HashMap<Node[], Integer> distances = new HashMap<>();
    private Integer distance = Integer.MAX_VALUE;

    public Node(int nodeId, String nodeName){
        this.nodeId = nodeId;
        this.nodeName = nodeName;
    }

    public void addEdge(Node endNode, Integer weight){
        this.edges.put(endNode, weight); 
    }

    public void addEdge(HashMap<Node, Integer> edges){
        this.edges.putAll(edges);
    }

    public void removeEdge(Node endNode){
        this.edges.remove(endNode);
    }

    public void addDistance(Node[] nodes, Integer distance){
        this.distances.put(nodes, distance);
    }

    public void addDistances(Node[] nodes, Integer distance){
        this.distances.put(nodes, distance);
    }

    public void setDistance(Integer dist){
        this.distance = dist;
    }

    public Integer getDistance(){
        return this.distance;
    }

    public HashMap<Node, Integer> getEdges(){
        return this.edges;
    }

    public Integer getWeight(Node n){
        return this.edges.get(n);
    }

    public boolean equals(Node n){
        if(this.nodeId == n.nodeId)
            return true;
        return false;
    }
}