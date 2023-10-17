package dev.akikato42.school.java;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;

import org.junit.Test;

public class NodeTest {
    @Test
    public void addEdge(){
        Node n1 = new Node(0, "test");
        Node n2 = new Node(1, "test2");
        HashMap<Node, Integer> edge = new HashMap<>();
        edge.put(n2, 5);
        n1.addEdge(n2, 5);

        assertEquals(n1.getEdges(), edge);
    }

    @Test
    public void getWeight(){
        Node n1 = new Node(0, "test");
        Node n2 = new Node(1, "test2");

        n1.addEdge(n2, 5);

        Integer weight = n1.getWeight(n2);

        assertEquals(weight, (Integer) 5);
    }

    /* public void addEdge(HashMap<Node, Integer> edges){

    }

    public void removeEdge(Node endNode){

    }

    public void addDistance(){

    }

    public HashMap<Node, Integer> getEdges(){

    }

    public boolean equals(){
        
    } */
}
