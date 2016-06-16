package project.damonyuan.ladys.Main;

/**
 * Created by damonyuan on 2016/6/14.
 * 设置tab page view
 */

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import project.damonyuan.ladys.Main.SimpleFragmentPagerAdapter;
import project.damonyuan.ladys.R;

public class ThirdActivity extends FragmentActivity {

    private SimpleFragmentPagerAdapter pagerAdapter;

    private ViewPager viewPager;

    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this);
        viewPager = (ViewPager) findViewById(R.id.navigation);
        viewPager.setAdapter(pagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
      //  tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }
}