package com.laojiu.app.base;

import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.laojiu.app.R;
import com.laojiu.app.adapter.MainViewPagerAdapter;
import com.laojiu.app.utils.Utils;
import com.laojiu.app.view.NoScrollViewPager;


public class BaseMainActivity extends BaseActivity {
    private long mFirstClick = 0;

    protected void initMainView(NoScrollViewPager viewPager, BottomNavigationView mainBnv) {

        if (viewPager == null || mainBnv == null) return;
        MainViewPagerAdapter adapter = new MainViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mainBnv.getMenu().getItem(position).setChecked(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        adjustNavigationIcoSize(mainBnv);
    }

    /**
     * 定义底部导航样式
     *
     * @param navigation
     */
    private void adjustNavigationIcoSize(BottomNavigationView navigation) {
        if (navigation == null) return;
        navigation.setItemIconTintList(null);

        navigation.setItemTextAppearanceActive(R.style.bottom_selected_text);
        navigation.setItemTextAppearanceInactive(R.style.bottom_normal_text);

        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigation.getChildAt(0);
        for (int i = 0; i < menuView.getChildCount(); i++) {
            BottomNavigationItemView itemView = (BottomNavigationItemView) menuView.getChildAt(i);
            final View iconView = itemView.findViewById(R.id.icon);
            final ViewGroup.LayoutParams layoutParams = iconView.getLayoutParams();
            final DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            layoutParams.height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, displayMetrics);
            layoutParams.width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, displayMetrics);
            iconView.setLayoutParams(layoutParams);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            onAppExit();
            return true;
        }
        return false;
    }

    public void onAppExit() {
        if (System.currentTimeMillis() - mFirstClick > 2000L) {
            mFirstClick = System.currentTimeMillis();
            Utils.showToast("再按一次退出");
            return;
        }
        finish();
    }

}
