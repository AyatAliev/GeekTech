package com.geektech.geektech.ui.admin.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.geektech.geektech.R;
import com.geektech.geektech.ui.model.Student;

import java.util.List;

public class AdapterStudent extends RecyclerView.Adapter<AdapterStudent.ViewHolder> {
    private List<Student> data;
    private Context context;
    private OnStudentClickListener listener;

    public AdapterStudent(List<Student> data, Context context, OnStudentClickListener listener) {
        this.data = data;
        this.context = context;
        this.listener = listener;
    }

    public void updateStudents(Student student,int position){
        data.remove(position);
        data.add(position, student);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_student, parent, false), listener);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(data.get(position), context);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView, imageViewChoice;
        private TextView textViewTitle, textViewRole, textViewFavorite;
        private Student student;

        public ViewHolder(@NonNull View itemView, final OnStudentClickListener listener) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image_view_cowboy);
            textViewTitle = itemView.findViewById(R.id.text_view_name);
            textViewRole = itemView.findViewById(R.id.text_view_role);
            textViewFavorite = itemView.findViewById(R.id.text_view_favorite);
            imageViewChoice = itemView.findViewById(R.id.image_view_choice);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onStudentClick(getAdapterPosition());
                }
            });
        }

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @SuppressLint("SetTextI18n")
        void onBind(Student student, Context context) {
            this.student = student;
            Glide
                    .with(context)
                    .load(student.getAvatarUrl())
                    .into(imageView);
            textViewTitle.setText(student.getName());
            textViewRole.setText(student.getGroup());
            textViewFavorite.setText(student.getFollowers()+"");
            if (student.isChoice()) imageViewChoice.setVisibility(View.VISIBLE);
            else imageViewChoice.setVisibility(View.GONE);
        }
    }

    public interface OnStudentClickListener {
        void onStudentClick(int id);
    }
}