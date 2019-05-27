package com.example.ancodertest;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import java.util.List;

public class MainAddapter extends RecyclerView.Adapter <MainViewHoder>{

    private int ITEM_TITLE = 1;
    private List<MainClassTypeListModel.MainClassTypeModel> objects;
    public ItemOnClick onClick;


    public void setDate(List<MainClassTypeListModel.MainClassTypeModel> objects) {
        this.objects = objects;
        notifyDataSetChanged();
    }

    public void setOnClick(ItemOnClick onClick) {
        this.onClick = onClick;
    }

    @NonNull
    @Override
    public MainViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_relative_layout,viewGroup,false);
        return new MainViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MainViewHoder mainViewHoder, final int i) {
        mainViewHoder.setModel(this.objects.get(i));
        if (onClick != null){
            mainViewHoder.itemView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    onClick.onItemClick(i,mainViewHoder);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return objects == null ? 0 : objects.size();
    }


    @Override
    public int getItemViewType(int position) {
//        if (objects.get(position)instanceof Object){
//
//        }
        return ITEM_TITLE;
    }

    public interface ItemOnClick {
        /**
         * Rv点击事件回调
         *
         * @param position
         * @param tag
         */
        void onItemClick(int position, RecyclerView.ViewHolder holder);
    }

}
