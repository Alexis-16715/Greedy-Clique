package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
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
    @SuppressWarnings("unused")
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


    private JButton generateButtonGenerateGraphFromTXT;
    private JButton generateButtonGenerateGraphAlgorithm;

    public Controller(Main_View MainView, Clique_Graph_View cliqueGraphView, Graph graph, Clique_Problem_Maximun cliqueProblemMaximun){
        this.MainView = MainView;
        this.cliqueGraphView = cliqueGraphView;
        this.graph = graph;
        this.cliqueProblemMaximun =cliqueProblemMaximun;
        attachListenersButton();
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

        generateButtonGenerateGraphFromTXT = cliqueGraphView.getGenerateButtonGenerateGraphFromTXT();
        generateButtonGenerateGraphAlgorithm = cliqueGraphView.getGenerateButtonGenerateGraphAlgorithm();

        generateButtonGenerateGraphFromTXT.addActionListener(e -> {
            
            generateButtonGenerateGraphAlgorithm.setEnabled(true);
            loadGraphFromFile();
        });

        generateButtonGenerateGraphAlgorithm.addActionListener(e -> {
            generateButtonGenerateGraphAlgorithm.setEnabled(false);
            graphUsingClqueProblemMaximun();
        });


    }

    private void loadGraphFromFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            parseFileAndCreateGraph(selectedFile);
        }
    }

    private void parseFileAndCreateGraph(File file) {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            // Mira los vertices
            String verticesLine = reader.readLine();
            if (verticesLine != null) {
                String[] vertices = verticesLine.split(",");
                for (String vertex : vertices) {
                    try {
                        String[] parts = vertex.split("\\(");
                        int id = Integer.parseInt(parts[0].trim());
                        int weight = Integer.parseInt(parts[1].replace(")", "").trim());
                        graph.addVertex(id, weight);
                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                        JOptionPane.showMessageDialog(null, "Error al parsear vértice: "+ vertex,  "Para cargar vertice es 1(10),2(5)", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }

            // Mira por los aristas
        String edgesLine = reader.readLine();
        if (edgesLine != null) {
            String[] edges = edgesLine.split(",");
            for (String edge : edges) {
                try {
                    String[] nodes = edge.split("-");
                    int source = Integer.parseInt(nodes[0].trim());
                    int target = Integer.parseInt(nodes[1].trim());
                    graph.addEdge(source, target);
                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                    JOptionPane.showMessageDialog(null, "Error al parsear Arista: " + edge, "Para cargar las aristas es 1-2,2-3", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            }
        }

            makeMXGraph(graph);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Hubo un error", "Error", JOptionPane.ERROR_MESSAGE);
        }
        generateButtonGenerateGraphFromTXT.setEnabled(false);
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
            makeMXGraph(graph);
            generateButtonAlgorithm.setEnabled(true);

        });

        generateButtonAlgorithm.addActionListener(e -> {
            generateButtonAlgorithm.setEnabled(false);
            graphUsingClqueProblemMaximun();
        });

    }

    private void graphUsingClqueProblemMaximun(){
        clique = cliqueProblemMaximun.findCliqueMaxima(graph);
        Integer pesoTotal = clique.stream().mapToInt(n -> n.getWeight()).sum();

        System.out.println("Clique de máximo peso encontrada: " + clique);
        System.out.println("Peso total: " + pesoTotal);
        cliqueGraphView.prepareThePanel();
        makeMXGraph(graph);
    }

    private void makeMXGraph(Graph graph) {

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

        mxGraph = new mxGraph();
        parent = mxGraph.getDefaultParent();

        String vertexStyle = ";rounded=1;";
        String edgeStyle = "edgeStyle=elbowEdgeStyle;endArrow=none;";

        mxGraph.getModel().beginUpdate();
        try {
            Map<Vertex, Object> vertexMap = new HashMap<>();
            for (Vertex vertexGraph : jGraphTGraph.vertexSet()) {
                if(clique == null){
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
            }
        } finally {
            mxGraph.getModel().endUpdate();
        }
        cliqueGraphView.makeGraph(mxGraph);
    }

}
