package demo.example.chuangke.gson;

import java.util.List;

public class HotListResult {
    private int status; //状态码
    private List<Hot_issues> hotpItemList;  //热门推荐表

   /* @Override
    public String toString() {
        return hotpItemList.get(1).toString()+hotpItemList.get(2)+hotpItemList.get(3)+hotpItemList.get(4)+hotpItemList.get(5);
    }*/

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Hot_issues> getHotpItemList() {
        return hotpItemList;
    }

    public void setHotpItemList(List<Hot_issues> hotpItemList) {
        this.hotpItemList = hotpItemList;
    }
}
