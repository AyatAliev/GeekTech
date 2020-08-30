package com.geektech.geektech.ui.student.home;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
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
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.util.Objects;
import java.util.UUID;

import static android.app.Activity.RESULT_OK;

public class HomeFragment extends Fragment implements OnClickHolder {

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    private Adapter adapter;
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

    private void requestPermission() {
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                PackageManager.PERMISSION_GRANTED)
            openGallery();
        else requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 102);
    }

    private void openGallery() {
        Log.e("ololo", "openGallery: " + "openGallery()");
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 101);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 102) {
            if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            }
        }
    }


    @Override
    public void click(int s, Group group) {
        navController.navigate(R.id.lessonFragmnet);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("ololo", "onActivityResult: edit profile");
        if (resultCode == RESULT_OK && requestCode == 101) {
            assert data != null;
            Uri uri = data.getData();
            String destinationFileName = UUID.randomUUID().toString() + ".jpg";
            assert uri != null;
            UCrop.of(uri, Uri.fromFile(new File(requireActivity().getCacheDir(), destinationFileName)))
                    .withAspectRatio(1, 1)
                    .start(requireActivity());
        }
    }

}
