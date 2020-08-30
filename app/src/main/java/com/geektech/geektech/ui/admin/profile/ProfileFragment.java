package com.geektech.geektech.ui.admin.profile;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.geektech.geektech.NotificationHelper;
import com.geektech.geektech.R;


public class ProfileFragment extends Fragment {
    NavController navController;
    Button mentor, open, close, lesson;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        mentor = root.findViewById(R.id.mentor);
        open = root.findViewById(R.id.open);
        close = root.findViewById(R.id.close);
        lesson = root.findViewById(R.id.lesson);

        final NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);



        notification();
        return root;
    }

    private void notification() {


        lesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.levelFragment);
            }
        });


        mentor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationHelper.CreateNatification(getActivity());
            }
        });

        open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationHelper.CreateNatificationOpen(getActivity());
            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationHelper.CreateNatificationClose(getActivity());
            }
        });
    }
}