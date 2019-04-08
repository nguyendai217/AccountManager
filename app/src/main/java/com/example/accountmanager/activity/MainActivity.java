package com.example.accountmanager.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.accountmanager.fragment.FragmentAccount;
import com.example.accountmanager.fragment.FragmentCategory;
import com.example.accountmanager.fragment.FragmentHome;
import com.example.accountmanager.fragment.FragmentInformation;
import com.example.accountmanager.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public final int FRAME_HOME = 0;
    public final int FRAME_ACCOUNT = 1;
    public final int FRAME_GROUP = 2;
    public final int FRAME_INFO = 3;
    public final int FRAME_SHARE = 4;
    public int FRAME;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        getSupportActionBar().setTitle("Home");
        FragmentHome fragmentHome = new FragmentHome();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_main, fragmentHome, fragmentHome.getTag()).commit();

    }

    @Override
    public void onBackPressed() {
//        DrawerLayout drawer = findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }
        FragmentHome fragmentHome = new FragmentHome();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_main, fragmentHome, fragmentHome.getTag()).commit();
        switch (FRAME) {
            case FRAME_HOME:
                showAlertDialog();
                break;
            case FRAME_ACCOUNT:
                displayview(0);
                break;
            case FRAME_GROUP:
                displayview(0);
                break;
            case FRAME_INFO:
                displayview(0);
                break;
        }
    }
    public static MenuInflater menuInflater;
    public static Menu menu;
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menuInflater = getMenuInflater();
        this.menu = menu;
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            FRAME = FRAME_HOME;
            getSupportActionBar().setTitle("Home");
            FragmentHome fragmentHome = new FragmentHome();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_main, fragmentHome, fragmentHome.getTag()).commit();

        } else if (id == R.id.nav_account) {
            FRAME = FRAME_ACCOUNT;
            getSupportActionBar().setTitle("Tài Khoản");
            FragmentAccount fragmentAccount = new FragmentAccount();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_main, fragmentAccount, fragmentAccount.getTag()).commit();

        } else if (id == R.id.nav_group) {
            FRAME = FRAME_GROUP;
            getSupportActionBar().setTitle("Danh Mục");
            FragmentCategory fragmentCategory = new FragmentCategory();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_main, fragmentCategory, fragmentCategory.getTag()).commit();
        } else if (id == R.id.nav_share) {
            FRAME = FRAME_SHARE;
            getSupportActionBar().setTitle("Share");
            FragmentHome fragmentHome = new FragmentHome();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_main, fragmentHome, fragmentHome.getTag()).commit();
        } else if (id == R.id.nav_info) {
            FRAME = FRAME_INFO;
            getSupportActionBar().setTitle("Thông tin");
            FragmentInformation fragmentInformation = new FragmentInformation();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_main, fragmentInformation, fragmentInformation.getTag()).commit();

        } else if (id == R.id.nav_logout) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void displayview(int positon) {
        switch (positon) {
            case 0:
                FRAME = FRAME_HOME;
                break;
            case 1:
                FRAME = FRAME_ACCOUNT;
                break;
            case 2:
                FRAME = FRAME_GROUP;
                break;

            case 3:
                FRAME = FRAME_INFO;
                break;
            case 4:
                FRAME = FRAME_SHARE;
                break;
        }
    }
    public void onclick_account(View view) {
        FRAME = FRAME_ACCOUNT;
        getSupportActionBar().setTitle("Account");
        FragmentAccount fragmentAccount = new FragmentAccount();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_main, fragmentAccount, fragmentAccount.getTag()).commit();
    }

    public void onclick_group(View view) {
        FRAME = FRAME_GROUP;
        getSupportActionBar().setTitle("Danh Mục");
        FragmentCategory fragmentCategory = new FragmentCategory();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_main, fragmentCategory, fragmentCategory.getTag()).commit();

    }
    public void onclick_info(View view) {
        FRAME = FRAME_INFO;
        getSupportActionBar().setTitle("Thông tin");
        FragmentInformation fragmentInformation = new FragmentInformation();
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_main, fragmentInformation, fragmentInformation.getTag()).commit();
    }
    public void onclick_logout(View view) {
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
    public void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông báo");
        builder.setMessage("Bạn có muốn thoát không?");
        builder.setCancelable(false);
        builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
