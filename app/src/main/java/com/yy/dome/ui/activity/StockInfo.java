package com.yy.dome.ui.activity;

public class StockInfo {
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    private String year;//名称

    public String getLqxs() {
        return lqxs;
    }

    public void setLqxs(String lqxs) {
        this.lqxs = lqxs;
    }

    private String lqxs;//录取人数

    public String getXcfs() {
        return xcfs;
    }

    public void setXcfs(String xcfs) {
        this.xcfs = xcfs;
    }

    private String xcfs;//波动值

    public String getWcxs() {
        return wcxs;
    }

    public void setWcxs(String wcxs) {
        this.wcxs = wcxs;
    }

    private String wcxs;//最低线差


    public StockInfo(String year, String lqxs, String xcfs, String wcxs) {
        this.year = year;
        this.lqxs = lqxs;
        this.xcfs = xcfs;
        this.wcxs = wcxs;

    }


}
