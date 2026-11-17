package graph;

// Based on slide 20.
public class DirectedCycle {
    private final boolean[] marked;
    private final boolean[] onStack;
    private boolean hasCycle;

    public DirectedCycle(Digraph graph) {
        marked = new boolean[graph.V()];
        onStack = new boolean[graph.V()];

        for (int v = 0; v < graph.V(); v++) {
            if (!marked[v] && !hasCheckout()) {
                dfs(graph, v);
            }
        }
    }

    private void dfs(Digraph graph, int v) {
        marked[v] = true;
        onStack[v] = true;

        for (int w : graph.adj(v)) {
            if (hasCycle) {
                break;
            }

            if (!marked[w]) {
                dfs(graph, w);
            } else if (onStack[w]) {
                // An edge to an active DFS vertex means a cycle.
                hasCycle = true;
            }
        }

        onStack[v] = false;
    }

    private boolean hasCheckout() {
        return hasCycle;
    }

    public boolean hasCycle() {
        return hasCycle;
    }
}