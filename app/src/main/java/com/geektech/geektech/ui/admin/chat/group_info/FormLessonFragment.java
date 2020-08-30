package com.geektech.geektech.ui.admin.chat.group_info;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.geektech.geektech.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class FormLessonFragment extends Fragment {
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_form_lesson, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }
}