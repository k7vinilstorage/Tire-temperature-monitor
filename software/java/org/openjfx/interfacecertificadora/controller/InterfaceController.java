/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.openjfx.interfacecertificadora.controller;

import org.openjfx.interfacecertificadora.service.Export;
import org.openjfx.interfacecertificadora.service.TemperatureConversion;

import java.awt.image.RenderedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.CountDownLatch;

import javax.swing.text.StyledEditorKit.BoldAction;

import java.util.List;

import javafx.application.Platform;
import javafx.fxml.Initializable;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.SnapshotParameters;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.embed.swing.SwingFXUtils;
import javafx.stage.Window;


import org.openjfx.interfacecertificadora.model.Storage;
import org.openjfx.interfacecertificadora.model.TimeRange;
import org.openjfx.interfacecertificadora.model.TireTemperature;
import org.openjfx.interfacecertificadora.service.USBReader;
import org.openjfx.interfacecertificadora.view.RangeInterface;

import javafx.util.StringConverter;
import org.openjfx.interfacecertificadora.view.Notify;



/**
 * FXML Controller class
 *
 * @author valuc
 */
public class InterfaceController implements Initializable {

    @FXML
    private LineChart<?, ?> lcTemp;

    @FXML
    private Label rtTitle2;
    
    @FXML
    private Label rtTitle1;
    
    @FXML
    private ToggleGroup ToogleTemp;

    @FXML
    private ToggleButton btnC;

    @FXML
    private Button btnExpChart;

    @FXML
    private Button btnExpData;

    @FXML
    private ToggleButton btnF;

    @FXML
    private ToggleButton btnFL;

    @FXML
    private ToggleButton btnFR;

    @FXML
    private ToggleButton btnK;

    @FXML
    private ToggleButton btnRL;

    @FXML
    private ToggleButton btnRR;

    @FXML
    private ToggleButton btnStatus;

    @FXML
    private Circle cicleStatus;

    @FXML
    private Label rtInformation;

    @FXML
    private Label rtStatus;

    @FXML
    private Label rtTEmpFL;

    @FXML
    private Label rtTEmpFR;

    @FXML
    private Label rtTEmpRL;

    @FXML
    private Label rtTEmpRR;

    @FXML
    private Label rtUnitFL;

    @FXML
    private Label rtUnitFR;

    @FXML
    private Label rtUnitRL;

    @FXML
    private Label rtUnitRR;

    private NumberAxis xAxis;

    private NumberAxis yAxis;
    
    private XYChart.Series frontRigth;
    private XYChart.Series frontLeft;
    private XYChart.Series rearRigth;
    private XYChart.Series rearLeft;
    
    private float scalarX;
    private float scalarY;
    private TireTemperature tireTemperature;
    private String temperatureUnit;
    private boolean rangeFlag;
    private volatile boolean linechartFlag;
    private USBReader reader;

    private Toggle oldToggleTemperature;

    @FXML
    private NumberAxis xLineChart;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        scalarX = 10;
        scalarY = 100;
        temperatureUnit = "C";
        tireTemperature = new TireTemperature(0, 0, 0, 0, 0);
        reader = USBReader.getInstance();

        oldToggleTemperature = btnC;
        
        xAxis = (NumberAxis) lcTemp.getXAxis();
        yAxis = (NumberAxis) lcTemp.getYAxis();

        setYAxis();
        setXAxis();

        lcTemp.setAnimated(false); 

        initializeSeries();
        switchTemperatureUnit();

