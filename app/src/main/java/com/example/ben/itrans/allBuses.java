package com.example.ben.itrans;

/**
 * Created by helen_000 on 7/3/2016.
 */
public class allBuses {
    private String busNo;

    public allBuses(){
    }

    public allBuses(String busNo){
        this.busNo = busNo;
    }

    public String getBusNo(){
        return busNo;
    }

    public void setBusNo(String busNum){
        this.busNo = busNum;
    }
}
