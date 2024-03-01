package com.Lohia.investment.Models;

public class ADD_POLICY_USER_MODEL {
    String NAME,DOB,NUMBER;

    public ADD_POLICY_USER_MODEL(String NAME, String DOB, String NUMBER) {
        this.NAME = NAME;
        this.DOB = DOB;
        this.NUMBER = NUMBER;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getDOB() {
        return DOB;
    }

    public void setDOB(String DOB) {
        this.DOB = DOB;
    }

    public String getNUMBER() {
        return NUMBER;
    }

    public void setNUMBER(String NUMBER) {
        this.NUMBER = NUMBER;
    }
}
