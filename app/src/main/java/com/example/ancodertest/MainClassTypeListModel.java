package com.example.ancodertest;

import com.example.ancodertest.AppBarLayoutTest.AppBarLayoutActivity;
import com.example.ancodertest.GestureDetector.GestureDetector;
import android.widget.ExpandableListAdapter;
import android.widget.TextView;

import com.example.ancodertest.ActivityThelifecycle.ActivityThelifecycle;
import com.example.ancodertest.AdapterViewFlipperTest.AdapterViewFlipperActivity;
import com.example.ancodertest.ArrayAdapterTest.ArrayAdapterActivity;
import com.example.ancodertest.ExpandableListViewTest.ExpandableListViewActivity;
import com.example.ancodertest.FragmentTest.FragmentTestActivity;
import com.example.ancodertest.FrameLayout.FrameLayoutActivity;
import com.example.ancodertest.GridLayout.GridLayoutActivity;
import com.example.ancodertest.GridLayout.GridViewActivity;
import com.example.ancodertest.ImageButtonTest.ImageButtonActivi;
import com.example.ancodertest.ImageView.ImageViewActivity;
import com.example.ancodertest.InternPush.IntentPushActivityB;
import com.example.ancodertest.LinearLayout.LinearLayoutActivity;
import com.example.ancodertest.ProgressBarTest.ProgressBarTestActivity;
import com.example.ancodertest.QuickContactBadgeTest.QuickContactBadgeActivity;
import com.example.ancodertest.RelativeLayout.RelativeLayoutActivity;
import com.example.ancodertest.ScrowViewActivityTest.LinkageNestedScrollView;
import com.example.ancodertest.ScrowViewActivityTest.ScrowViewActivity;
import com.example.ancodertest.SeekBarTest.SeekBarTestActivity;
import com.example.ancodertest.SimpleListViewTest.SimpleListViewActivity;
import com.example.ancodertest.StackViewTest.StackViewActivityTest;
import com.example.ancodertest.TableLayout.TableLayoutActivity;
import com.example.ancodertest.Test.TestActivity;
import com.example.ancodertest.ListActivityTest.listViewActivity;
import com.example.ancodertest.TheCustomViewTest.CustomStarView;
import com.example.ancodertest.ToolBarTest.ToolBarActivity;
import com.example.ancodertest.ViewSwitcherTest.ImageSwitcherActivity;
import com.example.ancodertest.ViewSwitcherTest.TextSwitcherActivity;
import com.example.ancodertest.ViewSwitcherTest.ViewSwitcherTestActivity;
import com.example.ancodertest.InternPush.intentPushActivity;

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
            new MainClassTypeModel("ImageViewActivity","imageView图片浏览器", ImageViewActivity.class),
            new MainClassTypeModel("ImageButtonActivi","图片的Button", ImageButtonActivi.class),
            new MainClassTypeModel("QuickContactBadgeActivity","快速联系QuickContactBadge", QuickContactBadgeActivity.class),
            new MainClassTypeModel("SimpleListViewActivity","超简单ListView", SimpleListViewActivity.class),
            new MainClassTypeModel("ArrayAdapterActivity","ArrayAdapter配合ListView", ArrayAdapterActivity.class),
            new MainClassTypeModel("listViewActivity","类似tableViewController",listViewActivity.class),
            new MainClassTypeModel("GridViewActivity","GridView SimpleAdapter", GridViewActivity.class),
            new MainClassTypeModel("ExpandableListViewActivity","可扩展的ListView", ExpandableListViewActivity.class),
            new MainClassTypeModel("AdapterViewFlipperActivity","Flipper", AdapterViewFlipperActivity.class),
            new MainClassTypeModel("StackViewActivityTest","stack 栈试图", StackViewActivityTest.class),
            new MainClassTypeModel("ProgressBarTestActivity","进度条", ProgressBarTestActivity.class),
            new MainClassTypeModel("SeekBarTestActivity","SeekBar控制音乐电影播放进度", SeekBarTestActivity.class),
            new MainClassTypeModel("ViewSwitcherTestActivity","类似collectionView", ViewSwitcherTestActivity.class),
            new MainClassTypeModel("ImageSwitcherActivity","---", ImageSwitcherActivity.class),
            new MainClassTypeModel("TextSwitcherActivity","text", TextSwitcherActivity.class),
            new MainClassTypeModel("intentPushActivity","跳转传值反向传值",intentPushActivity.class),
            new MainClassTypeModel("FragmentTestActivity","Fragent 测试", FragmentTestActivity.class),
            new MainClassTypeModel("ActivityThelifecycle","activity生命周期", ActivityThelifecycle.class),
            new MainClassTypeModel("GestureDetector","手势探测器", com.example.ancodertest.GestureDetector.GestureDetector.class),
            new MainClassTypeModel("CustomStarView","自定义✨view", CustomStarView.class),
            new MainClassTypeModel("ScrowViewActivity","ScrowView测试", ScrowViewActivity.class),
            new MainClassTypeModel("LinkageNestedScrollView","嵌套NestedScrollView", LinkageNestedScrollView.class),
            new MainClassTypeModel("ToolBarActivity","ToolBar测试", ToolBarActivity.class),
            new MainClassTypeModel("AppBarLayoutActivity","AppBarLayout测试", AppBarLayoutActivity.class)
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
