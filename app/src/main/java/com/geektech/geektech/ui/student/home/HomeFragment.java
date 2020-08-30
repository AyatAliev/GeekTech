package com.geektech.geektech.ui.student.home;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.geektech.geektech.MainActivity;
import com.geektech.geektech.R;
import com.geektech.geektech.ui.student.home.recyclerview.Adapter;
import com.geektech.geektech.ui.model.Group;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.Objects;

public class HomeFragment extends Fragment implements OnClickHolder {

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    private Adapter adapter;
    private NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        uploadData();

        recyclerView = root.findViewById(R.id.fh_recycler_view);
        adapter = new Adapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        return root;
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
        navController.navigate(R.id.lessonFragmnet);
    }

}
