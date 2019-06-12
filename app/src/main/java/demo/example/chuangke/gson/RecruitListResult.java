package demo.example.chuangke.gson;

import java.util.List;

public class RecruitListResult {
    private int status;                 //状态
    private List<RecuitItem> recruitList;//招贤表

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<RecuitItem> getRecruitList() {
        return recruitList;
    }

    public void setRecruitList(List<RecuitItem> recruitList) {
        recruitList = recruitList;
    }

}
