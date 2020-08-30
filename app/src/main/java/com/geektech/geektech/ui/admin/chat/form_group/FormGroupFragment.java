package com.geektech.geektech.ui.admin.chat.form_group;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.geektech.geektech.R;
import com.geektech.geektech.ui.admin.adapters.AdapterStudent;
import com.geektech.geektech.ui.model.Group;
import com.geektech.geektech.ui.model.Student;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.yalantis.ucrop.UCrop;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.app.Activity.RESULT_OK;


public class FormGroupFragment extends Fragment implements AdapterStudent.OnStudentClickListener {
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
    @BindView(R.id.text_view_add_student)
    TextView textViewAddStudent;
    @BindView(R.id.recyclerview_students)
    RecyclerView recyclerViewStudent;
    List<Student> studentList;
    List<Student> studentListDialog;
    AdapterStudent adapterStudent;
    RecyclerView mRecyclerViewStudent;
    AdapterStudent adapterStudentDialog;
    NavController navController;
    Uri uri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form_group, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        initialisation();
        setListener();
        recyclerViewStudent.setAdapter(adapterStudent);
    }

    private void setListener() {
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //https://avatars.yandex.net/get-music-content/41288/8ad6824a.a.2725630-1/m1000x1000

                Group group = new Group();
                group.setStudents(studentList);
                group.setTitle(editTextGroupName.getText().toString());
                group.setSensei(editTextGroupSensei.getText().toString());
                group.setImageUri(uri.toString());

                saveToFireStore(group);

                navController.popBackStack();
            }
        });

        buttonReplaceIconGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("ololo", "onClick: " + "saaaaaaaaaaaaaaaaaaaa");
                requestPermission();
            }
        });

        textViewAddStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                LayoutInflater inflater = LayoutInflater.from(requireContext());
                View content = inflater.inflate(R.layout.custom_dialog, null);
                builder.setView(content);
                mRecyclerViewStudent = content.findViewById(R.id.my_recycler_view);

                studentListDialog = new ArrayList<>();

                for (int i = 0; i < 10; i++) {
                    if (i % 2 == 0)
                        studentListDialog.add(new Student("", "Rahat", "21", "https://cdn.iconscout.com/icon/free/png-512/avatar-370-456322.png", true));
                    else
                        studentListDialog.add(new Student("", "Ruslan", "21", "https://avatars.yandex.net/get-music-content/41288/8ad6824a.a.2725630-1/m1000x1000", false));
                }

                adapterStudentDialog = new AdapterStudent(studentListDialog, requireContext(), FormGroupFragment.this);

                mRecyclerViewStudent.setAdapter(adapterStudentDialog);

                builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        studentListDialog = null;
                    }
                });

                builder.show();
            }
        });
    }

    private void initialisation() {
        studentList = new ArrayList<>();
        adapterStudent = new AdapterStudent(studentList, requireContext(), this);
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);
    }

    @Override
    public void onStudentClick(int position) {
        if (studentListDialog == null) {
            studentList.remove(position);
            adapterStudent.notifyDataSetChanged();
            return;
        }
        Student student = studentListDialog.get(position);
        student.setChoice(!student.isChoice());
        if (student.isChoice()) {
            studentList.add(student);
        } else {
            studentList.remove(student);
        }
        adapterStudent.notifyDataSetChanged();
        adapterStudentDialog.updateStudents(student, position);
    }

    private void saveToFireStore(Group group) {
        FirebaseFirestore
                .getInstance()
                .collection("group")
                .add(group)
                .addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()) {
                            Log.e("ololo", "onComplete: " + task.toString());
                        } else {
                            Log.e("ololo", "onComplete: ", task.getException());
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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void cropPhoto(Uri uri) {
        uploadToFB(uri);
        Glide
                .with(this)
                .load(uri)
                .circleCrop()
                .into(imageViewIconGroup);
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


    private void uploadToFB(Uri uri) {
        this.uri = uri;
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