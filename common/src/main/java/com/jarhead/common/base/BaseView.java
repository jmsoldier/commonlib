package com.jarhead.common.base;

/**
 * des:baseview
 */
public interface BaseView {
    /*******
     * 内嵌加载
     *******/
    void showLoading(String title);

    void stopLoading();

    void showErrorTip(String msg);
}