package org.openjfx.interfacecertificadora.service;

import java.awt.image.RenderedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.openjfx.interfacecertificadora.model.TireTemperature;

import javafx.stage.Window;
import javafx.stage.FileChooser;

public class Export {
    public static boolean exportPNG(RenderedImage image,  Window window) throws IOException{
        
        File file = getPath("Exportar imagem", "tireTemperature.png","Imagem PNG (*.png)", "*.png", window);

        if(file != null){
            ImageIO.write(image, "png",file);
            return true;
        }else{
            return false;
        }
    }

    public static File getPath(String title, String fileName, String filterLable, String extension, Window window){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(filterLable, extension);

        fileChooser.getExtensionFilters().add(extFilter);

        fileChooser.setInitialFileName(fileName);
        
        String userHome = System.getProperty("user.home");
        try{
           
            File downloadsDir = new File(userHome, "Downloads");
            if (downloadsDir.exists() && downloadsDir.isDirectory()) {
                fileChooser.setInitialDirectory(downloadsDir);
            } else {
                fileChooser.setInitialDirectory(new File(userHome));
            } 
        }catch (Exception e){
            fileChooser.setInitialDirectory(new File(userHome));
        }
        return fileChooser.showSaveDialog(window);
    }
    
    public static boolean exportCSV(List<TireTemperature> lsit, Window window) throws IOException{
        
        File file =  getPath("Exportar dados", "data.csv","CSV (*.csv)", "*.csv", window);
        if(file != null){
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
                writer.write("time;tire front left; tire front rigth; tire rear left; tire rear rigth");
                writer.newLine();

                for (TireTemperature tireTemperature : lsit){
                    writer.write(tireTemperature.getTime() + ";" + tireTemperature.getTireTemperatureFL()  + ";" + tireTemperature.getTireTemperatureFR() + ";" +tireTemperature.getTireTemperatureRL() + ";" +tireTemperature.getTireTemperatureRR());
                    writer.newLine();
                }
                return true;
            }
        }
        return false;
    }
}
