package com.Lohia.investment.Models;

import java.io.Serializable;
import java.util.Map;

public class Tree_Model implements Serializable {
    String gender,name;

    public  Tree_Model (){}
    public Tree_Model(String gender, String name) {
        this.gender = gender;
        this.name = name;
    }

    public Tree_Model(Map<String, Object> userData) {

    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
