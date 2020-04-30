package com.yy.dome.view;

/**
 * Created by huyuongjiang on 2017/8/22.
 */

public interface IView {

    void onLoadContributorStart();

    void onLoadContribtorComplete(String topContributor, int position);

    void onNetWork();

    void onError();

}
