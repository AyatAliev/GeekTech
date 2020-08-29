package com.geektech.geektech.ui.level;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.geektech.geektech.R;

public class OneJavaFragment extends Fragment {

    Button one;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_one_java, container, false);
        one=root.findViewById(R.id.oneJava);
        final NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.threeFragment);


            }
        });
        return root;
    }

}