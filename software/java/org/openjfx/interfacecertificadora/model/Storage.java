package org.openjfx.interfacecertificadora.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Storage {
    private List<TireTemperature> tireArmazen;

    private static Storage storageUnic;
    private Storage(){
        tireArmazen = new ArrayList<TireTemperature>();
    }

    public static Storage getInstance(){
        if(storageUnic == null){
            storageUnic = new Storage();
        }
        return storageUnic;
    }

    public  void add(TireTemperature tireTemperature){
        tireArmazen.add(tireTemperature);
    }

    public List<TireTemperature> getRange(TimeRange range){
        int idStart = binarySearch(range.getStart());
        int idEnd;

        if (range.getEnd() < 0){
            idEnd = tireArmazen.size() -1;
        }else{
            idEnd = binarySearch(range.getEnd());
        }

        if(idStart < 0 ){
            idStart = 0;
        }
        if(idEnd < 0){
            idEnd = tireArmazen.size() -1;
        }

        List<TireTemperature> subList = tireArmazen.subList(idStart,idEnd + 1);
        return subList;
    }

    public int binarySearch(int value){
        int time;
        int low = 0;
        int high = tireArmazen.size() -1;
        int mid;
        int id = -1;

        while( low <= high){
            mid = (low + high) >>> 1;
            time = tireArmazen.get(mid).getTime();

            if(time == value){
                id = mid;
                break;
            }else if( time < value){
                low = mid + 1;
            }else{
                high = mid -1;
            }
        }

        return id;
    }

    public List<TireTemperature> getList(){
        return tireArmazen;
    }
}
