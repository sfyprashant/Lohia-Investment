package com.Lohia.investment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.Lohia.investment.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class InvestmentActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    DrawerLayout drawerLayout;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment);

        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        int selectedColor = Color.parseColor("#000000"); // Change to your desired color
        int[][] states = new int[][]{
                new int[]{android.R.attr.state_checked}, // selected
                new int[]{-android.R.attr.state_checked} // not selected
        };
        int[] colors = new int[]{selectedColor, Color.GRAY}; // Change the second color to your unselected color
        ColorStateList colorStateList = new ColorStateList(states, colors);
        bottomNavigationView.setItemTextColor(colorStateList);
        bottomNavigationView.setItemIconTintList(colorStateList);
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.home) {
                loadFragment(new MutualFundsFragment());
            } else if (id == R.id.profile) {
                loadFragment(new ProfileFragment());
            } else if (id == R.id.setting) {
                loadFragment(new SettingFragment());
            }else if (id == R.id.add_policy) {
                loadFragment(new Add_Policy_Fragment());
            }

            return true;
        });
        bottomNavigationView.setSelectedItemId(R.id.home);
        NavigationView navigationView = findViewById(R.id.navigationView1);
        navigationView.setNavigationItemSelectedListener(this);

    }
    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
        super.onPointerCaptureChanged(hasCapture);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_item1_1) {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finishAffinity();
            Toast.makeText(this, "Item One Clicked", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_item1_2) {
            try {
                SharedPreferences pre = getSharedPreferences("login", MODE_PRIVATE);
                SharedPreferences.Editor editor = pre.edit();
                editor.putBoolean("flag", false);
                editor.apply();
                Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), signupActivity.class));
                finishAffinity();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Logout failed", Toast.LENGTH_SHORT).show();
            }
        }
        // Close the drawer after handling the item click
        DrawerLayout drawer = findViewById(R.id.drawerLayout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}