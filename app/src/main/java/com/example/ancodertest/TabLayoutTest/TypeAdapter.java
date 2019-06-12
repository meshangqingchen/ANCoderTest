package com.example.ancodertest.TabLayoutTest;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.ancodertest.R;

import java.util.List;

public class TypeAdapter extends RecyclerView.Adapter <TypeViewHoder>{

    private List<String>objects;

    public void setDate(List<String> objects) {
        this.objects = objects;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TypeViewHoder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.pageview_recyclerview_cell,viewGroup,false);
        return new TypeViewHoder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TypeViewHoder typeViewHoder, int i) {
        typeViewHoder.setModel(this.objects.get(i));
    }

    @Override
    public int getItemCount() {
        return objects == null ? 0 : objects.size();
    }
}
