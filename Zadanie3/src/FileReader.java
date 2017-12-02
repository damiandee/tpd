import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    private static String FILE_NAME = "src/dataA.txt";
    private static String FILE_NAME2 = "src/dataB.txt";

    public List<Action> getActionsList() {
        List<Action> actionsList = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(FILE_NAME);
            FileInputStream fis2 = new FileInputStream(FILE_NAME2);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            BufferedReader br2 = new BufferedReader(new InputStreamReader(fis2));
            String line = null;
            String[] actionLine = null;

            while ((line = br.readLine()) != null || (line = br2.readLine()) != null) {
                Action action = new Action();
                actionLine = line.split(",");
                action.setI(Integer.parseInt(actionLine[0]));
                action.setJ(Integer.parseInt(actionLine[1]));
                action.setA(Integer.parseInt(actionLine[2]));
                action.setM(Integer.parseInt(actionLine[3]));
                action.setB(Integer.parseInt(actionLine[4]));
                System.out.println(line);
                actionsList.add(action);
            }

        } catch (IOException e) {

        }
        return actionsList;
    }

}
