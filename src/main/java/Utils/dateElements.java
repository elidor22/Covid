package Utils;

import java.util.ArrayList;
import java.util.List;

public class dateElements {
    List<String> day= new ArrayList<>();

    public List<String> getDay() {
        return day;
    }

    public List<String> getMonth() {
        return month;
    }

    List<String> month= new ArrayList<>();
    public dateElements(boolean isConcern){
        if(isConcern)
            concernData();
        else
            cases();




    }

    void concernData(){
        ReadData rd = new ReadData(true);
        String SplitBy = "/";
        String strings[]={""};


        List ls = rd.getDate();

        for(int i=1;i< ls.size();i++) {
            String line = ls.get(i).toString();
            strings=line.split(SplitBy);
            month.add(strings[1]);
            day.add(strings[0]);
            //System.out.println(day.get(i-1));

        }


    }

    void cases(){
        ReadData rd = new ReadData(false);
        String SplitBy = "-";
        String strings[]={""};


        List ls = rd.getDate();

        for(int i=1;i< ls.size();i++) {
            String line = ls.get(i).toString();
            strings=line.split(SplitBy);
            month.add(strings[1]);
            day.add(strings[2]);
            //System.out.println(day.get(i-1));

        }
    }
}
