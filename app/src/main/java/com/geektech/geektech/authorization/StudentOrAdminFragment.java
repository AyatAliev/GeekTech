package com.geektech.geektech.authorization;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.geektech.geektech.MainActivity;
import com.geektech.geektech.R;
import com.geektech.geektech.preferenceHelper.PreferenceHelper;
import com.geektech.geektech.presenter.App;
import com.geektech.geektech.variable_constants.User;
import com.geektech.geektech.оnBoard.OnBoardActivity;


public class StudentOrAdminFragment extends Fragment {
    Button buttonStudent;
    Button buttonAdmin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_student_or_admin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        buttonStudent = view.findViewById(R.id.button_student);
        buttonAdmin = view.findViewById(R.id.button_admin);
        final NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

        buttonStudent.setOnClickListener(view12 -> {
            PreferenceHelper.getInstance(requireContext()).user(User.STUDENT.name());
            PreferenceHelper.getInstance(requireActivity()).setIsStudent();
            navController.popBackStack();
        });

        buttonAdmin.setOnClickListener(view1 -> {
            PreferenceHelper.getInstance(requireContext()).user(User.ADMIN.name());
            PreferenceHelper.getInstance(requireActivity()).setIsStudent();
            navController.popBackStack();
        });
    }
}