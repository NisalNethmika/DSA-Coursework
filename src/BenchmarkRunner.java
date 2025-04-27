import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BenchmarkRunner {
    
    /**
     * Runs Dinic's algorithm on multiple benchmark files in a directory
     * @param directoryPath Path to directory containing benchmark files
     * @param fileExtension File extension to look for (e.g., ".txt")
     */
    public static void runBenchmarks(String directoryPath, String fileExtension) {
        File directory = new File(directoryPath);
        
        if (!directory.exists() || !directory.isDirectory()) {
            System.err.println("Invalid directory path: " + directoryPath);
            return;
        }
        
        // Get all benchmark files
        List<File> benchmarkFiles = new ArrayList<>();
        for (File file : directory.listFiles()) {
            if (file.isFile() && file.getName().endsWith(fileExtension)) {
                benchmarkFiles.add(file);
            }
        }
        
        // Run benchmark on each file
        System.out.println("Running benchmarks on " + benchmarkFiles.size() + " files...");
        System.out.println("-----------------------------------------------");
        
        for (File file : benchmarkFiles) {
            runSingleBenchmark(file.getAbsolutePath());
            System.out.println("-----------------------------------------------");
        }
    }
    
    /**
     * Runs a benchmark on a single file
     * @param filePath Path to the benchmark file
     */
    public static void runSingleBenchmark(String filePath) {
        try {
            System.out.println("Benchmark: " + new File(filePath).getName());
            
            // Read the graph
            FlowNetwork network = GraphReader.readGraph(filePath);
            
            if (network != null) {
                // Run Dinic's algorithm
                DinicMaxFlow dinic = new DinicMaxFlow(network);
                
                // Measure performance
                long startTime = System.currentTimeMillis();
                int maxFlow = dinic.findMaxFlow();
                long endTime = System.currentTimeMillis();
                long duration = endTime - startTime;
                
                // Print results
                System.out.println("Maximum flow: " + maxFlow);
                System.out.println("Execution time: " + duration + " ms");
            }
        } catch (Exception e) {
            System.err.println("Error processing file " + filePath + ": " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        if (args.length > 0) {
            // If a directory is provided
            if (args.length > 1) {
                runBenchmarks(args[0], args[1]);
            } else {
                runBenchmarks(args[0], ".txt");
            }
        } else {
            System.out.println("Usage: java BenchmarkRunner <directory_path> [file_extension]");
        }
    }
}