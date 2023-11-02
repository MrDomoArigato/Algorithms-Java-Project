package dev.akikato42.school.java;

import java.util.HashMap;

public class Node {
    public final int nodeId;
    public final String nodeName;
    private HashMap<Node, Integer> edges = new HashMap<>();
    private Integer distance = Integer.MAX_VALUE;

    /**
     * Node constructor
     * @param nodeId Id number for node to use
     * @param nodeName Name of node to display
     */
    public Node(int nodeId, String nodeName){
        this.nodeId = nodeId;
        this.nodeName = nodeName;
    }

    /**
     * Adds a new edge from node to next node
     * @param endNode Next Node
     * @param weight Weight of edge to node
     */
    public void addEdge(Node endNode, Integer weight){
        this.edges.put(endNode, weight); 
    }

    /**
     * Allows for adding multiple edges
     * @param edges all new edges
     */
    public void addEdge(HashMap<Node, Integer> edges){
        this.edges.putAll(edges);
    }

    /**
     * Removes edge from node
     * @param endNode
     */
    public void removeEdge(Node endNode){
        this.edges.remove(endNode);
    }

    /**
     * Sets the current distance from start node to current node
     * @param dist
     */
    public void setDistance(Integer dist){
        this.distance = dist;
    }

    /**
     * Returns current value for distance
     * @return
     */
    public Integer getDistance(){
        return this.distance;
    }

    /**
     * Returns edge map
     * @return
     */
    public HashMap<Node, Integer> getEdges(){
        return this.edges;
    }

    /**
     * Get a weight to a specific node
     * @param n End node
     * @return Weight of edge to end node
     */
    public Integer getWeight(Node n){
        return this.edges.get(n);
    }

    public boolean equals(Node n){
        if(this.nodeId == n.nodeId)
            return true;
        return false;
    }
}