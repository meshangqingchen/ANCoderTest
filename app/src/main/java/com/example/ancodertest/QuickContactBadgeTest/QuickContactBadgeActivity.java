package com.example.ancodertest.QuickContactBadgeTest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.QuickContactBadge;

import com.example.ancodertest.R;

public class QuickContactBadgeActivity extends AppCompatActivity {
    QuickContactBadge badge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quick_contact_badge);

        // 获取QuickContactBadge组件
        badge = (QuickContactBadge) findViewById(R.id.badge);
        // 将QuickContactBadge组件与特定电话号码对应的联系人建立关联
        badge.assignContactFromPhone("020-88888888", false);
    }
}
