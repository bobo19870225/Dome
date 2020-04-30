package com.yy.dome.presenter;

/**
 * Created by zhangqie on 2017/9/1.
 */

public interface Presenter<V> {
    void attachView(V view);

    void detachView();

}
