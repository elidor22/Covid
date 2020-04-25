package Utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadData {

    public static void main(String[] args) {

        String csvFile = "/home/elidor/Documents/CovidProject/updated_2.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        List ls = new ArrayList();
        List countryl = new ArrayList();

        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] country = line.split(cvsSplitBy);

                ls.add(country[0]);
                countryl.add(country[1]);


            }
            System.out.println("Cases "+countryl.get(5));
            System.out.println("Date "+ls.get(20));

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
