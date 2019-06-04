package demo.example.chuangke.Fragments;

import android.content.Intent;
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
import android.widget.Button;
import android.widget.Toast;

import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;

import java.util.ArrayList;
import java.util.List;

import demo.example.chuangke.Adapter.RecyclerAdapter_hot;
import demo.example.chuangke.Li_activity;
import demo.example.chuangke.Loader.GlideImageLoader;
import demo.example.chuangke.New_activity;
import demo.example.chuangke.R;
import demo.example.chuangke.Reality.Hot_issues;
import demo.example.chuangke.Study_activity;

public class FragmentShouye extends Fragment implements View.OnClickListener {
    List<String> images = new ArrayList<>();
    List<Hot_issues> items = new ArrayList<>();
    private RecyclerView recyclerView;
    private Button btn1,btn2,btn3;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragmentshouye,container,false);
        initData();
        Banner banner = v.findViewById(R.id.banner);
        banner.setImageLoader(new GlideImageLoader());
        banner.setImages(images);
        banner.start();
        banner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Toast.makeText(getActivity(),"position: "+position,Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView = v.findViewById(R.id.recycleV_shouye);
        btn1 = v.findViewById(R.id.rumenshouce);
        btn2 = v.findViewById(R.id.xinlulicheng);
        btn3 = v.findViewById(R.id.zuixinzixun);

        initView();

        return v;
    }

    private void initView(){
        recyclerView.setAdapter(new RecyclerAdapter_hot(getActivity(),items));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        btn1.setBackgroundResource(R.drawable.rumenshouce);
        btn2.setBackgroundResource(R.drawable.xinlulicheng);
        btn3.setBackgroundResource(R.drawable.zuixinzixun);
        btn1.setOnClickListener(this);

    }
    private void initData(){
        items.add(new Hot_issues(0,null,0,0,null,null,R.drawable.ic_launcher_background));
        images.add("http://image14.m1905.cn/uploadfile/2018/0907/thumb_1_1380_460_20180907013518839623.jpg");
        images.add("http://image14.m1905.cn/uploadfile/2018/0906/thumb_1_1380_460_20180906040153529630.jpg");
        images.add("http://image13.m1905.cn/uploadfile/2018/0907/thumb_1_1380_460_20180907114844929630.jpg");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rumenshouce:
                startActivity(new Intent(getActivity(),Study_activity.class));
                break;
            case R.id.xinlulicheng:
                startActivity(new Intent(getActivity(), Li_activity.class));
                break;
            case R.id.zuixinzixun:
                startActivity(new Intent(getActivity(), New_activity.class));
        }
    }
}
