// Name - D.N.N. De Zoysa
// UOW ID - w2051810
// IIT ID - 20231024

import java.io.File;
import java.util.List;
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
            Graph network = GraphReader.readGraph(filePath, 0, vertices - 1);

            if (network != null) {
                // Run Edmonds-Karp algorithm
                EdmondsKarpAlgorithm maxFlow = new EdmondsKarpAlgorithm(network);

                long startTime = System.currentTimeMillis();
                int maxFlowValue = maxFlow.findMaxFlow();
                long endTime = System.currentTimeMillis();

                // Print augmenting paths
                System.out.println("Augmenting Paths:");
                List<EdmondsKarpAlgorithm.AugmentingPath> paths = maxFlow.getAugmentingPaths();
                for (int i = 0; i < paths.size(); i++) {
                    EdmondsKarpAlgorithm.AugmentingPath path = paths.get(i);
                    System.out.println("Path " + (i+1) + ": " + path);
                    System.out.println("Flow: " + path.getFlow());
                }

                // Print total results
                System.out.println("Maximum Flow: " + maxFlowValue);
                System.out.println("Time taken: " + (endTime - startTime) + " ms");
            }
        } catch (Exception e) {
            System.err.println("Error processing file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}