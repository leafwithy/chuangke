package demo.example.chuangke.Reality;

import android.graphics.Bitmap;

public class Hot_issues {
    public   int hid;
    public   String title;
    public   int concern_num;
    public   int comment_num;
    public   String content;
    public   String c_tag;
    public   String images;

    public Hot_issues() {
    }

    public Hot_issues(int hid , String title , int concern_num, int comment_num, String content, String c_tag, String images) {
        this.hid  = hid;
        this.title = title;
        this.concern_num = concern_num;
        this.comment_num = comment_num;
        this.content = content;
        this.c_tag = c_tag;
        this.images = images;
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
