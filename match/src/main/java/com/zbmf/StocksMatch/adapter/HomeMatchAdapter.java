package com.zbmf.StocksMatch.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zbmf.StocksMatch.R;
import com.zbmf.StocksMatch.bean.MatchBean;
import com.zbmf.worklibrary.adapter.ListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xuhao on 2017/11/24.
 */

public class HomeMatchAdapter extends ListAdapter<MatchBean> {

    public HomeMatchAdapter(Activity context) {
        super(context);
    }

    @Override
    protected int getLayout() {
        return R.layout.item_super_match_layout;
    }

    @Override
    public View getHolderView(int position, View convertView, MatchBean matchBean) {
        ViewHolder holder= (ViewHolder) convertView.getTag();
        if (holder == null) {
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder.tvMatchName.setText(matchBean.getTitle());
        holder.tvMatchPlayer.setText(String.format(mContext.getResources().getString(R.string.match_players),matchBean.getPlayers()));
        return convertView;
    }

    public class ViewHolder{
        @BindView(R.id.tv_match_name)
        TextView tvMatchName;
        @BindView(R.id.tv_match_type)
        TextView tvMatchType;
        @BindView(R.id.tv_match_action)
        TextView tvMatchAction;
        @BindView(R.id.tv_match_player)
        TextView tvMatchPlayer;
        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }

}
