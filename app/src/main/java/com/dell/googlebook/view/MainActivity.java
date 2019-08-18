package com.dell.googlebook.view;

import android.annotation.SuppressLint;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;


import com.dell.googlebook.R;

import com.dell.googlebook.view.fragment.FavoriteFragment;
import com.dell.googlebook.view.fragment.HomeFragment;

import java.util.Objects;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    Toolbar toolbar;
    DrawerLayout mDrawerLayout;
    NavigationView navigationView;
    android.support.v7.app.ActionBarDrawerToggle mToggle;
    public FavoriteFragment favoriteFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = findViewById(R.id.toolbar);
        //Navigation drawer
        mDrawerLayout = findViewById(R.id.main_acctivity);
        navigationView = findViewById(R.id.navigation);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);

        displaySelectItemNavigation(R.id.menu_home);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (mToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        menuItem.setChecked(true);
        displaySelectItemNavigation(menuItem.getItemId());
        return true;
    }

    public void displaySelectItemNavigation(int itemId) {
        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.menu_home:
                fragment = new HomeFragment();
                break;
            case R.id.menu_favorite:
                fragment = new FavoriteFragment();
                favoriteFragment = (FavoriteFragment) fragment;
                break;
        }

        //replacing the fragment
        if (fragment != null) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame_layout, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.main_acctivity);
        drawer.closeDrawer(GravityCompat.START);
    }

    @SuppressLint("NewApi")
    @Override
    protected void onResume() {
        Log.d("OnResume","onResume");
        if (!Objects.equals(favoriteFragment, null))
                favoriteFragment.updateList();
        super.onResume();
    }
}
