import java.util.List;

/**
 * Created by Damian Deska on 2017-12-02.
 */
public class CriticalPath {

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public List<Integer> getNodesNumbers() {
        return nodesNumbers;
    }

    public void setNodesNumbers(List<Integer> nodesNumbers) {
        this.nodesNumbers = nodesNumbers;
    }

    private double length;
    private List<Integer> nodesNumbers;
}
