package com.geektech.geektech.ui.admin.chat.group_info;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.geektech.R;
import com.geektech.geektech.ui.lesson.OnClick;
import com.geektech.geektech.ui.lesson.recyclerview.Adapter;
import com.geektech.geektech.ui.model.Lesson;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;

public class GroupInfoFragment extends Fragment implements OnClick {
    RecyclerView recyclerView;
    FloatingActionButton fab;
    NavController navController;
    Adapter adapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_group_info, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerview);
        fab = view.findViewById(R.id.fab);
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        adapter = new com.geektech.geektech.ui.lesson.recyclerview.Adapter(this);
        recyclerView.setAdapter(adapter);

        FirebaseFirestore
                .getInstance()
                .collection("lessons")
                .get()
                .addOnCompleteListener(task -> {
                    for (Lesson lesson : Objects.requireNonNull(task.getResult()).toObjects(Lesson.class)) {
                        adapter.update(lesson);
                    }
                });

        fab.setOnClickListener(view1 -> navController.navigate(R.id.formLessonFragment));
    }

    @Override
    public void click(int adapterPosition, Lesson lesson) {
        navController.navigate(R.id.settingsFragment);
    }
}
