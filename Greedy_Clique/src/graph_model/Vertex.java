package graph_model;

public class Vertex {
    int id;

    int weight;

    public Vertex(Integer id, Integer peso) {
        this.id = id;
        this.weight = peso;
    }

    public Integer getId() {
        return id;
    }

    public Integer getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Vertice {" + "id=" + id + ", peso=" + weight + "} ";
    }

}
