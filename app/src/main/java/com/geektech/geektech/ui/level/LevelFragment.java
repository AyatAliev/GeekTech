package com.geektech.geektech.ui.level;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.geektech.geektech.R;


public class LevelFragment extends Fragment {

    Button oneJava, threeAndroid;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_level, container, false);
        oneJava = root.findViewById(R.id.oneJava);
        threeAndroid = root.findViewById(R.id.threeA);

        final NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        oneJava.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.oneJavaFragment);
            }
        });

        threeAndroid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.threeAndroidFragment);
            }
        });


        return root;
    }
}