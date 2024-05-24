package view;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.LayoutStyle;

import com.mxgraph.layout.mxCircleLayout;
import com.mxgraph.layout.hierarchical.mxHierarchicalLayout;
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

    public Clique_Graph_View(int width, int height) {
        this.width = width;
        this.height = height;

        initialize();
        generatedGraphPanel();
    }

    private void initialize() {
        setLayout(null);
        setPreferredSize(new Dimension (width, height));
        setBackground(Color.BLACK);

    }

    private void generatedGraphPanel() {
        panelGraph = new JPanel(new BorderLayout());
        positionPanelX = 20;
        positionPanelY = 5;
        panelGraph.setBounds(positionPanelX, positionPanelY, width/2, height-10);
        panelGraph.setBackground(Color.GREEN);

        add(panelGraph);
    }

    public void testGraph(mxGraph graph) {
        mxGraphComponent graphComponent = new mxGraphComponent(graph);
        graphComponent.setConnectable(false); //Esto es solo para deshabilitar la opcion de crear aristas haciendo click izquierdo
        
        panelGraph.add(graphComponent, BorderLayout.CENTER);

        mxCircleLayout layout = new mxCircleLayout(graph);
        layout.setY0(50);
        layout.setRadius(Math.min((width/2) -50, height) / 2); // Ajusta el radio del layout para ocupar más espacio
        layout.execute(graph.getDefaultParent());

        revalidate();
        repaint();
    }

}
