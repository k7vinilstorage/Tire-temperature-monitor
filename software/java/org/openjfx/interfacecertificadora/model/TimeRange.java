package org.openjfx.interfacecertificadora.model;

public class TimeRange {
    int start;
    int end;

    public TimeRange(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public TimeRange() {
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public void scalar(int scalar){
        this.start = this.start * scalar;
        this.end = this.end * scalar;
    }

}
