package Utils;

import java.util.ArrayList;

public interface ArticleInterface {

    void collectArticle();//Collects the article data
    ArrayList<String> getArticle(String title);//Searches by article titles and returns a result of founded articles
}
