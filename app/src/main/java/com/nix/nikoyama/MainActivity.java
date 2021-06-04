package com.nix.nikoyama;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.WindowManager;

import com.shrikanthravi.customnavigationdrawer2.data.MenuItem;
import com.shrikanthravi.customnavigationdrawer2.widget.SNavigationDrawer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

        SNavigationDrawer sNavigationDrawer;
//        int color1=0;
        Class fragmentClass;
        public static Fragment fragment;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            if(getSupportActionBar()!=null) {
                getSupportActionBar().hide();
            }
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);

            sNavigationDrawer = findViewById(R.id.navigationDrawer);
            List<MenuItem> menuItems = new ArrayList<>();
            menuItems.add(new MenuItem("Home",R.color.background));
            menuItems.add(new MenuItem("Daily Activity",R.color.background));
            menuItems.add(new MenuItem("Gallery",R.color.background));
            menuItems.add(new MenuItem("Music & Video",R.color.background));
            menuItems.add(new MenuItem("Profile",R.color.background));
            sNavigationDrawer.setMenuItemList(menuItems);
            fragmentClass =  HomeFragment.class;
            try {
                fragment = (Fragment) fragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (fragment != null) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frameLayout, fragment).commit();
            }


            sNavigationDrawer.setOnMenuItemClickListener(new SNavigationDrawer.OnMenuItemClickListener() {
                @Override
                public void onMenuItemClicked(int position) {
                    System.out.println("Position "+position);

                    switch (position){
                        case 0:{
                            fragmentClass = HomeFragment.class;
                            break;
                        }
                        case 1:{
                            fragmentClass = DailyFragment.class;
                            break;
                        }
                        case 2:{
                            fragmentClass = GalleryFragment.class;
                            break;
                        }
                        case 3:{
                            fragmentClass = MusicFragment.class;
                            break;
                        }
                        case 4:{
                            fragmentClass = ProfileFragment.class;
                            break;
                        }

                    }
                    sNavigationDrawer.setDrawerListener(new SNavigationDrawer.DrawerListener() {

                        @Override
                        public void onDrawerOpened() {

                        }

                        @Override
                        public void onDrawerOpening(){

                        }

                        @Override
                        public void onDrawerClosing(){
                            System.out.println("Drawer closed");

                            try {
                                fragment = (Fragment) fragmentClass.newInstance();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                            if (fragment != null) {
                                FragmentManager fragmentManager = getSupportFragmentManager();
                                fragmentManager.beginTransaction().setCustomAnimations(android.R.animator.fade_in, android.R.animator.fade_out).replace(R.id.frameLayout, fragment).commit();

                            }
                        }

                        @Override
                        public void onDrawerClosed() {

                        }

                        @Override
                        public void onDrawerStateChanged(int newState) {
                            System.out.println("State "+newState);
                        }
                    });
                }
            });

        }
}