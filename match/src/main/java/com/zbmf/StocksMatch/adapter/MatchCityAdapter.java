package com.zbmf.StocksMatch.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zbmf.StocksMatch.R;
import com.zbmf.StocksMatch.bean.City;
import com.zbmf.StocksMatch.view.GlideOptionsManager;
import com.zbmf.worklibrary.adapter.ListAdapter;
import com.zbmf.worklibrary.util.DoubleFromat;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xuhao on 2017/12/12.
 */

public class MatchCityAdapter extends ListAdapter<City> {


    public MatchCityAdapter(Activity context) {
        super(context);
    }

    @Override
    protected int getLayout() {
        return R.layout.item_match_city_layout;
    }

    @Override
    public View getHolderView(int position, View convertView, City city) {
        ViewHolder holder= (ViewHolder) convertView.getTag();
        if(holder==null){
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        if(position==0){
            holder.imvRankImg.setVisibility(View.VISIBLE);
            holder.imvRankImg.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_gold));
            holder.tvRank.setVisibility(View.INVISIBLE);
        }else if(position==1){
            holder.imvRankImg.setVisibility(View.VISIBLE);
            holder.imvRankImg.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_silver));
            holder.tvRank.setVisibility(View.INVISIBLE);
        }else if(position==2){
            holder.imvRankImg.setVisibility(View.VISIBLE);
            holder.imvRankImg.setImageDrawable(mContext.getResources().getDrawable(R.drawable.icon_copper));
            holder.tvRank.setVisibility(View.INVISIBLE);
        }else{
            holder.tvRank.setVisibility(View.VISIBLE);
            holder.imvRankImg.setVisibility(View.GONE);
            holder.tvRank.setText((position+1)+"");
        }
        holder.tvName.setText(city.getCityName());
        holder.tvYield.setText(DoubleFromat.getStockDouble(city.getYield(),2)+"%");
        holder.tvYield.setTextColor(city.getYield()>0?mContext.getResources().getColor(R.color.red):mContext.getResources().getColor(R.color.green));
        Glide.with(mContext).load(city.getOne_avatar()).apply(GlideOptionsManager.getInstance().getRequestOptions()).into(holder.imvOneAvatar);
        Glide.with(mContext).load(city.getTwo_avatar()).apply(GlideOptionsManager.getInstance().getRequestOptions()).into(holder.imvTwoAvatar);
        Glide.with(mContext).load(city.getThree_avatar()).apply(GlideOptionsManager.getInstance().getRequestOptions()).into(holder.imvThreeAvatar);
        return convertView;
    }
    public class ViewHolder{
        @BindView(R.id.imv_rank_img)
        ImageView imvRankImg;
        @BindView(R.id.tv_rank)
        TextView tvRank;
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_yield)
        TextView tvYield;
        @BindView(R.id.imv_three_avatar)
        ImageView imvThreeAvatar;
        @BindView(R.id.imv_two_avatar)
        ImageView imvTwoAvatar;
        @BindView(R.id.imv_one_avatar)
        ImageView imvOneAvatar;
        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
