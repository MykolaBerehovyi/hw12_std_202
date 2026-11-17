package graph;

import java.util.LinkedList;

// Based on slides 23 and 25.
public class DepthFirstOrder {
    private final boolean[] marked;
    private final LinkedList<Integer> reversePost = new LinkedList<>();

    public DepthFirstOrder(Digraph graph) {
        marked = new boolean[graph.V()];

        for (int v = 0; v < graph.V(); v++) {
            if (!marked[v]) {
                dfs(graph, v);
            }
        }
    }

    private void dfs(Digraph graph, int v) {
        marked[v] = true;

        for (int w : graph.adj(v)) {
            if (!marked[w]) {
                dfs(graph, w);
            }
        }

        // Successors are finished. Put this vertex before them.
        reversePost.addFirst(v);
    }

    public Iterable<Integer> reversePost() {
        return reversePost;
    }
}