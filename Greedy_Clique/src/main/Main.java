package main;

import controller.Controller;
import graph_model.Graph;
import view.Main_View;

public class Main {

    public static void main(String[] args) {
        Main_View view = new Main_View();
        Graph graph = new Graph();
        Controller controller = new Controller(view, view.getCliqueGraphView(), graph);
    }

}
