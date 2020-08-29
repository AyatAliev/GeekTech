package com.geektech.geektech.ui.student.dashboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.geektech.geektech.NotificationHelper;
import com.geektech.geektech.R;

public class DashboardFragment extends Fragment {

    Button mentor, open, close, lesson;

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        mentor = root.findViewById(R.id.mentor);
        open = root.findViewById(R.id.open);
        close = root.findViewById(R.id.close);
        lesson = root.findViewById(R.id.lesson);
        final NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);


        lesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navController.navigate(R.id.levelFragment);
            }
        });

        notification();

        return root;
    }

    private void notification() {
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