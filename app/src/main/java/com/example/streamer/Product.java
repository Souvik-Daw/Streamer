package com.example.streamer;

public class Product {
    private String id;
    private String name;
    private String phno;
    private String email;
    private String studid;

    public Product(String id, String name, String phno, String email, String studid){
        this.id=id;
        this.name=name;
        this.phno=phno;
        this.email=email;
        this.studid=studid;
    }

    public Product() {

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

    public String getPhno() {
        return phno;
    }

    public void setPhno(String phno) {
        this.phno = phno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStudid() {
        return studid;
    }

    public void setStudid(String studid) {
        this.studid = studid;
    }
}