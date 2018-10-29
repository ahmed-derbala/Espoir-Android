package com.ahmedderbala.espoir;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.ahmedderbala.espoir.Cases.CasesFragment;
import com.ahmedderbala.espoir.Contacts.ContactsFragment;
import com.ahmedderbala.espoir.Events.EventsFragment;
import com.ahmedderbala.espoir.user.LoginActivity;
import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    final Fragment casesFragment = new CasesFragment();
    final Fragment eventsFragment = new EventsFragment();
    final Fragment contactsFragment = new ContactsFragment();

    final FragmentManager fm = getSupportFragmentManager();
    Fragment active = casesFragment;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fm.beginTransaction().hide(active).show(casesFragment).commit();
                    active = casesFragment;
                    return true;
                case R.id.navigation_dashboard:
                    fm.beginTransaction().hide(active).show(eventsFragment).commit();
                    active = eventsFragment;
                    return true;
                case R.id.navigation_notifications:
                    fm.beginTransaction().hide(active).show(contactsFragment).commit();
                    active = contactsFragment;
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fm.beginTransaction().add(R.id.main_container, eventsFragment, "2").hide(eventsFragment).commit();
        fm.beginTransaction().add(R.id.main_container, contactsFragment, "3").hide(contactsFragment).commit();
        fm.beginTransaction().add(R.id.main_container,casesFragment, "1").commit();

        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        initCollapsingToolbar();
        try {
            Glide.with(this).load(R.drawable.new_event).into((ImageView) findViewById(R.id.backdrop));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
            return true;
        }
        if (id == R.id.action_session) {
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    public void initCollapsingToolbar() {
        final CollapsingToolbarLayout collapsingToolbar =
                findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(" ");
        AppBarLayout appBarLayout = findViewById(R.id.appBarLayout);
        appBarLayout.setExpanded(true);

        // hiding & showing the title when toolbar expanded & collapsed
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbar.setTitle(getString(R.string.app_name));
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbar.setTitle(" ");
                    isShow = false;
                }
            }
        });
    }

}
