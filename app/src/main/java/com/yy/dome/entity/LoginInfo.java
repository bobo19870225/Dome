package com.yy.dome.entity;

public class LoginInfo {


    public class Data
    {
        private String phone;

        private String loginTime;

        private String followSchool;

        private String form;

        private String articleLike;

        private String score;

        private String password;

        private String followArticle;

        private String id;

        private String wlk;

        private String rank;

        private String area;

        private String address;

        private String name;

        private String age;

        private String nameAll;

        private String opinion;

        public void setPhone(String phone){
            this.phone = phone;
        }
        public String getPhone(){
            return this.phone;
        }
        public void setLoginTime(String loginTime){
            this.loginTime = loginTime;
        }
        public String getLoginTime(){
            return this.loginTime;
        }
        public void setFollowSchool(String followSchool){
            this.followSchool = followSchool;
        }
        public String getFollowSchool(){
            return this.followSchool;
        }
        public void setForm(String form){
            this.form = form;
        }
        public String getForm(){
            return this.form;
        }
        public void setArticleLike(String articleLike){
            this.articleLike = articleLike;
        }
        public String getArticleLike(){
            return this.articleLike;
        }
        public void setScore(String score){
            this.score = score;
        }
        public String getScore(){
            return this.score;
        }
        public void setPassword(String password){
            this.password = password;
        }
        public String getPassword(){
            return this.password;
        }
        public void setFollowArticle(String followArticle){
            this.followArticle = followArticle;
        }
        public String getFollowArticle(){
            return this.followArticle;
        }
        public void setId(String id){
            this.id = id;
        }
        public String getId(){
            return this.id;
        }
        public void setWlk(String wlk){
            this.wlk = wlk;
        }
        public String getWlk(){
            return this.wlk;
        }
        public void setRank(String rank){
            this.rank = rank;
        }
        public String getRank(){
            return this.rank;
        }
        public void setArea(String area){
            this.area = area;
        }
        public String getArea(){
            return this.area;
        }
        public void setAddress(String address){
            this.address = address;
        }
        public String getAddress(){
            return this.address;
        }
        public void setName(String name){
            this.name = name;
        }
        public String getName(){
            return this.name;
        }
        public void setAge(String age){
            this.age = age;
        }
        public String getAge(){
            return this.age;
        }
        public void setNameAll(String nameAll){
            this.nameAll = nameAll;
        }
        public String getNameAll(){
            return this.nameAll;
        }
        public void setOpinion(String opinion){
            this.opinion = opinion;
        }
        public String getOpinion(){
            return this.opinion;
        }
    }


    private int ret;

    private String token;

    private Data data;

    private String msg;

    public void setRet(int ret){
        this.ret = ret;
    }
    public int getRet(){
        return this.ret;
    }
    public void setToken(String token){
        this.token = token;
    }
    public String getToken(){
        return this.token;
    }
    public void setData(Data data){
        this.data = data;
    }
    public Data getData(){
        return this.data;
    }
    public void setMsg(String msg){
        this.msg = msg;
    }
    public String getMsg(){
        return this.msg;
    }


}
