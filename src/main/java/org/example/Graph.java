package org.example;

import java.util.*;

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

    public void removeNode(String label){
        var node = nodes.get(label);
        if (node == null)
            return;

        for (var n : adjacencyList.keySet()){
            adjacencyList.get(n).remove(node);
        }

        adjacencyList.remove(node);
        nodes.remove(label);
    }
    public void removeEdge(String from, String to){
        var fromNode = nodes.get(from);
        var toNode = nodes.get(to);

        if (fromNode == null || toNode == null)
            return;

        adjacencyList.get(fromNode).remove(toNode);
    }

    public void print(){
        for (var source: adjacencyList.keySet()){
            var target = adjacencyList.get(source);
            if (!target.isEmpty()) {
                System.out.println(source + " is connected to " + target);
            }
        }
    }

    public void traverseDepthFirstRecursion(String label){
        var node = nodes.get(label);
        if (node == null)
            return;
        traverseDepthFirstRecursion(node, new HashSet<>());
    }

    private void traverseDepthFirstRecursion(Node node, HashSet<Node> visited) {
        if (node == null)
            return;

        System.out.println(node);
        visited.add(node);

        for (var n : adjacencyList.get(node)) {
            if (!visited.contains(n)) {
                traverseDepthFirstRecursion(n, visited);
            }
        }
    }

    public void traverseDepthFirstIterative(String label){
        var node = nodes.get(label);
        if (node == null)
            return;

        HashSet<Node> visited = new HashSet<>();
        Stack<Node> stack = new Stack<>();

        stack.add(node);

        while (!stack.isEmpty()){
            var current = stack.pop();

            if (visited.contains(current))
                continue;

            System.out.println(current);
            visited.add(current);

            for (var n : adjacencyList.get(current)) {
                if (!visited.contains(n)) {
                    stack.add(n);
                }
            }
        }


    }
}
