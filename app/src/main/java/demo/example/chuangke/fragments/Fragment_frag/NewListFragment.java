package demo.example.chuangke.fragments.Fragment_frag;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import demo.example.chuangke.adapter.NewListAdapter;
import demo.example.chuangke.R;
import demo.example.chuangke.gson.NewListResult;
import demo.example.chuangke.gson.New_issues;
import demo.example.chuangke.util.HttpUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NewListFragment extends Fragment {
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmentidea_new,container,false);
        initView(v);
        initData();
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initView(View v){
        recyclerView = v.findViewById(R.id.recycleV1);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
    }

    private void initData(){
        getNewList();
    }

    //处理获取到的最新资讯数据
    private NewListResult handNewItemResource(String responseStr){
        try{
            JSONObject jsonArray = new JSONObject(responseStr);
            String jsonStr = jsonArray.toString();
            return new Gson().fromJson(jsonStr,NewListResult.class);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    //设置RecycleView子布局
    private void setRecyclerView(List<New_issues> newList){
        NewListAdapter rc = new NewListAdapter(getActivity(),newList);
        rc.setOnItemClickListener(new NewListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View v, String str) {
                Toast.makeText(getContext(),str,Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(rc);
    }

    private void getNewList(){
        final String url ="https://localhost/getNew.php";
        RequestBody requestBody = HttpUtil.IdeaRequestBody.getNewListRequestBody("");
        HttpUtil.sendRequest(url, requestBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(),"网络连接失败",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String newList = response.body().string();
                final NewListResult newListResult = handNewItemResource(newList);
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {

                    if (newListResult.getStatus() != 1 || newListResult == null) {
                        Toast.makeText(getContext(), "获取最新资讯列表失败", Toast.LENGTH_SHORT).show();
                    } else if (newListResult.getNew_issuesList() == null) {
                        Toast.makeText(getContext(), "列表为空", Toast.LENGTH_SHORT).show();
                    } else {
                        setRecyclerView(newListResult.getNew_issuesList());
                        Toast.makeText(getContext(), "成功获取列表", Toast.LENGTH_SHORT).show();
                    }
                }
                });
            }
        });
    }

}

