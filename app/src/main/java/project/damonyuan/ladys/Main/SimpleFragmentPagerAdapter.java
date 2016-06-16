package project.damonyuan.ladys.Main;

/**
 * Created by damonyuan on 2016/6/14.
 */

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import project.damonyuan.ladys.Main.PageFragment;

public class SimpleFragmentPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 9;
    private String tabTitles[] = new String[]{"All","Android","iOS","休息视频","福利","拓展资源","前端","瞎推荐","App"};
    private Context context;

    public SimpleFragmentPagerAdapter(FragmentManager fm,Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
