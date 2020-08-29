package com.geektech.geektech;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.geektech.geektech.authorization.PhoneActivity;
import com.geektech.geektech.preferenceHelper.PreferenceHelper;
import com.geektech.geektech.variable_constants.User;
import com.geektech.geektech.оnBoard.OnBoardActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebase();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        BottomNavigationView navView;
        AppBarConfiguration appBarConfiguration;

        if (PreferenceHelper.getInstance(this).user().equals(User.STUDENT.name())){
            navView = findViewById(R.id.nav_view_student);
            appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                    .build();
            navView.setVisibility(View.VISIBLE);
        } else if (PreferenceHelper.getInstance(this).user().equals(User.ADMIN.name())) {
            navView = findViewById(R.id.nav_view_admin);
            appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.navigation_chat, R.id.navigation_profile)
                    .build();
            navView.setVisibility(View.VISIBLE);
        } else {
            navView = findViewById(R.id.nav_view_admin);
            appBarConfiguration = new AppBarConfiguration.Builder(
                    R.id.navigation_chat, R.id.navigation_profile)
                    .build();
        }

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        if(PreferenceHelper.getInstance(this).user().equals(User.NO_USER.name()))
            navController.navigate(R.id.studentOrAdminFragment);
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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return true;
    }
}