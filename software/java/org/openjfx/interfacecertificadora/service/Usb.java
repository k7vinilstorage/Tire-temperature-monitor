/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.interfacecertificadora.service;
import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import java.io.IOException;
import java.util.function.Consumer;
/**
 *
 * @author vinicius
 */
public class Usb {
    private String temp;
    private int countDataInvalid;
    private int stringLength;
    private Consumer<String> error;

    public Usb() {
        this.temp = "";
        this.countDataInvalid = 0;
        this.stringLength = 1;
    }

    public Usb(int sizeString) {
        this.temp = "";
        this.countDataInvalid = 0;
        this.stringLength = sizeString;
    }
    
    
    public String[] readUSB(SerialPort portConnection) throws IOException, InterruptedException{
        int countAttempt = 0;
        while(!temp.contains("\n")){
            if(portConnection.bytesAvailable() <= 0){
                
                if(countAttempt < 1000){
                    
                    Thread.sleep(1);  // pausa por 1 milissegundo
                    
                    countAttempt++;
                    continue;
                }else{
                    throw new IOException("Nenhum dado recebido");
                }
            }
            countAttempt = 0;

            byte[] buffer = new byte[portConnection.bytesAvailable()];
            int bytesRead = portConnection.readBytes(buffer, buffer.length);
            if(bytesRead <= 0){
                continue;
            }
            temp += new String(buffer, 0, bytesRead);
        }

        String[] splitString = temp.split("\n");
        if(splitString.length > 1 ){
            
            temp = splitString[1];
        }else{
            temp = "";
        }

        String[] data = splitString[0].split("/");
        
        if(data.length == stringLength){
            countDataInvalid = 0;
            return data;
        }else{
            if(countDataInvalid >= 100){
                throw new IOException("Dados inválidos");
            }
            countDataInvalid++;
            return readUSB(portConnection);
        }
    }

    public SerialPort serialConnection() throws Exception{
        
        String device = System.getProperty("os.name").toLowerCase().contains("win") ? "ch340" : "ttyUSB";//-----------------------------------------

        SerialPort[] ports = SerialPort.getCommPorts();
        SerialPort targetPort = null;
        
        for (SerialPort port : ports) {
            if (port.getSystemPortName().toLowerCase().contains(device.toLowerCase()) ||
            port.getDescriptivePortName().toLowerCase().contains(device.toLowerCase())) {
                try{
                    openPort(port);
                    readUSB(port);
                    targetPort = port;
                    break;
                }catch(Throwable e){
                    System.out.println(e.getMessage());
                    closePort(port);
                } 
            }
        }
        
        if (targetPort == null) {
            throw new Exception("Dispositivo não encontrado!");
            
        }
        
        if (!openPort(targetPort)) {
            closePort(targetPort);
            throw new Exception("Falha ao abrir a porta!");
        }
        return targetPort;
    }

    public boolean openPort(SerialPort port){
        if(port == null ){
            return false;
        }
        if(port.isOpen()){
            return true;
        }
        port.setComPortParameters(115200, 8, 1, 0);
        port.setComPortTimeouts(SerialPort.TIMEOUT_NONBLOCKING , 0, 0);
        
        port.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_PORT_DISCONNECTED; //| SerialPort.LISTENING_EVENT_PORT_DISCONNECTED
            }

            @Override
            public void serialEvent(SerialPortEvent event) {
                closePort(port);
                if(error != null){
                    error.accept("Porta serial desconectada");
                } 
            }
        });
        return port.openPort(); 
    }

    public void closePort(final SerialPort port){
        if(port != null && port.isOpen()){
            port.closePort();
        }
    }

    public void setPortCloseError(Consumer<String> error) {
        this.error = error;
    }
}
