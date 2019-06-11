package demo.example.chuangke.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import demo.example.chuangke.adapter.TitleFragmentPagerAdapter;
import demo.example.chuangke.Fragments.Fragment_frag.fragment_hot;
import demo.example.chuangke.Fragments.Fragment_frag.fragment_new;
import demo.example.chuangke.R;


public class Fragmentidea extends Fragment implements View.OnClickListener{
    private TabLayout layout;
    private ViewPager viewPager;
    ArrayList<Fragment> fragment = new ArrayList<Fragment>();
    List<String> titles = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragmentidea,container,false);
        viewPager = (ViewPager)v.findViewById(R.id.viewpager);
        layout =  (TabLayout)v.findViewById(R.id.tabLayout);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        TitleFragmentPagerAdapter adapter  = new TitleFragmentPagerAdapter(getFragmentManager(),fragment,titles);
        viewPager.setAdapter(adapter);
        layout.setupWithViewPager(viewPager);
        layout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void initView(){
        titles.add("热门推荐");
        titles.add("最新动态");
        titles.add("招贤组队");
        fragment.add(new fragment_hot());
        fragment.add(new fragment_new());
        fragment.add(new RecruitListFragment());
    }
    @Override
    public void onClick(View v) {

    }
}
