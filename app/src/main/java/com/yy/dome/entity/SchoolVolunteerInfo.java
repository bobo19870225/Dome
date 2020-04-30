package com.yy.dome.entity;

import java.util.List;

public class SchoolVolunteerInfo {


    public static class SmartSubject
    {
        private int id;

        private String batch;

        private String subject;

        private String name;

        private String money;

        private int planNumber;

        private String studyYear;

        public Boolean getCheck() {
            return isCheck;
        }

        public void setCheck(Boolean check) {
            isCheck = check;
        }

        private Boolean isCheck = false;

        public void setId(int id){
            this.id = id;
        }
        public int getId(){
            return this.id;
        }
        public void setBatch(String batch){
            this.batch = batch;
        }
        public String getBatch(){
            return this.batch;
        }
        public void setSubject(String subject){
            this.subject = subject;
        }
        public String getSubject(){
            return this.subject;
        }
        public void setName(String name){
            this.name = name;
        }
        public String getName(){
            return this.name;
        }
        public void setMoney(String money){
            this.money = money;
        }
        public String getMoney(){
            return this.money;
        }
        public void setPlanNumber(int planNumber){
            this.planNumber = planNumber;
        }
        public int getPlanNumber(){
            return this.planNumber;
        }
        public void setStudyYear(String studyYear){
            this.studyYear = studyYear;
        }
        public String getStudyYear(){
            return this.studyYear;
        }
    }


    public static class AllSubject
    {
        private int id;

        private String batch;

        private String subject;

        private String name;

        private String money;

        private int planNumber;

        private String studyYear;

        public void setId(int id){
            this.id = id;
        }
        public int getId(){
            return this.id;
        }
        public void setBatch(String batch){
            this.batch = batch;
        }
        public String getBatch(){
            return this.batch;
        }
        public void setSubject(String subject){
            this.subject = subject;
        }
        public String getSubject(){
            return this.subject;
        }
        public void setName(String name){
            this.name = name;
        }
        public String getName(){
            return this.name;
        }
        public void setMoney(String money){
            this.money = money;
        }
        public String getMoney(){
            return this.money;
        }
        public void setPlanNumber(int planNumber){
            this.planNumber = planNumber;
        }
        public int getPlanNumber(){
            return this.planNumber;
        }
        public void setStudyYear(String studyYear){
            this.studyYear = studyYear;
        }
        public String getStudyYear(){
            return this.studyYear;
        }
    }


        private List<SmartSubject> smartSubject;

        private List<AllSubject> allSubject;

        public void setSmartSubject(List<SmartSubject> smartSubject){
            this.smartSubject = smartSubject;
        }
        public List<SmartSubject> getSmartSubject(){
            return this.smartSubject;
        }
        public void setAllSubject(List<AllSubject> allSubject){
            this.allSubject = allSubject;
        }
        public List<AllSubject> getAllSubject(){
            return this.allSubject;
        }


}
