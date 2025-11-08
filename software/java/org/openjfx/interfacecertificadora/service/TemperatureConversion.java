package org.openjfx.interfacecertificadora.service;

public class TemperatureConversion {
    public static double celsiusToFahrenheit(double value){
        return (value *1.8 +3200 );
    }

    public static double celsiusToKelvin(double value){
        return value + 27315 ;
    }

    public static double kelvinToCelsius(double value){
        return value - 27315 ;
    }
    
    public static double fahrenheitToCelsius(double value){
        return ((value - 3200) *5)/9 ;
    }

    public static double celsiusToFahrenheit(String value){
        return (Integer.valueOf(value) *1.8 +3200 );
    }

    public static double celsiusToKelvin(String value){
        return Integer.valueOf(value) + 27315 ;
    }

    public static double kelvinToCelsius(String value){
        return Integer.valueOf(value) - 27315 ;
    }
    
    public static double fahrenheitToCelsius(String value){
        return ((Integer.valueOf(value) - 3200) *5)/9 ;
    }
}

