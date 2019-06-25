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

import demo.example.chuangke.activity.Actiivty_idea_itemclick.IdeaHotissuesActivity;
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

    RecyclerView recyclerView;
    private HotListAdapter rc;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.idea_hot,container,false);
        recyclerView = v.findViewById(R.id.recycleV2);
        initData();
        initView();
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    private void initView(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getActivity()),DividerItemDecoration.VERTICAL));

    }

    private void initData(){
        getHotList();
    }

    private void setRecyclerView(List<Hot_issues> hotList){
        rc = new HotListAdapter(getActivity(),hotList);
        rc.onAttachedToRecyclerView(recyclerView);
        rc.setOnItemClickListener(new HotListAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(Context context) {
                IdeaHotissuesActivity.actionStart(context);
            }
        });
        recyclerView.setAdapter(rc);

    }

    private HotListResult handHotListRequest(String responseStr){
        try{
            JSONObject jsonArray = new JSONObject(responseStr);
            String jsonStr = jsonArray.toString();
          //  Log.d("responses",jsonStr);
            return new Gson().fromJson(jsonStr,HotListResult.class);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
    private void getHotList(){
        final String url = "http://192.168.191.1/get_hotpoints.php";
        RequestBody requestBody = HttpUtil.IdeaRequestBody.getHotListRequestBody("1");
        HttpUtil.sendRequest(url, requestBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        Log.d("hotListFragment", "网络连接失败");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                 String responseStr = response.body().string();
                final    HotListResult hotListResult = handHotListRequest(responseStr);

             //   Log.d("responses",responseStr);
                Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(hotListResult!=null) {
                            //Log.d("responses",""+hotListResult.getHotpItemList().get(0));
                            if (hotListResult.getStatus() ==0 ) {

                                Log.d("hotListFragment", "数据库连接失败");
                            } else if (hotListResult.getStatus()==1&&hotListResult.getHotpItemList() == null) {

                                Log.d("hotListFragment", "列表为空");
                            } else {
                                setRecyclerView(hotListResult.getHotpItemList());
                                Log.d("hotListFragment", "获取列表成功");
                            }
                        }else{
                            Log.d("hotListFragment", "url错误");
                        }
                    }
                });
            }
        });
    }
}

