package org.chzz.demo.model;

import android.content.Context;
import android.databinding.BaseObservable;

/**
 * 作者:copy 邮件:2499551993@qq.com
 * 创建时间:15/5/21 14:53
 * 描述:
 */
public class RefreshModel  extends BaseObservable {

    private Context context;
    public String title;
    public String detail;
    public String avatorPath;
    public boolean selected;

    public RefreshModel( ) {

    }
    public RefreshModel(Context context) {
        this.context = context;
    }

    public String getAvatorPath() {
        return avatorPath;
    }

    public void setAvatorPath(String avatorPath) {
        this.avatorPath = avatorPath;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}