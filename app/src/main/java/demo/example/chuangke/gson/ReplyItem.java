package demo.example.chuangke.gson;

public class ReplyItem {
    private String cid;         //对应的评论id
    private String replyId;     //回复id
    private String uid;         //评论的用户id
    private String name;       //回复的用户名
    private String content;    //回复的内容
    //private 回复时间

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getReplyId() {
        return replyId;
    }

    public void setReplyId(String replyId) {
        this.replyId = replyId;
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

}
