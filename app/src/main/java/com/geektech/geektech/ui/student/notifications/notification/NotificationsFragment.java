package com.geektech.geektech.ui.student.notifications.notification;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.geektech.geektech.R;

public class NotificationsFragment extends Fragment implements Contract.View {
    NavController navController;
    Contract.Presenter presenter;
    LinearLayout linearLayoutEditName;
    LinearLayout linearLayoutEditGroup;
    TextView textView;
    LinearLayout linearLayoutExchange;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notifications, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialisation(view);
        setListener();

    }

    private void setListener() {
        linearLayoutEditName.setOnClickListener(view -> presenter.onLinearLayoutStudentNameWasClicked());

        linearLayoutEditGroup.setOnClickListener(view -> presenter.onLinearLayoutStudentGroupWasClicked());


        textView.setOnClickListener(view -> presenter.onTextViewWebViewWasClicked());
    }

    private void initialisation(View view) {
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        presenter = new Presenter(this);
        linearLayoutEditName = view.findViewById(R.id.linear_layout_edit_student_name);
        linearLayoutEditGroup = view.findViewById(R.id.linear_layout_edit_student_group);
        textView = view.findViewById(R.id.tv_test);
    }

    @Override
    public void openEditFragment(Bundle arguments) {
        navController.navigate(R.id.editFragment, arguments);
    }

    @Override
    public void openTestFragment() {
        navController.navigate(R.id.testWebViewFragment);
    }


}


