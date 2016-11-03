package org.chzz.demo.view.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import org.chzz.adapter.CHZZAdapterViewAdapter;
import org.chzz.adapter.CHZZViewHolderHelper;
import org.chzz.banner.CHZZBanner;
import org.chzz.demo.App;
import org.chzz.demo.R;
import org.chzz.demo.engine.Engine;
import org.chzz.demo.model.BannerModel;
import org.chzz.demo.model.RefreshModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 作者:copy 邮件:2499551993@qq.com
 * 创建时间:16/7/21 下午8:26
 * 描述:
 */
public class ListViewDemoActivity extends Activity {
    private ListView mContentLv;
    private CHZZBanner mBanner;
    private ContentAdapter mContentAdapter;

    private Engine mEngine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_demo);
        mContentLv = (ListView) findViewById(R.id.lv_content);

        mEngine = App.getInstance().getEngine();

        initListView();


        loadBannerData();
        loadContentData();
    }

    /**
     * 初始化ListView
     */
    private void initListView() {
        // 初始化HeaderView
        View headerView = View.inflate(this, R.layout.layout_header, null);
        mBanner = (CHZZBanner) headerView.findViewById(R.id.banner);
        mBanner.setAdapter(new CHZZBanner.Adapter() {
            @Override
            public void fillBannerItem(CHZZBanner banner, View view, Object model, int position) {
                Glide.with(ListViewDemoActivity.this).load(model).placeholder(R.mipmap.holder).error(R.mipmap.holder).dontAnimate().thumbnail(0.1f).into((ImageView) view);
            }
        });

        // 初始化ListView
        mContentLv.addHeaderView(headerView);
        mContentAdapter = new ContentAdapter(this);
        mContentLv.setAdapter(mContentAdapter);
    }

    /**
     * 加载头部广告条的数据
     */
    private void loadBannerData() {
        mEngine.fetchItemsWithItemCount(5).enqueue(new Callback<BannerModel>() {
            @Override
            public void onResponse(Call<BannerModel> call, Response<BannerModel> response) {
                BannerModel bannerModel = response.body();
                mBanner.setData(bannerModel.imgs, bannerModel.tips);
            }

            @Override
            public void onFailure(Call<BannerModel> call, Throwable t) {
                Toast.makeText(App.getInstance(), "加载广告条数据失败", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 加载内容列表数据
     */
    private void loadContentData() {
        mEngine.loadContentData("http://7xk9dj.com1.z0.glb.clouddn.com/refreshlayout/api/defaultdata.json").enqueue(new Callback<List<RefreshModel>>() {
            @Override
            public void onResponse(Call<List<RefreshModel>> call, Response<List<RefreshModel>> response) {
                mContentAdapter.setData(response.body());
            }

            @Override
            public void onFailure(Call<List<RefreshModel>> call, Throwable t) {
                Toast.makeText(App.getInstance(), "加载内容数据失败", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private class ContentAdapter extends CHZZAdapterViewAdapter<RefreshModel> {

        public ContentAdapter(Context context) {
            super(context, R.layout.item_normal);
        }

        @Override
        protected void fillData(CHZZViewHolderHelper helper, int position, RefreshModel model) {
            helper.setText(R.id.tv_item_normal_title, model.title).setText(R.id.tv_item_normal_detail, model.detail);
        }
    }
}
