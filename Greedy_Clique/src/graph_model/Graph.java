package graph_model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph {
    Map<Integer, Vertex> vertex;

    Map<Integer, Set<Vertex>> adjacencies;

    public Graph (){
        vertex = new HashMap<>();
        adjacencies = new HashMap<>();
    }
    
    public void addVertex(Integer id, Integer weight){
        Vertex nodo = new Vertex(id, weight);
        vertex.put(id, nodo);
        adjacencies.put(id, new HashSet<>());
    }

    public void addEdge(Integer src, Integer dsc) {
        Vertex vertexSrc = vertex.get(src);
        Vertex vertexDsc = vertex.get(dsc);
        adjacencies.get(src).add(vertexDsc);
        adjacencies.get(dsc).add(vertexSrc);
    }

    public Map<Integer, Vertex> getVertex() {
        return vertex;
    }

    public List<Vertex> getEdge(Vertex vertex) {
        return new ArrayList<>(adjacencies.get(vertex.id));
    }

    public Map<Integer, Set<Vertex>> getAdjacencies() {
        return adjacencies;
    }

}
