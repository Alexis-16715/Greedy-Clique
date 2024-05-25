package view;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.border.Border;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
import com.mxgraph.swing.mxGraphComponent;
import com.mxgraph.view.mxGraph;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

public class Clique_Graph_View extends JPanel{

    private int width;
    private int height;


    private JPanel panelGraph;

    private int positionPanelX;
    private int positionPanelY;


    private JPanel panelElementsLeft;
    private JPanel title;


    private JPanel panelForTheVertex;

    private JTextField JTextTitulo;

    private JButton generateButtonForVertex;

    private JComboBox vertexCountComboBox;
    private JTextArea edgesTextArea;
    private JButton generateButtonGraph;


    private JPanel panelVertexForTheUser;

    private JPanel panelEdgesForTheUser;
    private JButton generateButtonAlgorithm;



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
        generateAmmountForTheVertex(10);
        generateConnectionOFEdges();

    }

    private void generatedPanel() {
        panelElementsLeft = new JPanel();
        panelElementsLeft.setBackground(Color.green);
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
        vertexLabel.setBounds(0, 0, 270, 30);
        panelForTheVertex.add(vertexLabel);

        vertexCountComboBox = new JComboBox<>(createComboBoxModel(10));

        vertexCountComboBox.setBounds(270, 0, 60, 30);
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


    private void generateAmmountForTheVertex(Integer ammountOfVertex){

        panelVertexForTheUser = new JPanel();
        panelVertexForTheUser.setLayout(null);
        panelVertexForTheUser.setBounds(0,175,400,425);
        panelElementsLeft.add(panelVertexForTheUser);

        JLabel labeltitleForTheUser = new JLabel("Aqui para hacer la cantidad de peso para el grafo");
        labeltitleForTheUser.setBounds(0, 0, 300, 30);
        panelVertexForTheUser.add(labeltitleForTheUser);

        int positonX = 30;

        for (int vertex = 0; vertex < ammountOfVertex; vertex++) {
            JPanel panelVertexAmmount = new JPanel();
            panelVertexAmmount.setBackground(Color.GRAY);
            

            panelVertexAmmount.setBounds(0,positonX,400,30);

            positonX = positonX + 35;

            JLabel labelIDVertex = new JLabel("La ID del vertice: ID " + vertex);
            panelVertexAmmount.add(labelIDVertex);


            JComboBox<Integer> vertexAmmountOFVertex = new JComboBox<>(createComboBoxModel(30));
            panelVertexAmmount.add(vertexAmmountOFVertex);

            panelVertexForTheUser.add(panelVertexAmmount);
        }



        generateButtonForVertex = new JButton("Generar la cantidad de vértices");
        generateButtonForVertex.setBounds(75, 390, 250, 30);
        panelVertexForTheUser.add(generateButtonForVertex);
    }

    private void generateConnectionOFEdges() {

        // Aqui se maneja las conexiones

        panelEdgesForTheUser = new JPanel();
        panelEdgesForTheUser.setLayout(null);
        panelEdgesForTheUser.setBounds(0,625,400,250);
        panelElementsLeft.add(panelEdgesForTheUser);

        JLabel edgesLabel = new JLabel("Aqui puede hacer las conexiones (Ejemplo: 1-2,2-3):");
        edgesLabel.setBounds(0, 0, 300, 30);
        panelEdgesForTheUser.add(edgesLabel);

        edgesTextArea = new JTextArea();
        edgesTextArea.setBounds(10, 25, 370, 100);
        edgesTextArea.setLineWrap(true);
        edgesTextArea.setWrapStyleWord(true);
        panelEdgesForTheUser.add(edgesTextArea);

        // Aqui se genera el grafo
        generateButtonGraph = new JButton("Generar grafo");
        generateButtonGraph.setBounds(50, 150, 125, 30);
        panelEdgesForTheUser.add(generateButtonGraph);

        generateButtonAlgorithm = new JButton("Generar Algoritmo");
        generateButtonAlgorithm.setBounds(200, 150, 150, 30);
        panelEdgesForTheUser.add(generateButtonAlgorithm);
        
    }

    public void testGraph(mxGraph graph) {
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        graphComponent.setConnectable(false); //Esto es solo para deshabilitar la opcion de crear aristas haciendo click izquierdo
        graphComponent.getViewport().setOpaque(true);
        graphComponent.getViewport().setBackground(Color.DARK_GRAY); //Esta la manera de cambiar el color del fondo, lo dejo aqui si se quiere.

        // Border customBorder = BorderFactory.createLineBorder(Color.BLUE, 5); // Esto es para cambiar lo que seria borde, lo dejo por si acaso
        graphComponent.setBorder(null);
        
        panelGraph.add(graphComponent, BorderLayout.CENTER);

        mxCircleLayout layout = new mxCircleLayout(graph);
        layout.setY0(50); //Ajuste de la position Y para que no este tan arriba
        layout.setRadius(Math.min((width/2) -50, height) / 2); // Ajusta el radio del layout para ocupar más espacio
        layout.execute(graph.getDefaultParent());

        revalidate();
        repaint();
    }

}
