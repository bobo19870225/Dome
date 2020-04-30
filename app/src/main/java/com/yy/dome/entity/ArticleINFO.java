package com.yy.dome.entity;

import java.util.List;

public class ArticleINFO {

    public class Data
    {
        private String id;

        private int valid;

        private String name;

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
        public void setName(String name){
            this.name = name;
        }
        public String getName(){
            return this.name;
        }
    }
        private int ret;
        private List<Data> data;
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

}
