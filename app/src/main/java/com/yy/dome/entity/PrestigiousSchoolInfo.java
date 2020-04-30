package com.yy.dome.entity;

import java.util.List;

public class PrestigiousSchoolInfo {


    public String getHotRank() {
        return hotRank;
    }

    public void setHotRank(String hotRank) {
        this.hotRank = hotRank;
    }

    private String hotRank;

    public String getSchoolCode() {
        return schoolCode;
    }

    public void setSchoolCode(String schoolCode) {
        this.schoolCode = schoolCode;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getPertain() {
        return pertain;
    }

    public void setPertain(String pertain) {
        this.pertain = pertain;
    }

    private String pertain;
    private String createTime;

    private String schoolCode;
    private String is211;

    public String getIs985() {
        return is985;
    }

    public void setIs985(String is985) {
        this.is985 = is985;
    }

    private String is985;

    private String logo;

    private int  enrollRatio;
    private String  ranking;

    private String city;

    private String type1;

    private String schoolType;

    private String minScore;

    private String schoolName;

    private List<Subjects> subjects;

    private String subject;

    private int rowno;

    private String enrollNumber;

    private String code;

    private String id;

    private String wlk;

    private String batch;

    private String name;

    public String getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(String maxScore) {
        this.maxScore = maxScore;
    }

    private String maxScore;

    private List<String> nameAll;

    private String branch;

    private String year;

    private String schoolId;

    private String planNumber;

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getPlanNumber() {
        return planNumber;
    }

    public void setPlanNumber(String planNumber) {
        this.planNumber = planNumber;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public List<String> getNameAll() {
        return nameAll;
    }

    public void setNameAll(List<String> nameAll) {
        this.nameAll = nameAll;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getWlk() {
        return wlk;
    }

    public void setWlk(String wlk) {
        this.wlk = wlk;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEnrollNumber() {
        return enrollNumber;
    }

    public void setEnrollNumber(String enrollNumber) {
        this.enrollNumber = enrollNumber;
    }

    public int getRowno() {
        return rowno;
    }

    public void setRowno(int rowno) {
        this.rowno = rowno;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<Subjects> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subjects> subjects) {
        this.subjects = subjects;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }


    public String getMinScore() {
        return minScore;
    }

    public void setMinScore(String minScore) {
        this.minScore = minScore;
    }


    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getSchoolType() {
        return schoolType;
    }

    public void setSchoolType(String schoolType) {
        this.schoolType = schoolType;
    }
    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }



    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }


    public String getIs211() {
        return is211;
    }

    public void setIs211(String is211) {
        this.is211 = is211;
    }

    public int getEnrollRatio() {
        return enrollRatio;
    }

    public void setEnrollRatio(int enrollRatio) {
        this.enrollRatio = enrollRatio;
    }


    public class Subjects{

        private int id;


        private String subject;


        private String name;


        private int planNumber;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }


        public String getSubject() {
            return subject;
        }

        public void setSubject(String subject) {
            this.subject = subject;
        }


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getPlanNumber() {
            return planNumber;
        }

        public void setPlanNumber(int planNumber) {
            this.planNumber = planNumber;
        }

    }

}
