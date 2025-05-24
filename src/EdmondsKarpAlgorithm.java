// Name - D.N.N. De Zoysa
// UOW ID - w2051810
// IIT ID - 20231024

import java.util.*;

public class EdmondsKarpAlgorithm {
    private final Graph network;
    private List<AugmentingPath> augmentingPaths;

    public EdmondsKarpAlgorithm(Graph network) {
        this.network = network;
        this.augmentingPaths = new ArrayList<>();
    }

    public int findMaxFlow() {
        int maxFlow = 0;
        augmentingPaths.clear();

        // Find augmenting paths using BFS and augment flow
        int pathFlow;
        while ((pathFlow = findAugmentingPath()) > 0) {
            maxFlow += pathFlow;
        }

        return maxFlow;
    }

    private int findAugmentingPath() {
        int source = network.getSource();
        int sink = network.getSink();
        int vertices = network.getVertices();

        // Keep track of the path
        int[] parent = new int[vertices];
        Arrays.fill(parent, -1);

        // Store the edge used to reach each vertex
        Edge[] edgeTo = new Edge[vertices];

        // BFS queue
        Queue<Integer> queue = new LinkedList<>();
        queue.add(source);
        parent[source] = source;

        // Run BFS to find a path
        while (!queue.isEmpty() && parent[sink] == -1) {
            int current = queue.poll();

            // Explore all neighbors
            for (int neighbor : network.getNeighbors(current)) {
                Edge edge = network.getEdge(current, neighbor);

                // If not visited and has remaining capacity
                if (parent[neighbor] == -1 && edge.remainingCapacity() > 0) {
                    parent[neighbor] = current;
                    edgeTo[neighbor] = edge;
                    queue.add(neighbor);
                }
            }
        }

        // If we didn't reach the sink, no augmenting path exists
        if (parent[sink] == -1) {
            return 0;
        }

        // Find bottleneck capacity
        int bottleneck = Integer.MAX_VALUE;
        for (int v = sink; v != source; v = parent[v]) {
            bottleneck = Math.min(bottleneck, edgeTo[v].remainingCapacity());
        }

        // Construct the path for output
        List<Integer> pathVertices = new LinkedList<>();
        for (int v = sink; v != source; v = parent[v]) {
            pathVertices.add(0, v);
        }
        pathVertices.add(0, source);

        // Create and store the augmenting path
        AugmentingPath augmentingPath = new AugmentingPath(pathVertices, bottleneck);
        augmentingPaths.add(augmentingPath);

        // Augment flow along the path
        for (int v = sink; v != source; v = parent[v]) {
            edgeTo[v].augment(bottleneck);
        }

        return bottleneck;
    }


     // Returns the list of augmenting paths found

    public List<AugmentingPath> getAugmentingPaths() {
        return augmentingPaths;
    }


    // Returns the flow on a specific edge

    public int getFlow(int from, int to) {
        return network.getEdge(from, to).getFlow();
    }

    // Class to represent an augmenting path

    public static class AugmentingPath {
        private List<Integer> vertices;
        private int flow;

        public AugmentingPath(List<Integer> vertices, int flow) {
            this.vertices = vertices;
            this.flow = flow;
        }

        public List<Integer> getVertices() {
            return vertices;
        }

        public int getFlow() {
            return flow;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < vertices.size(); i++) {
                sb.append(vertices.get(i));
                if (i < vertices.size() - 1) {
                    sb.append(" -> ");
                }
            }
            return sb.toString();
        }
    }
}