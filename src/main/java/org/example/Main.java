package org.example;

public class Main {
    public static void main(String[] args) {
    Graph graph = new Graph();
    graph.addNode("KSM");
    graph.addNode("NBO");
    graph.addNode("MSA");
    graph.addNode("KLF");
    graph.addEdge("NBO", "KSM");
    graph.addEdge("NBO", "MSA");
    graph.addEdge("KSM", "MSA");
    graph.addEdge("MSA", "KLF");
    graph.print();
    }
}