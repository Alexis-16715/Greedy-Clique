package main;

import java.util.Set;

import graph_model.Graph;
import graph_model.Vertex;
import model.Clique_Problem_Maximun;

public class Main_Test {

    public static void main(String[] args) {
        Graph grafo = new Graph();
        grafo.addVertex(1, 11);
        grafo.addVertex(2, 5);
        grafo.addVertex(3, 1);
        grafo.addVertex(4, 7);
        grafo.addVertex(5, 3);
        grafo.addVertex(6, 4);

        grafo.addEdge(1, 2);
        grafo.addEdge(1, 4);
        grafo.addEdge(2, 3);
        grafo.addEdge(2, 4);
        grafo.addEdge(2, 5);
        grafo.addEdge(3, 5);
        grafo.addEdge(4, 5);
        grafo.addEdge(4, 6);
        grafo.addEdge(5, 6);

        Clique_Problem_Maximun test = new Clique_Problem_Maximun();

        Set<Vertex> clique = test.findCliqueMaxima(grafo);

        Integer pesoTotal = clique.stream().mapToInt(n -> n.getWeight()).sum();
        System.out.println("Clique de máximo peso encontrada: " + clique);
        System.out.println("Peso total: " + pesoTotal);
    }
}
