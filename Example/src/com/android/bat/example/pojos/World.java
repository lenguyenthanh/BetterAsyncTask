package com.android.bat.example.pojos;

import java.util.List;

/**
 * Copyright (c) 2013 Thanh Le
 * Created by Thanh Le
 * Date: 9/16/13
 * Time: 8:14 PM
 */
public class World{
    private List<Country> worldpopulation;

    public List<Country> getWorldpopulation(){
        return worldpopulation;
    }

    public void setWorldpopulation(final List<Country> aWorldpopulation){
        worldpopulation = aWorldpopulation;
    }
}
