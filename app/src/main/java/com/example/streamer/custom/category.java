package com.example.streamer.custom;

public class category {
    String id;
    String main_category;
    String category_name;

    public category(String id, String main_category, String category_name) {
        this.id = id;
        this.main_category = main_category;
        this.category_name = category_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMain_category() {
        return main_category;
    }

    public void setMain_category(String main_category) {
        this.main_category = main_category;
    }

    public String getCategory_name() {
        return category_name;
    }

    public void setCategory_name(String category_name) {
        this.category_name = category_name;
    }
}