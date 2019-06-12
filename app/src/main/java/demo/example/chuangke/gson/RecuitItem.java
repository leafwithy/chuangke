package demo.example.chuangke.gson;

import java.io.Serializable;

public class RecuitItem implements Serializable {

    private String uid;
    private int rid;
    private String intro;
    private String demand;
    private Boolean isStar;
    private int star_sum;
    private int concern_sum;
    private String time;

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getDemand() {
        return demand;
    }

    public void setDemand(String demand) {
        this.demand = demand;
    }

    public Boolean getStar() {
        return isStar;
    }

    public void setStar(Boolean star) {
        isStar = star;
    }

    public int getStar_sum() {
        return star_sum;
    }

    public void setStar_sum(int star_sum) {
        this.star_sum = star_sum;
    }

    public int getConcern_sum() {
        return concern_sum;
    }

    public void setConcern_sum(int concern_sum) {
        this.concern_sum = concern_sum;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }





}
