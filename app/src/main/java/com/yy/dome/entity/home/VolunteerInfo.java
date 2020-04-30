package com.yy.dome.entity.home;

import java.util.List;

public class VolunteerInfo {

    public class Data
    {
        private String id;

        private String name;

        private String grade;

        private String parent;

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
        public void setGrade(String grade){
            this.grade = grade;
        }
        public String getGrade(){
            return this.grade;
        }
        public void setParent(String parent){
            this.parent = parent;
        }
        public String getParent(){
            return this.parent;
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
