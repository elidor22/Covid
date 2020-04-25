import Utils.*;//imports all the util package scripts

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        ReadData rd = new ReadData();
        List ls = new ArrayList();
        ls = rd.getCases();
        System.out.println(ls.get(0)+"  :"+ls.get(20));
    }
}
