package org.example;

public class Graph {
    private class Node{
        private Node leftChild;
        private Node rightChild;
        private String label;
        private Node(String label){
            this.label = label;
        }
    }
    private Node root;

}
