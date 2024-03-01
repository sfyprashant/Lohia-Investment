package com.Lohia.investment.Models;

import java.util.ArrayList;

public class LifeCompanyName {
    public static ArrayList<String> getLifeCompanyIndia() {
        ArrayList<String> banksInIndia = new ArrayList<>();

        banksInIndia.add(" ");
        banksInIndia.add("Aditya Birla Life");
        banksInIndia.add("Ageas Federal");
        banksInIndia.add("Aegon Life");
        banksInIndia.add("Aviva Life");
        banksInIndia.add("Bharti Axa");
        banksInIndia.add("Bajaj Allianz Life");
        banksInIndia.add("Canara HSBC");
        banksInIndia.add("Edelweiss Tokio Life");
        banksInIndia.add("Future Generalli Life");
        banksInIndia.add("HDFC Life");
        banksInIndia.add("ICICI Prudential");
        banksInIndia.add("IndiaFirst Life");
        banksInIndia.add("Kotak Mahindra Life");
        banksInIndia.add("LIC of India");
        banksInIndia.add("Max Life");
        banksInIndia.add("PNB Metlife");
        banksInIndia.add("Reliance Nippon Life");
        banksInIndia.add("SBI Life");
        banksInIndia.add("Shriram Life");
        banksInIndia.add("TATA AIA Life");



        return banksInIndia;
    }
    public static String[] getLifeCompany() {
        ArrayList<String> CompanyList = getLifeCompanyIndia();
        String[] CompanyArray = new String[CompanyList.size()];

        for (int i = 0; i < CompanyList.size(); i++) {
            CompanyArray[i] = CompanyList.get(i);
        }

        return CompanyArray;
    }
}
