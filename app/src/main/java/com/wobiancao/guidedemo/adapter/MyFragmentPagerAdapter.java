package com.wobiancao.guidedemo.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.wobiancao.guidedemo.fragment.FragmentOnePage;
import com.wobiancao.guidedemo.fragment.FragmentThreePage;
import com.wobiancao.guidedemo.fragment.FragmentTwoPage;
/**
 * Created by wobiancao on 19/1/11.
 */
public class MyFragmentPagerAdapter extends FragmentPagerAdapter{
    private final static int PAGE_COUNT = 3;

    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment itemFragment = null;
        switch (i){
            case 0:
                itemFragment = FragmentOnePage.newInstance();
                break;
            case 1:
                itemFragment = FragmentTwoPage.newInstance();
                break;
            case 2:
                itemFragment = FragmentThreePage.newInstance();
                break;
             default:
                break;
        }

        return itemFragment;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
}
