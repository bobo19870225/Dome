package com.yy.dome.ui.activity.school.form;

import java.util.List;

public class FormLookUpInfo {

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    private int ret;
    private String msg;
    private String token;
    private List<DataBean> data;


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * schoolName : 北京大学医学部
         * minScore : 670
         * recruitYear : 2019
         * enrollYear : 2018
         * maxScore : 684
         * hotRank : 0
         * enrollRatio : 96
         * enrollNumber : 18
         * city : 北京海淀区
         * type1 : 医药类
         * subs : [{"wlk":"1","id":130,"batch":"1","subject":"620101K","name":"临床医学","money":"6000","languag":"不限","planNumber":4,"studyYear":"8"},{"wlk":"1","id":131,"batch":"1","subject":"620301","name":"药学","money":"6000","languag":"不限","planNumber":3,"studyYear":"6"},{"wlk":"1","id":132,"batch":"1","subject":"620601K","name":"预防医学","money":"6000","languag":"不限","planNumber":3,"studyYear":"7"},{"wlk":"1","id":133,"batch":"1","subject":"620102K","name":"口腔医学","money":"6000","languag":"不限","planNumber":1,"studyYear":"8"},{"wlk":"1","id":134,"batch":"1","subject":"100101K","name":"基础医学","money":"6000","languag":"不限","planNumber":1,"studyYear":"8"},{"wlk":"1","id":135,"batch":"1","subject":"620101K","name":"临床医学","money":"6000","languag":"不限","planNumber":3,"studyYear":"5"}]
         * name : 北京大学医学部（北京海淀区）（以下专业色盲色弱者不予录取。）
         * minRank : 189
         * schoolId : 765
         * planNumber : 17
         */

        private String schoolName;
        private String minScore;
        private String recruitYear;
        private String enrollYear;
        private String maxScore;
        private String hotRank;
        private String enrollRatio;
        private String enrollNumber;
        private String city;
        private String type1;
        private String name;
        private String minRank;
        private String schoolId;
        private String planNumber;
        private List<SubsBean> subs;

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

        public String getRecruitYear() {
            return recruitYear;
        }

        public void setRecruitYear(String recruitYear) {
            this.recruitYear = recruitYear;
        }

        public String getEnrollYear() {
            return enrollYear;
        }

        public void setEnrollYear(String enrollYear) {
            this.enrollYear = enrollYear;
        }

        public String getMaxScore() {
            return maxScore;
        }

        public void setMaxScore(String maxScore) {
            this.maxScore = maxScore;
        }

        public String getHotRank() {
            return hotRank;
        }

        public void setHotRank(String hotRank) {
            this.hotRank = hotRank;
        }

        public String getEnrollRatio() {
            return enrollRatio;
        }

        public void setEnrollRatio(String enrollRatio) {
            this.enrollRatio = enrollRatio;
        }

        public String getEnrollNumber() {
            return enrollNumber;
        }

        public void setEnrollNumber(String enrollNumber) {
            this.enrollNumber = enrollNumber;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getType1() {
            return type1;
        }

        public void setType1(String type1) {
            this.type1 = type1;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMinRank() {
            return minRank;
        }

        public void setMinRank(String minRank) {
            this.minRank = minRank;
        }

        public String getSchoolId() {
            return schoolId;
        }

        public void setSchoolId(String schoolId) {
            this.schoolId = schoolId;
        }

        public String getPlanNumber() {
            return planNumber;
        }

        public void setPlanNumber(String planNumber) {
            this.planNumber = planNumber;
        }

        public List<SubsBean> getSubs() {
            return subs;
        }

        public void setSubs(List<SubsBean> subs) {
            this.subs = subs;
        }

        public static class SubsBean {
            /**
             * wlk : 1
             * id : 130
             * batch : 1
             * subject : 620101K
             * name : 临床医学
             * money : 6000
             * languag : 不限
             * planNumber : 4
             * studyYear : 8
             */

            private String wlk;
            private int id;
            private String batch;
            private String subject;
            private String name;
            private String money;
            private String languag;
            private int planNumber;
            private String studyYear;

            public String getWlk() {
                return wlk;
            }

            public void setWlk(String wlk) {
                this.wlk = wlk;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getBatch() {
                return batch;
            }

            public void setBatch(String batch) {
                this.batch = batch;
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

            public String getMoney() {
                return money;
            }

            public void setMoney(String money) {
                this.money = money;
            }

            public String getLanguag() {
                return languag;
            }

            public void setLanguag(String languag) {
                this.languag = languag;
            }

            public int getPlanNumber() {
                return planNumber;
            }

            public void setPlanNumber(int planNumber) {
                this.planNumber = planNumber;
            }

            public String getStudyYear() {
                return studyYear;
            }

            public void setStudyYear(String studyYear) {
                this.studyYear = studyYear;
            }
        }
    }

}
