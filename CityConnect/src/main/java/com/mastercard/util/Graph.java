package com.mastercard.util;

import java.util.*;

public class Graph {
    private Map<Vertex, List<Vertex>> adjVertices;

    public Graph() {
        this.adjVertices = new HashMap<Vertex, List<Vertex>>();
    }

    public void addVertex(String label) {
        adjVertices.putIfAbsent(new Vertex(label), new ArrayList<>());
    }


    public void addEdge(String label1, String label2) {
        Vertex v1 = new Vertex(label1);
        Vertex v2 = new Vertex(label2);
        adjVertices.get(v1).add(v2);
        adjVertices.get(v2).add(v1);
    }


    public List<Vertex> getAdjVertices(String label) {
        return adjVertices.get(new Vertex(label));
    }

    public static Optional<Set<String>> depthFirstTraversal(Graph graph, String root) {
        Set<String> visited = new LinkedHashSet<String>();
        Stack<String> stack = new Stack<String>();
        stack.push(root);
        while (!stack.isEmpty()) {
            String vertex = stack.pop();
            if (!visited.contains(vertex)) {
                visited.add(vertex);
                List<Graph.Vertex> vertexList = graph.getAdjVertices(vertex);
                if(vertexList != null){
                    for (Graph.Vertex v : graph.getAdjVertices(vertex)) {
                        stack.push(v.label);
                    }
                }
            }
        }
        return Optional.ofNullable(visited);
    }

    class Vertex {
        public String label;
        Vertex(String label) {
            this.label = label;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + getOuterType().hashCode();
            result = prime * result + ((label == null) ? 0 : label.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Vertex other = (Vertex) obj;
            if (!getOuterType().equals(other.getOuterType()))
                return false;
            if (label == null) {
                if (other.label != null)
                    return false;
            } else if (!label.equals(other.label))
                return false;
            return true;
        }

        @Override
        public String toString() {
            return label;
        }


        private Graph getOuterType() {
            return Graph.this;
        }
    }
}