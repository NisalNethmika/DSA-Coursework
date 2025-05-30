// Name - D.N.N. De Zoysa
// UOW ID - w2051810
// IIT ID - 20231024

import java.util.*;

public class Graph {
    // Using HashMap<Integer, HashMap<Integer, Edge>> for adjacency list
    private final HashMap<Integer, HashMap<Integer, Edge>> graph;
    private final int source;
    private final int sink;
    private final int vertices;
    
    public Graph(int vertices, int source, int sink) {
        this.graph = new HashMap<>();
        this.source = source;
        this.sink = sink;
        this.vertices = vertices;
        
        // Initialize empty adjacency lists for all vertices
        for (int i = 0; i < vertices; i++) {
            graph.put(i, new HashMap<>());
        }
    }
    
    // Add an edge with capacity both ways
    public void addEdge(int from, int to, int capacity) {
        Edge forward = new Edge(from, to, capacity);
        Edge backward = new Edge(to, from, 0);
        
        // Set as residual edges of each other
        forward.setResidual(backward);
        backward.setResidual(forward);
        
        // Add to graph
        graph.get(from).put(to, forward);
        graph.get(to).put(from, backward);
    }
    
    // Get neighbors of a vertex
    public Set<Integer> getNeighbors(int vertex) {
        return graph.get(vertex).keySet();
    }
    
    // Get edge between vertices
    public Edge getEdge(int from, int to) {
        return graph.get(from).get(to);
    }
    
    // Getters for source, sink, vertices
    public int getSource() { return source; }
    public int getSink() { return sink; }
    public int getVertices() { return vertices; }
}