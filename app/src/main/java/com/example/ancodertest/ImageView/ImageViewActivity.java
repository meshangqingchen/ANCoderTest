package com.example.ancodertest.ImageView;

import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.graphics.Bitmap;

import com.example.ancodertest.R;

public class ImageViewActivity extends AppCompatActivity {

    // 定义一个访问图片的数组
    int[] images = new int[]{
            R.drawable.lijiang,
            R.drawable.qiao,
            R.drawable.shuangta,
            R.drawable.shui,
            R.drawable.xiangbi,
    };
    // 定义默认显示的图片
    int currentImg = 2;
    // 定义图片的初始透明度
    private int alpha = 255;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);

        final Button plus = (Button) findViewById(R.id.zendatoumingdu);
        final Button minus = (Button) findViewById(R.id.jiangditoumingdu);
        final Button next = (Button) findViewById(R.id.xiayizhang);
        final ImageView image1 = (ImageView) findViewById(R.id.bigimage);
        final ImageView image2 = (ImageView) findViewById(R.id.smailimage);

        // 定义查看下一张图片的监听器
        next.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // 控制ImageView显示下一张图片
                image1.setImageResource(
                        images[++currentImg % images.length]);
            }
        });

        // 定义改变图片透明度的方法
        View.OnClickListener listener = new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (v == plus)
                {
                    alpha += 20;
                }
                if (v == minus)
                {
                    alpha -= 20;
                }
                if (alpha >= 255)
                {
                    alpha = 255;
                }
                if (alpha <= 0)
                {
                    alpha = 0;
                }
                // 改变图片的透明度
                image1.setImageAlpha(alpha);
            }
        };
        // 为两个按钮添加监听器
        plus.setOnClickListener(listener);
        minus.setOnClickListener(listener);


        image1.setOnTouchListener(new View.OnTouchListener(){
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) image1
                        .getDrawable();
                // 获取第一个图片显示框中的位图
                Bitmap bitmap = bitmapDrawable.getBitmap();
                System.out.println(bitmap.getWidth());
                System.out.println(image1.getWidth());
                // bitmap图片实际大小与第一个ImageView的缩放比例
                double scaleX = 1.0 * bitmap.getWidth() / image1.getWidth();
                double scaleY = 1.0 * bitmap.getHeight() / image1.getHeight();


                // 获取需要显示的图片的开始点
                System.out.println("x = "+event.getX());
                System.out.println("y = "+event.getY());

                int x = (int) (event.getX() * scaleX);
                int y = (int) (event.getY() * scaleY);

                if (x + 60 > bitmap.getWidth())
                {
                    x = bitmap.getWidth() - 60;
                }

                if (y + 60 > bitmap.getHeight())
                {
                    y = bitmap.getHeight() - 60;
                }

                if (x - 60 <= 0){
                    x = 60;
                }

                if (y - 60 <= 0){
                    y = 60;
                }

                // 显示图片的指定区域
                image2.setImageBitmap(Bitmap.createBitmap(bitmap
                        , x-60, y-60, 120, 120));
                image2.setImageAlpha(alpha);
                return false;
            }
        });

    }
}

















