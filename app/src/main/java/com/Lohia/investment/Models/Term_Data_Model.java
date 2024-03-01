package com.Lohia.investment.Models;

public class Term_Data_Model {
    String policytype,dateofcommencement,premium,sum,paylink,logo;
    int rootcheck;

    public Term_Data_Model(String policytype, String dateofcommencement, String premium, String sum, String paylink, String logo, int rootcheck) {
        this.policytype = policytype;
        this.dateofcommencement = dateofcommencement;
        this.premium = premium;
        this.sum = sum;
        this.paylink = paylink;
        this.logo = logo;
        this.rootcheck = rootcheck;
    }

    public String getPolicytype() {
        return policytype;
    }

    public void setPolicytype(String policytype) {
        this.policytype = policytype;
    }

    public String getDateofcommencement() {
        return dateofcommencement;
    }

    public void setDateofcommencement(String dateofcommencement) {
        this.dateofcommencement = dateofcommencement;
    }

    public String getPremium() {
        return premium;
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
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
