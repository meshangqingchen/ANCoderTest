package com.example.ancodertest.MarqueeLayout;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import com.example.ancodertest.R;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class MarqueeLayoutActivity extends AppCompatActivity {
    @BindView(R.id.marquee_layout)
    MarqueeLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marquee_layout);
        ButterKnife.bind(this);
        List<String> headLines = new ArrayList<>();
        headLines.add("0");
        headLines.add("1");
        headLines.add("2");
        headLines.add("3");
        headLines.add("4");
        headLines.add("5");
        headLines.add("6");
        headLines.add("7");
        headLines.add("8");
        headLines.add("9");
        MarqueeLayoutAdapter <String> topAdapter = new MarqueeLayoutAdapter<String>(headLines) {
            @Override
            protected int getItemLayoutId() {
                return R.layout.template_headline_marquee_item;
            }

            @Override
            protected void initView(View view, int position, String item) {
                ((TextView)view).setText(item);
            }
        };
        topAdapter.setItemClickListener(new OnItemClickListener() {
            @Override
            public void onClick(View view, int position) {
                Log.i("view",position+"");
            }
        },null);
        layout.setAdapter(topAdapter);
        layout.start();
    }
}
