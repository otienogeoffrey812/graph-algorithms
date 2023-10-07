package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WeightedGraph {
    private class Node{
        private String label;
        private List<Edge> edges = new ArrayList<>();
        private Node(String label){
            this.label = label;
        }

        @Override
        public String toString(){
            return label;
        }
        private void addEdge(Node to, int weight){
            edges.add(new Edge(this,  to, weight));
        }
        public List<Edge> getEdges(){
            return edges;
        }
    }
    private class Edge{
        private Node from;
        private Node to;
        private int weight;
        public Edge(Node from, Node to, int weight){
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString(){
            return from + "->" + to;
        }
    }
    private HashMap<String, Node> nodes = new HashMap<>();
    public void addNode(String label){
        nodes.putIfAbsent(label, new Node(label));
    }
    public void addEdge(String from, String to, int weight){
        var fromNode = nodes.get(from);
        var toNode = nodes.get(to);

        if (fromNode == null || toNode == null){
            throw new IllegalArgumentException();
        }

        fromNode.addEdge(toNode, weight);
        toNode.addEdge(fromNode, weight);
    }
    public void print(){
        for (var node: nodes.values()){
            var edges = node.getEdges();
            if (!edges.isEmpty()) {
                System.out.println(node + " is connected to " + edges);
            }
        }
    }

}
