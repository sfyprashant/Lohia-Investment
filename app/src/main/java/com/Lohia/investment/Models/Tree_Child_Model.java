package com.Lohia.investment.Models;

public class Tree_Child_Model {
    String C_gender,C_name;


    public String getC_gender() {
        return C_gender;
    }

    public void setC_gender(String c_gender) {
        C_gender = c_gender;
    }

    public String getC_name() {
        return C_name;
    }

    public void setC_name(String c_name) {
        C_name = c_name;
    }

    public Tree_Child_Model(String c_gender, String c_name) {
        C_gender = c_gender;
        C_name = c_name;
    }
}
