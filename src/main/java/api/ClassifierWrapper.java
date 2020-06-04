package api;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ClassifierWrapper {
    boolean classify = false;
    static String result;
    public  List<String>run(String url) throws IOException, InterruptedException {
        filePath(url);
        Process p = Runtime.getRuntime().exec("/home/elidor/test2/venv/bin/python /home/elidor/test2/loadModel.py");
        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
        String ret = in.readLine();


        System.out.println(ret);

        //Modify the data so it can be parsed as a double
        String value = ret.replace("[[","")
                .replace("]]","").replace("  "," ");
        System.out.println("value is : "+value);
        result = value;
        System.out.println("Hello");
        System.out.println(reader());
        return reader();
    }

     void filePath(String url) throws FileNotFoundException, UnsupportedEncodingException {
        String line =url;
        PrintWriter writer = new PrintWriter("theBridge.txt", "UTF-8");
        writer.println(line);
        writer.close();

    }

     List<String> reader(){


    String SplitBy = " ";
    String array[] =result.split(SplitBy);

    Double  covid = Double.parseDouble(array[0].substring(0,5));
    Double normal = Double.parseDouble(array[1].substring(0,5));
    Double pneumonia = Double.parseDouble(array[2].substring(0,5));
    System.out.println("Covid "+covid);
    System.out.println("Normal "+normal);
    System.out.println("Pneumonia "+pneumonia);
    classify = classify(covid,normal,pneumonia);
    System.out.println("Is covid: "+classify(covid,normal,pneumonia));
    //Load all elements to an List
    List<String> ls = Arrays.asList(covid+"",normal+"",pneumonia+"");
    return ls;
    }
    boolean classify(double cov, double normal, double pneumonia){
    if(cov>normal&&cov>pneumonia)
        return true;

    return false;


    }

    public static void main(String args[]) throws IOException, InterruptedException {
        Scanner sc = new Scanner(System.in);
        ClassifierWrapper classifierWrapper = new ClassifierWrapper();
        classifierWrapper.run(sc.nextLine());
    }

}
