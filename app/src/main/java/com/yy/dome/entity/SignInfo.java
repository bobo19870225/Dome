package com.yy.dome.entity;

public class SignInfo {

    public class Root
    {
        private int ret;

        private String phone;

        private String token;

        public void setRet(int ret){
            this.ret = ret;
        }
        public int getRet(){
            return this.ret;
        }
        public void setPhone(String phone){
            this.phone = phone;
        }
        public String getPhone(){
            return this.phone;
        }
        public void setToken(String token){
            this.token = token;
        }
        public String getToken(){
            return this.token;
        }
    }

}
