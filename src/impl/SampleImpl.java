package impl;

import first_semester_eksamen.Sample;

public class SampleImpl implements Sample {
    private final String date;
    private final Time time;
    private final int amp;

    public SampleImpl(String date, Time time, int amp) {
        this.date = date;
        this.time = time;
        this.amp = amp;
    }

    @Override public String getDate() { return this.date; }
    @Override public Time getTime() { return this.time; }
    @Override public int getAmplitude() { return this.amp; }

    @Override
    public String toString() {
        return "Sample{" + "date=" + date + ", time=" + time + 
                ", amp=" + amp + '}';
    }
}
