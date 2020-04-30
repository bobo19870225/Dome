package com.yy.dome.entity;

import java.util.List;

public class SchoolTypeInfo {


    public static class Data
    {
        private String id;

        private String name;

        boolean isSelect;
        public boolean isSelect() {
            return isSelect;
        }
        public void setSelect(boolean select) {
            isSelect = select;
        }

        public void setId(String id){
            this.id = id;
        }
        public String getId(){
            return this.id;
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
