package com.wobiancao.guidedemo.adapter;


import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wobiancao.guidedemo.R;


/**
 * Created by wobiancao on 19/1/11.
 */
public class TextPagerAdapter extends PagerAdapter {
    private final static int PAGE_COUNT = 3;
    @Override
    public Object instantiateItem(ViewGroup collection, int position) {
        View view = LayoutInflater.from(collection.getContext()).inflate(R.layout.pager_adapter_text, null);
        TextView mTitle = view.findViewById(R.id.pager_text_title);
        TextView mInfo = view.findViewById(R.id.pager_text_info);
        switch (position) {
            case 0:
                mTitle.setText(collection.getResources().getString(R.string.guid_text_one_title));
                mInfo.setText(collection.getResources().getString(R.string.guid_text_one_info));
                break;
            case 1:
                mTitle.setText(collection.getResources().getString(R.string.guid_text_two_title));
                mInfo.setText(collection.getResources().getString(R.string.guid_text_two_info));
                break;
            case 2:
                mTitle.setText(collection.getResources().getString(R.string.guid_text_three_title));
                mInfo.setText(collection.getResources().getString(R.string.guid_text_three_info));
                break;
            default:
                break;
        }

        collection.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup collection, int position, Object view) {
        collection.removeView((View) view);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
