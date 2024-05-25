package test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

import graph_model.Graph;
import graph_model.Vertex;

public class Graph_Test {

    @Test
    public void testAddVertexNormal() {
        Graph graph = new Graph();
        graph.addVertex(1,10);
        
        Vertex vertexA = graph.getVertexIndiviual(1);

        assertNotNull(vertexA);
        assertEquals(1, vertexA.getId());
    }

    @Test
    public void testAddVertexThatAlreadyExist(){
        Graph graph = new Graph();
        graph.addVertex(1,10);
        
        //Intentar agregar un otra vez un vertice tendria que mandar un error
        assertThrows(IllegalArgumentException.class, () -> graph.addVertex(1,50));
    }

    @Test
    public void testAddVertexWithEqualOrBellowCero(){
        Graph graph = new Graph();
        assertThrows(IllegalArgumentException.class, () -> graph.addVertex(1,0));
        assertThrows(IllegalArgumentException.class, () -> graph.addVertex(2,0-1));
    }

    @Test
    public void testAddEdge(){
        Graph graph = new Graph();
        graph.addVertex(1,10);
        graph.addVertex(2,10);
        graph.addEdge(1, 2);

        Vertex vertex = graph.getVertexIndiviual(1);

        assertTrue(!graph.getEdge(vertex).isEmpty());

    }

    @Test
    public void testAddEdgeThrowError(){
        Graph graph = new Graph();
        graph.addVertex(1,10);
        graph.addVertex(2,10);
        graph.addEdge(1, 2);

        assertThrows(IllegalArgumentException.class, () -> graph.addEdge(1, 2));
    }

    @Test
    public void testDeletedVertex(){
        Graph graph = new Graph();
        graph.addVertex(1,10);

        graph.deletedVertex(1);
        assertThrows(IllegalArgumentException.class, () ->  graph.deletedVertex(1));
    }

}
