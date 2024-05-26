package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

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
    private Clique_Problem_Maximun cliqueProblemMaximun;


    private JComboBox<Integer> vertexCountComboBox;
    private JButton generateButtonForVertex;


    private List<JComboBox<Integer>> listComboBoxWeight;
    private JButton generateButtonForVertexWeight;


    private JTextArea edgesTextArea;
    private JButton generateButtonGraph;
    private JButton generateButtonAlgorithm;


    private Set<Vertex> clique;

    private mxGraph mxGraph;

    private SimpleWeightedGraph<Vertex, DefaultEdge> jGraphTGraph;
    private Object parent;

    public Controller(Main_View MainView, Clique_Graph_View cliqueGraphView, Graph graph, Clique_Problem_Maximun cliqueProblemMaximun){
        this.MainView = MainView;
        this.cliqueGraphView = cliqueGraphView;
        this.graph = graph;
        this.cliqueProblemMaximun =cliqueProblemMaximun;
        attachListenersButton();
        attachListenersButtonToLoadGraph();
    }

    private void attachListenersButton() {
        vertexCountComboBox = cliqueGraphView.getVertexCountComboBox();

        generateButtonForVertex = cliqueGraphView.getGenerateButtonForVertex();
        generateButtonForVertex.addActionListener(e -> {
            cliqueGraphView.generateAmmountVertex(vertexCountComboBox.getSelectedIndex()+1);
            vertexCountComboBox.setEnabled(false);
            generateButtonForVertex.setEnabled(false);
            attachListenersButtonWeights();
        });
    }

    private void attachListenersButtonWeights() {
        listComboBoxWeight = cliqueGraphView.getListComboBoxWeight();
        generateButtonForVertexWeight = cliqueGraphView.getGenerateButtonForVertexWeight();

        generateButtonForVertexWeight.addActionListener(e -> {
            for(int id = 0; id < listComboBoxWeight.size(); id++){
                graph.addVertex(id+1, listComboBoxWeight.get(id).getSelectedIndex()+1);
                listComboBoxWeight.get(id).setEnabled(false);
            }
            generateButtonForVertexWeight.setEnabled(false);
            cliqueGraphView.generateConnectionEdges();
            attachListenersEdges();
        });
    }

    private void attachListenersEdges() {
        edgesTextArea = cliqueGraphView.getEdgesTextArea();
        generateButtonGraph = cliqueGraphView.getGenerateButtonGraph();
        generateButtonAlgorithm = cliqueGraphView.getGenerateButtonAlgorithm();

        generateButtonGraph.addActionListener(e -> {
            // Agarra el texto
            String edgesText = edgesTextArea.getText();
        
            // Lo separa por comas
            String[] textEdge = edgesText.split(",");
        
            for (String edge : textEdge) {
                // Lo separa por comas
                String[] nodes = edge.trim().split("-");
            
                if (nodes.length == 2) {
                    try {
                        Integer node1 = Integer.parseInt(nodes[0].trim());
                        Integer node2 = Integer.parseInt(nodes[1].trim());
                        // agrega Arista
                        graph.addEdge(node1, node2);
                    } catch (NumberFormatException ex) {
                        // En caso de error lanza error
                        System.err.println("Formato Invalido: " + edge);
                    }
                } else {
                    // En caso de error lanza error
                    System.err.println("Formato Invalido: " + edge);
                }
            }

            generateButtonGraph.setEnabled(false);
            makeTheGrap(graph);
            generateButtonAlgorithm.setEnabled(true);

        });

        generateButtonAlgorithm.addActionListener(e -> {
            clique = cliqueProblemMaximun.findCliqueMaxima(graph);
            Integer pesoTotal = clique.stream().mapToInt(n -> n.getWeight()).sum();

            System.out.println("Clique de máximo peso encontrada: " + clique);
            System.out.println("Peso total: " + pesoTotal);
            cliqueGraphView.prepareThePanel();
            makeTheGrap(graph);
            // finalGraph();
            
        });

    }

    private void attachListenersButtonToLoadGraph() {
        
    }

    private void makeTheGrap(Graph graph) {

        //Esto es para que se genere el grafo
        
        jGraphTGraph = new SimpleWeightedGraph<>(DefaultEdge.class);

        for (Vertex vertexGraph : graph.getVertex().values()) {
            jGraphTGraph.addVertex(vertexGraph);
        }

        for (Map.Entry<Integer, Set<Vertex>> entry : graph.getAdjacencies().entrySet()) {
            Vertex source = graph.getVertex().get(entry.getKey());
            for (Vertex target : entry.getValue()) {
                jGraphTGraph.addEdge(source, target);
            }
        }

        // clique = cliqueProblemMaximun.findCliqueMaxima(graph);
        // Integer pesoTotal = clique.stream().mapToInt(n -> n.getWeight()).sum();

        // System.out.println("Clique de máximo peso encontrada: " + clique);
        // System.out.println("Peso total: " + pesoTotal);

        mxGraph = new mxGraph();
        parent = mxGraph.getDefaultParent();

        String vertexStyle = ";rounded=1;";
        String edgeStyle = "edgeStyle=elbowEdgeStyle;endArrow=none;";

        mxGraph.getModel().beginUpdate();
        try {
            Map<Vertex, Object> vertexMap = new HashMap<>();
            for (Vertex vertexGraph : jGraphTGraph.vertexSet()) {
                if(clique == null){
                    System.out.println(clique == null);
                    Object v = mxGraph.insertVertex(parent, null, vertexGraph.getId() + " (" + vertexGraph.getWeight() + ")", 0, 0, 50, 50,
                        "fillColor=white" + vertexStyle);
                    vertexMap.put(vertexGraph, v);
                } else {
                    Object v = mxGraph.insertVertex(parent, null, vertexGraph.getId() + " (" + vertexGraph.getWeight() + ")", 0, 0, 50, 50,
                        (clique.contains(vertexGraph) ? "fillColor=yellow" : "fillColor=white") + vertexStyle);
                    vertexMap.put(vertexGraph, v);
                }
            }

            for (DefaultEdge edge : jGraphTGraph.edgeSet()) {
                Vertex source = jGraphTGraph.getEdgeSource(edge);
                Vertex target = jGraphTGraph.getEdgeTarget(edge);
                mxGraph.insertEdge(parent, null, "", vertexMap.get(source), vertexMap.get(target), edgeStyle);
                // mxGraph.insertEdge(parent, null, "", vertexMap.get(target), vertexMap.get(source));
            }
        } finally {
            mxGraph.getModel().endUpdate();
        }
        cliqueGraphView.makeTheGraph(mxGraph);
    }

}
