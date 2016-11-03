package org.chzz.demo.engine;

import org.chzz.demo.model.BannerModel;
import org.chzz.demo.model.RefreshModel;
import org.chzz.demo.model.StaggeredModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * 作者:copy 邮件:2499551993@qq.com
 * 创建时间:15/9/17 下午2:01
 * 描述:
 */
public interface Engine {

    @GET("defaultdata6.json")
    Call<List<RefreshModel>> loadInitDatas();

    @GET("newdata{pageNumber}.json")
    Call<List<RefreshModel>> loadNewData(@Path("pageNumber") int pageNumber);

    @GET("moredata{pageNumber}.json")
    Call<List<RefreshModel>> loadMoreData(@Path("pageNumber") int pageNumber);

    @GET("staggered_default.json")
    Call<List<StaggeredModel>> loadDefaultStaggeredData();

    @GET("staggered_new{pageNumber}.json")
    Call<List<StaggeredModel>> loadNewStaggeredData(@Path("pageNumber") int pageNumber);

    @GET("staggered_more{pageNumber}.json")
    Call<List<StaggeredModel>> loadMoreStaggeredData(@Path("pageNumber") int pageNumber);

    @GET("5item.json")
    Call<BannerModel> getBannerModel();

    @GET("{itemCount}item.json")
    Call<BannerModel> fetchItemsWithItemCount(@Path("itemCount") int itemCount);

    @GET
    Call<List<RefreshModel>> loadContentData(@Url String url);

    @GET("normalModels.json")
    Call<List<RefreshModel>> getNormalModels();
}