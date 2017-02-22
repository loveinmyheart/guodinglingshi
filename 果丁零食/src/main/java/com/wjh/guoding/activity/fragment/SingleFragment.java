package com.wjh.guoding.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wjh.guoding.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lenovo on 2017/2/17.
 */
public class SingleFragment extends Fragment {

    String []tags={
            "精选优品",
            "每日折扣"
    };

    List<Fragment> fragments=new ArrayList<>();
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private FragmentPagerAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_single_layout, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //初始化控件
        initViews(view);
        //初始化adapter
        initAdapter();
        //初始化viewpager
        viewPager.setAdapter(adapter);
        //初始化tablayout
//        tabLayout.setTabMode(TabLayout.MODE_FIXED);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(0);
    }

    private void initAdapter() {
        final SingleFirstFragment fragment1=new SingleFirstFragment();
        SingleSecondFragment fragment2=new SingleSecondFragment();
        fragments.add(fragment1);
        fragments.add(fragment2);
        adapter = new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int position) {

                return fragments.get(position);
            }

            @Override
            public int getCount() {
                return tags.length;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return tags[position];
            }
        };
    }

    private void initViews(View view) {
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        tabLayout = (TabLayout) view.findViewById(R.id.tablayout);
    }
}
