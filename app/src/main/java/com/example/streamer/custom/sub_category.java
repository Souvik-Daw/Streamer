package com.example.streamer.custom;

public class sub_category {
    String id;
    String sub_category;
    String category_id;
    String amount;
    String image;
    String time_duration;

    public sub_category(String id, String sub_category, String category_id, String amount, String image, String time_duration) {
        this.id = id;
        this.sub_category = sub_category;
        this.category_id = category_id;
        this.amount = amount;
        this.image = image;
        this.time_duration = time_duration;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSub_category() {
        return sub_category;
    }

    public void setSub_category(String sub_category) {
        this.sub_category = sub_category;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTime_duration() {
        return time_duration;
    }

    public void setTime_duration(String time_duration) {
        this.time_duration = time_duration;
    }
}