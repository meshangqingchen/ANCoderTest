package com.example.ancodertest.ArrayAdapterTest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.ancodertest.R;



public class ArrayAdapterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_array_adapter);

        ListView list1 = (ListView) findViewById(R.id.list1);
        // 定义一个数组
        String[] arr1 = {
                "孙悟空", "猪八戒", "牛魔王"
//                ,"孙悟空", "猪八戒", "牛魔王" ,
//                "孙悟空", "猪八戒", "牛魔王" ,
//                "孙悟空", "猪八戒", "牛魔王" ,
//                "孙悟空", "猪八戒", "牛魔王" ,
//                "孙悟空", "猪八戒", "牛魔王" ,
//                "孙悟空", "猪八戒", "牛魔王" ,
//                "孙悟空", "猪八戒", "牛魔王" ,
//                "孙悟空", "猪八戒", "牛魔王" ,
//                "孙悟空", "猪八戒", "牛魔王"
        };
        // 将数组包装为ArrayAdapter
        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>
                (this, R.layout.array_adapter_array_item, arr1);
        // 为ListView设置Adapter
        list1.setAdapter(adapter1);


        ListView list2 = (ListView) findViewById(R.id.list2);
        // 定义一个数组
        String[] arr2 = { "Java", "Hibernate", "Spring" , "Android" };
        // 将数组包装为ArrayAdapter
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>
                (this, R.layout.array_adapter_checked_item, arr2);
        // 为ListView设置Adapter
        list2.setAdapter(adapter2);
    }
}
