import java.util.ArrayList;
import java.util.List;

/**
 * Created by Damian Deska on 2017-12-02.
 */
public class Pert {

    private static int EXPECTED_TIME = 48;

    public void computeActionFactor(List<Action> actionsList) {
        for(Action action : actionsList) {
           // action.setT(computeT(action));
            //action.setS_square(computeS_square(action));
        }
    }

//    private double computeT(Action action) {
//        return ((action.getA() + (4.0* action.getM()) + action.getB()) / 6.0);
//    }

//    private double computeS_square(Action action) {
//        double subtract = action.getB() - action.getA();
//        double divide = subtract / 6.0;
//        double s_square = Math.round(Math.pow(divide, 2) * 100);
//        s_square = s_square/100;
//        return s_square;
//    }

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
