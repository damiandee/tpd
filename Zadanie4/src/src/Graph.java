import java.util.*;

public class Graph {

    private int maxVertices, maxEdges;
    private int nVertices, nEdges;
    private String[] vertices;
    private double edgeCost[];
    private int edgeFrom[];
    private int edgeTo[];
    private String startNode;
    private String endNode;
    double INF = Double.MAX_VALUE;
    Map<String, Integer> nodesMap = new HashMap<>();

    private final Set<String> settledV = new HashSet<String>();
    private final PriorityQueue<String> unSettledV;
    private final Map<String, Double> d = new HashMap<String, Double>();
    private final Map<String, String> pred = new HashMap<String, String>();

    private final Comparator<String> minDistanceComparator = new Comparator<String>() {

        @Override
        public int compare(String from, String to) {
            double result = getMinDistance(from) - getMinDistance(to);
            return (int) ((result == 0) ? from.compareTo(to) : result);
        }
    };

    private int idxNextVertex[];
    private double lengthCriticalPath = 0.0;

    private boolean print;

    Graph(int maxVertices, int maxEdges) {

        this.maxVertices = maxVertices;
        this.maxEdges = maxEdges;

        this.nodesMap.put("A", 1);
        this.nodesMap.put("B", 2);
        this.nodesMap.put("C", 3);
        this.nodesMap.put("D", 4);
        this.nodesMap.put("E", 5);
        this.nodesMap.put("F", 6);
        this.nodesMap.put("G", 7);
        this.nodesMap.put("H", 8);
        this.nodesMap.put("I", 9);

        nVertices = 0;
        nEdges = 0;

        vertices = new String[maxVertices];
        edgeCost = new double[maxEdges];
        edgeFrom = new int[maxEdges];
        edgeTo = new int[maxEdges];

        print = true;
        unSettledV = new PriorityQueue<String>(maxVertices,
                minDistanceComparator);

    }

    public void quiet() {
        print = false;
    }

    public void addVertex(String vert) {
        if (nVertices >= maxVertices) {
            throw new IndexOutOfBoundsException("No space for more vertices");
        }

        boolean found = false;
        for (int i = 0; i < nVertices; i++) {
            if (vertices[i].equals(vert))
                found = true;
        }

        if (found) {
            System.out.println("GRAPH: vertex " + vert + " is already known");
        } else {
            // if not then put vertex in array and increase counter
            if (print)
                System.out.println("GRAPH: Add Vertex: " + vert);
            vertices[nVertices] = vert;
            nVertices++;
        }
    }

    public void addEdge(String from, String to, double cost) {
        int i1, i2;

        i1 = findVertex(from);
        i2 = findVertex(to);

        if (nEdges >= maxEdges) {
            throw new ArrayIndexOutOfBoundsException("No space for more edges");
        }

        if (print)
            System.out.println("GRAPH: Add Edge: " + from + " - " + to + " : "
                    + cost);

        edgeCost[nEdges] = cost;
        edgeFrom[nEdges] = i1;
        edgeTo[nEdges] = i2;
        nEdges++;
    }

    public void criticalPath() {

        idxNextVertex = new int[nVertices];

        if (print)
            System.out.println("GRAPH: Find critical path");

        this.getStart();
        this.getEnd();

        setMinDistance(startNode, 0);
        runBellmanFord();
    }

    private void runBellmanFord() {

        double[] distance = new double[nVertices];
        Arrays.fill(distance, INF);
        distance[findVertex(startNode)] = 0;
        pred.put(startNode, null);
        for (int i = 0; i < nVertices; ++i)
            for (int j = 0; j < nEdges; ++j) {
                if (distance[edgeFrom[j]] == INF)
                    continue;
                double newDistance = distance[edgeFrom[j]] - edgeCost[j];
                if (newDistance < distance[edgeTo[j]]) {
                    distance[edgeTo[j]] = newDistance;
                    pred.put(vertices[edgeTo[j]], vertices[edgeFrom[j]]);
                }
            }

        for (int i = 0; i < nEdges; ++i)
            if (distance[edgeFrom[i]] != INF
                    && distance[edgeTo[i]] > distance[edgeFrom[i]]
                    + edgeCost[i]) {
                if (print) System.out.println("Cycles detected!");
                return;
            }

        for (int i = 0; i < distance.length; ++i)
            if (distance[i] == INF)
                System.out.println("There's no path between " + startNode
                        + " and " + endNode);

        populatePath(distance);
    }

