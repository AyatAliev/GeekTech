package com.geektech.geektech.ui.admin;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.geektech.geektech.R;
import com.geektech.geektech.presenter.App;
import com.geektech.geektech.ui.model.Group;
import com.geektech.geektech.ui.student.home.HomeFragment;
import com.geektech.geektech.ui.student.home.OnClickHolder;
import com.geektech.geektech.ui.student.home.recyclerview.Adapter;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Objects;

public class ChatFragment extends Fragment implements OnClickHolder {
    RecyclerView recyclerView;
    FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_chat, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerview);
        fab = view.findViewById(R.id.fab);
        Adapter adapter = new Adapter(this);
        recyclerView.setAdapter(adapter);

        adapter.update(new Group("title"));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void click(int s, Group group) {
        Log.e("ololo", "click: " + "click");
        saveToFireStore(group);
    }

    private void saveToFireStore(Group group) {
        FirebaseFirestore
                .getInstance()
                .collection("group")
                .add(group)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if(task.isSuccessful()){
                            Log.e("ololo", "onComplete: " + task.toString());
                        } else {
                            Log.e("ololo", "onComplete: ", task.getException());
                        }
                    }
                });
    }
}