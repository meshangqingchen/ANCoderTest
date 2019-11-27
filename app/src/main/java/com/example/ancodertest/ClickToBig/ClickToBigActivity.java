package com.example.ancodertest.ClickToBig;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ancodertest.R;
public class ClickToBigActivity extends AppCompatActivity {

    private RecyclerView recyclerView_project;
    private ReAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_click_to_big);

        recyclerView_project = findViewById(R.id.re);

        adapter = new ReAdapter();
        adapter.setData(this, 28);//设置数据
        adapter.setHasStableIds(true);
        recyclerView_project.setLayoutManager(new GridLayoutManager(this, 1, GridLayoutManager.HORIZONTAL, false));//网格布局
        recyclerView_project.setAdapter(adapter);//设置适配器
        //适配器的点击事件
        adapter.setItemOnClick(new ReAdapter.itemOnClick() {
            @Override
            public void OnClick(int item) {
                adapter.setItem(item);
                //刷新视图
                adapter.notifyDataSetChanged();
            }
        });
    }
}
