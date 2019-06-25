package demo.example.chuangke.gson;

import java.util.List;

public class ProveListResult {
    private int status ;
    private List<Prove_issues> proveItemList;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Prove_issues> getProveItemList() {
        return proveItemList;
    }

    public void setProveItemList(List<Prove_issues> proveItemList) {
        this.proveItemList = proveItemList;
    }
}
