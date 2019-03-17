package sitsko.vlad.epamhomework;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

public class GooglePodcastsActivity extends AppCompatActivity {

    public static final String NEW_EPISODES = "New episodes";
    public static final String IN_PROGRESS = "In progress";
    public static final String DOWNLOAD = "Download";
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_podcast);

        Toolbar toolbar = findViewById(R.id.toolbar_podcasts);
        Drawable drawable = AppCompatResources.getDrawable(this, R.drawable.ic_search);
        toolbar.setNavigationIcon(drawable);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        viewPager = findViewById(R.id.viewPager);
        initViewPager(viewPager);

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_podcasts_toolbar, menu);

        return true;
    }

    private void initViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addSinglePage(new SinglePageFragment(), NEW_EPISODES);
        adapter.addSinglePage(new SinglePageFragment(), IN_PROGRESS);
        adapter.addSinglePage(new SinglePageFragment(), DOWNLOAD);
        viewPager.setAdapter(adapter);
    }

}
