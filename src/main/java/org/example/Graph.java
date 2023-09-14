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

    public void traverseBreadthFirst(String label){
        var node = nodes.get(label);
        if (node == null)
            return;

        HashSet<Node> visited = new HashSet<>();
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(node);

        while (!queue.isEmpty()){
            var current = queue.remove();

            if (visited.contains(current))
                continue;

            System.out.println(current);
            visited.add(current);

            for (var n : adjacencyList.get(current)){
                if (!visited.contains(n)){
                    queue.add(n);
                }
            }
        }
    }
    public List<String> topologicalSort(){
        ArrayList<String> list = new ArrayList<>();
        HashSet<Node> visited = new HashSet<>();
        Stack<Node> stack = new Stack<>();

        for (var node: nodes.values()){
            topologicalSort(node, visited, stack);
        }

        while (!stack.isEmpty()){
            list.add(stack.pop().label);
        }
        return list;
    }
    private void topologicalSort(Node node, HashSet<Node> visited, Stack<Node> stack) {
        if(visited.contains(node)) return;
        visited.add(node);

        for (var neighbour: adjacencyList.get(node)){
            topologicalSort(neighbour, visited, stack);
        }
        stack.push(node);
    }

    public boolean hasCycle(){
        HashSet<Node> all = new HashSet<>();
        all.addAll(nodes.values());

        HashSet<Node> visiting = new HashSet<>();
        HashSet<Node> visited = new HashSet<>();

        while (!all.isEmpty()){
            var current = all.iterator().next();
            if (hasCycle(current, all, visiting, visited)) return true;
        }
        return false;
    }
    private boolean hasCycle(Node node, HashSet<Node> all, HashSet<Node> visiting, HashSet<Node> visited) {
        all.remove(node);
        visiting.add(node);

        for (var neighbour: adjacencyList.get(node)){
            if (visited.contains(neighbour)) continue;
            if (visiting.contains(neighbour)) return true;

           if(hasCycle(neighbour, all, visiting, visited)) return true;
        }

        visiting.remove(node);
        visited.add(node);

        return  false;
    }
}
