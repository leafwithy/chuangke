package demo.example.chuangke.fragments;

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
import android.widget.Button;
import android.widget.Toast;

import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import demo.example.chuangke.activity.Activity_shouye_btn.MainJourneyActivity;
import demo.example.chuangke.activity.Activity_shouye_btn.MainHandbookActivity;
import demo.example.chuangke.activity.Activity_shouye_btn.MainMessageActivity;
import demo.example.chuangke.activity.Activity_shouye_btn.MainProveActivity;

import demo.example.chuangke.adapter.MainListAdapter;
import demo.example.chuangke.gson.ProveListResult;
import demo.example.chuangke.gson.Prove_issues;
import demo.example.chuangke.loader.GlideImageLoader;
import demo.example.chuangke.R;

import demo.example.chuangke.util.HttpUtil;
import okhttp3.Call;
import okhttp3.Callback;

import okhttp3.RequestBody;
import okhttp3.Response;

public class MainFragment extends Fragment implements View.OnClickListener {
    List<String> images = new ArrayList<>();

    private RecyclerView recyclerView;
    private Button btn1,btn2,btn3;
    private Banner banner;
    private MainListAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.main,container,false);
        recyclerView = v.findViewById(R.id.recycleV_shouye);
        btn1 = v.findViewById(R.id.rumenshouce);
        btn2 = v.findViewById(R.id.xinlulicheng);
        btn3 = v.findViewById(R.id.zuixinzixun);
        banner = v.findViewById(R.id.banner);
        initData();
        initView();
        return v;
    }
    private void initData(){
        // items.add(new Hot_issues(0,null,0,0,null,null,R.drawable.ic_launcher_background));

        images.add("http://image14.m1905.cn/uploadfile/2018/0907/thumb_1_1380_460_20180907013518839623.jpg");
        images.add("http://image14.m1905.cn/uploadfile/2018/0906/thumb_1_1380_460_20180906040153529630.jpg");
        images.add("http://image13.m1905.cn/uploadfile/2018/0907/thumb_1_1380_460_20180907114844929630.jpg");
        getProveList();
    }
    private void initView(){
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(images);
        banner.start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(getActivity(),"position: "+position,Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(Objects.requireNonNull(getActivity()),DividerItemDecoration.VERTICAL));
        btn1.setBackgroundResource(R.drawable.rumenshouce);
        btn2.setBackgroundResource(R.drawable.xinlulicheng);
        btn3.setBackgroundResource(R.drawable.zuixinzixun);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);

    }
    private void setRecyclerView(List<Prove_issues> items){

        adapter = new MainListAdapter(getActivity(),items);
        adapter.onAttachedToRecyclerView(recyclerView);
        adapter.setOnItemClickListener(new MainListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Context context) {
                MainProveActivity.actionStart(context);
            }

        });
        recyclerView.setAdapter(adapter);
    }



    @Override
    public void onClick(View v) {
            switch (v.getId()) {
                case R.id.rumenshouce:
                    MainHandbookActivity.actionStart(Objects.requireNonNull(getContext()));
                    break;
                case R.id.xinlulicheng:
                    MainJourneyActivity.actionStart(Objects.requireNonNull(getContext()));
                    break;
                case R.id.zuixinzixun:
                    MainMessageActivity.actionStart(Objects.requireNonNull(getContext()));
            }
    }
    private ProveListResult handProveItemList(String responseStr){
        try{
            JSONObject jsonArray = new JSONObject(responseStr);
            String jsonStr = jsonArray.toString();
            return new Gson().fromJson(jsonStr,ProveListResult.class);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void getProveList(){
        String url = "http://192.168.191.1/get_provelist.php";
        RequestBody requestbody = HttpUtil.MainRequestBody.getProveItemListRequestBody("1");
        HttpUtil.sendRequest(url, requestbody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

                Log.d("proveList", "网络连接失败");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseStr = response.body().string();
                final ProveListResult proveListResult= handProveItemList(responseStr);
                Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(proveListResult!=null) {
                            if (proveListResult.getStatus() == 0) {

                                Log.d("proveList", "数据库连接失败");
                            } else if (proveListResult.getStatus() != 0 && proveListResult.getProveItemList() == null) {

                                Log.d("proveList", "列表为空");
                            } else {
                                setRecyclerView(proveListResult.getProveItemList());

                                Log.d("proveList", "获取列表成功");
                            }
                        }else{

                            Log.d("proveList", "url错误");
                        }
                    }
                });
            }
        });
    }
}
