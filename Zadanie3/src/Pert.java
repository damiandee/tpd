import java.util.ArrayList;
import java.util.List;

/**
 * Created by Damian Deska on 2017-12-02.
 */
public class Pert {

    private static int EXPECTED_TIME = 48;

    public void computeActionFactor(List<Action> actionsList) {
        for(Action action : actionsList) {
            action.setT(computeT(action));
            action.setS_square(computeS_square(action));
        }
    }

    private double computeT(Action action) {
        return ((action.getA() + (4.0* action.getM()) + action.getB()) / 6.0);
    }

    private double computeS_square(Action action) {
        double subtract = action.getB() - action.getA();
        double divide = subtract / 6.0;
        double s_square = Math.round(Math.pow(divide, 2) * 100);
        s_square = s_square/100;
        return s_square;
    }

    public void computeProbability(List<Action> actionsList, String variant) {
        Graph graph = new Graph(8, 11);
        CriticalPath criticalPath = graph.getCriticalPath(actionsList, variant);

        List<Action> criticalPathActionsList = getCriticalPathActions(criticalPath.getNodesNumbers(), actionsList);

        double s_squareSum = getS_squareSum(criticalPathActionsList);
        double s = Math.sqrt(s_squareSum);

        double z = ((EXPECTED_TIME - criticalPath.getLength()) / s);

        System.out.println("s_square: " + s_squareSum + "\ns: " + s + "\nt: " + criticalPath.getLength() +
                "\nz: " + z + "\n============================================");
    }

    private static List<Action> getCriticalPathActions(List<Integer> nodesNumbers, List<Action> actionsList) {
        List<Action> criticalPathActionsList = new ArrayList<>();
        for (int i = 0; i < nodesNumbers.size(); i++) {
            for (Action action : actionsList) {
                if (action.getI() == nodesNumbers.get(i) && action.getJ() == nodesNumbers.get(i + 1)) {
                    criticalPathActionsList.add(action);
                }
            }
        }
        return criticalPathActionsList;
    }

    private static double getS_squareSum(List<Action> actionsList) {
        double sum = 0;
        for (Action action : actionsList) {
            sum += action.getS_square();
        }
        return sum;
    }

}
