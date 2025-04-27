import java.io.File;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Check if a file path is provided as command line argument
        String filePath;
        if (args.length > 0) {
            filePath = args[0];
        } else {
            // Use default path or prompt user
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter path to benchmark file: ");
            filePath = scanner.nextLine();
            scanner.close();
        }

        try {
            // Read the number of vertices first to determine default sink
            Scanner fileScanner = new Scanner(new File(filePath));
            int vertices = fileScanner.nextInt();
            fileScanner.close();
            
            // Read the graph with source 0 and sink as the last vertex
            FlowNetwork network = GraphReader.readGraph(filePath, 0, vertices - 1);
            
            if (network != null) {
                // Run Dinic's algorithm
                DinicMaxFlow dinic = new DinicMaxFlow(network);
                
                long startTime = System.currentTimeMillis();
                int maxFlow = dinic.findMaxFlow();
                long endTime = System.currentTimeMillis();
                
                // Print results
                System.out.println("Maximum flow: " + maxFlow);
                System.out.println("Time taken: " + (endTime - startTime) + " ms");
                
                // Optionally print flows on each edge
                printEdgeFlows(network, dinic);
            }
        } catch (Exception e) {
            System.err.println("Error processing file: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    private static void printEdgeFlows(FlowNetwork network, DinicMaxFlow dinic) {
        System.out.println("\nEdge flows (from -> to: flow/capacity):");
        for (int i = 0; i < network.getVertices(); i++) {
            for (int j : network.getNeighbors(i)) {
                Edge edge = network.getEdge(i, j);
                if (edge.getCapacity() > 0) {  // Only original edges, not residual
                    System.out.println(i + " -> " + j + ": " + dinic.getFlow(i, j) + 
                                     "/" + edge.getCapacity());
                }
            }
        }
    }
}