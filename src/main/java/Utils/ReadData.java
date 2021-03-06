package Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadData {
    public List getDate() {
        return date;
    }

    public List getCases() {
        return cases;
    }

    List date = new ArrayList();
    List cases = new ArrayList();
    public ReadData(boolean isConcern) {

        String csvFile = "/home/elidor/IdeaProjects/Covid/concern_levels.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        //If we want concern data it provides the concern level csv
        if(isConcern){
            csvFile = "/home/elidor/IdeaProjects/Covid/concern_levels.csv";
            br = null;
            line = "";
            cvsSplitBy = ",";
        }
        //Else provide cases
        else if(!isConcern){
            csvFile = "/home/elidor/IdeaProjects/Covid/new_cases.csv";
            br = null;
            line = "";
            cvsSplitBy = ",";
        }




        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                /**A simple regex implementation to split the data and cases from each other*/
                String[] data = line.split(cvsSplitBy);

                date.add(data[0]);
                cases.add(data[1]);


            }
            //System.out.println("Cases "+ cases.get(1));
           //System.out.println("Date "+ date.get(20));

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


}
