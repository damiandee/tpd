public class Edge implements Comparable {
    private int startPoint;
    private int endPoint;
    private float weight;

    public Edge(int startPoint, int endPoint, float weight) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.weight = weight;
    }

    public int getStartPoint() {
        return startPoint;
    }

    public int getEndPoint() {
        return endPoint;
    }

    public float getWeight() {
        return weight;
    }

    public int compareTo(Object o) {
        Edge other = (Edge) o;
        return Double.compare(this.weight, other.weight);
    }

    public String toString() {
        return startPoint + "-" + endPoint + " (" + weight + ")";
    }

}