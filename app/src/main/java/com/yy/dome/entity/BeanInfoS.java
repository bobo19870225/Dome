package com.yy.dome.entity;

import java.util.List;

public class BeanInfoS {

    public class Data
    {
        private String wlk;

        private int minScore;

        private int maxScore;

        private String batch;

        private int enrollNumber;

        private String year;

        private int batchLine;

        private int ranking;

        public void setWlk(String wlk){
            this.wlk = wlk;
        }
        public String getWlk(){
            return this.wlk;
        }
        public void setMinScore(int minScore){
            this.minScore = minScore;
        }
        public int getMinScore(){
            return this.minScore;
        }
        public void setMaxScore(int maxScore){
            this.maxScore = maxScore;
        }
        public int getMaxScore(){
            return this.maxScore;
        }
        public void setBatch(String batch){
            this.batch = batch;
        }
        public String getBatch(){
            return this.batch;
        }
        public void setEnrollNumber(int enrollNumber){
            this.enrollNumber = enrollNumber;
        }
        public int getEnrollNumber(){
            return this.enrollNumber;
        }
        public void setYear(String year){
            this.year = year;
        }
        public String getYear(){
            return this.year;
        }
        public void setBatchLine(int batchLine){
            this.batchLine = batchLine;
        }
        public int getBatchLine(){
            return this.batchLine;
        }
        public void setRanking(int ranking){
            this.ranking = ranking;
        }
        public int getRanking(){
            return this.ranking;
        }
    }


        private int ret;

        private List<Data> data;

        private String msg;

        public void setRet(int ret){
            this.ret = ret;
        }
        public int getRet(){
            return this.ret;
        }
        public void setData(List<Data> data){
            this.data = data;
        }
        public List<Data> getData(){
            return this.data;
        }
        public void setMsg(String msg){
            this.msg = msg;
        }
        public String getMsg(){
            return this.msg;
        }


}
