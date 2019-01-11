package com.wobiancao.guidedemo.fragment;

import android.os.Bundle;
import android.widget.ImageView;

import com.wobiancao.guidedemo.LazyLoadFragment;
import com.wobiancao.guidedemo.R;
/**
 * Created by wobiancao on 19/1/11.
 */
public class FragmentOnePage extends LazyLoadFragment {
    ImageView mBgView;
    ImageView mShowView;

    public static FragmentOnePage newInstance() {
        FragmentOnePage page = new FragmentOnePage();
        Bundle args = new Bundle();
        page.setArguments(args);
        return page;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mBgView = findViewById(R.id.image_one_bg);
        mShowView = findViewById(R.id.image_one_show);
    }

    @Override
    protected int setContentView() {
        return R.layout.frgamnet_onepage;
    }

    @Override
    protected void lazyLoad() {

    }
}
