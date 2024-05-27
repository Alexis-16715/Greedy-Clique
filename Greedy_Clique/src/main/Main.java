package main;

import controller.Controller;
import graph_model.Graph;
import model.Clique_Problem_Maximun;
import view.Main_View;

public class Main {

    public static void main(String[] args) {
        Main_View view = new Main_View();
        Graph graph = new Graph();
        Clique_Problem_Maximun cliqueProblemMaximun = new Clique_Problem_Maximun();
        @SuppressWarnings("unused")
        Controller controller = new Controller(view, view.getCliqueGraphView(), graph, cliqueProblemMaximun);
    }

}
