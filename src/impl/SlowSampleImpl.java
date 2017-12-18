package impl;

import first_semester_eksamen.SlowSample;

public class SlowSampleImpl extends SampleImpl implements SlowSample  {
    private final int peak;
    
    public SlowSampleImpl(String date, Time time, int amp, int peak){
        super(date, time, amp);
        this.peak = peak;
    }
    
    @Override public int getPeak() { return this.peak; }

    @Override
    public String toString() {
        return "SlowSampleImpl{" + "sample=" + super.toString() + ",peak=" + peak + '}';
    }
}
