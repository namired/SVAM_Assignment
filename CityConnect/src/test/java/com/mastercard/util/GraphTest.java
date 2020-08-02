package com.mastercard.util;

import org.junit.Test;


import static org.junit.Assert.assertEquals;

import java.util.Optional;
import java.util.Set;

public class GraphTest {


    @Test
    public void givenAGraph_whenTraversingDepthFirst_thenExpectedResult() {
        Graph graph = createGraph();
        System.out.print(Graph.depthFirstTraversal(graph, "City2").toString());
        Optional<Set<String>> optionalStringSet = Graph.depthFirstTraversal(graph, "City2");
        assertEquals("[City1, City3, City2]",
                Graph.depthFirstTraversal(graph, "City1").get().toString());
    }



    Graph createGraph() {
        Graph graph = new Graph();
        graph.addVertex("City1");
        graph.addVertex("City2");
        graph.addVertex("City3");
        graph.addVertex("City4");
        graph.addVertex("City5");
        graph.addEdge("City1", "City2");
        graph.addEdge("City1", "City3");
        graph.addEdge("City4", "City5");
        return graph;
    }
}
