// Name - D.N.N. De Zoysa
// UOW ID - w2051810
// IIT ID - 20231024

public class Edge {
    private final int from;
    private final int to;
    private final int capacity;
    private int flow;
    private Edge residual;
    
    public Edge(int from, int to, int capacity) {
        this.from = from;
        this.to = to;
        this.capacity = capacity;
        this.flow = 0;
    }
    
    // Get residual capacity of this edge
    public int remainingCapacity() {
        return capacity - flow;
    }
    
    // Augment flow along this edge
    public void augment(int bottleneck) {
        flow += bottleneck;
        residual.flow -= bottleneck;
    }
    
    public void setResidual(Edge residual) {
        this.residual = residual;
    }
    
    // Getters
    public int getFrom() { return from; }
    public int getTo() { return to; }
    public int getCapacity() { return capacity; }
    public int getFlow() { return flow; }
    public Edge getResidual() { return residual; }
}