package com.threetai.guanlinsystem;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.apistore.sdk.ApiCallBack;
import com.baidu.apistore.sdk.ApiStoreSDK;
import com.baidu.apistore.sdk.network.Parameters;
import com.google.gson.Gson;
import com.threetai.guanlinsystem.bean.JavaBean;
import com.threetai.guanlinsystem.bean.MyAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    private static final String HTTP_URL = "http://apis.baidu.com/heweather/weather/free";
    private static final String TAG = "MainActivity";
    @BindView(R.id.et_city)
    EditText etCity;
    @BindView(R.id.bt_search)
    Button btSearch;
    @BindView(R.id.lv_listView)
    ListView lvListView;
    @BindView(R.id.tv_aqi)
    TextView tvAqi;
    @BindView(R.id.tv_co)
    TextView tvCo;
    @BindView(R.id.tv_no2)
    TextView tvNo2;
    @BindView(R.id.tv_o3)
    TextView tvO3;
    @BindView(R.id.tv_pm10)
    TextView tvPm10;
    @BindView(R.id.tv_pm25)
    TextView tvPm25;
    @BindView(R.id.tv_qlty)
    TextView tvQlty;
    @BindView(R.id.tv_so2)
    TextView tvSo2;
    @BindView(R.id.layout_aqi)
    LinearLayout layoutAqi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }


    @OnClick(R.id.bt_search)
    public void onClick() {
        String city = etCity.getText().toString().trim();
        if (TextUtils.isEmpty(city)) {
            Toast.makeText(MainActivity.this, "城市不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        Parameters parameters = new Parameters();
        Log.i(TAG, "当前城市为:" + city);
        parameters.put("city", city);
        ApiStoreSDK.execute(HTTP_URL, ApiStoreSDK.GET, parameters, new ApiCallBack() {
            @Override
            public void onSuccess(int i, String s) {
                super.onSuccess(i, s);
                //替换json数据中的空格和3.0,不然无法解析
                String trim = s.trim().replace(" ", "").replace("3.0", "");
                Log.i(TAG, trim);
                //解析json数据
                Gson gson = new Gson();
                JavaBean javaBean = gson.fromJson(trim, JavaBean.class);
                String status = javaBean.getHeWeatherdataservice().get(0).getStatus();
                if ("ok".equals(status)) {
                    JavaBean.HeWeatherdataserviceBean heWeatherdataserviceBean = javaBean.getHeWeatherdataservice().get(0);
                    JavaBean.HeWeatherdataserviceBean.AqiBean aqi = heWeatherdataserviceBean.getAqi();
                    if (aqi == null) {
                        //无aqi信息不显示
                        layoutAqi.setVisibility(View.GONE);
                        Toast.makeText(getApplicationContext(), "无空气指数信息", Toast.LENGTH_SHORT).show();
                        return;
                    } else {
                        //有aqi信息显示
                        layoutAqi.setVisibility(View.VISIBLE);
                        Toast.makeText(getApplicationContext(), "成功获取空气指数信息", Toast.LENGTH_SHORT).show();
                        JavaBean.HeWeatherdataserviceBean.AqiBean.CityBean city = aqi.getCity();
                        //设置城市的aqi信息
                        tvAqi.setText(city.getAqi());
                        tvSo2.setText(city.getSo2());
                        tvQlty.setText(city.getQlty());
                        tvPm25.setText(city.getPm25());
                        tvPm10.setText(city.getPm10());
                        tvCo.setText(city.getCo());

                        lvListView.setVisibility(View.VISIBLE);
                        Log.i(TAG, "数据长度:" + String.valueOf(heWeatherdataserviceBean.getHourly_forecast().size()));
                        lvListView.setAdapter(new MyAdapter(MainActivity.this, heWeatherdataserviceBean.getHourly_forecast()));
                        setListViewHeightBasedOnChildren(lvListView);
                    }
                } else if ("unknowncity".equals(status)) {
                    //城市输入错误
                    Toast.makeText(getApplicationContext(), "城市输入有误", Toast.LENGTH_SHORT).show();
                    lvListView.setVisibility(View.GONE);
                }


            }

            @Override
            public void onComplete() {
                super.onComplete();
            }

            @Override
            public void onError(int i, String s, Exception e) {
                super.onError(i, s, e);
                Toast.makeText(MainActivity.this, "加载失败", Toast.LENGTH_SHORT).show();
            }
        });


    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        // 获取ListView对应的Adapter
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }

        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            // listAdapter.getCount()返回数据项的数目
            View listItem = listAdapter.getView(i, null, listView);
            // 计算子项View 的宽高
            listItem.measure(0, 0);
            // 统计所有子项的总高度
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        // listView.getDividerHeight()获取子项间分隔符占用的高度
        // params.height最后得到整个ListView完整显示需要的高度
        listView.setLayoutParams(params);
    }

}
