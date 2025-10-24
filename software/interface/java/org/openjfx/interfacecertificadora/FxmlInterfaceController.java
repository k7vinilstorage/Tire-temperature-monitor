/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package org.openjfx.interfacecertificadora;

import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.TimeUnit;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;
import javafx.scene.control.Label;
import javafx.util.Duration;



/**
 * FXML Controller class
 *
 * @author valuc
 */
public class FxmlInterfaceController implements Initializable {

    @FXML
    private LineChart<?, ?> lcTemp;

    @FXML
    private Label rtTitle2;
    
    @FXML
    private Label rtTitle1;
    
    private XYChart.Series frontRigth;
    private XYChart.Series frontLeft;
    private XYChart.Series rearRigth;
    private XYChart.Series rearLeft;
    
    private float currentX = 0;
    private final int TOTAL_POINTS = 999999;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        frontRigth = new XYChart.Series();
        frontLeft = new XYChart.Series();
        rearRigth = new XYChart.Series();
        rearLeft = new XYChart.Series();

        frontRigth.setName("teste1");
        frontLeft.setName("Dianteiro esquerdo");
        rearRigth.setName("Traseiro direito");
        rearLeft.setName("Traseiro esquerdo");
        
        lcTemp.setAnimated(false);
        //a ordem importa, ela que define a cor
        lcTemp.getData().addAll(frontLeft,frontRigth,rearLeft,rearRigth);
        
        
        for(float i= 0; i <50; i+=1){
            frontRigth.getData().add(new Data(i, gerarGrafico(i,0.01f)));
            frontLeft.getData().add(new Data(i, gerarGrafico(i,0.003f)));
            rearRigth.getData().add(new Data(i, gerarGrafico(i,0.004f)));
            rearLeft.getData().add(new Data(i, gerarGrafico(i,0.001f)));
        }
        frontRigth.setNode(null);
        frontLeft.setNode(null);
        rearRigth.setNode(null);
        rearLeft.setNode(null);
        
       //iniciarAtualizacaoAutomatica();
    }    
    
    public static double cubica(double a, double b, double c, double d, double x) {
        return b * Math.pow(x, 2) + c * x + d;
    }
    
    private void iniciarAtualizacaoAutomatica() {
        Timeline timeline = new Timeline(
            new KeyFrame(Duration.seconds(0.001), e -> adicionarProximoPonto())
        );
        timeline.setCycleCount(TOTAL_POINTS); 
        timeline.play();
        System.gc();
    }
     
    private void adicionarProximoPonto() {
        if (currentX < TOTAL_POINTS) {
            // Adicionar um ponto para cada série
            frontRigth.getData().add(new Data(currentX, gerarGrafico(currentX,0.01f)));
            frontLeft.getData().add(new Data(currentX, gerarGrafico(currentX,0.003f)));
            rearRigth.getData().add(new Data(currentX, gerarGrafico(currentX,0.004f)));
            rearLeft.getData().add(new Data(currentX, gerarGrafico(currentX,0.001f)));
            
            currentX+=0.01f;
        }
    }
    
    public static float gerarGrafico(float x, float a) {
                
        double random = Math.random();

        return (float)(120 - (100* Math.exp(-a * x)) + random);
    }
}
