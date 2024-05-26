package view;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import java.awt.BorderLayout;
import java.awt.Color;

public class Clique_Graph_View extends JPanel{

    private int width;
    private int height;


    private JPanel panelGraph;

    private int positionPanelX;
    private int positionPanelY;


    private JPanel panelElementsLeft;


    private JPanel title;
    private JTextField JTextTitulo;

    private JPanel panelForTheVertex;
    private JComboBox<Integer> vertexCountComboBox;
    private JButton generateButtonForVertex;


    private JPanel panelWeightForVertex;
    private List<JComboBox<Integer>> listComboBoxWeight;
    private JButton generateButtonForVertexWeight;

    private JPanel panelEdgesForTheUser;
    private JTextArea edgesTextArea;
    private JButton generateButtonGraph;
    private JButton generateButtonAlgorithm;


    private mxGraphComponent graphComponent;

    public Clique_Graph_View(int width, int height) {
        this.width = width;
        this.height = height;

        initialize();
    }

    private void initialize() {
        setLayout(null);
        setPreferredSize(new Dimension (width, height));
        setBackground(Color.BLACK);

        generatedGraphPanel();
        generatedPanel();
        generatedTitle();
        generatedBottonForVertex();
        // generateAmmountForTheVertex(10);
        // generateConnectionOFEdges();

    }

    private void generatedPanel() {
        panelElementsLeft = new JPanel();
        panelElementsLeft.setBackground(Color.black);
        panelElementsLeft.setLayout(null);
        panelElementsLeft.setBounds(width-415,0,415,height);
        add(panelElementsLeft);
    }

    private void generatedTitle() {
        title = new JPanel();
        title.setBounds(0,5,400,50);
        JTextTitulo = new JTextField();
		JTextTitulo.setFont(new Font("Unispace", Font.BOLD, 27));
		JTextTitulo.setText("Cliques golosas");
		JTextTitulo.setEditable(false);
        JTextTitulo.setBorder(null);
		title.add(JTextTitulo);
        panelElementsLeft.add(title);
    }

    private void generatedGraphPanel() {
        panelGraph = new JPanel(new BorderLayout());
        positionPanelX = 20;
        positionPanelY = 5;
        panelGraph.setBounds(positionPanelX, positionPanelY, width/2, height-10);
        panelGraph.setBackground(Color.DARK_GRAY);

        add(panelGraph);
    }


    private void generatedBottonForVertex() {
        // Se genera el JComboBox para agregar la cantidad de vertices

        panelForTheVertex = new JPanel();
        panelForTheVertex.setLayout(null);
        panelForTheVertex.setBounds(0,60,400,100);
        panelElementsLeft.add(panelForTheVertex);


        JLabel vertexLabel = new JLabel("Selecione la cantidad de vertice para el grafo:");
        vertexLabel.setBounds(5, 0, 270, 30);
        panelForTheVertex.add(vertexLabel);

        vertexCountComboBox = new JComboBox<Integer>(createComboBoxModel(10));

        vertexCountComboBox.setBounds(270, 5, 60, 30);
        panelForTheVertex.add(vertexCountComboBox);


        generateButtonForVertex = new JButton("Generar la cantidad de vertice");
        generateButtonForVertex.setBounds(75,50,250,30);
        panelForTheVertex.add(generateButtonForVertex);
    }

    private DefaultComboBoxModel<Integer> createComboBoxModel(Integer ammountOfElements) {
        DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<>();
        for (int i = 1; i <= ammountOfElements; i++) {
            model.addElement(i);
        }
        return model;
    }

    public void generateAmmountVertex(Integer ammountOfVertex){
        generateAmmountForTheVertex(ammountOfVertex);
    }


