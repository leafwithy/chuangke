package demo.example.chuangke.Fragments.Fragment_frag;

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

import java.util.ArrayList;
import java.util.List;

import demo.example.chuangke.Adapter.RecycleAdapter_new;
import demo.example.chuangke.Adapter.RecyclerAdapter_hot;
import demo.example.chuangke.R;
import demo.example.chuangke.Reality.Hot_issues;
import demo.example.chuangke.Reality.New_issues;

public class fragment_new extends Fragment {
    List<New_issues> items = new ArrayList<>();
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragmentidea_new,container,false);
        recyclerView = v.findViewById(R.id.recycleV1);
        initData();
        initView();
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
    private void initView(){
        recyclerView.setAdapter(new RecycleAdapter_new(getActivity(),items));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL));
    }
    private void initData(){
      //  items.add(new New_issues("标题","内容","大哥大","明天"));

    }

}

