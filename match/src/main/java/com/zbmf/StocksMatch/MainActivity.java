package com.zbmf.StocksMatch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RadioGroup;

import com.zbmf.StocksMatch.fragment.DrillFragment;
import com.zbmf.StocksMatch.fragment.HomeFragment;
import com.zbmf.StocksMatch.fragment.MatchFragment;
import com.zbmf.StocksMatch.fragment.MineFragment;
import com.zbmf.StocksMatch.fragment.StockFragment;
import com.zbmf.StocksMatch.util.ShowActivity;
import com.zbmf.worklibrary.adapter.ViewPageAdapter;
import com.zbmf.worklibrary.view.ViewPageNoScroll;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class MainActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    @BindView(R.id.vp_main)
    ViewPageNoScroll vpMain;
    @BindView(R.id.rg_main_menu)
    RadioGroup rgMainMenu;
    private HomeFragment homeFragment;
    private MatchFragment matchFragment;
    private DrillFragment drillFragment;
    private StockFragment stockFragment;
    private MineFragment mineFragment;

    private List<Fragment> fragmentList;
    private int select;
    private Unbinder mUnBinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullscreen(true);
        setContentView(R.layout.activity_main);
        mUnBinder = ButterKnife.bind(this);
        initView();
        getFragmentList();
    }

    private void setFullscreen(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    private void initView() {
        rgMainMenu.setOnCheckedChangeListener(this);
    }

    private void getFragmentList() {
        fragmentList = new ArrayList<>();
        homeFragment = HomeFragment.newInstance();
        matchFragment = MatchFragment.newInstance();
        drillFragment = DrillFragment.newInstance();
        stockFragment = StockFragment.newInstance();
        mineFragment = MineFragment.newInstance();
        fragmentList.add(homeFragment);
        fragmentList.add(matchFragment);
        fragmentList.add(drillFragment);
        fragmentList.add(stockFragment);
        fragmentList.add(mineFragment);
        vpMain.setAdapter(new ViewPageAdapter(getSupportFragmentManager(), fragmentList));
        vpMain.setOffscreenPageLimit(fragmentList.size());
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.rb_home:
                select = 0;
                break;
            case R.id.rb_match:
                select = 1;
                break;
            case R.id.rb_drill:
                if (ShowActivity.isLogin(this)) {
                    select = 2;
                }
                break;
            case R.id.rb_stock:
                if (ShowActivity.isLogin(this)) {
                    select = 3;
                }
                break;
            case R.id.rb_mine:
                if (ShowActivity.isLogin(this)) {
                    select = 4;
                }
                break;
        }
        vpMain.setCurrentItem(select, false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
    }
}
