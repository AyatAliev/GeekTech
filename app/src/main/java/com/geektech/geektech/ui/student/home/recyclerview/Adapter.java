package com.geektech.geektech.ui.student.home.recyclerview;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.geektech.geektech.R;
import com.geektech.geektech.ui.student.home.OnClickHolder;
import com.geektech.geektech.ui.model.Group;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Holder> {
    private ArrayList<Group> list = new ArrayList<>();
    OnClickHolder onClickHolder;

    public ArrayList<Group> getList() {
        return list;
    }

    public void addAll(ArrayList<String> list){
        list.addAll(list);
        notifyDataSetChanged();
    }

    public Adapter(OnClickHolder onClickHolder) {
        this.onClickHolder = onClickHolder;
    }

    public void update(Group group) {
        list.add(0, group);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_group, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.onBind(list.get(position));
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        private ImageView imageView;
        private TextView textView;

        public Holder(@NonNull View itemView) {
            super(itemView);
           init();
        }

        private void init(){
            imageView = itemView.findViewById(R.id.item_image_view);
            textView = itemView.findViewById(R.id.item_tv_title);
        }

        public void onBind(final Group s) {
            if (s.getImageUri() != null) {
                Glide.with(itemView.getContext()).load(s.getImageUri()).into(imageView);
                Log.e("ololo", "onBind: " + s.getImageUri() );
            } else {
                imageView.setBackgroundResource(R.drawable.logo_geek);
            }
            if (s.getTitle() != null) {
                textView.setText(s.getTitle());
            }
            onClickHolder(s);

        }
        private void onClickHolder(final Group s){
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onClickHolder.click(getAdapterPosition(), s);
                }
            });
        }
    }
}

