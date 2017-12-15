package com.zbmf.StocksMatch.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zbmf.StocksMatch.R;
import com.zbmf.StocksMatch.adapterinterface.OnAdapterClickListener;
import com.zbmf.StocksMatch.bean.DealSys;
import com.zbmf.StocksMatch.bean.Yield;
import com.zbmf.StocksMatch.constatns.Constans;
import com.zbmf.StocksMatch.view.GlideOptionsManager;
import com.zbmf.worklibrary.adapter.ListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 根据条目进行分类
 */
public class MatchRankAdapter extends ListAdapter<Yield> {

    private OnAdapterClickListener adapterClickListener;
    private int flag;
    public MatchRankAdapter(Activity context) {
        super(context);
    }

    @Override
    protected int getLayout() {
        return R.layout.stock_rank_flow;
    }

    public MatchRankAdapter(Activity context,int flag) {
        super(context);
        this.flag=flag;
    }
    public void setAdapterClickListener(OnAdapterClickListener adapterClickListener) {
        this.adapterClickListener = adapterClickListener;
    }

    @Override
    public View getHolderView(int position, View convertView, Yield yield) {
        ViewHolder holder=(ViewHolder) convertView.getTag();
        if (holder == null) {
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

        holder.tvNickname.setText(yield.getNickname());
        String yieldStr="";
        switch (flag){
            case Constans.DAY_RANK:
                yieldStr=String.format("%+.2f%%", yield.getDay_yield()*100);
                break;
            case Constans.WEEK_RANK:
                yieldStr=String.format("%+.2f%%", yield.getWeek_yield()*100);
                break;
            case Constans.MOUNTH_RANK:
                yieldStr=String.format("%+.2f%%", yield.getMonth_yield()*100);
                break;
            case Constans.ALL_RANK:
                yieldStr=String.format("%+.2f%%", yield.getYield()*100);
                break;
        }
        holder.tvYield.setText(yieldStr);
        final DealSys dealSys=yield.getDealSys();
        if(dealSys!=null){
            holder.tvAction.setText(dealSys.getAction()!=null?dealSys.getAction().replace(" ",""):"");
            holder.tvSymbol.setText(dealSys.getStock_name()!=null?dealSys.getStock_name():dealSys.getName()!=null?dealSys.getName():"");
        }
        Glide.with(mContext).load(yield.getAvatar()).apply(GlideOptionsManager.getInstance().getRequestOptions()).into(holder.ivRcv);
        return convertView;
    }


    public class ViewHolder {
        @BindView(R.id.imv_rank_img)
        ImageView imvRankImg;
        @BindView(R.id.tv_rank)
        TextView tvRank;
        @BindView(R.id.iv_rcv)
        ImageView ivRcv;
        @BindView(R.id.tv_nickname)
        TextView tvNickname;
        @BindView(R.id.tv_yield)
        TextView tvYield;
        @BindView(R.id.tv_action)
        TextView tvAction;
        @BindView(R.id.tv_symbol)
        TextView tvSymbol;
        @BindView(R.id.tv_common)
        TextView tvCommon;
        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
