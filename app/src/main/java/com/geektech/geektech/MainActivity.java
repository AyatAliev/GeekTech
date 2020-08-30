package com.geektech.geektech;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.geektech.geektech.authorization.PhoneActivity;
import com.geektech.geektech.authorization.StudentOrAdminFragment;
import com.geektech.geektech.preferenceHelper.PreferenceHelper;
import com.geektech.geektech.ui.admin.chat.form_group.FormGroupFragment;
import com.geektech.geektech.variable_constants.User;
import com.geektech.geektech.оnBoard.OnBoardActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.yalantis.ucrop.UCrop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;
    BottomNavigationView navView;
    AppBarConfiguration appBarConfiguration;
    NavOptions navOptions;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebase();
        searchView = findViewById(R.id.action_search);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        BottomNavigationView navView;
        AppBarConfiguration appBarConfiguration;

        if (PreferenceHelper.getInstance(this).user().equals(User.STUDENT.name())) {
            navView = findViewById(R.id.nav_view_student);
            appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                    .build();
        } else if (PreferenceHelper.getInstance(this).user().equals(User.ADMIN.name())) {
            navView = findViewById(R.id.nav_view_admin);
            appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.navigation_chat, R.id.navigation_profile)
                    .build();
            navOptions = new NavOptions.Builder()
                    .setPopUpTo(R.id.navigation_chat, true)
                    .build();

        } else {
            navView = findViewById(R.id.nav_view_student);
            appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.navigation_chat, R.id.navigation_profile)
                    .build();
        }
        navView.setVisibility(View.VISIBLE);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        if (PreferenceHelper.getInstance(this).user().equals(User.NO_USER.name())) {
            navController.navigate(R.id.studentOrAdminFragment);
        }

        if (PreferenceHelper.getInstance(this).user().equals(User.ADMIN.name())) {
            navController.navigate(R.id.navigation_chat, new Bundle(), navOptions);
        }

        if (PreferenceHelper.getInstance(this).user().equals(User.ADMIN.name()))
            navController.navigate(R.id.navigation_chat);

        boolean isStudent = PreferenceHelper.getInstance(this).isStudent();
        if (isStudent) {
            navController.navigate(R.id.studentOrAdminFragment);
            finish();
            return;
        }
    }

    private void firebase() {
        boolean isShown = PreferenceHelper.getInstance(this).isShown();
        if (!isShown) {
            startActivity(new Intent(this, OnBoardActivity.class));
            finish();
            return;
        }

        if (FirebaseAuth.getInstance().getCurrentUser() == null) {
            startActivity(new Intent(MainActivity.this, PhoneActivity.class));
            finish();
            return;
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.toolbar_serach_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        if (item.getItemId() == R.id.action_search) {

        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == UCrop.REQUEST_CROP) {
            assert data != null;
            Log.e("ololo", "onActivityResult: edit profile suuuupppeerr");
            Fragment navHostFragment = getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
            assert navHostFragment != null;
            FormGroupFragment editProfileFragment = ((FormGroupFragment) navHostFragment.getChildFragmentManager().getFragments().get(0));
            editProfileFragment.cropPhoto(UCrop.getOutput(data));
        }
    }
}