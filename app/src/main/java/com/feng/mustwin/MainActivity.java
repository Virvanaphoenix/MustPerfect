package com.feng.mustwin;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.feng.mustwin.fragment.MainFirstFragment;
import com.feng.mustwin.fragment.MainFouthFragment;
import com.feng.mustwin.fragment.MainSecondFragment;
import com.feng.mustwin.fragment.MainThirdFragment;
import com.feng.mustwin.view.NoScrollViewPager;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity implements ViewPager.OnPageChangeListener {

    /**
     * 轮播图
     */
    @ViewInject(R.id.tabmain_viewPager)
    private NoScrollViewPager mViewPager;
    /**
     * 底部附近
     */
    @ViewInject(R.id.main_bottom_online_text)
    private TextView mOnLineText;
    /**
     * 底部查询
     */
    @ViewInject(R.id.main_bottom_offline_text)
    private TextView mOffLineText;
    /**
     * 底部上传
     */
    @ViewInject(R.id.main_bottom_kao_text)
    private TextView mKaoText;
    /**
     * 底部关于
     */
    @ViewInject(R.id.main_bottom_mine_text)
    private TextView mMineText;
    private List<Fragment> mFragments = new ArrayList<>();
    private FragmentPagerAdapter mAdapter;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        ViewUtils.inject(this);
        //dea26972415039db91250c26cc8cc7f4 高德key
        mFragments.add(new MainFirstFragment());
        mFragments.add(new MainSecondFragment());
        mFragments.add(new MainThirdFragment());
        mFragments.add(new MainFouthFragment());
        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public int getCount() {
                return mFragments.size();
            }

            @Override
            public Fragment getItem(int arg0) {
                return mFragments.get(arg0);
            }
        };

        mViewPager.setAdapter(mAdapter);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setOnPageChangeListener(this);
        mViewPager.setNoScroll(true);
        onLineClick(mOnLineText);

    }

    /**
     * 底部在线课程点击事件
     *
     * @param v
     */
    @OnClick(R.id.main_bottom_online_text)
    private void onLineClick(View v) {
        v.setEnabled(false);
        mOffLineText.setEnabled(true);
        mKaoText.setEnabled(true);
        mMineText.setEnabled(true);
        mViewPager.setCurrentItem(0);
    }

    /**
     * 底部线下课程点击事件
     *
     * @param v
     */
    @OnClick(R.id.main_bottom_offline_text)
    private void offLineClick(View v) {
        v.setEnabled(false);
        mOnLineText.setEnabled(true);
        mKaoText.setEnabled(true);
        mMineText.setEnabled(true);
        mViewPager.setCurrentItem(1);
    }

    /**
     * 底部考试点击事件
     *
     * @param v
     */
    @OnClick(R.id.main_bottom_kao_text)
    private void kaoClick(View v) {
        v.setEnabled(false);
        mOnLineText.setEnabled(true);
        mOffLineText.setEnabled(true);
        mMineText.setEnabled(true);
        mViewPager.setCurrentItem(2);
    }

    /**
     * 底部我的点击事件
     *
     * @param v
     */
    @OnClick(R.id.main_bottom_mine_text)
    private void mineClick(View v) {
        v.setEnabled(false);
        mOnLineText.setEnabled(true);
        mOffLineText.setEnabled(true);
        mKaoText.setEnabled(true);
        mViewPager.setCurrentItem(3);
    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {

    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

}
