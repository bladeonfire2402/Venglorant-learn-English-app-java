package com.sinhvien.venglorant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView navigationView;
    HomeFragment homeFragment = new HomeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navigationView = findViewById(R.id.bottomNavigationView);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame, new HomeFragment()).commit();
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                if (item.getItemId() == R.id.Home) {
                    selectedFragment = new HomeFragment();
                } else if (item.getItemId() == R.id.profile) {
                    selectedFragment = new ProfileFragment();

                } else if (item.getItemId() == R.id.setting) {
                    selectedFragment = new SettingFragment();

                } else if (item.getItemId() == R.id.Volcal) {
                    selectedFragment = new LearnVolcaFragment();

                }
                if (selectedFragment != null) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.frame, selectedFragment).commit();
                }
                return true;
            }
        });
    }
}