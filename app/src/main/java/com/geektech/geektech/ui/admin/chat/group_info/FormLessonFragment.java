package com.geektech.geektech.ui.admin.chat.group_info;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.geektech.geektech.R;
import com.geektech.geektech.ui.model.Lesson;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FormLessonFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.image_view_icon_group)
    ImageView imageViewIconGroup;
    @BindView(R.id.button_replace_icon_group)
    Button buttonReplaceIconGroup;
    @BindView(R.id.button_save)
    Button buttonSave;
    @BindView(R.id.edit_text_group_name)
    EditText editTextGroupName;
    @BindView(R.id.edit_text_sensei)
    EditText editTextGroupSensei;
    NavController navController;
    Uri uri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_form_lesson, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

        buttonSave.setOnClickListener(this);

        buttonReplaceIconGroup.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {

        Lesson lesson = new Lesson();
        lesson.setDesc(editTextGroupSensei.getText().toString());
        lesson.setTitle(editTextGroupName.getText().toString());

        FirebaseFirestore
                .getInstance()
                .collection("lessons")
                .add(lesson)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful())
                        Toast.makeText(requireActivity(), "Урок успешно добавлен", Toast.LENGTH_SHORT).show();
                    else {
                        Toast.makeText(requireActivity(), "Урок не добавлен", Toast.LENGTH_SHORT).show();
                        Log.e("ololo", "onClick: ", task.getException());
                    }
                });
    }
}