package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Graph {
    private class Node{
        private String label;
        private Node(String label){
            this.label = label;
        }
        @Override
        public String toString(){
            return label;
        }
    }
    private Node root;
    HashMap<String, Node> nodes = new HashMap<>();
    HashMap<Node, List<Node>> adjacencyList = new HashMap<>();

    public void addNode(String label){
        var node = new Node(label);
        nodes.putIfAbsent(label, node);
        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    public void addEdge(String from, String to){
        var fromNode = nodes.get(from);
        var toNode = nodes.get(to);

        if (fromNode == null || toNode == null){
            throw new IllegalArgumentException();
        }
        adjacencyList.get(fromNode).add(toNode);
    }

    public void print(){
        for (var source: adjacencyList.keySet()){
            var target = adjacencyList.get(source);
            if (!target.isEmpty()) {
                System.out.println(source + " is connected to " + target);
            }
        }
    }

}
