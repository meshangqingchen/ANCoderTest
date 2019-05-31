package com.example.ancodertest.FragmentTest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.ancodertest.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class FragmentTestActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView imageView1 ,imageView2,imageView3,imageView4;
    private List<Fragment> mListFragment = new ArrayList<>();
    private Fragment currentFragment;
    fragment_test1 welfareFragment1;
    fragment_test2 welfareFragment2;
    fragment_test3 welfareFragment3;
    fragment_test4 welfareFragment4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_test);
        imageView1 = (ImageView)findViewById(R.id.bottmimageview1);
        imageView2 = (ImageView)findViewById(R.id.bottmimageview2);
        imageView3 = (ImageView)findViewById(R.id.bottmimageview3);
        imageView4 = (ImageView)findViewById(R.id.bottmimageview4);

        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView3.setOnClickListener(this);
        imageView4.setOnClickListener(this);

        welfareFragment1 = new fragment_test1();
        welfareFragment2 = new fragment_test2();
        welfareFragment3 = new fragment_test3();
        welfareFragment4 = new fragment_test4();
        currentFragment = welfareFragment1;
        switchFragment(welfareFragment1);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bottmimageview1:
                switchFragment(welfareFragment1);
                break;
            case R.id.bottmimageview2:
                switchFragment(welfareFragment2);
                break;
            case R.id.bottmimageview3:
                switchFragment(welfareFragment3);
                break;
            case R.id.bottmimageview4:
                switchFragment(welfareFragment4);
                break;

            default:
                break;
        }
    }

    private void switchFragment(Fragment targetFragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        if (!mListFragment.contains(targetFragment)){
            transaction.hide(currentFragment).
                    add(R.id.fl_main,targetFragment).
                    show(targetFragment).
                    commit();

            mListFragment.add(targetFragment);
        }else {
            transaction
                    .hide(currentFragment)
                    .show(targetFragment)
                    .commit();
        }
        currentFragment = targetFragment;
    }
}
