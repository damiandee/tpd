import java.util.ArrayList;
import java.util.List;

public class Main {

    private static String DATA_A = "src/dataA.txt";
    private static String DATA_B = "src/dataB.txt";

    public static void main(String[] args) {
        executeProgram(DATA_A);
        executeProgram(DATA_B);
    }

    private static void executeProgram(String dataVariantName) {
        String letter = null;
        if("src/dataA.txt".equals(dataVariantName)) {
            letter = "A";
        } else {
            letter = "B";
        }
        FileReader fileReader = new FileReader();
        List<Action> actionsList = fileReader.getActionsList(dataVariantName);
        Pert pert = new Pert();
        pert.computeActionFactor(actionsList);
        pert.computeProbability(actionsList, letter);
    }

}
