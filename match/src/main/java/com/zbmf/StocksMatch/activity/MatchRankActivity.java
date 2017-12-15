package com.zbmf.StocksMatch.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.zbmf.StocksMatch.R;
import com.zbmf.StocksMatch.adapter.MatchFragmentAdapter;
import com.zbmf.StocksMatch.bean.MatchBean;
import com.zbmf.StocksMatch.constatns.Constans;
import com.zbmf.StocksMatch.constatns.IntentKey;
import com.zbmf.StocksMatch.fragment.rank.MatchRankFragment;
import com.zbmf.StocksMatch.fragment.rank.NewDiealFragment;
import com.zbmf.worklibrary.presenter.BasePresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * Created by xuhao on 2017/12/1.
 */

public class MatchRankActivity extends BaseActivity<BasePresenter> {
    @BindView(R.id.rank_tab_layout)
    TabLayout rankTabLayout;
    @BindView(R.id.rank_viewpager)
    ViewPager rankViewpager;
    private String match_id;
    private List<Fragment>fragmentList;
    @Override
    protected int getLayout() {
        return R.layout.activity_match_rank_layout;
    }

    @Override
    protected String initTitle() {
        return getString(R.string.match_rank);
    }

    @Override
    protected void initData(Bundle bundle) {
        if(bundle!=null){
            match_id=bundle.getString(IntentKey.MATCH_ID);
            fragmentList=new ArrayList<>();
            fragmentList.add(NewDiealFragment.newInstance());
            fragmentList.add(MatchRankFragment.newInstance(match_id,Constans.DAY_RANK));
            fragmentList.add(MatchRankFragment.newInstance(match_id,Constans.WEEK_RANK));
            fragmentList.add(MatchRankFragment.newInstance(match_id,Constans.MOUNTH_RANK));
            fragmentList.add(MatchRankFragment.newInstance(match_id,Constans.ALL_RANK));
            MatchFragmentAdapter adapter=new MatchFragmentAdapter(this,getSupportFragmentManager(),
                    fragmentList, Arrays.asList(getResources().getStringArray(R.array.match_rank_tag)));
            rankViewpager.setAdapter(adapter);
            rankTabLayout.setupWithViewPager(rankViewpager);
            rankViewpager.setOffscreenPageLimit(fragmentList.size());
        }
    }

    @Override
    protected BasePresenter initPresent() {
        return null;
    }
}
