import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    public List<Action> getActionsList(String fileName) {
        List<Action> actionsList = new ArrayList<>();
        try {
            FileInputStream fis = new FileInputStream(fileName);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis));
            String line = null;
            String[] actionLine = null;

            while ((line = br.readLine()) != null) {
                Action action = new Action();
                actionLine = line.split(",");
                action.setI(Integer.parseInt(actionLine[0]));
                action.setJ(Integer.parseInt(actionLine[1]));
                action.setA(Integer.parseInt(actionLine[2]));
                action.setM(Integer.parseInt(actionLine[3]));
                action.setB(Integer.parseInt(actionLine[4]));
                actionsList.add(action);
            }

        } catch (IOException e) {

        }
        return actionsList;
    }

}