        updateTemperature();
    }    

    @FXML
    void exportChart(ActionEvent event) {
        try{
            WritableImage image = lcTemp.snapshot(new SnapshotParameters(), null);
            RenderedImage reder = SwingFXUtils.fromFXImage(image, null);
            Window window = lcTemp.getScene().getWindow();

            if(Export.exportPNG(reder, window)){
                Platform.runLater(() -> {
                    Notify.notify("information","Salvar imagem", "Imagem salva com sucesso", null);
                });
            }
        }catch (IOException e){
            Platform.runLater(() -> {
                Notify.notify("error","Salvar iamgm", e.getMessage(), null);
            });
            
        } 
    }

    @FXML
    void exportReport(ActionEvent event) {
        try{
            Storage storage = Storage.getInstance();
            Window window = lcTemp.getScene().getWindow();

            if(Export.exportCSV(storage.getList(), window)){
                Platform.runLater(() -> {
                    Notify.notify("information","Salvar dados", "CSV salvo com sucesso", null);
                });
                
            }
        }catch (IOException e){ 
            Platform.runLater(() -> {
                Notify.notify("error","Salvar iamgm", e.getMessage(), null);
            });
        }
        
    }

    @FXML
    void highlightFrontLeft(ActionEvent event) {
        if(btnFL.isSelected()){
            Platform.runLater(() -> frontLeft.getNode().setStyle("-fx-stroke: rgba(120,120,120,0.2);"));
        }else{
            Platform.runLater(() -> frontLeft.getNode().setStyle("-fx-stroke: rgba(245,2,56,1);"));
        }
    }

    @FXML
    void highlightFrontRigth(ActionEvent event) {
        if(btnFR.isSelected()){
            Platform.runLater(() -> frontRigth.getNode().setStyle("-fx-stroke: rgba(120,120,120,0.2);"));
        }else{
            Platform.runLater(() -> frontRigth.getNode().setStyle("-fx-stroke: rgba(219,129,0,1);"));
        }
    }

    @FXML
    void highlightRearLeft(ActionEvent event) {
        if(btnRL.isSelected()){
           Platform.runLater(() ->  rearLeft.getNode().setStyle("-fx-stroke: rgba(120,120,120,0.2);"));
        }else{
           Platform.runLater(() ->  rearLeft.getNode().setStyle("-fx-stroke: rgba(0,199,30,1);"));
        }
    }

    @FXML
    void highlightRearRigth(ActionEvent event) {
        if(btnRR.isSelected()){
            Platform.runLater(() -> rearRigth.getNode().setStyle("-fx-stroke: rgba(120,120,120,0.2);"));
        }else{
            Platform.runLater(() -> rearRigth.getNode().setStyle("-fx-stroke: rgba(0,159,230,1);"));
        }
    }

    @FXML
    void readData(ActionEvent event) {
        
        boolean status = reader.isActive();
        
        if(status){
            reader.stopReading();
            statusOFF();
        }else{
            
            try{
                if(rangeFlag){
                    rangeFlag = false;
                    
                    if(tireTemperature != null && tireTemperature.getTime() != 0){
                        
                        TimeRange range = new TimeRange();
                        range.setStart((tireTemperature.getTime() - (60 * (int)scalarX)));
                        if (range.getStart() < 0){
                            range.setStart(0);
                        }

                        range.setEnd(tireTemperature.getTime() );
                        resetLineChart(range);
                    }
                }

                new Thread(()->{
                    
                    while (linechartFlag) try { Thread.sleep(1); } catch (InterruptedException e) {}
                    if(xAxis.getUpperBound() < (60 * scalarX)){
                        Platform.runLater(()-> xAxis.setUpperBound(60 * (int)scalarX));
                    }

                    try{
                        
                        reader.startReading();
                        getErrorSerial();
                        statusOn();
                        
                    }catch(Throwable e){
                        Platform.runLater(() -> {
                            Notify.notify("error", "Erro ao iniciar conexão",e.getMessage(), null);
                        });
                        
                        reader.stopReading();
                        statusOFF();
                    }
                }).start();
 
            }catch(Throwable e){
               
                Platform.runLater(() -> {
                    Notify.notify("error", "Falha na conexão", e.getMessage(), null);
                });
                
                statusOFF();
            }
        }
    }

    public void getErrorSerial(){
        reader.setReadErrot(usbError -> {
            Platform.runLater(() -> {
                
                Notify.notify("error", "Erro na leitura serial", usbError, null);
                reader.stopReading();
                statusOFF();
            });
            statusOFF();
        });
    }

    public void statusOn(){
        Platform.runLater(()->{
            cicleStatus.getStyleClass().remove("btnStatus-off");
            cicleStatus.getStyleClass().add("btnStatus-on");
            rtStatus.setText("Online");
        });
    }

    public void statusOFF(){
        Platform.runLater(()->{
            cicleStatus.getStyleClass().remove("btnStatus-on");
            cicleStatus.getStyleClass().add("btnStatus-off");
            rtStatus.setText("Offline");
        });
    }

    @FXML
    void selectRange(ActionEvent event) {
        USBReader reader = USBReader.getInstance();
        if(reader.isActive() == false){
            rangeFlag = true;
            try{
                TimeRange range = RangeInterface.getRange();
                range.scalar((int)scalarX);

                if(range.getEnd() > tireTemperature.getTime()){
                    range.setEnd(-1);
                }
                resetLineChart(range);
            }catch (Exception e){
            }
        }else{
            Platform.runLater(() -> {
                Notify.notify("warning", "Sistema ativo", "Desligue a transmisão." , null);
            });
        }
    }

    private void resetLineChart(TimeRange range){
        Storage storage = Storage.getInstance();
        List<TireTemperature> list = storage.getRange(range);

        Platform.runLater(()->{
            linechartFlag = true;
            this.frontRigth.getData().clear();
            this.frontLeft.getData().clear();
            this.rearRigth.getData().clear();
            this.rearLeft.getData().clear();

            xAxis.setLowerBound(list.get(0).getTime());
            xAxis.setUpperBound(list.get(list.size() -1).getTime());

            list.forEach(tire ->{
                
                this.frontRigth.getData().add(new Data(tire.getTime(),tire.getTireTemperatureFR()));
                this.frontLeft.getData().add(new Data(tire.getTime(),tire.getTireTemperatureFL()));
                this.rearRigth.getData().add(new Data(tire.getTime(),tire.getTireTemperatureRR()));
                this.rearLeft.getData().add(new Data(tire.getTime(),tire.getTireTemperatureRL()));
                
            });
            linechartFlag = false;
        });
    }
    
    private void initializeSeries(){
        //cria as linhas do gico 
        this.frontRigth = new XYChart.Series();
        this.frontLeft = new XYChart.Series();
        this.rearRigth = new XYChart.Series();
        this.rearLeft = new XYChart.Series();

        
        //a ordem importa, ela que define a cor
        this.lcTemp.getData().addAll(frontLeft,frontRigth,rearLeft,rearRigth);
    }

    public void switchTemperatureUnit() {
        ToogleTemp.selectedToggleProperty().addListener((obs, oldToggle, newToggle) -> {
        
            if (newToggle == null) {
                oldToggle.setSelected(true);
                oldToggleTemperature = oldToggle;
            }else if (newToggle != oldToggleTemperature) {
                oldToggleTemperature = newToggle;
                ToggleButton btnSelected = (ToggleButton)newToggle;
                

                switch (btnSelected.getId()) {
                    case "btnC":
                        setCelsius();
                        break;
                        
                    case "btnF":
                        setFahrenheit();
                        break;

                    case "btnK":
                        setKelvin();
                        break;
                
                    default:
                        setCelsius();
                        break;
                }
            }
        });
    }
    
    public void updateTemperature(){
        USBReader reader = USBReader.getInstance();
        reader.addDataListener(value -> {

            Platform.runLater(() -> {
                this.tireTemperature = value;
                
                this.frontRigth.getData().add(new Data(value.getTime(),value.getTireTemperatureFR()));
                this.frontLeft.getData().add(new Data(value.getTime(),value.getTireTemperatureFL()));
                this.rearRigth.getData().add(new Data(value.getTime(),value.getTireTemperatureRR()));
                this.rearLeft.getData().add(new Data(value.getTime(),value.getTireTemperatureRL()));

                if(value.getTime() > (60*scalarX)){
                    xAxis.setLowerBound(xAxis.getLowerBound() + 1);
                    xAxis.setUpperBound(xAxis.getUpperBound() + 1);

                    this.frontRigth.getData().remove(0);
                    this.frontLeft.getData().remove(0);
                    this.rearRigth.getData().remove(0);
                    this.rearLeft.getData().remove(0);
                }
                printTemperature();
            });
        });
        
    }

    public void setXAxis(){
        xAxis.setLowerBound(0);
        xAxis.setUpperBound(60 * scalarX);
        xAxis.setTickUnit(5 * scalarX);
        xAxis.setMinorTickCount(5 * (int)scalarX);

        xAxis.setAutoRanging(false);
        xAxis.setForceZeroInRange(false);

        xAxis.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number object) {
                return String.format("%.0f", object.doubleValue() / scalarX);
            }

            @Override
            public Number fromString(String string) {
                return Double.parseDouble(string) * scalarX; 
            }
        });
    }

    public void setYAxis(){
        yAxis.setLowerBound(10 * scalarY);
        yAxis.setUpperBound(150 * scalarY);
        yAxis.setTickUnit(10 * scalarY);
        yAxis.setMinorTickCount(10 * (int)scalarY);

        yAxis.setAutoRanging(false);
        yAxis.setForceZeroInRange(false);

        setCelsius();   
    }

    public void setCelsius(){
        temperatureUnit = "C";

        rtUnitFL.setText("°C");
        rtUnitFR.setText("°C");
        rtUnitRL.setText("°C");
        rtUnitRR.setText("°C");

        yAxis.setLabel("Temperatura (°C)");

        yAxis.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number object) {
                return String.format("%.0f", object.doubleValue()  / scalarY);
            }

            @Override
            public Number fromString(String string) {
                return Double.parseDouble(string) * scalarY; 
            }
        });

        printTemperature();
    }

    public void setKelvin(){
        temperatureUnit = "K";

        rtUnitFL.setText(" K");
        rtUnitFR.setText(" K");
        rtUnitRL.setText(" K");
        rtUnitRR.setText(" K");

        yAxis.setLabel("Temperatura (K)");

        yAxis.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number object) {
                return String.format("%.0f", TemperatureConversion.celsiusToKelvin(object.doubleValue()) / scalarY);
            }

            @Override
            public Number fromString(String string) {
                return TemperatureConversion.kelvinToCelsius(Double.parseDouble(string)) * scalarY; 
            }
        });

        printTemperature();
    }

    public void setFahrenheit(){
        temperatureUnit = "F";

        rtUnitFL.setText("°F");
        rtUnitFR.setText("°F");
        rtUnitRL.setText("°F");
        rtUnitRR.setText("°F");

        rtTEmpFL.setText(String.valueOf(rtTEmpFL.getText()));
        

        yAxis.setLabel("Temperatura (°F)");

        yAxis.setTickLabelFormatter(new StringConverter<Number>() {
            @Override
            public String toString(Number object) {
                return String.format("%.0f", (TemperatureConversion.celsiusToFahrenheit(object.doubleValue())) / scalarY);
            }

            @Override
            public Number fromString(String string) {
                return TemperatureConversion.fahrenheitToCelsius(Double.parseDouble(string))  * scalarY; 
            }
        });

        printTemperature();
    }

    public void printTemperature(){

        switch (temperatureUnit) {
            case "C":
                this.rtTEmpFL.setText(String.format("%.02f",tireTemperature.getTireTemperatureFL()/scalarY));
                this.rtTEmpFR.setText(String.format("%.02f",tireTemperature.getTireTemperatureFR()/scalarY));
                this.rtTEmpRL.setText(String.format("%.02f",tireTemperature.getTireTemperatureRL()/scalarY));
                this.rtTEmpRR.setText(String.format("%.02f",tireTemperature.getTireTemperatureRR()/scalarY));

                break;

            case "F":
                this.rtTEmpFL.setText(String.format("%.02f",TemperatureConversion.celsiusToFahrenheit(tireTemperature.getTireTemperatureFL())/scalarY));
                this.rtTEmpFR.setText(String.format("%.02f",TemperatureConversion.celsiusToFahrenheit(tireTemperature.getTireTemperatureFR())/scalarY));
                this.rtTEmpRL.setText(String.format("%.02f",TemperatureConversion.celsiusToFahrenheit(tireTemperature.getTireTemperatureRL())/scalarY));
                this.rtTEmpRR.setText(String.format("%.02f",TemperatureConversion.celsiusToFahrenheit(tireTemperature.getTireTemperatureRR())/scalarY));
                break;
            
            case "K":
                this.rtTEmpFL.setText(String.format("%.02f",TemperatureConversion.celsiusToKelvin(tireTemperature.getTireTemperatureFL())/scalarY));
                this.rtTEmpFR.setText(String.format("%.02f",TemperatureConversion.celsiusToKelvin(tireTemperature.getTireTemperatureFR())/scalarY));
                this.rtTEmpRL.setText(String.format("%.02f",TemperatureConversion.celsiusToKelvin(tireTemperature.getTireTemperatureRL())/scalarY));
                this.rtTEmpRR.setText(String.format("%.02f",TemperatureConversion.celsiusToKelvin(tireTemperature.getTireTemperatureRR())/scalarY));
                break;
        
            default:
                this.rtTEmpFL.setText(String.format("%.02f",tireTemperature.getTireTemperatureFL()/scalarY));
                this.rtTEmpFR.setText(String.format("%.02f",tireTemperature.getTireTemperatureFR()/scalarY));
                this.rtTEmpRL.setText(String.format("%.02f",tireTemperature.getTireTemperatureRL()/scalarY));
                this.rtTEmpRR.setText(String.format("%.02f",tireTemperature.getTireTemperatureRR()/scalarY));
                break;
        }
    }
    
}

