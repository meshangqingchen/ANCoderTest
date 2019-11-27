package com.example.ancodertest.ClickToBig;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ancodertest.R;

import java.util.List;
import java.util.Random;

public class ReAdapter extends RecyclerView.Adapter<ReAdapter.MyViewHolder> {

    private int i;
    private Context context;
    private itemOnClick itemOnclick;
    private int item;
    //获得点击的下标 由外部传进来进行判断哪个要放大（即点即了哪个或者选中了哪个）
    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    //数据源
    public void setData(Context context, int i) {
        this.context = context;
        this.i = i;
    }

    //设置回调接口
    public void setItemOnClick(itemOnClick itemOnclick) {
        this.itemOnclick = itemOnclick;
    }

    //点击事件回调出去
    public interface itemOnClick {
        void OnClick(int item);
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new MyViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.click_to_big_cell_layout,viewGroup,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        TextView tv = myViewHolder.itemView.findViewById(R.id.speed_tv);
        tv.setBackgroundColor(0xff000000 | new Random().nextInt(0x00ffffff));
        tv.setText(String.valueOf(i));
        if (getItem() == i) {
            //1.1为原来的大小+1的0.1倍放
            if (myViewHolder.itemView != null){

                myViewHolder.itemView.setPivotY(150f);
                myViewHolder.itemView.setPivotX(150f);
                myViewHolder.itemView.setScaleX(1.1f);
                myViewHolder.itemView.setScaleY(1.1f);
            }
        } else {
            //缩小同理   1为布局设定的大小
            if (myViewHolder.itemView != null){
                myViewHolder.itemView.setScaleX(1f);
                myViewHolder.itemView.setScaleY(1f);
            }
        }
        //item监听
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemOnclick != null){
                    itemOnclick.OnClick(i);
                }
            }
        });
    }


    //重写方法保证不会数据错乱
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return i;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{


        MyViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
