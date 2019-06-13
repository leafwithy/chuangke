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
import java.util.ArrayList;
import java.util.List;

import demo.example.chuangke.adapter.HotListAdapter;
import demo.example.chuangke.R;
import demo.example.chuangke.gson.HotListResult;
import demo.example.chuangke.gson.Hot_issues;
import demo.example.chuangke.util.HttpUtil;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HotListFragment extends Fragment {
    List<Hot_issues> items = new ArrayList<>();
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmentidea_hot,container,false);
        initView(v);
        initData();
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initView(View v){
        recyclerView = v.findViewById(R.id.recycleV2);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));

    }

    private void initData(){
        getHotList();
    }

    private void setRecyclerView(List<Hot_issues> hotList){
        HotListAdapter rc = new HotListAdapter(getActivity(),hotList);
        rc.setOnItemClickListener(new HotListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, String str) {
                Toast.makeText(getContext(),str,Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(rc);
    }
    private HotListResult handHotListRequest(String responseStr){
        try{
            JSONObject jsonArray = new JSONObject(responseStr);
            String jsonStr = jsonArray.toString();
            return new Gson().fromJson(jsonStr,HotListResult.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    private void getHotList(){
        final String url = "";
        RequestBody requestBody = HttpUtil.IdeaRequestBody.getHotListRequestBody("");
        HttpUtil.sendRequest(url, requestBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getContext(), "网络连接失败", Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String responseStr = response.body().string();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        HotListResult hotListResult = handHotListRequest(responseStr);
                        if(hotListResult.getStatus()!=1||hotListResult==null){
                            Toast.makeText(getContext(), "获取列表失败", Toast.LENGTH_SHORT).show();
                        }else if(hotListResult.getHot_issuesList()==null){
                            Toast.makeText(getContext(), "列表为空", Toast.LENGTH_SHORT).show();
                        }else{
                            setRecyclerView(hotListResult.getHot_issuesList());
                            Toast.makeText(getContext(), "获取列表成功", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}

