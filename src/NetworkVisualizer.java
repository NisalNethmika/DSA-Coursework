public class NetworkVisualizer {
    /**
     * Prints a visual representation of the network with flow information
     */
    public static void printNetwork(FlowNetwork network, DinicMaxFlow dinic) {
        System.out.println("Flow Network:");
        System.out.println("Vertices: " + network.getVertices());
        System.out.println("Source: " + network.getSource());
        System.out.println("Sink: " + network.getSink());
        System.out.println("\nEdges (from -> to: flow/capacity):");
        
        for (int i = 0; i < network.getVertices(); i++) {
            for (int j : network.getNeighbors(i)) {
                Edge edge = network.getEdge(i, j);
                if (edge.getCapacity() > 0) {  // Only print original edges
                    System.out.println(i + " -> " + j + ": " + dinic.getFlow(i, j) + "/" + edge.getCapacity());
                }
            }
        }
    }
    
    /**
     * Prints just the network structure without flow information
     */
    public static void printNetworkStructure(FlowNetwork network) {
        System.out.println("Flow Network Structure:");
        System.out.println("Vertices: " + network.getVertices());
        System.out.println("Source: " + network.getSource());
        System.out.println("Sink: " + network.getSink());
        System.out.println("\nEdges (from -> to: capacity):");
        
        for (int i = 0; i < network.getVertices(); i++) {
            for (int j : network.getNeighbors(i)) {
                Edge edge = network.getEdge(i, j);
                if (edge.getCapacity() > 0) {  // Only print original edges
                    System.out.println(i + " -> " + j + ": " + edge.getCapacity());
                }
            }
        }
    }
}