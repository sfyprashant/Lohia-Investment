package com.Lohia.investment.Models;

public class uploadmodel {
    public String policynumberu;
    public String imageUrl;
    public int gstu;
    public String userid;
    public String check;

    public uploadmodel() {
        // Default constructor required for Firestore deserialization
    }

    public uploadmodel(String policynumberu, String imageUrl, int gstu, String userid, String check) {
        this.policynumberu = policynumberu;
        this.imageUrl = imageUrl;
        this.gstu = gstu;
        this.userid = userid;
        this.check = check;
    }

    public String getPolicynumberu() {
        return policynumberu;
    }

    public void setPolicynumberu(String policynumberu) {
        this.policynumberu = policynumberu;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getGstu() {
        return gstu;
    }

    public void setGstu(int gstu) {
        this.gstu = gstu;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getCheck() {
        return check;
    }

    public void setCheck(String check) {
        this.check = check;
    }
}
