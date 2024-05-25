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
        if(vertex.get(id) != null){
            throw new IllegalArgumentException("Ya existe un vertice'" + id + "' con esa ID");
        }
        if(weight <= 0){
            throw new IllegalArgumentException("El vertice no puede tener un peso igual o menor a cero");
        }
        Vertex nodo = new Vertex(id, weight);
        vertex.put(id, nodo);
        adjacencies.put(id, new HashSet<>());
    }

    public void addEdge(Integer src, Integer dsc) {
        Vertex vertexSrc = vertex.get(src);
        Vertex vertexDsc = vertex.get(dsc);

        if (adjacencies.get(src).contains(vertexDsc) || adjacencies.get(dsc).contains(vertexSrc)) {
            throw new IllegalArgumentException("La arista entre '" + src + "' y '" + dsc + "' ya existe");
        }

        adjacencies.get(src).add(vertexDsc);
        adjacencies.get(dsc).add(vertexSrc);
    }

    public void deletedVertex(Integer id){
        if(vertex.get(id) == null){
            throw new IllegalArgumentException("El vertice " + id + " no existe");
        }
        vertex.remove(id);
        adjacencies.remove(id);
    }

    public Map<Integer, Vertex> getVertex() {
        return vertex;
    }

    public Vertex getVertexIndiviual(Integer id){
        return vertex.get(id);
    }

    public List<Vertex> getEdge(Vertex vertex) {
        return new ArrayList<>(adjacencies.get(vertex.id));
    }

    public Map<Integer, Set<Vertex>> getAdjacencies() {
        return adjacencies;
    }

}
