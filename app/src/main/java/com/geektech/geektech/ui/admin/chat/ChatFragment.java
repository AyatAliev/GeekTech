package com.geektech.geektech.ui.admin.chat;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
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
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;
import java.util.Objects;

public class ChatFragment extends Fragment implements OnClickHolder {
    RecyclerView recyclerView;
    FloatingActionButton fab;
    NavController navController;
    Adapter adapter;

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
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        adapter = new Adapter(this);
        recyclerView.setAdapter(adapter);

        uploadData();

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.formGroupFragment);
            }
        });
    }

    private void uploadData() {
        FirebaseFirestore
                .getInstance()
                .collection("group")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful())
                            for (Group group : Objects.requireNonNull(task.getResult()).toObjects(Group.class)) {
                                adapter.update(group);
                            }
                        else {
                            Log.e("ololo", "onComplete: isSuccessful = false", task.getException() );
                        }
                    }
                });
    }

    @Override
    public void click(int s, Group group) {
        Log.e("ololo", "click: click group");
        navController.navigate(R.id.groupInfoFragment);
    }


}