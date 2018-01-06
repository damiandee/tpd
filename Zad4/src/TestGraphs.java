import java.util.Iterator;

public class TestGraphs {

    public static void main(String[] args) {

        Graph g = new Graph(9);
        System.out.println("Graph:");

        g.addEdge(0, 1, 7);
        g.addEdge(0, 2, 5);
        g.addEdge(0, 3, 6);
        g.addEdge(1, 4, 7);
        g.addEdge(1, 5, 8);
        g.addEdge(2, 4, 4);
        g.addEdge(2, 5, 5);
        g.addEdge(2, 6, 9);
        g.addEdge(3, 5, 6);
        g.addEdge(3, 6, 5);
        g.addEdge(4, 7, 7);
        g.addEdge(4, 8, 4);
        g.addEdge(5, 7, 6);
        g.addEdge(6, 7, 8);
        g.addEdge(6, 8, 7);

        g.printGraph();

        System.out.println("Bellman-Ford Shortest Path:");
        BellmanFord(g, 0);
    }

    public static void BellmanFord(Graph g, int startVertex) {

        float[] distances = new float[g.getvCount()];
        int[] predecessors = new int[g.getvCount()];

        for (int i = 0; i < distances.length; i++) {
            distances[i] = Float.MAX_VALUE;
            predecessors[i] = -1;
        }
        distances[startVertex] = 0;

        for (int i = 1; i < g.getvCount() - 1; i++) {
            for (int j = 0; j < g.getvCount() - 1; j++) {
                Iterator<Edge> it = g.neighbours(j).iterator();
                while (it.hasNext()) {
                    Edge e = it.next();
                    if (distances[e.getStartPoint()] + e.getWeight() < distances[e.getEndPoint()]) {
                        distances[e.getEndPoint()] = distances[e.getStartPoint()] + e.getWeight();
                        predecessors[e.getEndPoint()] = e.getStartPoint();
                    }
                }
            }
        }

        for (int i = 0; i < g.getvCount() - 1; i++) {
            Iterator<Edge> it = g.neighbours(i).iterator();
            while (it.hasNext()) {
                Edge e = it.next();
                if (distances[e.getStartPoint()] + e.getWeight() < distances[e.getEndPoint()]) {
                    System.out.println("Graph contains negative-weight circle!");
                    return;
                }
            }
        }

        System.out.println("Vertex\tDistance\tPredecessor");
        for (int i = 0; i < g.getvCount(); i++) {
            System.out.println(i + "\t\t " + distances[i] + "\t\t\t" + predecessors[i]);
        }

    }
}