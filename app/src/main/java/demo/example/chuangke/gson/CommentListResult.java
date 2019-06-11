package demo.example.chuangke.gson;

import java.util.List;

public class CommentListResult {
    private int status;                     //状态
    private List<CommentItem> commentList;  //评论表

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<CommentItem> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentItem> commentList) {
        this.commentList = commentList;
    }
}
