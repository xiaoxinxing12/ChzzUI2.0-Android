package org.chzz.banner.transformer;

import android.view.View;

import com.nineoldandroids.view.ViewHelper;

/**
 * 作者:copy 邮件:2499551993@qq.com
 * 创建时间:15/6/19 17:39
 * 描述:
 */
public class CubePageTransformer extends CHZZPageTransformer {
    private float mMaxRotation = 90.0f;

    public CubePageTransformer() {
    }

    public CubePageTransformer(float maxRotation) {
        setMaxRotation(maxRotation);
    }

    @Override
    public void handleInvisiblePage(View view, float position) {
        ViewHelper.setPivotX(view, view.getMeasuredWidth());
        ViewHelper.setPivotY(view, view.getMeasuredHeight() * 0.5f);
        ViewHelper.setRotationY(view, 0);
    }

    @Override
    public void handleLeftPage(View view, float position) {
        ViewHelper.setPivotX(view, view.getMeasuredWidth());
        ViewHelper.setPivotY(view, view.getMeasuredHeight() * 0.5f);
        ViewHelper.setRotationY(view, mMaxRotation * position);
    }

    @Override
    public void handleRightPage(View view, float position) {
        ViewHelper.setPivotX(view, 0);
        ViewHelper.setPivotY(view, view.getMeasuredHeight() * 0.5f);
        ViewHelper.setRotationY(view, mMaxRotation * position);
    }

    public void setMaxRotation(float maxRotation) {
        if (maxRotation >= 0.0f && maxRotation <= 90.0f) {
            mMaxRotation = maxRotation;
        }
    }

}
