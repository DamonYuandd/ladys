package project.damonyuan.ladys.Main;

import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.List;

import project.damonyuan.ladys.Commom;
import project.damonyuan.ladys.R;
import project.damonyuan.ladys.Config;

public class MainActivity extends Commom {
    private NavigationView mNavigationView;
    //DrawerLayout控件
    private DrawerLayout mDrawerLayout;


    //tabs
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private List<Fragment> list_fragment;
    private String[] mTitle = new String[20];
    private String[] mData = new String[20];
    private SimpleFragmentPagerAdapter pagerAdapter;
    private static Context appContext;
    //private Fragmenthotpot mHotpot;
   // private FragmenthotRec mHotRec;
    //tablayout的标题
   // private String[] mTitles = new String[]{"今日热点","热门推荐"};

    public static Context getAppContext() {
        return appContext;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().setLogo(R.drawable.icon_people);
        setContentView(R.layout.activity_main);
        ConnectivityManager cm=(ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info=cm.getActiveNetworkInfo();
        if(info!=null){
            //Toast.makeText(MainActivity.this, "连网正常"+info.getTypeName(), Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(MainActivity.this, "未连网", Toast.LENGTH_SHORT).show();
        }

        //draw view
        getSupportActionBar().setLogo(R.drawable.ic_list_white_24dp);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        initView();
    }

    //添加tab
    private void initTab() {
        //初始化ViewPager
        viewPager = (ViewPager) findViewById(R.id.navigation);
        // 设置适配器
        pagerAdapter = new SimpleFragmentPagerAdapter(getSupportFragmentManager(), this);
        viewPager.setAdapter(pagerAdapter);
        // 初始化TabLayout
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        // 为TabLayout设置ViewPager
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initView(){
      //  mNavigationView = (NavigationView) this.findViewById(R.id.navigation);
        mDrawerLayout = (DrawerLayout) this.findViewById(R.id.drawer_layout);
        initTab();
    }

}
