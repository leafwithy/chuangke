package demo.example.chuangke.fragments.Fragment_frag;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import demo.example.chuangke.activity.Actiivty_idea_itemclick.IdeaNewissuesActivity;
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
        View v = inflater.inflate(R.layout.idea_new,container,false);
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
        recyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getActivity()),DividerItemDecoration.VERTICAL));
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
        rc.onAttachedToRecyclerView(recyclerView);
        rc.setOnItemClickListener(new NewListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(Context context) {
                IdeaNewissuesActivity.actionStart(context);
            }
        });
        recyclerView.setAdapter(rc);
    }

    private void getNewList(){
        final String url ="http://192.168.191.1/get_newnews.php";
        RequestBody requestBody = HttpUtil.IdeaRequestBody.getNewListRequestBody("1");
        HttpUtil.sendRequest(url, requestBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Log.d("newlistfragment","网络连接失败");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                 String newList = response.body().string();
                final NewListResult newListResult = handNewItemResource(newList);
                Log.d("newlistsize", newList);
            Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if(newListResult!=null) {
                        if (newListResult.getStatus() ==0) {

                            Log.d("newlistfragment","数据库连接失败");
                        } else if (newListResult.getStatus()==1&&newListResult.getNewItemList() == null) {

                            Log.d("newlistfragment","列表为空");
                        } else {
                            setRecyclerView(newListResult.getNewItemList());

                            Log.d("newlistfragment","成功获取列表");
                        }
                    }else{
                        Log.d("newlistfragment","url错误");
                    }
                }
                });
            }
        });
    }

}

