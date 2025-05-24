// Name - D.N.N. De Zoysa
// UOW ID - w2051810
// IIT ID - 20231024

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GraphReader {

    public static Graph readGraph(String filePath, int source, int sink) {
        try {
            Scanner scanner = new Scanner(new File(filePath));
            
            // Read number of vertices
            int vertices = scanner.nextInt();
            
            // Create flow network with source and sink
            Graph network = new Graph(vertices, source, sink);
            
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


}