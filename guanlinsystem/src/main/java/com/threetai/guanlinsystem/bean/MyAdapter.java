package com.threetai.guanlinsystem.bean;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.threetai.guanlinsystem.R;

import java.util.List;

/**
 * Created by Administrator on 2016/7/19.
 */
public class MyAdapter extends BaseAdapter {
    public Context mContext;
    public LayoutInflater inflater;
    public List<JavaBean.HeWeatherdataserviceBean.HourlyForecastBean> mdata;

    public MyAdapter(Context context, List<JavaBean.HeWeatherdataserviceBean.HourlyForecastBean> data) {
        this.mdata = data;
        this.mContext = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        if (mdata != null) {
            return mdata.size();
        } else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        if (mdata != null) {
            return mdata.get(position);
        } else {
            return null;
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.hourly_forecast, null);
            holder.date = (TextView) convertView.findViewById(R.id.tv_date);
            holder.hum = (TextView) convertView.findViewById(R.id.tv_hum);
            holder.pop = (TextView) convertView.findViewById(R.id.tv_pop);
            holder.pres = (TextView) convertView.findViewById(R.id.tv_pres);
            holder.tmp = (TextView) convertView.findViewById(R.id.tv_tmp);
            holder.dir = (TextView) convertView.findViewById(R.id.tv_dir);
            holder.sc = (TextView) convertView.findViewById(R.id.tv_sc);
            holder.spd = (TextView) convertView.findViewById(R.id.tv_spd);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        //给id赋值
        holder.date.setText(mdata.get(position).getDate().substring(10, mdata.get(position).getDate().length()));
        holder.hum.setText("湿度:" + mdata.get(position).getHum());
        holder.pop.setText("降水概率:" + mdata.get(position).getPop());
        holder.pres.setText("气压:" + mdata.get(position).getPres());
        holder.tmp.setText("温度:" + mdata.get(position).getTmp());
        holder.dir.setText("风向(方向):" + mdata.get(position).getWind().getDir());
        holder.sc.setText("风力等级:" + mdata.get(position).getWind().getSc());
        holder.spd.setText("风速(Kmph):" + mdata.get(position).getWind().getSpd());
        return convertView;
    }

    static class ViewHolder {
        public TextView date;//	2016-07-2010:00
        public TextView hum;//	97
        public TextView pop;//	95
        public TextView pres;//	998
        public TextView tmp;//	28
        public TextView dir;//	南风
        public TextView sc;//	微风
        public TextView spd;//	13

    }


}
