package com.geektech.geektech.ui.lesson.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.geektech.geektech.R;
import com.geektech.geektech.ui.model.Lesson;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {
    private ArrayList<Lesson> list = new ArrayList<>();

    public void update(Lesson lesson){
        list.add(lesson);
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lesson,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.onBind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView desc;
        private ImageView imageView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.item_tv_title);
            desc = itemView.findViewById(R.id.item_tv_desc);
            imageView = itemView.findViewById(R.id.item_image_view);
        }

        public void onBind(Lesson lesson){
            title.setText(lesson.getTitle());
            desc.setText(lesson.getDesc());
            Glide.with(itemView).load(lesson.getUri()).into(imageView);
        }
    }
}
