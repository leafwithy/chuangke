package demo.example.chuangke.gson;

import java.util.List;

public class HotListResult {
    private int status; //状态码
    private List<Hot_issues> hot_issuesList;  //热门推荐表

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Hot_issues> getHot_issuesList() {
        return hot_issuesList;
    }

    public void setHot_issuesList(List<Hot_issues> hot_issuesList) {
        this.hot_issuesList = hot_issuesList;
    }
}
