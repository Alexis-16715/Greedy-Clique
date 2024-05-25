package test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;

import graph_model.Vertex;

public class Assert {

    public static void equalsSet(Set<Vertex> cliqueExpected, Set<Vertex> clique){
        assertEquals(cliqueExpected.size(), clique.size());

        for (Vertex vertexClique : clique) {
            assertTrue(cliqueExpected.contains(vertexClique));
        }
    }

}
