import java.util.List;

public class DynamicProgramming {

    private static String DATA = "src/data.txt";


    public static void main(String[] args) {
        executeProgram(DATA);
    }

    private static void executeProgram(String dataVariantName) {
        FileReader fileReader = new FileReader();
        List<Action> actionsList = fileReader.getActionsList(dataVariantName);
        Pert pert = new Pert();
        pert.computeActionFactor(actionsList);
        pert.computeProbability(actionsList);
    }

}