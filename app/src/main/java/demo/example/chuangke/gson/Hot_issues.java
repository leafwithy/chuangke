package demo.example.chuangke.gson;

import android.graphics.Bitmap;

public class Hot_issues {
    private   int hid;
    private  String title;
    private  int concern_num;
    private  int comment_num;
    private  String content;
    private  String c_tag;
    private  String images;

    public void setHid(int hid) {
        this.hid = hid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setConcern_num(int concern_num) {
        this.concern_num = concern_num;
    }

    public void setComment_num(int comment_num) {
        this.comment_num = comment_num;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setC_tag(String c_tag) {
        this.c_tag = c_tag;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public Hot_issues() {
    }
    public  String getImages() {
        return images;
    }
    public  int getHid() {
        return hid;
    }

    public  String getTitle() {
        return title;
    }

    public  int getConcern_num() {
        return concern_num;
    }

    public  int getComment_num() {
        return comment_num;
    }

    public  String getContent() {
        return content;
    }

    public  String getC_tag() {
        return c_tag;
    }
}
