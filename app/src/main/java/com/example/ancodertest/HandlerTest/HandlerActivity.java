package com.example.ancodertest.HandlerTest;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.ancodertest.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class HandlerActivity extends AppCompatActivity {

    @BindView(R.id.show)
    public TextView mTextView;
    public Handler mHandler;

    // 步骤1：（自定义）新创建Handler子类(继承Handler类) & 复写handleMessage（）方法
    class Mhandler extends Handler {

        // 通过复写handlerMessage() 从而确定更新UI的操作
        @Override
        public void handleMessage(Message msg) {
            // 根据不同线程发送过来的消息，执行不同的UI操作
            // 根据 Message对象的what属性 标识不同的消息
            switch (msg.what) {
                case 1:
                    mTextView.setText("执行了线程1的UI操作");
                    break;
                case 2:
                    mTextView.setText("执行了线程2的UI操作");
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);
        ButterKnife.bind(this);

        Sample sample = new Sample();
        sample.test();

        Sample sample2 = new Sample();
        sample2.test();

        mHandler = new Mhandler();
        new Thread(){
             public void run() {
                 try {
                     Thread.sleep(3000);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }

                 // 步骤3：创建所需的消息对象
                 Message msg = Message.obtain();
                 msg.what = 1; // 消息标识
                 msg.obj = "A"; // 消息内存存放

                 // 步骤4：在工作线程中 通过Handler发送消息到消息队列中
                 mHandler.sendMessage(msg);
             }
         }.start();

         new Thread(new Runnable() {
             @Override
             public void run() {
                 try {
                     Thread.sleep(6000);
                 } catch (InterruptedException e) {
                     e.printStackTrace();
                 }
                 // 通过sendMessage（）发送
                 // a. 定义要发送的消息
                 Message msg = Message.obtain();
                 msg.what = 2; //消息的标识
                 msg.obj = "B"; // 消息的存放
                 // b. 通过Handler发送消息到其绑定的消息队列
                 mHandler.sendMessage(msg);

             }
         }).start();

         //3
        new Thread(){
            public void run() {
                try {
                    Thread.sleep(9000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // 步骤3：创建所需的消息对象
                Message msg = Message.obtain();
                msg.what = 1; // 消息标识
                msg.obj = "A"; // 消息内存存放

                // 步骤4：在工作线程中 通过Handler发送消息到消息队列中
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mTextView.setText("执行了线程3的UI操作");
                    }
                });
            }
        }.start();
    }

}
