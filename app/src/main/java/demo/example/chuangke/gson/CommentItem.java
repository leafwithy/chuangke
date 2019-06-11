package demo.example.chuangke.gson;

import java.util.ArrayList;
import java.util.List;

public class CommentItem {
    private String cid;                 //评论id
    private String uid;                 //评论的用户id
    private String name;               //评论用户名
    private String content;            //评论内容
    private int replySum;           //回复的数量
    private List<ReplyItem> replyList;  //回复列表
    private String profileUrl;          //头像的Url地址
    //private 评论时间

    //无参构造器
    public CommentItem(){}
    //有参构造器
    public CommentItem(String cid,String uid,String name,String content,String profileUrl){
        this.cid = cid;
        this.uid = uid;
        this.name = name;
        this.content = content;
        this.replySum = 0;
        this.replyList = new ArrayList<ReplyItem>();
        this.profileUrl = profileUrl;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getReplySum() {
        return replySum;
    }

    public void setReplySum(int replySum) {
        this.replySum = replySum;
    }

    public List<ReplyItem> getReplyList() {
        return replyList;
    }

    public void setReplyList(List<ReplyItem> replyList) {
        this.replyList = replyList;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }


}