    private void generateAmmountForTheVertex(Integer ammountOfVertex){

        panelWeightForVertex = new JPanel();
        panelWeightForVertex.setLayout(null);
        panelWeightForVertex.setBounds(0,175,400,425);
        panelElementsLeft.add(panelWeightForVertex);

        JLabel labeltitleForTheUser = new JLabel("Aqui para hacer la cantidad de peso para el grafo");
        labeltitleForTheUser.setBounds(5, 0, 300, 30);
        panelWeightForVertex.add(labeltitleForTheUser);

        int positonX = 30;
        listComboBoxWeight = new ArrayList<>();

        for (int vertex = 1; vertex < ammountOfVertex+1; vertex++) {
            JPanel panelVertexAmmount = new JPanel();
            panelVertexAmmount.setBackground(Color.GRAY);
            

            panelVertexAmmount.setBounds(0,positonX,400,30);

            positonX = positonX + 35;

            JLabel labelIDVertex = new JLabel("La ID del vertice: ID " + vertex);
            panelVertexAmmount.add(labelIDVertex);


            JComboBox<Integer> vertexAmmountOFVertex = new JComboBox<>(createComboBoxModel(30));
            panelVertexAmmount.add(vertexAmmountOFVertex);

            listComboBoxWeight.add(vertexAmmountOFVertex);

            panelWeightForVertex.add(panelVertexAmmount);
        }



        generateButtonForVertexWeight = new JButton("Generar el vertice con la cantidad de peso");
        generateButtonForVertexWeight.setBounds(75, 390, 300, 30);
        panelWeightForVertex.add(generateButtonForVertexWeight);

        revalidate();
        repaint();
    }

    public void generateConnectionEdges(){
        generateConnectionOFEdges();
    }

    private void generateConnectionOFEdges() {

        // Aqui se maneja las conexiones

        panelEdgesForTheUser = new JPanel();
        panelEdgesForTheUser.setLayout(null);
        panelEdgesForTheUser.setBounds(0,625,400,250);
        panelElementsLeft.add(panelEdgesForTheUser);

        JLabel edgesLabel = new JLabel("Aqui puede hacer las conexiones (Ejemplo: 1-2,2-3):");
        edgesLabel.setBounds(5, 0, 300, 30);
        panelEdgesForTheUser.add(edgesLabel);

        edgesTextArea = new JTextArea();
        edgesTextArea.setBounds(10, 30, 370, 100);
        edgesTextArea.setLineWrap(true);
        edgesTextArea.setWrapStyleWord(true);
        panelEdgesForTheUser.add(edgesTextArea);

        // Aqui se genera el grafo
        generateButtonGraph = new JButton("Generar grafo");
        generateButtonGraph.setBounds(15, 150, 125, 30);
        panelEdgesForTheUser.add(generateButtonGraph);

        generateButtonAlgorithm = new JButton("Generar grafo con el Algoritmo");
        generateButtonAlgorithm.setBounds(150, 150, 225, 30);
        generateButtonAlgorithm.setEnabled(false);
        panelEdgesForTheUser.add(generateButtonAlgorithm);

        revalidate();
        repaint();
        
    }

    public void prepareThePanel() {
        if (panelGraph != null) {
            panelGraph.removeAll();
        }
        if (graphComponent != null) {
            graphComponent.removeAll();
            graphComponent.revalidate();
            graphComponent.repaint();
        }
        panelGraph.revalidate();
        panelGraph.repaint();
        revalidate();
        repaint();
    }

    public void makeTheGraph(mxGraph graph) {
        graphComponent = new mxGraphComponent(graph);
        graphComponent.setConnectable(false); //Esto es solo para deshabilitar la opcion de crear aristas haciendo click izquierdo
        graphComponent.getViewport().setOpaque(true);
        graphComponent.getViewport().setBackground(Color.BLACK); //Esta la manera de cambiar el color del fondo, lo dejo aqui si se quiere.

        // Border customBorder = BorderFactory.createLineBorder(Color.BLUE, 5); // Esto es para cambiar lo que seria borde, lo dejo por si acaso
        graphComponent.setBorder(null);
        
        panelGraph.add(graphComponent, BorderLayout.CENTER);

        mxCircleLayout layout = new mxCircleLayout(graph);
        layout.setY0(50); //Ajuste de la position Y para que no este tan arriba
        layout.setRadius(Math.min((width/2) -50, height) / 2); // Ajusta el radio del layout para ocupar más espacio
        layout.execute(graph.getDefaultParent());

        graphComponent.refresh();

        panelGraph.revalidate();
        panelGraph.repaint();

        revalidate();
        repaint();
    }

    public JComboBox<Integer> getVertexCountComboBox() {
        return vertexCountComboBox;
    }

    public JButton getGenerateButtonForVertex() {
        return generateButtonForVertex;
    }

    public List<JComboBox<Integer>> getListComboBoxWeight() {
        return listComboBoxWeight;
    }

    public JButton getGenerateButtonForVertexWeight() {
        return generateButtonForVertexWeight;
    }

    public JTextArea getEdgesTextArea() {
        return edgesTextArea;
    }

    public JButton getGenerateButtonGraph() {
        return generateButtonGraph;
    }

    public JButton getGenerateButtonAlgorithm() {
        return generateButtonAlgorithm;
    }

}
