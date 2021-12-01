package com.example.streamer.custom;

public class subject {
    String id;
    String subcat_id;
    String subject;

    public subject(String id, String subcat_id, String subject) {
        this.id = id;
        this.subcat_id = subcat_id;
        this.subject = subject;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubcat_id() {
        return subcat_id;
    }

    public void setSubcat_id(String subcat_id) {
        this.subcat_id = subcat_id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}