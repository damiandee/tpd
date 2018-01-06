import java.util.Iterator;
import java.util.PriorityQueue;

public class Graph {

    private int vCount;
    private PriorityQueue<Edge>[] adj;

    public int getvCount() {
        return vCount;
    }

    public Graph(int vCount) {
        this.vCount = vCount;
        adj = new PriorityQueue[vCount];
        for (int i = 0; i < vCount; i++) {
            adj[i] = new PriorityQueue<Edge>();
        }
    }

    public void addEdge(int i, int j, float weight) {
        adj[i].add(new Edge(i, j, weight));
    }

    public PriorityQueue<Edge> neighbours(int vertex) {
        return adj[vertex];
    }

    public void printGraph() {
        for (int i = 0; i < vCount; i++) {
            PriorityQueue<Edge> edges = neighbours(i);
            Iterator<Edge> it = edges.iterator();
            System.out.print(i + ": ");
            for (int j = 0; j < edges.size(); j++) {
                System.out.print(it.next() + " ");
            }
            System.out.println();
        }
    }
}
