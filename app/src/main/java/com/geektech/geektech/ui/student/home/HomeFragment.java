package com.geektech.geektech.ui.student.home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.ContextCompat;
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

import java.util.ArrayList;
import java.util.Objects;

public class HomeFragment extends Fragment implements OnClickHolder {

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    private Adapter adapter;
    private SearchView searchView;
    private ArrayList<String> groups = new ArrayList<>();

    private NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        requestPermission();
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

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == R.id.action_search){
            search();
        }
        return true;
    }

    private void search() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                newText = newText.toLowerCase();
                for (int i = 0; i < groups.size(); i++) {
                    String text = groups.get(i).toLowerCase();
                    if (text.contains(newText)) {
                        groups.add(groups.get(i));
                    }
                }
                adapter.addAll(groups);
                adapter.notifyDataSetChanged();
                return false;
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
                        if (task.isSuccessful()) {
                            for (Group g : Objects.requireNonNull(task.getResult()).toObjects(Group.class)) {
                                groups.add(g.getTitle());
                            }
                            for (Group group : Objects.requireNonNull(task.getResult()).toObjects(Group.class)) {
                                adapter.update(group);
                            }
                        }
                        else {
                            Log.e("ololo", "onComplete: isSuccessful = false", task.getException());
                        }
                    }
                });
    }

    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED){
        }
        else requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 102);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 102) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            }
        }
    }


    @Override
    public void click(int s, Group group) {
        navController.navigate(R.id.lessonFragmnet);
    }

}
