package com.geektech.geektech.ui.lesson;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.geektech.R;
import com.geektech.geektech.ui.lesson.recyclerview.Adapter;

public class LessonFragmnet extends Fragment {

    private RecyclerView recyclerView;
    private Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View root = inflater.inflate(R.layout.fragment_lesson, container, false);

       recyclerView = root.findViewById(R.id.lf_recyclerview);
       recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
       adapter = new Adapter();
       recyclerView.setAdapter(adapter);
        return root;
    }


}