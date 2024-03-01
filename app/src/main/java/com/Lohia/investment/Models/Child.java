package com.Lohia.investment.Models;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;

public class Child implements Parcelable {
    public String name;
    public String usertype;
    public String gander;
    public String number;
    public String select_parent;
    public String naturework;
    public String address;
    public String email;
    public String channel;
    public String fathername;
    public String work_profile;
    public String dob;

    public Child(Parcel in) {
        name = in.readString();
        usertype = in.readString();
        gander = in.readString();
        number = in.readString();
        select_parent = in.readString();
        naturework = in.readString();
        address = in.readString();
        email = in.readString();
        channel = in.readString();
        fathername = in.readString();
        work_profile = in.readString();
        dob = in.readString();
    }

    public static final Creator<Child> CREATOR = new Creator<Child>() {
        @Override
        public Child createFromParcel(Parcel in) {
            return new Child(in);
        }

        @Override
        public Child[] newArray(int size) {
            return new Child[size];
        }
    };

    public Child() {

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(usertype);
        parcel.writeString(gander);
        parcel.writeString(number);
        parcel.writeString(select_parent);
        parcel.writeString(naturework);
        parcel.writeString(address);
        parcel.writeString(email);
        parcel.writeString(channel);
        parcel.writeString(fathername);
        parcel.writeString(work_profile);
        parcel.writeString(dob);
    }
}
