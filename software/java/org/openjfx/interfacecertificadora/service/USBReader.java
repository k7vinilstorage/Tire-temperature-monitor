/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.interfacecertificadora.service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.openjfx.interfacecertificadora.model.TireTemperature;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import java.io.IOException;

import javafx.application.Platform;

/**
 *
 * @author valuc
 */
public class USBReader {

    private final List<Consumer<TireTemperature>> listeners;
    private volatile boolean running;
    private Thread thread;
    private SerialPort portConnection;
    private int x;
    private TireTemperature tireTemperature;
    
    private int stringLength;
    private int fistTire;
    private Usb usb;

    private Consumer<String> readError;
    SerialPort port;
    
    public static USBReader usbReaderUnic;

    private USBReader() {
        listeners = new ArrayList<>();
        running = false;
        thread = null;
        portConnection = null;
        stringLength = 31;
        fistTire = 24;
        usb = new Usb(stringLength);

        x = 0;
    }
    
    public static synchronized USBReader getInstance(){
        if (usbReaderUnic == null){
            usbReaderUnic = new USBReader();
        }
        return usbReaderUnic;
    }
    
    public void startReading() throws Exception {
        
        if (running) return;
        running = true;
        
        thread = new Thread(() -> {
            
            usb.setPortCloseError(portError ->{
                readError.accept(portError);
            });
            
            try{
                port = usb.serialConnection();
            }catch (Exception e){
                readError.accept( e.getMessage());
                return;
            }

            while (running) {
                try{
                    String[] data = usb.readUSB(port);
                    
                    try{
                        tireTemperature = procssesData(data);
                        notifyListeners(tireTemperature); 
                        x++;  

                    }catch(Exception e){}
                    
                    
                }catch(InterruptedException e){
                    
                }catch(IOException e){
                    readError.accept(e.getMessage());
                    return;
                }
            }  
                    
        });
        thread.setDaemon(true);
        thread.start();
    }
    
    public void stopReading(){     
        running = false;
        usb.closePort(port);
        if (thread != null) {
            thread.interrupt();
        }
    }
    
    public void setReadErrot(Consumer<String> error) {
        this.readError = error;
    }
    

    private TireTemperature procssesData(String[] split) throws Exception{
        if(split.length == stringLength){
            int tireFL = Math.round(Float.parseFloat(split[fistTire + 0])*100);
            int tireFR = Math.round(Float.parseFloat(split[fistTire + 1])*100);
            int tireRL = Math.round(Float.parseFloat(split[fistTire + 2])*100);
            int tireRR = Math.round(Float.parseFloat(split[fistTire + 3])*100);
            
            return new TireTemperature(tireFL,tireFR,tireRL,tireRR,x);
        }else{
            throw new Exception("Dados incompletos");
        }
    }

    public void addDataListener(Consumer<TireTemperature> listener) {
        listeners.add(listener);
    }

    private void notifyListeners(TireTemperature value) {
        for (Consumer<TireTemperature> listener : listeners) listener.accept(value);
    }

    private TireTemperature readFromUSB(){
        byte[] buffer = new byte[1024];
        int bytesRead = portConnection.readBytes(buffer, buffer.length);
        
        if (bytesRead > 0) {
            String temp = new String(buffer, 0, bytesRead);
            String[] split = temp.split("/");
            if(split.length == 31){
                try{
                    int tireFL = Math.round(Float.parseFloat(split[24])*100);
                    int tireFR = Math.round(Float.parseFloat(split[25])*100);
                    int tireRL = Math.round(Float.parseFloat(split[26])*100);
                    int tireRR = Math.round(Float.parseFloat(split[27])*100);
                    tireTemperature = new TireTemperature(tireFL,tireFR,tireRL,tireRR,x);
                }catch(NumberFormatException e){
                    return readFromUSB();
                }
            }
        } 
        return tireTemperature;
    }

    public boolean isActive(){
        if(thread != null){
            return thread.isAlive();
        }else{
            return false;
        }
    }
    
}