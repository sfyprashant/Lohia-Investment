package com.Lohia.investment.Models;

import java.util.ArrayList;

public class HealthCompanyName {
    public static ArrayList<String> getHealthCompanyIndia() {
        ArrayList<String> banksInIndia = new ArrayList<>();

        banksInIndia.add(" ");
        banksInIndia.add("Acko General");
        banksInIndia.add("Aditya Birla Health");
        banksInIndia.add("Bajaj Allianz");
        banksInIndia.add("Care Health ");
        banksInIndia.add("CholaMandalam MS");
        banksInIndia.add("Future Generalli");
        banksInIndia.add("Go Digit");
        banksInIndia.add("HDFC Ergo");
        banksInIndia.add("ICICI Lombard");
        banksInIndia.add("Iffco Tokio");
        banksInIndia.add("Kotak Mahindra");
        banksInIndia.add("Liberty General");
        banksInIndia.add("Magma HDI");
        banksInIndia.add("Manipal Cigna Health");
        banksInIndia.add("National Insurance");
        banksInIndia.add("Navi General");
        banksInIndia.add("New India Assurance");
        banksInIndia.add("Niva Bupa Health");
        banksInIndia.add("Oriental Insurance");
        banksInIndia.add("Reliance General");
        banksInIndia.add("Royal Sundaram");
        banksInIndia.add("SBI General");
        banksInIndia.add("Shriram general");
        banksInIndia.add("Star Health");
        banksInIndia.add("TATA AIG");
        banksInIndia.add("United India");
        banksInIndia.add("Universal Sompo");
        banksInIndia.add("Zuno General");




        return banksInIndia;
    }
    public static String[] getHealthCompany() {
        ArrayList<String> CompanyList = getHealthCompanyIndia();
        String[] CompanyArray = new String[CompanyList.size()];

        for (int i = 0; i < CompanyList.size(); i++) {
            CompanyArray[i] = CompanyList.get(i);
        }

        return CompanyArray;
    }
}
