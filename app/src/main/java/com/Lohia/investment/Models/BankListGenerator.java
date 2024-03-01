package com.Lohia.investment.Models;

import java.util.ArrayList;

public class BankListGenerator {
    public static ArrayList<String> getBanksInIndia() {
        ArrayList<String> banksInIndia = new ArrayList<>();
        // Public Sector Banks
        banksInIndia.add(" ");
        banksInIndia.add("State Bank of India (SBI)");
        banksInIndia.add("Punjab National Bank (PNB)");
        banksInIndia.add("Bank of Baroda (BOB)");
        banksInIndia.add("Canara Bank");
        banksInIndia.add("Union Bank of India");
        banksInIndia.add("Bank of India (BOI)");
        banksInIndia.add("Indian Bank");
        banksInIndia.add("Central Bank of India");
        banksInIndia.add("Indian Overseas Bank");
        banksInIndia.add("Bank of Maharashtra");
        banksInIndia.add("UCO Bank");
        banksInIndia.add("Punjab & Sind Bank");
        banksInIndia.add("IDBI Bank");

// Private Sector Banks
        banksInIndia.add("HDFC Bank");
        banksInIndia.add("ICICI Bank");
        banksInIndia.add("Axis Bank");
        banksInIndia.add("Kotak Mahindra Bank");
        banksInIndia.add("Yes Bank");
        banksInIndia.add("IndusInd Bank");
        banksInIndia.add("Bandhan Bank");
        banksInIndia.add("IDFC First Bank");
        banksInIndia.add("RBL Bank");
        banksInIndia.add("Federal Bank");

// Regional Rural Banks (RRBs)
        banksInIndia.add("Baroda Rajasthan Kshetriya Gramin Bank");
        banksInIndia.add("Karnataka Vikas Grameena Bank");
        banksInIndia.add("Uttar Bihar Gramin Bank");
        banksInIndia.add("Andhra Pradesh Grameena Vikas Bank");
        banksInIndia.add("Assam Gramin Vikash Bank");

// Cooperative Banks
        banksInIndia.add("Saraswat Bank");
        banksInIndia.add("Abhyudaya Co-operative Bank");
        banksInIndia.add("Kerala State Cooperative Bank");
        banksInIndia.add("Gujarat State Co-operative Bank");

// Payment Banks
        banksInIndia.add("Paytm Payments Bank");
        banksInIndia.add("Airtel Payments Bank");
        banksInIndia.add("Jio Payments Bank");

        return banksInIndia;
    }
    public static String[] getBanks() {
        ArrayList<String> bankList = getBanksInIndia();
        String[] banksArray = new String[bankList.size()];

        for (int i = 0; i < bankList.size(); i++) {
            banksArray[i] = bankList.get(i);
        }

        return banksArray;
    }

}
