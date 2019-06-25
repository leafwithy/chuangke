package demo.example.chuangke.gson;



public class Hot_issues {
    private   int hid;
    private  String title;
    private  int c_tag;
    private  int care_num;
    private  String images;


    @Override
    public String toString() {
        return ""+hid+title+c_tag+care_num+images ;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public int getHid() {
        return hid;
    }

    public void setHid(int hid) {
        this.hid = hid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public int getCare_num() {
        return care_num;
    }

    public void setCare_num(int care_num) {
        this.care_num = care_num;
    }

    public int getC_tag() {
        return c_tag;
    }

    public void setC_tag(int c_tag) {
        this.c_tag = c_tag;
    }
}
