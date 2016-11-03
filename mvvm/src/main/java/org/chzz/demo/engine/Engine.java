package org.chzz.demo.engine;

import org.chzz.demo.model.BannerModel;
import org.chzz.demo.model.RefreshModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * ============================================================
 * 版权 ：深圳市医友智能技术有限公司 版权所有 (c)   2016/11/2
 * 作者:copy   xiaoxinxing12@qq.com
 * 版本 ：1.0
 * 创建日期 ： 2016/11/2--9:30
 * 描述 ：
 * 修订历史 ：
 * ============================================================
 **/

public interface Engine {

    @GET("defaultdata6.json")
    Observable<List<RefreshModel>> loadInitDatas();

    @GET("newdata{pageNumber}.json")
    Observable<List<RefreshModel>> loadNewData(@Path("pageNumber") int pageNumber);

    @GET("5item.json")
    Call<BannerModel> getBannerModel();
}
