package sitsko.vlad.epamhomework;

import android.content.res.ColorStateList;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.ImageViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.util.Random;

import sitsko.vlad.epamhomework.compoundview.ProfileDataModel;
import sitsko.vlad.epamhomework.compoundview.ProfileView;
import sitsko.vlad.epamhomework.fragments.CombinedFragment;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private Toolbar toolbar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeAsUpIndicator(R.drawable.ic_menu);

        drawerLayout = findViewById(R.id.drawer_layout);

        navigationView = findViewById(R.id.nav_view);

        MyNavigationView myNavigationViewListener = new MyNavigationView();
        navigationView.setNavigationItemSelectedListener(myNavigationViewListener);

        View headerView = navigationView.getHeaderView(0);
        final ProfileView profileView = headerView.findViewById(R.id.profile_view);

        final ProfileDataModel profileDataModel = getProfileModel();
        profileView.updateUserInfo(profileDataModel);

        final ImageView profileImage = profileView.findViewById(R.id.profile_image);
        profileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageViewCompat.setImageTintList(profileImage, ColorStateList.valueOf(new Random().nextInt()));
            }
        });
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private ProfileDataModel getProfileModel() {
        final ProfileDataModel profileModel = new ProfileDataModel();
        profileModel.setName(getString(R.string.vlad_sitsko));
        profileModel.setMail(getString(R.string.vlad_sitsko_gmail_com));
        profileModel.setIcon(R.drawable.ic_person);

        return profileModel;
    }

    private class MyNavigationView implements NavigationView.OnNavigationItemSelectedListener {

        private static final String NUMBER_BUNDLE_KEY = "NUMBER_KEY";
        private static final String FRAGMENT_ONE_TEXT = "Fragment #1";
        private static final String FRAGMENT_TWO_TEXT = "Fragment #2";

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragment = null;
            Class fragmentClass;
            Bundle bundle = new Bundle();

            int id = menuItem.getItemId();

            if (id == R.id.first_fragment) {
                bundle.putString(NUMBER_BUNDLE_KEY, FRAGMENT_ONE_TEXT);
            } else if (id == R.id.second_fragment) {
                bundle.putString(NUMBER_BUNDLE_KEY, FRAGMENT_TWO_TEXT);
            }

            fragmentClass = CombinedFragment.class;

            try {
                fragment = (CombinedFragment) fragmentClass.newInstance();
                fragment.setArguments(bundle);
            } catch (Exception e) {
                e.printStackTrace();
            }

            FragmentManager fragmentManager = getSupportFragmentManager();

            if (fragment != null) {
                fragmentManager.beginTransaction().replace(R.id.frame_container, fragment).commit();
            } else {
                return false;
            }

            menuItem.setChecked(true);
            setTitle(menuItem.getTitle());

            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }
    }
}
