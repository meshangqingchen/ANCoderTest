package com.example.ancodertest.TabLayoutTest;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.ancodertest.R;

import java.util.ArrayList;

public class TabLayoutTestActivity extends AppCompatActivity {
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout_test);

        ArrayList<String>titles = new ArrayList<>();
        ArrayList<Fragment>fragments = new ArrayList<>();


        fragments.add(TypeFragment.newInstance());
        fragments.add(TypeFragment.newInstance());
        fragments.add(TypeFragment.newInstance());

        titles.add("tab1");
        titles.add("tab2");
        titles.add("tab3");

        mViewPager = findViewById(R.id.viewpager);
        TabLayout tableLayout = (TabLayout) findViewById(R.id.tablayout);

        TypePageAdapter typePageAdapter = new TypePageAdapter(getSupportFragmentManager());
        typePageAdapter.setData(fragments,titles);

        mViewPager.setAdapter(typePageAdapter);
        mViewPager.setOffscreenPageLimit(titles.size()-1);
        tableLayout.setupWithViewPager(mViewPager);
    }
}
