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
public class TekananDarah {
    private int id;
    private String tanggal;
    private int sistol;
    private int diastol;
    private int pulse;
    public TekananDarah(int id,String tanggal,int sistol,int diastol,int pulse){
        this.id=id;
        this.tanggal=tanggal;
        this.sistol=sistol;
        this.diastol=diastol;
        this.pulse=pulse;
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
     * @return the sistol
     */
    public int getSistol() {
        return sistol;
    }

    /**
     * @param sistol the sistol to set
     */
    public void setSistol(int sistol) {
        this.sistol = sistol;
    }

    /**
     * @return the diastol
     */
    public int getDiastol() {
        return diastol;
    }

    /**
     * @param diastol the diastol to set
     */
    public void setDiastol(int diastol) {
        this.diastol = diastol;
    }

    /**
     * @return the pulse
     */
    public int getPulse() {
        return pulse;
    }

    /**
     * @param pulse the pulse to set
     */
    public void setPulse(int pulse) {
        this.pulse = pulse;
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
