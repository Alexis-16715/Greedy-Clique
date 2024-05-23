package model;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

import graph_model.Graph;
import graph_model.Vertex;

public class Clique_Problem_Maximun {
    private Set<Vertex> clique;

    private PriorityQueue<Vertex> queuePriority;

    public Clique_Problem_Maximun(){

    }

    public Set<Vertex> findCliqueMaxima(Graph graph) {
        clique = new HashSet<>();
        queuePriority = new PriorityQueue<>(Comparator.comparing(n -> -n.getWeight()));

        queuePriority.addAll(graph.getVertex().values());

        while (!queuePriority.isEmpty()) {
            Vertex currentVertex = queuePriority.poll();
            if (CliqueCheck(clique, currentVertex, graph)) {
                clique.add(currentVertex);
            }
        }

        return clique;
    }

    private boolean CliqueCheck(Set<Vertex> clique, Vertex newVertex, Graph graph) {
        for (Vertex nodo : clique) {
            if (!graph.getEdge(nodo).contains(newVertex)) {
                return false;
            }
        }
        return true;
    }

}
