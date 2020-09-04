package com.example.demo;

public class Courses {
    Integer ass1,ass2,ass3,avg;
    String fedback;

    public Courses() {
    }

    public Courses(Integer ass1, Integer ass2, Integer ass3, String fedback) {
        this.ass1 = ass1;
        this.ass2 = ass2;
        this.ass3 = ass3;
        this.avg = ((ass1+ass2+ass3)/3);
        this.fedback=fedback;
    }

    public Integer getAss1() {
        return ass1;
    }

    public void setAss1(Integer ass1) {
        this.ass1 = ass1;
    }

    public Integer getAss2() {
        return ass2;
    }

    public void setAss2(Integer ass2) {
        this.ass2 = ass2;
    }

    public Integer getAss3() {
        return ass3;
    }

    public void setAss3(Integer ass3) {
        this.ass3 = ass3;
    }

    public Integer getAvg() {
        return avg;
    }

    public void setAvg(Integer avg) {
        this.avg =avg;
    }

    public String getFedback() {
        return fedback;
    }

    public void setFedback(String fedback) {
        this.fedback = fedback;
    }
}
