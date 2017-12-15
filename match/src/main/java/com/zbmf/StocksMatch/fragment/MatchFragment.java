package com.zbmf.StocksMatch.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.zbmf.StocksMatch.R;
import com.zbmf.StocksMatch.adapter.MatchFragmentAdapter;
import com.zbmf.StocksMatch.constatns.Constans;
import com.zbmf.StocksMatch.fragment.drill.MatchCityFragment;
import com.zbmf.StocksMatch.fragment.drill.MatchListFragment;
import com.zbmf.worklibrary.presenter.BasePresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

/**
 * Created by xuhao on 2017/11/27.
 */

public class MatchFragment extends BaseFragment {

    @BindView(R.id.timeline_tablayout)
    TabLayout timelineTablayout;
    @BindView(R.id.timeline_viewpager)
    ViewPager timelineViewpager;

    public static MatchFragment newInstance() {
        MatchFragment fragment = new MatchFragment();
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_match_layout;
    }

    @Override
    protected BasePresenter initPresent() {
        return null;
    }

    @Override
    protected void initView() {
        setTitleMessage(getString(R.string.match));
    }

    @Override
    protected void initData() {
        List<Fragment>infolist=new ArrayList<>();
        infolist.add(MatchCityFragment.newInstance(Constans.CITY));
        infolist.add(MatchCityFragment.newInstance(Constans.SCHOOL));
        infolist.add(MatchCityFragment.newInstance(Constans.BUSINESS));
        infolist.add(MatchListFragment.newInstance(Constans.NEW_MATCH));
        infolist.add(MatchListFragment.newInstance(Constans.All_MATCH));
        MatchFragmentAdapter matchFragmentAdapter=new MatchFragmentAdapter(getActivity(),
                getChildFragmentManager(),
                infolist,
                Arrays.asList(getResources().getStringArray(R.array.match_tag)));
        timelineViewpager.setAdapter(matchFragmentAdapter);
        timelineTablayout.setupWithViewPager(timelineViewpager);
        timelineViewpager.setOffscreenPageLimit(infolist.size());
    }
}
