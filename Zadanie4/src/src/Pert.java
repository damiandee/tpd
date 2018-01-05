import java.util.ArrayList;
import java.util.List;

/**
 * Created by Damian Deska on 2017-12-02.
 */
public class Pert {

    //private static int EXPECTED_TIME = 48;

    public void computeActionFactor(List<Action> actionsList) {
        for(Action action : actionsList) {
            action.setT(computeT(action));
        }
    }

    private double computeT(Action action) {
        return action.getS();
    }

    public void computeProbability(List<Action> actionsList) {
        Graph graph = new Graph(9, 15);
        CriticalPath criticalPath = graph.getCriticalPath(actionsList);
    }

    private static List<Action> getCriticalPathActions(List<Integer> nodesNumbers, List<Action> actionsList) {
        List<Action> criticalPathActionsList = new ArrayList<>();
        for (int i = 0; i < nodesNumbers.size(); i++) {
            for (Action action : actionsList) {
                if (action.getP() == nodesNumbers.get(i) && action.getK() == nodesNumbers.get(i + 1)) {
                    criticalPathActionsList.add(action);
                }
            }
        }
        return criticalPathActionsList;
    }

}
