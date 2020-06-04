package api;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Result {


    private @Id @GeneratedValue
    Long id;

    public String getCov_ind() {
        return cov_ind;
    }

    private String cov_ind;

    public String getNormal_ind() {
        return normal_ind;
    }

    private String normal_ind;

    public String getPneumonia_ind() {
        return pneumonia_ind;
    }

    public String getResult() {
        return result;
    }

    public String getUri() {
        return uri;
    }

    public void setCov_ind(String cov_ind) {
        this.cov_ind = cov_ind;
    }

    public void setNormal_ind(String normal_ind) {
        this.normal_ind = normal_ind;
    }

    public void setPneumonia_ind(String pneumonia_ind) {
        this.pneumonia_ind = pneumonia_ind;
    }

    public void setResult(String result) {
        this.result = result;
    }

    private String pneumonia_ind;
    private String result;

    public void setUri(String uri) {
        this.uri = uri;
    }

    private String uri;
    //The empty constructor is called by default by Spring and without it the program just fails to be loaded at all
    public Result(){

    }

    public Result(String cov_ind, String normal_ind, String pneumonia_ind, String result, String uri) {
        this.cov_ind= cov_ind;
        this.normal_ind = normal_ind;
        this.pneumonia_ind=pneumonia_ind;
        this.result=result;
        this.uri = uri;

    }
}
