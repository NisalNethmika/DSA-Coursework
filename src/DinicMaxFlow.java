import java.util.*;

public class DinicMaxFlow {
    private final FlowNetwork network;
    private int[] level; // Stores level graph
    
    public DinicMaxFlow(FlowNetwork network) {
        this.network = network;
        this.level = new int[network.getVertices()];
    }
    
    // Find maximum flow using Dinic's algorithm
    public int findMaxFlow() {
        int maxFlow = 0;
        
        // While we can create a valid level graph
        while (bfs()) {
            // Track next edge to explore for each vertex
            HashMap<Integer, Iterator<Integer>> next = new HashMap<>();
            for (int i = 0; i < network.getVertices(); i++) {
                next.put(i, network.getNeighbors(i).iterator());
            }
            
            // Find blocking flows
            int flow;
            while ((flow = dfs(network.getSource(), Integer.MAX_VALUE, next)) > 0) {
                maxFlow += flow;
            }
        }
        
        return maxFlow;
    }
    
    // BFS to create level graph
    private boolean bfs() {
        // Reset level array
        Arrays.fill(level, -1);
        
        // Create queue and add source
        Queue<Integer> queue = new LinkedList<>();
        queue.add(network.getSource());
        level[network.getSource()] = 0;
        
        // BFS to assign levels
        while (!queue.isEmpty()) {
            int current = queue.poll();
            
            for (int neighbor : network.getNeighbors(current)) {
                Edge edge = network.getEdge(current, neighbor);
                
                // If not visited and has remaining capacity
                if (level[neighbor] == -1 && edge.remainingCapacity() > 0) {
                    level[neighbor] = level[current] + 1;
                    queue.add(neighbor);
                }
            }
        }
        
        // Return whether we reached the sink
        return level[network.getSink()] != -1;
    }
    
    // DFS to find blocking flow
    private int dfs(int vertex, int flow, HashMap<Integer, Iterator<Integer>> next) {
        // If we reached the sink, return flow
        if (vertex == network.getSink()) {
            return flow;
        }
        
        // Explore neighbors
        while (next.get(vertex).hasNext()) {
            int neighbor = next.get(vertex).next();
            Edge edge = network.getEdge(vertex, neighbor);
            
            // If valid edge in level graph
            if (level[neighbor] == level[vertex] + 1 && edge.remainingCapacity() > 0) {
                // Find bottleneck flow through this path
                int bottleneck = dfs(neighbor, Math.min(flow, edge.remainingCapacity()), next);
                
                // If path found, augment flow and return
                if (bottleneck > 0) {
                    edge.augment(bottleneck);
                    return bottleneck;
                }
            }
        }
        
        // No path found
        return 0;
    }
    
    // Get resulting flow through a particular edge
    public int getFlow(int from, int to) {
        return network.getEdge(from, to).getFlow();
    }
}