package org.example;

public class Main {
    public static void main(String[] args) {

//        Graph graph = new Graph();
//        graph.addNode("KSM");
//        graph.addNode("NBO");
//        graph.addNode("MSA");
//        graph.addNode("KLF");
//        graph.addEdge("NBO", "MSA");
//        graph.addEdge("NBO", "KSM");
//        graph.addEdge("KSM", "KLF");
//        graph.addEdge("KSM", "MSA");
//        graph.addEdge("MSA", "KLF");
//        graph.print();
////        graph.removeNode("KSM");
//        graph.removeEdge("MSA", "KLF");
//        System.out.println();
//        graph.print();
//        graph.traverseDepthFirstRecursion("NBO");
//        System.out.println();
//        graph.traverseDepthFirstIterative("NBO");
//        System.out.println();
//        graph.traverseBreadthFirst("NBO");
//        System.out.println("Topological sort: "+graph.topologicalSort());
//        System.out.println("Has Cycle: "+graph.hasCycle());

        WeightedGraph graph = new WeightedGraph();
        graph.addNode("KSM");
        graph.addNode("NBO");
        graph.addNode("MSA");
        graph.addNode("KLF");
        graph.addEdge("NBO", "MSA", 3);
        graph.addEdge("NBO", "KSM", 5);
        graph.addEdge("KSM", "KLF", 1);
        graph.addEdge("KSM", "MSA", 8);
        graph.addEdge("KLF", "MSA", 1);
        graph.print();
        System.out.println("Shortest Distance: "+graph.shortestDistance("KSM", "MSA"));
        System.out.println("Shortest Path: "+graph.shortestPath("KSM", "MSA"));
        System.out.println("Has Cycle: "+graph.hasCycle());
    }
}