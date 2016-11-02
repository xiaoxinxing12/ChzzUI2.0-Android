package org.chzz.demo.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.chzz.demo.R;
import org.chzz.picker.TimePickerView;
import org.chzz.utils.DateUtils;
import org.chzz.widget.CHZZSpinner;
import org.chzz.widget.CHZZSpinnerBaseAdapter;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * ============================================================
 * 版权 ：深圳市医友智能技术有限公司 版权所有 (c)   2016/11/1
 * 作者:copy   xiaoxinxing12@qq.com
 * 版本 ：1.0
 * 创建日期 ： 2016/11/1--9:03
 * 描述 ：
 * 修订历史 ：
 * ============================================================
 **/

public class MainActivity extends BaseActivity implements CHZZSpinnerBaseAdapter.onCheckBoxChecked {
    @Bind(R.id.but_adapter)
    Button butAdapter;
    @Bind(R.id.but_refresh)
    Button butRefresh;
    @Bind(R.id.tv_title)
    TextView tvTitle;
    @Bind(R.id.but_banner)
    Button butBanner;
    @Bind(R.id.but_indicator)
    Button butIndicator;
    @Bind(R.id.but_tabLayout)
    Button butTabLayout;
    @Bind(R.id.chzz_spinner)
    CHZZSpinner chzzSpinner;
    @Bind(R.id.chzz_spinner1)
    CHZZSpinner chzzSpinner1;
    List<String> dataset = new LinkedList<>(Arrays.asList("One11111111111111111111111111111111111111111111111111111111", "Two", "Three", "Four", "Five"));
    @Bind(R.id.tv_Time)
    TextView tvTime;
    private TimePickerView pvTime;
    private Map<String, Integer> mIntegerList = new HashMap<>();

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        tvTitle.setText("CHZZ—2.0");
    }

    @Override
    protected void setListener() {
        butAdapter.setOnClickListener(this);
        butRefresh.setOnClickListener(this);
        butBanner.setOnClickListener(this);
        butIndicator.setOnClickListener(this);
        butTabLayout.setOnClickListener(this);
        initSpinner();
        initTimePicker();
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.but_adapter:
                Intent adapter = new Intent(this, AdapterActivity.class);
                startActivity(adapter);
                break;
            case R.id.but_refresh:
                Intent refresh = new Intent(this, RefreshActivity.class);
                startActivity(refresh);

                break;
            case R.id.but_banner:
                Intent banner = new Intent(this, BannerActivity.class);
                startActivity(banner);

                break;
            case R.id.but_indicator:
                Intent indicator = new Intent(this, IndicatorActivity.class);
                startActivity(indicator);

                break;
            case R.id.but_tabLayout:
                Intent tabLayout = new Intent(this, SimpleHomeActivity.class);
                startActivity(tabLayout);

                break;
        }
    }

    private void initSpinner() {

        chzzSpinner.attachDataSource(dataset);
        chzzSpinner.setSelectedIndex(3);
        chzzSpinner.setText("9999");
        chzzSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, dataset.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        chzzSpinner1.attachDataSource(dataset, true, this);
        chzzSpinner1.setSelectedIndex(3);
        chzzSpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, dataset.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void initTimePicker() {
        tvTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvTime.setStartDateAndTips(DateUtils.stringToDates("2016-09-06"), "不能小于开始日期");
                pvTime.show();
            }
        });

        pvTime = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH_DAY,
                DateUtils.stringToDates("2016-09-04"), DateUtils.stringToDates("2016-09-07"), "不能超出轮转期");
        //控制时间范围
        pvTime.setTime(new Date());
        pvTime.setTitle("2016-09-04至2016-09-07");
        pvTime.setCyclic(false);
        pvTime.setCancelable(true);
        //时间选择后回调
        pvTime.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {

            @Override
            public void onTimeSelect(Date date) {
                tvTime.setText(getTime(date));
            }
        });
    }
    public static String getTime(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }

    @Override
    public void setOnCheckedListener(int position, boolean isChecked) {
        if (isChecked) {
            mIntegerList.put(position + "", position);
            Toast.makeText(this, position + "增加", Toast.LENGTH_LONG).show();
        } else {
            mIntegerList.remove(position + "");
            Toast.makeText(this, position + "删除", Toast.LENGTH_LONG).show();
        }
        chzzSpinner1.setText("已选" + mIntegerList.size() + "");
    }

}
