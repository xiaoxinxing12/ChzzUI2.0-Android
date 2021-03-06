package org.chzz.banner;

import android.content.Context;
import android.widget.Scroller;

/**
 * 作者:copy 邮件:2499551993@qq.com
 * 创建时间:15/6/19 下午11:59
 * 描述:
 */
public class CHZZBannerScroller extends Scroller {
    private int mDuration = 1000;

    public CHZZBannerScroller(Context context, int duration) {
        super(context);
        mDuration = duration;
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy) {
        super.startScroll(startX, startY, dx, dy, mDuration);
    }

    @Override
    public void startScroll(int startX, int startY, int dx, int dy, int duration) {
        super.startScroll(startX, startY, dx, dy, mDuration);
    }
}