package com.geektech.geektech.ui.lesson;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.geektech.R;
import com.geektech.geektech.ui.detailLesson.DetailVideoActivity;
import com.geektech.geektech.ui.lesson.recyclerview.Adapter;
import com.geektech.geektech.ui.model.Lesson;

public class LessonFragmnet extends Fragment implements OnClick{

    private RecyclerView recyclerView;
    private Adapter adapter;
    private NavController navController;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_lesson, container, false);

        recyclerView = root.findViewById(R.id.lf_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new Adapter(this);
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        recyclerView.setAdapter(adapter);
        return root;
    }


    @Override
    public void click(int adapterPosition, Lesson lesson) {
        startActivity(new Intent(getContext(), DetailVideoActivity.class));
    }
}