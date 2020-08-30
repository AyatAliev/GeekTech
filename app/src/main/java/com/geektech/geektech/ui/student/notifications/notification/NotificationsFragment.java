package com.geektech.geektech.ui.student.notifications.notification;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.geektech.geektech.NotificationHelper;
import com.geektech.geektech.R;

public class NotificationsFragment extends Fragment implements Contract.View {
    NavController navController;
    Contract.Presenter presenter;
    LinearLayout linearLayoutEditName;
    LinearLayout linearLayoutEditGroup;
    TextView textView;

    Button mentor, open, close, lesson;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
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

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initialisation(view);
        setListener();

    }

    private void setListener() {
        linearLayoutEditName.setOnClickListener(view -> presenter.onLinearLayoutStudentNameWasClicked());

        linearLayoutEditGroup.setOnClickListener(view -> presenter.onLinearLayoutStudentGroupWasClicked());

        linearLayoutEditGroup.setOnClickListener(view -> presenter.onLinearLayoutStudentGroupWasClicked());
    }

    private void initialisation(View view) {
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
        presenter = new Presenter(this);
        linearLayoutEditName = view.findViewById(R.id.linear_layout_edit_student_name);
        linearLayoutEditGroup = view.findViewById(R.id.linear_layout_edit_student_group);
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


