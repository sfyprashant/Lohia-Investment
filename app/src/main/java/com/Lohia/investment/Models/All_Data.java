package com.Lohia.investment.Models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class All_Data implements Parcelable {
    public String naturework;
    public ArrayList<CarPolicy> car_policy;
    public String channel;
    public String work_profile;
    public String fathername;
    public String name;
    public String dob;
    public String number;
    public String gander;
    public String usertype;
    public Object select_parent;
    public ArrayList<HealthPolicy> health_policy;
    public ArrayList<TermPolicy> term_policy;
    public ArrayList<Child> children;
    public String email;
    public String address;

    public All_Data(Parcel in) {
        naturework = in.readString();
        channel = in.readString();
        work_profile = in.readString();
        fathername = in.readString();
        name = in.readString();
        dob = in.readString();
        number = in.readString();
        gander = in.readString();
        usertype = in.readString();
        email = in.readString();
        address = in.readString();
    }

    public static final Creator<All_Data> CREATOR = new Creator<All_Data>() {
        @Override
        public All_Data createFromParcel(Parcel in) {
            return new All_Data(in);
        }

        @Override
        public All_Data[] newArray(int size) {
            return new All_Data[size];
        }
    };

    public All_Data() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(naturework);
        parcel.writeString(channel);
        parcel.writeString(work_profile);
        parcel.writeString(fathername);
        parcel.writeString(name);
        parcel.writeString(dob);
        parcel.writeString(number);
        parcel.writeString(gander);
        parcel.writeString(usertype);
        parcel.writeString(email);
        parcel.writeString(address);
    }

    public static class CarPolicy implements Parcelable {
        public String premium;
        public String paylink;
        public String vehiclecategory;
        public String model;
        public String startdate;
        public String channel;
        public String select_user;
        public String policytype;
        public String vehiclenumber;
        public String enddate;
        public String companyname;
        public String policynumber;
        public Object policyfrom;
        public Object types;

        public CarPolicy(Parcel in) {
            premium = in.readString();
            paylink = in.readString();
            vehiclecategory = in.readString();
            model = in.readString();
            startdate = in.readString();
            channel = in.readString();
            select_user = in.readString();
            policytype = in.readString();
            vehiclenumber = in.readString();
            enddate = in.readString();
            companyname = in.readString();
            policynumber = in.readString();
            policyfrom = in.readString();
            types = in.readString();
        }

        public static final Creator<CarPolicy> CREATOR = new Creator<CarPolicy>() {
            @Override
            public CarPolicy createFromParcel(Parcel in) {
                return new CarPolicy(in);
            }

            @Override
            public CarPolicy[] newArray(int size) {
                return new CarPolicy[size];
            }
        };

        public CarPolicy() {

        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(@NonNull Parcel parcel, int i) {
            parcel.writeString(premium);
            parcel.writeString(paylink);
            parcel.writeString(vehiclecategory);
            parcel.writeString(model);
            parcel.writeString(startdate);
            parcel.writeString(channel);
            parcel.writeString(select_user);
            parcel.writeString(policytype);
            parcel.writeString(vehiclenumber);
            parcel.writeString(enddate);
            parcel.writeString(companyname);
            parcel.writeString(policynumber);
        }
    }



    public static class HealthPolicy implements Parcelable {
        public String membersinsured;
        public String paylink;
        public String firstpolicynumber;
        public String gst;
        public String select_user;
        public String policytype;
        public String policystartdate;
        public String policyenddate;
        public String assigntochannel;
        public String policynumber;
        public String policyfrom;
        public String fistpolicydate;
        public String ttd;
        public String suma;

        public HealthPolicy(Parcel in) {
            membersinsured = in.readString();
            paylink = in.readString();
            firstpolicynumber = in.readString();
            gst = in.readString();
            select_user = in.readString();
            policytype = in.readString();
            policystartdate = in.readString();
            policyenddate = in.readString();
            assigntochannel = in.readString();
            policynumber = in.readString();
            policyfrom = in.readString();
            fistpolicydate = in.readString();
            ttd = in.readString();
            suma = in.readString();
        }

        public static final Creator<HealthPolicy> CREATOR = new Creator<HealthPolicy>() {
            @Override
            public HealthPolicy createFromParcel(Parcel in) {
                return new HealthPolicy(in);
            }

            @Override
            public HealthPolicy[] newArray(int size) {
                return new HealthPolicy[size];
            }
        };

        public HealthPolicy() {

        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(@NonNull Parcel parcel, int i) {
            parcel.writeString(membersinsured);
            parcel.writeString(paylink);
            parcel.writeString(firstpolicynumber);
            parcel.writeString(gst);
            parcel.writeString(select_user);
            parcel.writeString(policytype);
            parcel.writeString(policystartdate);
            parcel.writeString(policyenddate);
            parcel.writeString(assigntochannel);
            parcel.writeString(policynumber);
            parcel.writeString(policyfrom);
            parcel.writeString(fistpolicydate);
            parcel.writeString(ttd);
            parcel.writeString(suma);
        }
    }

    public static class TermPolicy implements Parcelable {
        public String premiumpaymentupto;
        public String paymentmode;
        public String paylink;
        public String approxmaturityamount;
        public String dateofcommencement;
        public String sumassured;
        public String mode;
        public String select_user;
        public String premiumwithoutgst;
        public String premiumgst;
        public String channel;
        public String companyname;
        public String maturitydate;
        public String policynumber;
        public String firstunpaidpremium;
        public Object policyfrom;
        public String premiumpayingterm;
        public Object types;
        public String planname;
        public Object ttd;
        public String term;

        public TermPolicy(Parcel in) {
            premiumpaymentupto = in.readString();
            paymentmode = in.readString();
            paylink = in.readString();
            approxmaturityamount = in.readString();
            dateofcommencement = in.readString();
            sumassured = in.readString();
            mode = in.readString();
            select_user = in.readString();
            premiumwithoutgst = in.readString();
            premiumgst = in.readString();
            channel = in.readString();
            companyname = in.readString();
            maturitydate = in.readString();
            policynumber = in.readString();
            firstunpaidpremium = in.readString();
            policyfrom = in.readString();
            premiumpayingterm = in.readString();
            types = in.readString();
            planname = in.readString();
            ttd = in.readString();
            term = in.readString();
        }

        public static final Creator<TermPolicy> CREATOR = new Creator<TermPolicy>() {
            @Override
            public TermPolicy createFromParcel(Parcel in) {
                return new TermPolicy(in);
            }

            @Override
            public TermPolicy[] newArray(int size) {
                return new TermPolicy[size];
            }
        };

        public TermPolicy() {

        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(@NonNull Parcel parcel, int i) {
            parcel.writeString(premiumpaymentupto);
            parcel.writeString(paymentmode);
            parcel.writeString(paylink);
            parcel.writeString(approxmaturityamount);
            parcel.writeString(dateofcommencement);
            parcel.writeString(sumassured);
            parcel.writeString(mode);
            parcel.writeString(select_user);
            parcel.writeString(premiumwithoutgst);
            parcel.writeString(premiumgst);
            parcel.writeString(channel);
            parcel.writeString(companyname);
            parcel.writeString(maturitydate);
            parcel.writeString((String) policyfrom);
            parcel.writeString(policynumber);
            parcel.writeString(firstunpaidpremium);
            parcel.writeString(premiumpayingterm);
            parcel.writeString((String) types);
            parcel.writeString(planname);
            parcel.writeString((String) ttd);
            parcel.writeString(term);
        }
    }
}
