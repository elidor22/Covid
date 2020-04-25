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
    public ReadData() {

        String csvFile = "/home/elidor/Documents/CovidProject/updated_2.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";


        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] country = line.split(cvsSplitBy);

                date.add(country[0]);
                cases.add(country[1]);


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
