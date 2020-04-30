package com.yy.dome.entity;

import java.util.List;

public class VRMapInfo {



        private String phone;

        private String token;

        private String schoolId;

        private String goodsId;

        private String ret;

        private String msg;

        private List<String> data;

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
        public void setSchoolId(String schoolId){
            this.schoolId = schoolId;
        }
        public String getSchoolId(){
            return this.schoolId;
        }
        public void setGoodsId(String goodsId){
            this.goodsId = goodsId;
        }
        public String getGoodsId(){
            return this.goodsId;
        }
        public void setRet(String ret){
            this.ret = ret;
        }
        public String getRet(){
            return this.ret;
        }
        public void setMsg(String msg){
            this.msg = msg;
        }
        public String getMsg(){
            return this.msg;
        }
        public void setData(List<String> data){
            this.data = data;
        }
        public List<String> getData(){
            return this.data;
        }


}
