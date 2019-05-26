package demo.example.chuangke.Fragments;

import android.media.Image;
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

import demo.example.chuangke.Adapter.RecyclerAdapter;
import demo.example.chuangke.Loader.GlideImageLoader;
import demo.example.chuangke.R;

public class FragmentShouye extends Fragment {
    List<String> images = new ArrayList<>();
    String[] items = new String[]{
        "风声","雨声","读书声","声声入耳"
    };
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
        recyclerView.setAdapter(new RecyclerAdapter(getActivity(),items));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
        btn1.setBackgroundResource(R.drawable.rumenshouce);
        btn2.setBackgroundResource(R.drawable.xinlulicheng);
        btn3.setBackgroundResource(R.drawable.zuixinzixun);

    }
    private void initData(){

        images.add("http://image14.m1905.cn/uploadfile/2018/0907/thumb_1_1380_460_20180907013518839623.jpg");
        images.add("http://image14.m1905.cn/uploadfile/2018/0906/thumb_1_1380_460_20180906040153529630.jpg");
        images.add("http://image13.m1905.cn/uploadfile/2018/0907/thumb_1_1380_460_20180907114844929630.jpg");
    }
}
