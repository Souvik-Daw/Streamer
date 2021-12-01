package com.example.streamer.custom;

import java.io.Serializable;

public class question implements Serializable{
    String id;
    String category_id;
    String subcategory_id;
    String subject_id;
    String set_no;
    String question;
    String option_one;
    String option_two;
    String option_three;
    String option_four;
    String answer;
    String solution;
    String status;

    public question() {

    }

    public question(String id, String category_id,
                    String subcategory_id, String subject_id, String set_no,
                    String question, String option_one, String option_two, String option_three,
                    String option_four, String answer, String solution, String status)
    {
        this.id = id;
        this.category_id = category_id;
        this.subcategory_id = subcategory_id;
        this.subject_id = subject_id;
        this.set_no = set_no;
        this.question = question;
        this.option_one = option_one;
        this.option_two = option_two;
        this.option_three = option_three;
        this.option_four = option_four;
        this.answer = answer;
        this.solution = solution;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getSubcategory_id() {
        return subcategory_id;
    }

    public void setSubcategory_id(String subcategory_id) {
        this.subcategory_id = subcategory_id;
    }

    public String getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(String subject_id) {
        this.subject_id = subject_id;
    }

    public String getSet_no() {
        return set_no;
    }

    public void setSet_no(String set_no) {
        this.set_no = set_no;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOption_one() {
        return option_one;
    }

    public void setOption_one(String option_one) {
        this.option_one = option_one;
    }

    public String getOption_two() {
        return option_two;
    }

    public void setOption_two(String option_two) {
        this.option_two = option_two;
    }

    public String getOption_three() {
        return option_three;
    }

    public void setOption_three(String option_three) {
        this.option_three = option_three;
    }

    public String getOption_four() {
        return option_four;
    }

    public void setOption_four(String option_four) {
        this.option_four = option_four;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}