package demo.example.chuangke.gson;

import java.util.List;

public class NewListResult {
    private int status;   //状态码
    private List<New_issues> new_issuesList;  //最新资讯表

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<New_issues> getNew_issuesList() {
        return new_issuesList;
    }

    public void setNew_issuesList(List<New_issues> new_issuesList) {
        this.new_issuesList = new_issuesList;
    }
}
