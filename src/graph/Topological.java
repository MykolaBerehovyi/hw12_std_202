package graph;

// Based on slide 29.
// Time: O(V + E). Extra space: O(V).
public class Topological {
    private Iterable<Integer> order;

    public Topological(Digraph graph) {
        DirectedCycle cycleFinder = new DirectedCycle(graph);

        if (!cycleFinder.hasCycle()) {
            DepthFirstOrder dfs = new DepthFirstOrder(graph);
            order = dfs.reversePost();
        }
    }

    public Iterable<Integer> order() {
        return order;
    }

    public boolean isDAG() {
        // A graph without cycles has a topological order.
        return order != null;
    }
}