package Utils;/*
    File name: Utils.Utils.java
    Purpose:
        Utilities class, used accross the app in order to provide specific functionalities.
    Last Update: -

 */


import javax.net.ssl.SSLEngineResult;
import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class Utils {

    public static void CreateNewCSV(String file) throws IOException {
        File f = new File(file);
        StringBuilder builder = new StringBuilder();
        if(f.exists())
        {
            BufferedReader stream = new BufferedReader(new FileReader(f));

            String line = "";
            String[] top = stream.readLine().split(",");

            Hashtable<Integer, Double> rowSums = new Hashtable<>();

            while((line = stream.readLine()) != null) {
                String[] info = line.split(",");

                for(int i = 4; i < top.length - 4; i++) {
                    Double val = Double.parseDouble(info[i]);

                    if(rowSums.containsKey(i)) {
                        Double nVal = rowSums.get(i) + val;

                        rowSums.replace(i, nVal);
                        System.out.println("Value for " + top[i] + ": " + nVal);
                    }
                    else
                        rowSums.put(i, val);
                }
            }

            String header = String.join(",", Arrays.copyOfRange(top, 4, top.length - 5));
            header = header.substring(0, header.lastIndexOf(","));   // remove the last comma
            builder.append(header);
            builder.append("\n");

            // append the line
            rowSums.forEach((k, v) -> {
                builder.append(v.toString());
                builder.append(",");
            });

            builder.substring(0, builder.lastIndexOf(","));

            BufferedWriter writer = new BufferedWriter(new FileWriter("updated.csv"));
            writer.write(builder.toString());
            writer.flush();

            writer.close();
            System.out.println("Done");
        }
        else
        {
            System.out.println("Invalid file " + file);
        }
    }

}
