/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.interfacecertificadora.model;

/**
 *
 * @author valuc
 */

 
public class TireTemperature {
    private int tireTemperatureFL;
    private int tireTemperatureFR;
    private int tireTemperatureRL;
    private int tireTemperatureRR;
    private int time;

    public TireTemperature(int tireTemperatureFL, int tireTemperatureFR, int tireTemperatureRL, int tireTemperatureRR, int time) {
        this.tireTemperatureFL = tireTemperatureFL;
        this.tireTemperatureFR = tireTemperatureFR;
        this.tireTemperatureRL = tireTemperatureRL;
        this.tireTemperatureRR = tireTemperatureRR;
        this.time = time;
    }

    public TireTemperature() {
    }

    public void setTireTemperature(int tireTemperatureFL, int tireTemperatureFR, int tireTemperatureRL, int tireTemperatureRR, int time) {
        this.tireTemperatureFL = tireTemperatureFL;
        this.tireTemperatureFR = tireTemperatureFR;
        this.tireTemperatureRL = tireTemperatureRL;
        this.tireTemperatureRR = tireTemperatureRR;
        this.time = time;
    }

    public int getTireTemperatureFL() {
        return tireTemperatureFL;
    }

    public int getTireTemperatureFR() {
        return tireTemperatureFR;
    }

    public int getTireTemperatureRL() {
        return tireTemperatureRL;
    }

    public int getTireTemperatureRR() {
        return tireTemperatureRR;
    }

    public int getTime() {
        return time;
    }

    public void setTireTemperatureFL(int tireTemperatureFL) {
        this.tireTemperatureFL = tireTemperatureFL;
    }

    public void setTireTemperatureFR(int tireTemperatureFR) {
        this.tireTemperatureFR = tireTemperatureFR;
    }

    public void setTireTemperatureRL(int tireTemperatureRL) {
        this.tireTemperatureRL = tireTemperatureRL;
    }

    public void setTireTemperatureRR(int tireTemperatureRR) {
        this.tireTemperatureRR = tireTemperatureRR;
    }

    public void setTime(int time) {
        this.time = time;
    }

}
