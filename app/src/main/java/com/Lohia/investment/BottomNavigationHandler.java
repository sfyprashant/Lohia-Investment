package com.Lohia.investment;

import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.Lohia.investment.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;



    public class BottomNavigationHandler implements BottomNavigationView.OnNavigationItemSelectedListener {
        private FragmentManager fragmentManager;

        private final int home = 1;
        private final int setting = 2;

        private final int profile = 3;

        public BottomNavigationHandler(FragmentManager fragmentManager) {

            this.fragmentManager = fragmentManager;
        }

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

                            switch (item.getItemId()) {
                                case home:
                                    selectedFragment = new HomeFragment();
                                    break;
                                case setting:
                                    selectedFragment = new SettingFragment();
                                    break;
                                case profile:
                                    selectedFragment = new ProfileFragment();
                                    break;
                                // Handle other menu items if needed
                            }

                            if (selectedFragment != null) {
                                fragmentManager.beginTransaction()
                                        .replace(R.id.fragment_container, selectedFragment)
                                        .commit();
                            }

            return true;
        }
    }

