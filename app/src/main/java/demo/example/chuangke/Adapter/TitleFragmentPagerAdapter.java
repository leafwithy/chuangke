package demo.example.chuangke.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TitleFragmentPagerAdapter extends FragmentPagerAdapter {
    List<Fragment> mFragmentList =null;
    private List<String> titles;

    public TitleFragmentPagerAdapter(FragmentManager fragmentManager, ArrayList<Fragment> fragments){
        super(fragmentManager);
        mFragmentList = fragments ;
    }
    public TitleFragmentPagerAdapter(FragmentManager fragmentManager, ArrayList<Fragment> fragments , List<String> titles){
        super(fragmentManager);
        this.mFragmentList = fragments ;
        this.titles = titles;
    }

    public TitleFragmentPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;
        if(i<mFragmentList.size()){
            fragment = mFragmentList.get(i);
        }else{
            fragment = mFragmentList.get(0);
        }
        return fragment;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(titles!=null&&titles.size()>0){
            return titles.get(position);
        }
        return null;
    }

}
