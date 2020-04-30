package com.yy.dome.entity;

public class VersionInfo {

    public class Data
    {
        private int id;

        private String number;

        private int type;

        public String getvType() {
            return vType;
        }

        public void setvType(String vType) {
            this.vType = vType;
        }

        private String vType;

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        private String body;

        public void setId(int id){
            this.id = id;
        }
        public int getId(){
            return this.id;
        }
        public void setNumber(String number){
            this.number = number;
        }
        public String getNumber(){
            return this.number;
        }
        public void setType(int type){
            this.type = type;
        }
        public int getType(){
            return this.type;
        }
        public void setVType(String vType){
            this.vType = vType;
        }
        public String getVType(){
            return this.vType;
        }
    }


        private int ret;

        private Data data;

        private String token;

        public void setRet(int ret){
            this.ret = ret;
        }
        public int getRet(){
            return this.ret;
        }
        public void setData(Data data){
            this.data = data;
        }
        public Data getData(){
            return this.data;
        }
        public void setToken(String token){
            this.token = token;
        }
        public String getToken(){
            return this.token;
        }



}
