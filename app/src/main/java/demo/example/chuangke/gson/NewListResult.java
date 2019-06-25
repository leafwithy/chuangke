package demo.example.chuangke.gson;

import java.util.List;

public class NewListResult {
    private int status;   //状态码
    private List<New_issues> newItemList;  //最新资讯表

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<New_issues> getNewItemList() {
        return newItemList;
    }

    public void setNewItemList(List<New_issues> newItemList) {
        this.newItemList = newItemList;
    }
}
