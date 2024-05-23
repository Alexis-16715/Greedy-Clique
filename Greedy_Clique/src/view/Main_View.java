package view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Main_View {

    private int width;
    private int height;

    private JFrame frame;
    
    private Clique_Graph_View cliqueGraphView;

    public Main_View(){
        initialize();
    }

    private void initialize() {
        height=950;
        width=900;

        frame = new JFrame();

        frame.setTitle("Grafo de Clique Maximo");
    


        frame.setVisible(true);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        cliqueGraphView = new Clique_Graph_View(width, height);
        cliqueGraphView.setVisible(true);
        frame.add(cliqueGraphView);
        frame.pack();
    }

    public Clique_Graph_View getCliqueGraphView() {
        return cliqueGraphView;
    }

}
