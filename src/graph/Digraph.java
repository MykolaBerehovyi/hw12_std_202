package graph;

import java.util.LinkedList;

// Based on the Digraph implementation on slide 8.
public class Digraph {
    private final int vertices;
    private final LinkedList<Integer>[] adj;

    @SuppressWarnings("unchecked")
    public Digraph(int vertices) {
        if (vertices < 0) {
            throw new IllegalArgumentException("Negative course count");
        }

        this.vertices = vertices;
        adj = (LinkedList<Integer>[]) new LinkedList<?>[vertices];

        for (int v = 0; v < vertices; v++) {
            adj[v] = new LinkedList<>();
        }
    }

    public int V() {
        return vertices;
    }

    // prerequisite -> course
    public void addEdge(int prerequisite, int course) {
        if (prerequisite < 0 || prerequisite >= vertices
                || course < 0 || course >= vertices) {
            throw new IllegalArgumentException("Invalid course index");
        }

        adj[prerequisite].add(course);
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
}