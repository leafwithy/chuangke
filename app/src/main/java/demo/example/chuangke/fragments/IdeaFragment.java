package demo.example.chuangke.fragments;

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

import demo.example.chuangke.fragments.Fragment_frag.RecruitListFragment;
import demo.example.chuangke.adapter.TitleFragmentPagerAdapter;
import demo.example.chuangke.fragments.Fragment_frag.HotListFragment;
import demo.example.chuangke.fragments.Fragment_frag.NewListFragment;
import demo.example.chuangke.R;


public class IdeaFragment extends Fragment {
    ArrayList<Fragment> fragment = new ArrayList<>();
    List<String> titles = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.idea,container,false);
        initView(v);
        return v;
    }

    private void initView(View v) {
        ViewPager viewPager =v.findViewById(R.id.viewpager);
        TabLayout layout =  v.findViewById(R.id.tabLayout);

        titles.add("热门推荐");
        titles.add("最新动态");
        titles.add("招贤组队");
        fragment.add(new HotListFragment());
        fragment.add(new NewListFragment());
        fragment.add(new RecruitListFragment());

        TitleFragmentPagerAdapter adapter = null;
        if (fragment != null && titles != null) {
            adapter = new TitleFragmentPagerAdapter(getFragmentManager(), fragment, titles);
        }
        viewPager.setAdapter(adapter);
        layout.setupWithViewPager(viewPager);
    }
}
