package com.Lohia.investment.Models;

import android.content.Context;

import java.util.ArrayList;

public class FD_Model {
    String FD_from,FD_type,OrganizationBank,OrganizationPB,Frequency_interest,FDRNo,Interest_Rate,Investment_Amount,Maturity_Date,Nominee_Name;

    public FD_Model(String FD_from, String FD_type, String organizationBank, String organizationPB, String frequency_interest, String FDRNo, String interest_Rate, String investment_Amount, String maturity_Date, String nominee_Name) {
        this.FD_from = FD_from;
        this.FD_type = FD_type;
        this.OrganizationBank = organizationBank;
        this.OrganizationPB = organizationPB;
        this.Frequency_interest = frequency_interest;
        this.FDRNo = FDRNo;
        this.Interest_Rate = interest_Rate;
        this.Investment_Amount = investment_Amount;
        this.Maturity_Date = maturity_Date;
        this.Nominee_Name = nominee_Name;
    }



    public String getFD_from() {
        return FD_from;
    }

    public void setFD_from(String FD_from) {
        this.FD_from = FD_from;
    }

    public String getFD_type() {
        return FD_type;
    }

    public void setFD_type(String FD_type) {
        this.FD_type = FD_type;
    }

    public String getOrganizationBank() {
        return OrganizationBank;
    }

    public void setOrganizationBank(String organizationBank) {
        this.OrganizationBank = organizationBank;
    }

    public String getOrganizationPB() {
        return OrganizationPB;
    }

    public void setOrganizationPB(String organizationPB) {
        this.OrganizationPB = organizationPB;
    }

    public String getFrequency_interest() {
        return Frequency_interest;
    }

    public void setFrequency_interest(String frequency_interest) {
        this.Frequency_interest = frequency_interest;
    }

    public String getFDRNo() {
        return FDRNo;
    }

    public void setFDRNo(String FDRNo) {
        this.FDRNo = FDRNo;
    }

    public String getInterest_Rate() {
        return Interest_Rate;
    }

    public void setInterest_Rate(String interest_Rate) {
        this.Interest_Rate = interest_Rate;
    }

    public String getInvestment_Amount() {
        return Investment_Amount;
    }

    public void setInvestment_Amount(String investment_Amount) {
        this.Investment_Amount = investment_Amount;
    }

    public String getMaturity_Date() {
        return Maturity_Date;
    }

    public void setMaturity_Date(String maturity_Date) {
        this.Maturity_Date = maturity_Date;
    }

    public String getNominee_Name() {
        return Nominee_Name;
    }

    public void setNominee_Name(String nominee_Name) {
        this.Nominee_Name = nominee_Name;
    }
}
