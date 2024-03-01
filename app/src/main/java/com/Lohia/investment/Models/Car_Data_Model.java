package com.Lohia.investment.Models;

public class Car_Data_Model {
    String Type,time,companyname,premium,paylink,logo;
    int rootcheck;

    public Car_Data_Model(String type, String time, String companyname, String premium, String paylink, String logo, int rootcheck) {
        Type = type;
        this.time = time;
        this.companyname = companyname;
        this.premium = premium;
        this.paylink = paylink;
        this.logo = logo;
        this.rootcheck = rootcheck;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }

    public String getPremium() {
        return premium;
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }

    public String getPaylink() {
        return paylink;
    }

    public void setPaylink(String paylink) {
        this.paylink = paylink;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public int getRootcheck() {
        return rootcheck;
    }

    public void setRootcheck(int rootcheck) {
        this.rootcheck = rootcheck;
    }
}
