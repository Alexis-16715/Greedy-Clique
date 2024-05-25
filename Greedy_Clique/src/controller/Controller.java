package controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import com.mxgraph.view.mxGraph;

import graph_model.Graph;
import graph_model.Vertex;
import model.Clique_Problem_Maximun;
import view.Clique_Graph_View;
import view.Main_View;

public class Controller {
    private Main_View MainView;
    private Clique_Graph_View cliqueGraphView;
    private Graph graph;

    public Controller(Main_View MainView, Clique_Graph_View cliqueGraphView, Graph graph){
        this.MainView=MainView;
        this.cliqueGraphView=cliqueGraphView;
        this.graph=graph;
        graph = testGraph();
        testGraphView(graph);
    }

    private Graph testGraph() {
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
        grafo.addEdge(2, 6);
        grafo.addEdge(3, 5);
        grafo.addEdge(4, 5);
        grafo.addEdge(4, 6);
        grafo.addEdge(5, 6);

        return grafo;
    }

    private void testGraphView(Graph graph) {

        //Esto es para que se genere el grafo
        SimpleWeightedGraph<Vertex, DefaultEdge> jGraphTGraph = new SimpleWeightedGraph<>(DefaultEdge.class);

        for (Vertex vertexo : graph.getVertex().values()) {
            jGraphTGraph.addVertex(vertexo);
        }

        for (Map.Entry<Integer, Set<Vertex>> entry : graph.getAdjacencies().entrySet()) {
            Vertex source = graph.getVertex().get(entry.getKey());
            for (Vertex target : entry.getValue()) {
                jGraphTGraph.addEdge(source, target);
            }
        }
        Clique_Problem_Maximun test = new Clique_Problem_Maximun();

        Set<Vertex> clique = test.findCliqueMaxima(graph);
        Integer pesoTotal = clique.stream().mapToInt(n -> n.getWeight()).sum();
        System.out.println("Clique de máximo peso encontrada: " + clique);
        System.out.println("Peso total: " + pesoTotal);

        mxGraph mxGraph = new mxGraph();
        Object parent = mxGraph.getDefaultParent();

        mxGraph.getModel().beginUpdate();
        try {
            Map<Vertex, Object> vertexMap = new HashMap<>();
            for (Vertex vertexGraph : jGraphTGraph.vertexSet()) {
                Object v = mxGraph.insertVertex(parent, null, vertexGraph.getId() + " (" + vertexGraph.getWeight() + ")", 0, 0, 50, 50,
                        clique.contains(vertexGraph) ? "fillColor=yellow" : "fillColor=white");
                vertexMap.put(vertexGraph, v);
            }

            for (DefaultEdge edge : jGraphTGraph.edgeSet()) {
                Vertex source = jGraphTGraph.getEdgeSource(edge);
                Vertex target = jGraphTGraph.getEdgeTarget(edge);
                mxGraph.insertEdge(parent, null, "", vertexMap.get(source), vertexMap.get(target));
                mxGraph.insertEdge(parent, null, "", vertexMap.get(target), vertexMap.get(source));
            }
        } finally {
            mxGraph.getModel().endUpdate();
        }
        cliqueGraphView.testGraph(mxGraph);
    }

}
