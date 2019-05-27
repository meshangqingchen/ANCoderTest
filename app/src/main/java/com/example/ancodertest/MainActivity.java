package com.example.ancodertest;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import static java.util.Arrays.asList;

public class MainActivity extends AppCompatActivity implements MainAddapter.ItemOnClick {


    private RecyclerView recyclerView;
    MainClassTypeListModel listModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);
        initView();
    }

    private void initView() {
        listModel = new MainClassTypeListModel();
        recyclerView = (RecyclerView)findViewById(R.id.home_recycle_view);
        LinearLayoutManager manager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(manager);
        MainAddapter addapter = new MainAddapter();
        addapter.setOnClick(this);
        recyclerView.setAdapter(addapter);
        addapter.setDate(listModel.getDataList());
    }

    @Override
    public void onItemClick(int position, RecyclerView.ViewHolder holder) {

        MainClassTypeListModel.MainClassTypeModel model = listModel.getDataList().get(position);
        Intent intent = new Intent(this,model.getaClass());
        startActivity(intent);

//        try {
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }
}












