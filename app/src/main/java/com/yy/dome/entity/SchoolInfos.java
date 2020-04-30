package com.yy.dome.entity;

import java.util.List;

public class SchoolInfos {


    public class Subjects {

        private String subject;

        public void setSubject(String subject) {
            this.subject = subject;
        }

        public String getSubject() {
            return this.subject;
        }

    }

        private String batch;

        private String branch;

        private String code;

        private String enrollNumber;

        private int id;

        private String logo;

        private int minScore;

        private String name;

        private List<String> nameAll;

        private int planNumber;

        private int rowno;

        private String schoolId;

        private String schoolName;

        private List<Subjects> subjects;

        private String wlk;

        private String year;

        public void setBatch(String batch){
            this.batch = batch;
        }
        public String getBatch(){
            return this.batch;
        }
        public void setBranch(String branch){
            this.branch = branch;
        }
        public String getBranch(){
            return this.branch;
        }
        public void setCode(String code){
            this.code = code;
        }
        public String getCode(){
            return this.code;
        }
        public void setEnrollNumber(String enrollNumber){
            this.enrollNumber = enrollNumber;
        }
        public String getEnrollNumber(){
            return this.enrollNumber;
        }
        public void setId(int id){
            this.id = id;
        }
        public int getId(){
            return this.id;
        }
        public void setLogo(String logo){
            this.logo = logo;
        }
        public String getLogo(){
            return this.logo;
        }
        public void setMinScore(int minScore){
            this.minScore = minScore;
        }
        public int getMinScore(){
            return this.minScore;
        }
        public void setName(String name){
            this.name = name;
        }
        public String getName(){
            return this.name;
        }
        public void setNameAll(List<String> nameAll){
            this.nameAll = nameAll;
        }
        public List<String> getNameAll(){
            return this.nameAll;
        }
        public void setPlanNumber(int planNumber){
            this.planNumber = planNumber;
        }
        public int getPlanNumber(){
            return this.planNumber;
        }
        public void setRowno(int rowno){
            this.rowno = rowno;
        }
        public int getRowno(){
            return this.rowno;
        }
        public void setSchoolId(String schoolId){
            this.schoolId = schoolId;
        }
        public String getSchoolId(){
            return this.schoolId;
        }
        public void setSchoolName(String schoolName){
            this.schoolName = schoolName;
        }
        public String getSchoolName(){
            return this.schoolName;
        }
        public void setSubjects(List<Subjects> subjects){
            this.subjects = subjects;
        }
        public List<Subjects> getSubjects(){
            return this.subjects;
        }
        public void setWlk(String wlk){
            this.wlk = wlk;
        }
        public String getWlk(){
            return this.wlk;
        }
        public void setYear(String year){
            this.year = year;
        }
        public String getYear(){
            return this.year;
        }



        private String trimArray;

        private String remove;

        private String unique;

        private String sameCount;

        private String contains;


        public void setTrimArray(String trimArray){
            this.trimArray = trimArray;
        }
        public String getTrimArray(){
            return this.trimArray;
        }
        public void setRemove(String remove){
            this.remove = remove;
        }
        public String getRemove(){
            return this.remove;
        }
        public void setUnique(String unique){
            this.unique = unique;
        }
        public String getUnique(){
            return this.unique;
        }
        public void setSameCount(String sameCount){
            this.sameCount = sameCount;
        }
        public String getSameCount(){
            return this.sameCount;
        }
        public void setContains(String contains){
            this.contains = contains;
        }
        public String getContains(){
            return this.contains;
        }


}
