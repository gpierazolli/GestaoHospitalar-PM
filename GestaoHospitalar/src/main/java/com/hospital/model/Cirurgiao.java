package com.hospital.model;

public class Cirurgiao extends Profissional {
    public Cirurgiao() {}
    private String areaCirurgica;
    public String getAreaCirurgica(){ 
        return areaCirurgica; 
    }
    public void setAreaCirurgica(String areaCirurgica){ 
        this.areaCirurgica = areaCirurgica; 
    }
}