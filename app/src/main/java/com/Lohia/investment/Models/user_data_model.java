package com.Lohia.investment.Models;

import com.google.firebase.database.IgnoreExtraProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Map;

@IgnoreExtraProperties
public class user_data_model {

   String suma,gst,policytype,policystartdate,paylink,logo;
   int rootcheck;

   public user_data_model(String suma, String gst, String policytype, String policystartdate, String paylink, String logo, int rootcheck) {
      this.suma = suma;
      this.gst = gst;
      this.policytype = policytype;
      this.policystartdate = policystartdate;
      this.paylink = paylink;
      this.logo = logo;
      this.rootcheck = rootcheck;
   }

   public String getSuma() {
      return suma;
   }

   public void setSuma(String suma) {
      this.suma = suma;
   }

   public String getGst() {
      return gst;
   }

   public void setGst(String gst) {
      this.gst = gst;
   }

   public String getPolicytype() {
      return policytype;
   }

   public void setPolicytype(String policytype) {
      this.policytype = policytype;
   }

   public String getPolicystartdate() {
      return policystartdate;
   }

   public void setPolicystartdate(String policystartdate) {
      this.policystartdate = policystartdate;
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
