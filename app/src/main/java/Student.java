package com.example.demo;

public class Student {
    private String  id;
    private String name;
    private String phn;
    private String mail,pass;

    public Student() {
    }

    public Student(String id, String name, String phn, String mail, String pass) {
        this.id = id;
        this.name = name;
        this.phn = phn;
        this.mail = mail;
        this.pass = pass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhn() {
        return phn;
    }

    public void setPhn(String phn) {
        this.phn = phn;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
