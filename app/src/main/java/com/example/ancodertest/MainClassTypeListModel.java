package com.example.ancodertest;

import android.widget.TextView;

import com.example.ancodertest.FrameLayout.FrameLayoutActivity;
import com.example.ancodertest.GridLayout.GridLayoutActivity;
import com.example.ancodertest.ImageView.ImageViewActivity;
import com.example.ancodertest.LinearLayout.LinearLayoutActivity;
import com.example.ancodertest.RelativeLayout.RelativeLayoutActivity;
import com.example.ancodertest.TableLayout.TableLayoutActivity;
import com.example.ancodertest.Test.TestActivity;
import java.util.List;
import static java.util.Arrays.asList;

public class MainClassTypeListModel {
    List<MainClassTypeModel> dataList = asList(
            new MainClassTypeModel("TestActivity","第一个测试而已", TestActivity.class),
            new MainClassTypeModel("LinearLayoutActivity","测试线性布局", LinearLayoutActivity.class),
            new MainClassTypeModel("TableLayoutActivity","测试丰富的表格布局", TableLayoutActivity.class),
            new MainClassTypeModel("FrameLayoutActivity","测试帧布局", FrameLayoutActivity.class),
            new MainClassTypeModel("RelativeLayoutActivity","相对布局", RelativeLayoutActivity.class),
            new MainClassTypeModel("GridLayoutActivity","网格布局", GridLayoutActivity.class),
            new MainClassTypeModel("ImageViewActivity","imageView图片浏览器", ImageViewActivity.class)
    );

    public List<MainClassTypeModel> getDataList() {
        return dataList;
    }

    public class MainClassTypeModel{

        String className;
        String describe;
        Class aClass;

        public MainClassTypeModel(String classNameStr, String describeStr, Class aClass){
            super();
            className = classNameStr;
            describe = describeStr;
            this.aClass = aClass;
        }

        public String getClassName() {
            return className;
        }

        public String getDescribe() {
            return describe;
        }

        public Class getaClass() {
            return aClass;
        }
    }
}
