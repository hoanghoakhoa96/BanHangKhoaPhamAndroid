package com.example.gietb.banhangkhoapham;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.Toast;

import adapter.PagerAdapter;
import drawer.ISendButton;
import drawer.SignInDrawerFragment;
import drawer.SignOutDrawerFragment;

public class MainActivity extends AppCompatActivity implements ISendButton {

    private DrawerLayout mDrawerLayout;
    private ViewPager viewPager;
    private ImageButton btnDrawer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = findViewById(R.id.viewPager);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);

        initControls();
    }

    private void initControls() {
        mDrawerLayout = findViewById(R.id.drawerLayout);
        btnDrawer = findViewById(R.id.menuButton);
        btnDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDrawerLayout.openDrawer(Gravity.LEFT);
            }
        });
        changeDrawer(null);
    }

    @Override
    public void onBackPressed() {
        if (viewPager.getCurrentItem() == 0)
            super.onBackPressed();
        else {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    public void changeDrawer(View view) {
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        Fragment fragment = null;
        String tag = null;
        if (view == null) {
            fragment = new SignInDrawerFragment();
            fragmentTransaction.replace(R.id.frameDrawer, fragment, "SIGN_IN");
            fragmentTransaction.commit();
            return;
        }
        switch (view.getId()) {
            case R.id.buttonSignOut:
                fragment = new SignInDrawerFragment();
                tag = "SIGN_IN";
                break;
            case R.id.signInButton:
                fragment = new SignOutDrawerFragment();
                tag = "SIGN_OUT";
                break;
        }
        fragmentTransaction.replace(R.id.frameDrawer, fragment, tag);
        fragmentTransaction.commit();
    }

    @Override
    public void sendButton(Button button) {
        changeDrawer(button);
    }
}
