import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GraphReader {
    
    /**
     * Reads a graph from a file in the specified format and creates a FlowNetwork.
     * The format is:
     * - First line: number of vertices
     * - Next lines: from to capacity
     * 
     * @param filePath path to the input file
     * @param source source vertex (default 0)
     * @param sink sink vertex (default num_vertices-1)
     * @return FlowNetwork instance representing the graph
     */
    public static FlowNetwork readGraph(String filePath, int source, int sink) {
        try {
            Scanner scanner = new Scanner(new File(filePath));
            
            // Read number of vertices
            int vertices = scanner.nextInt();
            
            // Create flow network with source and sink
            FlowNetwork network = new FlowNetwork(vertices, source, sink);
            
            // Read edges
            while (scanner.hasNext()) {
                int from = scanner.nextInt();
                int to = scanner.nextInt();
                int capacity = scanner.nextInt();
                
                // Add edge to network
                network.addEdge(from, to, capacity);
            }
            
            scanner.close();
            return network;
            
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Overloaded method that assumes source is 0 and sink is (vertices-1)
     */
    public static FlowNetwork readGraph(String filePath) {
        try {
            Scanner scanner = new Scanner(new File(filePath));
            
            // Read number of vertices
            int vertices = scanner.nextInt();
            
            // Create flow network with source 0 and sink (vertices-1)
            return readGraph(filePath, 0, vertices - 1);
            
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filePath);
            e.printStackTrace();
            return null;
        }
    }
}