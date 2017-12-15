package com.zbmf.StocksMatch.adapter;


import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.TextView;

import com.zbmf.StocksMatch.R;
import com.zbmf.StocksMatch.bean.City;
import com.zbmf.worklibrary.pullrefreshrecycle.adapter.BaseListRecyclerAdapter;
import com.zbmf.worklibrary.pullrefreshrecycle.holder.RecyclerHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xuhao on 2017/11/27.
 */

public class HomeCityAdapter extends BaseListRecyclerAdapter<City, HomeCityAdapter.ViewHolder> {

    public HomeCityAdapter(Context context) {
        super(context);
    }

    @Override
    public int getViewLayoutId(int viewType) {
        return R.layout.item_home_city_layout;
    }

    @Override
    public ViewHolder createViewHolder(View itemView, int viewType) {
        return new ViewHolder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void convert(ViewHolder holder, City city, int position) {
        holder.tvCityName.setText(city.getCityName());
        switch (position){
            case 0:
                holder.tvCityName.setBackground(getContext().getResources().getDrawable(R.drawable.icon_home_city1));
                break;
            case 1:
                holder.tvCityName.setBackground(getContext().getResources().getDrawable(R.drawable.icon_home_city2));
                break;
            case 2:
                holder.tvCityName.setBackground(getContext().getResources().getDrawable(R.drawable.icon_home_city3));
                break;
            case 3:
                holder.tvCityName.setBackground(getContext().getResources().getDrawable(R.drawable.icon_home_city4));
                break;
            case 4:
                holder.tvCityName.setBackground(getContext().getResources().getDrawable(R.drawable.icon_home_city5));
                break;
            case 5:
                holder.tvCityName.setBackground(getContext().getResources().getDrawable(R.drawable.icon_home_city6));
                break;
        }
    }

    public class ViewHolder extends RecyclerHolder {
        @BindView(R.id.tv_city_name)
        TextView tvCityName;

        public ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
