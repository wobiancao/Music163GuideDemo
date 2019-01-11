package com.wobiancao.guidedemo.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.wobiancao.guidedemo.LazyLoadFragment;
import com.wobiancao.guidedemo.MainActivity;
import com.wobiancao.guidedemo.R;
/**
 * Created by wobiancao on 19/1/11.
 */
public class FragmentThreePage extends LazyLoadFragment {
    ImageView mBgView;
    ImageView mShowView;
    Animation mShowAnim, mAlphaAnim;

    public static FragmentThreePage newInstance() {
        FragmentThreePage page = new FragmentThreePage();
        Bundle args = new Bundle();
        page.setArguments(args);
        return page;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mBgView = findViewById(R.id.image_three_bg);
        mShowView = findViewById(R.id.image_three_show);
        mShowAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.trans_three_bottom_up);
        mAlphaAnim   = AnimationUtils.loadAnimation(getActivity(), R.anim.alpha);
        mBgView.setVisibility(View.INVISIBLE);
        mShowView.setVisibility(View.INVISIBLE);

    }

    @Override
    protected int setContentView() {
        return R.layout.frgamnet_threepage;
    }

    @Override
    protected void lazyLoad() {
        MainActivity.SHOW_TWO_ANIM = false;
        mBgView.post(new Runnable() {
            @Override
            public void run() {
                mBgView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mBgView.startAnimation(mAlphaAnim);
                        mBgView.setVisibility(View.VISIBLE);
                    }
                }, 250);

                mShowView.startAnimation(mShowAnim);
                mShowView.setVisibility(View.VISIBLE);
            }
        });
    }
}