    private void populatePath(double[] distance) {
        idxNextVertex[findVertex(endNode)] = -1;

        String vertex = endNode;
        String p = "";
        while (true) {
            for (Map.Entry<String, String> previous : pred.entrySet()) {
                if (previous.getKey() == vertex) {
                    double maxDist = INF;
                    if (previous.getValue() != null) {
                        if (vertices[findVertex(previous.getKey())] == vertex
                                && distance[findVertex(previous.getValue())] < maxDist) {
                            maxDist = distance[findVertex(previous.getKey())];
                            p = vertices[findVertex(previous.getValue())];
                        }
                    } else {
                        p = null;
                        break;
                    }
                }
                if (print)
                    System.out.println("finished with predecessors");
            }
            int previous = 0;
            if (p != null) {
                double edgeC = 0;
                for (int i = 0; i < maxEdges; i++) {
                    if (vertices[edgeFrom[i]] == p
                            && vertices[edgeTo[i]] == vertex)
                        edgeC = edgeCost[i];
                }
                lengthCriticalPath += edgeC;
                previous = findVertex(p);
                idxNextVertex[previous] = findVertex(vertex);
                vertex = vertices[previous];
            } else
                break;
        }
        if (print)
            System.out.println("finished populating");
    }

    public void setMinDistance(String vertex, double distance) {
        unSettledV.remove(vertex);
        d.put(vertex, distance);
        unSettledV.add(vertex);
    }

    private double getMinDistance(String from) {
        Double dist = d.get(from);
        return (dist == null) ? Integer.MAX_VALUE : dist;
    }

    public List<Integer> verticesCriticalPath() {
        String list = vertices[0];
        int currentIdx = 0;
        List<Integer> nodesNumbersList = new ArrayList<>();
        nodesNumbersList.add(1);
        while (idxNextVertex[currentIdx] >= 0) {
            currentIdx = idxNextVertex[currentIdx];
            nodesNumbersList.add(nodesMap.get(vertices[currentIdx]));
        }
        return nodesNumbersList;
    }

    public double criticalPathLength() {

        return lengthCriticalPath;
    }

    public CriticalPath getCriticalPath(List<Action> actionsList) {
        Graph graph = makeGraph(actionsList);

        graph.criticalPath();
        System.out.println("Critical Path length: " + graph.criticalPathLength());
        System.out.println("Critical Path " + ": " + graph.verticesCriticalPath());

        CriticalPath criticalPath = new CriticalPath();
        criticalPath.setLength(graph.criticalPathLength());
        criticalPath.setNodesNumbers(graph.verticesCriticalPath());

        return criticalPath;
    }

    private Graph makeGraph(List<Action> actionsList) {
        Graph graph = null;

            graph = new Graph(9, 15);
            graph.addVertexes(graph);
            graph.addEdgesA(graph, actionsList);

        return graph;
    }

    private void addVertexes(Graph graph){
        graph.quiet();
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");
        graph.addVertex("H");
        graph.addVertex("I");

    }

    private void addEdgesA(Graph graph, List<Action> actionsList) {
        graph.addEdge("A", "B", actionsList.get(0).getT());
        graph.addEdge("A", "C", actionsList.get(1).getT());
        graph.addEdge("A", "D", actionsList.get(2).getT());
        graph.addEdge("B", "E", actionsList.get(3).getT());
        graph.addEdge("B", "F", actionsList.get(4).getT());
        graph.addEdge("C", "E", actionsList.get(5).getT());
        graph.addEdge("C", "F", actionsList.get(6).getT());
        graph.addEdge("C", "G", actionsList.get(7).getT());
        graph.addEdge("D", "F", actionsList.get(8).getT());
        graph.addEdge("D", "G", actionsList.get(9).getT());
        graph.addEdge("E", "H", actionsList.get(10).getT());
        graph.addEdge("E", "I", actionsList.get(11).getT());
        graph.addEdge("F", "H", actionsList.get(12).getT());
        graph.addEdge("G", "H", actionsList.get(13).getT());
        graph.addEdge("H", "I", actionsList.get(14).getT());
    }


    private void getEnd() {
        for (int i = 0; i < vertices.length; i++) {
            int from = 0;
            if (vertices[i] != null)
                from = findVertex(vertices[i]);
            for (int j = 0; j < edgeFrom.length; j++) {
                if (edgeFrom[from] != from)
                    endNode = vertices[i];
            }
        }
        if (print)
            System.out.println(endNode);
    }

    private void getStart() {
        startNode = vertices[0];
        if (print)
            System.out.println(startNode);
    }

    private int findVertex(String vertex) {
        int i1 = -1;
        boolean found = false;

        for (int i = 0; i < nVertices; i++) {
            if (vertices[i].equals(vertex)) {
                found = true;
                i1 = i;
                break;
            }
        }

        if (!found) {
            throw new IllegalArgumentException("Vertex " + vertex
                    + " not in graph");
        }

        return i1;
    }

}
