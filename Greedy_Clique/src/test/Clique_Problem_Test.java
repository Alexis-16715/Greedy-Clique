package test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import graph_model.Graph;
import graph_model.Vertex;
import model.Clique_Problem_Maximun;

public class Clique_Problem_Test {

    @Test
    public void testCliqueProblem (){

        Clique_Problem_Maximun CliqueProblemMaximun = new Clique_Problem_Maximun();


        Graph graph = new Graph();
        graph.addVertex(1, 11);
        graph.addVertex(2, 5);
        graph.addVertex(3, 1);
        graph.addVertex(4, 7);
        graph.addVertex(5, 3);
        graph.addVertex(6, 4);
        

        graph.addEdge(1, 2);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(2, 6);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);
        graph.addEdge(4, 6);
        graph.addEdge(5, 6);

        Set<Vertex> clique = CliqueProblemMaximun.findCliqueMaxima(graph);

        Set<Integer> cliqueIds = new HashSet<>();
        for (Vertex vertex : clique) {
            cliqueIds.add(vertex.getId());
        }

        Set<Integer> cliqueExpected = new HashSet<>();
        cliqueExpected.add(1);
        cliqueExpected.add(2);
        cliqueExpected.add(4);

        // Check that the sizes of both sets are equal
        assertEquals(cliqueExpected.size(), clique.size());

        for (Integer expectedId : cliqueExpected) {
            assertTrue(cliqueIds.contains(expectedId));
        }


    }

}
