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

import javafx.application.Platform;

/**
 *
 * @author valuc
 */
public class USBReader {

    private final List<Consumer<TireTemperature>> listeners;
    private Consumer<String> onError;
    private volatile boolean running;
    private Thread thread;
    private SerialPort portConnection;
    private int x;
    private TireTemperature tireTemperature;
    
    public static USBReader usbReaderUnic;

    private USBReader() {
        listeners = new ArrayList<>();
        running = false;
        thread = null;
        portConnection = null;

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
            try{
                SerialConnection();  
            }catch (Exception e){
                if (onError != null) {
                    onError.accept(e.getMessage());
                }
                running = false;
                return;
            }

            if(portConnection == null){
                if (onError != null) {
                    onError.accept("Porta não encontrada");
                }
                running = false;
                return;
            }
            
            byte[] buffer = new byte[1024];
            portConnection.readBytes(buffer, buffer.length);

            while (running) {
                TireTemperature tireTemperature = readFromUSB();  
                notifyListeners(tireTemperature);    
                x++;  
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
    
    public void stopReading(){
        running = false;
        if (thread != null) {
            thread.interrupt();
        }
        portConnection.closePort();
        portConnection = null;
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
            System.out.println(temp);
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
    
    public void SerialConnection() throws Exception{
        
        String device = "CH340";

        SerialPort[] ports = SerialPort.getCommPorts();
        SerialPort targetPort = null;
        
        for (SerialPort port : ports) {
            if (port.getSystemPortName().toLowerCase().contains(device.toLowerCase()) ||
                port.getDescriptivePortName().toLowerCase().contains(device.toLowerCase())) {
                targetPort = port;
                break;
            }
        }
        
        if (targetPort == null) {
            if (onError != null) {
                throw new Exception("Dispositivo não encontrado!");
            }
        }

        SerialPort connection = targetPort;
        connection.setComPortParameters(115200, 8, 1, 0);
        connection.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 50, 0);
        if (!connection.openPort()) {
            if (onError != null) {
                throw new Exception("Falha ao abrir a porta!");
            }
        }
        connection.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_PORT_DISCONNECTED;
            }

            @Override
            public void serialEvent(SerialPortEvent event) {
                connection.closePort();
                System.out.println("erro:");
                onError.accept("Dispositivo desconectado");   
            }
        });

        this.portConnection = targetPort;
    }

    public void setOnError(Consumer<String> onError) {
        this.onError = onError;
    }
}