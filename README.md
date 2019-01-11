# Music163GuideDemo

`开始之前先看效果`

![网易云原效果](https://upload-images.jianshu.io/upload_images/1216032-a99c749c85e18ce6.GIF?imageMogr2/auto-orient/strip)
![我实现的效果](https://upload-images.jianshu.io/upload_images/1216032-cb76b5abebceedb3.GIF?imageMogr2/auto-orient/strip)

质量有所压缩，具体可去下载网易云音乐自行查看效果

[本demo apk文件下载](https://github.com/wobiancao/Music163GuideDemo/blob/master/app-debug.apk)

#### 分析
- 目测布局：分为两个viewpager，上面展示文字的viewpager和下面的图片viewpager；
- 进一步观察：上面的文字viewpager滑动有延迟，而图片viewpager是没有滑动自带动画的，而且都没有自带滑动手势效果；
- 分析得出：两个viewpager都拦截滑动事件，文字viewpager需要设置切换时间，有动画效果，图片viewpager去掉自带动画；
- 分析图片viewpager动画效果，都是两张图片，一张背景，一张上浮图片；打开之后，背景：透明度由0到1；上浮图片：由下往上冒出;第三张图片，头像上浮之外还有个变小的过程

`分析完毕，接下来具体实现`

#### 实现

- 先实现viewpager滑动拦截，拦截点击事件就行，具体看代码不多说
```
public class MyInterceptViewPager extends ViewPager {
    private boolean isScrollable = true;

    public MyInterceptViewPager(Context context) {
        super(context);
    }

    public MyInterceptViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return isScrollable && super.onTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return isScrollable && super.onInterceptTouchEvent(ev);
    }

    @Override
    public void setCurrentItem(int item) {
        super.setCurrentItem(item, false);//表示切换的时候，不需要切换时间。
    }

    @Override
    public void setCurrentItem(int item, boolean smoothScroll) {
        super.setCurrentItem(item, smoothScroll);
    }
}
```
- 通过反射，实现viewpager切换动画速度修改，新建一个类继承于Scroller
```
public class FixedSpeedScroller extends Scroller {
    private int mDuration = 1500;

    public FixedSpeedScroller(Context context) {
        super(context);
    }

    public FixedSpeedScroller(Context context, Interpolator interpolator) {
        super(context, interpolator);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        // Ignore received duration, use fixed one instead
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    public void setmDuration(int time) {
        mDuration = time;
    }

    public int getmDuration() {
        return mDuration;
    }
}

```
- 具体使用
```
 try {
            Field field = ViewPager.class.getDeclaredField("mScroller");
            field.setAccessible(true);
            FixedSpeedScroller scrollerText = new FixedSpeedScroller(this, new AccelerateInterpolator());
            field.set(mTextViewPager, scrollerText);
            scrollerText.setmDuration(400);
        } catch (Exception e) {

        }
```
- 实现两个viewpager的联动，根据横向滑动距离和方向判断是否应该翻页
```
mTouchLayout.setOnTouchListener(new View.OnTouchListener() {
            float startX;
            float startY;//没有用到
            float endX;
            float endY;//没有用到
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        startX = event.getX();
                        startY = event.getY();
                        break;
                    case MotionEvent.ACTION_UP:
                        endX = event.getX();
                        endY = event.getY();
                        WindowManager windowManager = (WindowManager) getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
                        Point size = new Point();
                        windowManager.getDefaultDisplay().getSize(size);
                        int width = size.x;
                        if (startX - endX >= (width / 8)){// startX - endX 大于0 且大于宽的1/8 可以往后翻页
                            if (pageIndex == 0){
                                mImageViewPager.setCurrentItem(1);
                                mTextPager.setCurrentItem(1, true);
                            }else if (pageIndex == 1){
                                mImageViewPager.setCurrentItem(2);
                                mTextPager.setCurrentItem(2, true);
                            }
                        }else if (endX - startX  >= (width / 8)){ // endX - startX   大于0 且大于宽的1/8 可以往前翻页
                            if (pageIndex == 2){
                                mImageViewPager.setCurrentItem(1);
                                mTextPager.setCurrentItem(1, true);
                            }else if (pageIndex == 1){
                                mImageViewPager.setCurrentItem(0);
                                mTextPager.setCurrentItem(0, true);
                            }
                        }

                        break;
                }
                return true;
            }
        });
```

#### 最后

- 本demo资源来自解压网易云音乐apk，因为喜欢，所以模仿。

- 如果本demo对你有帮助，来个共赢的事吧，扫个红包也没损失↓↓↓

![1547192479521.jpg](https://upload-images.jianshu.io/upload_images/1216032-fee6451bd19e7f3e.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/240)


