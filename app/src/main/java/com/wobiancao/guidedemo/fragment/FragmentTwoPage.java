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
public class FragmentTwoPage extends LazyLoadFragment {
    ImageView mBgView;
    ImageView mShowView;
    ImageView mHeadView;
    Animation mShowAnim, mAlphaAnim;

    public static FragmentTwoPage newInstance() {
        FragmentTwoPage page = new FragmentTwoPage();
        Bundle args = new Bundle();
        page.setArguments(args);
        return page;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        mBgView = findViewById(R.id.image_two_bg);
        mShowView = findViewById(R.id.image_two_show);
        mHeadView = findViewById(R.id.image_two_head);
        mShowAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.trans_two_bottom_up);
        mAlphaAnim   = AnimationUtils.loadAnimation(getActivity(), R.anim.alpha);
    }

    @Override
    protected int setContentView() {
        return R.layout.frgamnet_twopage;
    }

    @Override
    protected void lazyLoad() {
        if (MainActivity.SHOW_TWO_ANIM){
            mBgView.setVisibility(View.INVISIBLE);
            mShowView.setVisibility(View.INVISIBLE);
            mHeadView.setVisibility(View.INVISIBLE);
            mBgView.post(new Runnable() {
                @Override
                public void run() {
                    mBgView.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            mBgView.startAnimation(mAlphaAnim);
                            mBgView.setVisibility(View.VISIBLE);
                            mHeadView.startAnimation(mAlphaAnim);
                            mHeadView.setVisibility(View.VISIBLE);
                        }
                    }, 250);
                    mShowView.startAnimation(mShowAnim);
                    mShowView.setVisibility(View.VISIBLE);

                }
            });
        } else {
            mBgView.setVisibility(View.VISIBLE);
            mShowView.setVisibility(View.VISIBLE);
            mHeadView.setVisibility(View.VISIBLE);
        }
    }
}
