package com.yy.dome.entity.home;

import java.util.List;

public class ArticleInfo {

    public class Data
    {
        private String id;

        private int valid;

        private String time;

        private String title;

        private int rowno;

        private int seeTimes;

        private int hot;

        private String userPhone;

        private String titleImg;

        private int type;

        public void setId(String id){
            this.id = id;
        }
        public String getId(){
            return this.id;
        }
        public void setValid(int valid){
            this.valid = valid;
        }
        public int getValid(){
            return this.valid;
        }
        public void setTime(String time){
            this.time = time;
        }
        public String getTime(){
            return this.time;
        }
        public void setTitle(String title){
            this.title = title;
        }
        public String getTitle(){
            return this.title;
        }
        public void setRowno(int rowno){
            this.rowno = rowno;
        }
        public int getRowno(){
            return this.rowno;
        }
        public void setSeeTimes(int seeTimes){
            this.seeTimes = seeTimes;
        }
        public int getSeeTimes(){
            return this.seeTimes;
        }
        public void setHot(int hot){
            this.hot = hot;
        }
        public int getHot(){
            return this.hot;
        }
        public void setUserPhone(String userPhone){
            this.userPhone = userPhone;
        }
        public String getUserPhone(){
            return this.userPhone;
        }
        public void setTitleImg(String titleImg){
            this.titleImg = titleImg;
        }
        public String getTitleImg(){
            return this.titleImg;
        }
        public void setType(int type){
            this.type = type;
        }
        public int getType(){
            return this.type;
        }
    }

        private int ret;

        private int page;

        private List<Data> data;

        private String msg;

        private String token;

        public void setRet(int ret){
            this.ret = ret;
        }
        public int getRet(){
            return this.ret;
        }
        public void setPage(int page){
            this.page = page;
        }
        public int getPage(){
            return this.page;
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
        public void setToken(String token){
            this.token = token;
        }
        public String getToken(){
            return this.token;
        }




}
