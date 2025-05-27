# Maximum Flow Algorithm Implementation

This project implements the Edmonds-Karp algorithm, a technique for computing the maximum flow in a flow network.

## Overview

The Edmonds-Karp algorithm is an implementation of the Ford-Fulkerson method for computing the maximum flow in a flow network. It uses breadth-first search to find augmenting paths, ensuring that the algorithm terminates and has a polynomial time complexity.

## Components

- `Graph.java`: Represents a flow network using adjacency lists.
- `Edge.java`: Represents directed edges with capacity and flow.
- `GraphReader.java`: Reads graph data from files.
- `EdmondsKarpAlgorithm.java`: Implements the Edmonds-Karp algorithm.
- `Main.java`: Entry point that processes user input and displays results.

## Algorithm

The Edmonds-Karp algorithm repeatedly finds augmenting paths using BFS and increases flow along these paths until no more augmenting paths exist. This implementation:

1. Uses BFS to find the shortest augmenting path
2. Calculates the bottleneck capacity along the path
3. Augments flow along the path by the bottleneck value
4. Repeats until no augmenting path exists
5. Returns the total flow value