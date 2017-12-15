package com.zbmf.StocksMatch.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.zbmf.StocksMatch.R;
import com.zbmf.StocksMatch.bean.StockholdsBean;
import com.zbmf.worklibrary.adapter.ListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by xuhao on 2017/10/24.
 */

public class MatchDetailStockHoldAdapter extends ListAdapter<StockholdsBean> {
    private LayoutInflater inflater;
    private OnCommit onClickListener;

    public void setOnClickListener(OnCommit onClickListener) {
        this.onClickListener = onClickListener;
    }

    public MatchDetailStockHoldAdapter(Activity context) {
        super(context);
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    protected int getLayout() {
        return R.layout.item_match_stock_hold;
    }

    @Override
    public View getHolderView(int position, View convertView, final StockholdsBean stockholdsBean) {
        ViewHolder   holder = (ViewHolder) convertView.getTag();
        if (holder == null) {
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder.tvPrice.setText(String.format("%.2f", stockholdsBean.getPrice_buy()));
        holder.tvPrice2.setText(String.format("%.2f", stockholdsBean.getCurrent()));
        holder.tvYield.setText(String.format("%+.2f%%", stockholdsBean.getYield_float() * 100));
        holder.tvName.setText(stockholdsBean.getName());
        holder.tvCommit.setText(stockholdsBean.getComment_count() + "");
        holder.tvYield.setTextColor(stockholdsBean.getYield_float()>0?mContext.getResources().getColor(R.color.red):mContext.getResources().getColor(R.color.green));
        if (onClickListener != null) {
            holder.tvCommit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickListener.onCommit(stockholdsBean);
                }
            });
        }
        return convertView;
    }

    public class ViewHolder {
        @BindView(R.id.tv_name)
        TextView tvName;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.tv_price2)
        TextView tvPrice2;
        @BindView(R.id.tv_yield)
        TextView tvYield;
        @BindView(R.id.tv_commit)
        TextView tvCommit;
        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    public interface OnCommit {
        void onCommit(StockholdsBean stockholdsBean);
    }
}
