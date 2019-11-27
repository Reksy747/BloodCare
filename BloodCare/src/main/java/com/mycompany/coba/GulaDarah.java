/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.coba;

/**
 *
 * @author A S U S
 */
public class GulaDarah {
    private int id;
    private String tanggal;
    private int miligram;
    private int milimol;
    
    public GulaDarah(int id,String tanggal,int miligram,int milimol){
        this.id=id;
        this.tanggal=tanggal;
        this.miligram = miligram;
        this.milimol = milimol;
    }
    
    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /**
     * @return the miligram
     */
    public int getMiligram() {
        return miligram;
    }

    /**
     * @param miligram the sistol to set
     */
    public void setMiligram(int miligram) {
        this.miligram = miligram;
    }

    /**
     * @return the milimol
     */
    public int getMilimol() {
        return milimol;
    }

    /**
     * @param milimol the diastol to set
     */
    public void setMilimol(int milimol) {
        this.milimol = milimol;
    }

    /**
     * @return the tanggal
     */
    public String getTanggal() {
        return tanggal;
    }

    /**
     * @param tanggal the tanggal to set
     */
    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
    
}
