package com.android.bat.example.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Copyright (c) 2013 Thanh Le
 * Created by Thanh Le
 * Date: 9/16/13
 * Time: 8:13 PM
 */
public class Country{
    private String rank;
    private String country;
    private String population;
    private String flag;

    public String getRank(){
        return rank;
    }

    public void setRank(final String aRank){
        rank = aRank;
    }

    public String getCountry(){
        return country;
    }

    public void setCountry(final String aCountry){
        country = aCountry;
    }

    public String getPopulation(){
        return population;
    }

    public void setPopulation(final String aPopulation){
        population = aPopulation;
    }

    public String getFlag(){
        return flag;
    }

    public void setFlag(final String aFlag){
        flag = aFlag;
    }

    @Override
    public String toString(){
        return "Number " + rank + ", " + country + " has " + population + " people";
    }
}
