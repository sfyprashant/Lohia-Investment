package com.Lohia.investment.Models;

import java.util.Date;

public class Notification_list {
    String nameuser,policyNumber;
    Date endDate;

    public Notification_list(String nameuser, String policyNumber, Date endDate) {
        this.nameuser = nameuser;
        this.policyNumber = policyNumber;
        this.endDate = endDate;
    }

    public String getNameuser() {
        return nameuser;
    }

    public void setNameuser(String nameuser) {
        this.nameuser = nameuser;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
