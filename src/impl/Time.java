package impl;

public class Time implements Comparable<Time> {
    private int hour, min;

    public Time(int hour, int min) {
        this.hour = hour;
        this.min = min;
    }
    
    public Time(String time){
//        if(!time.matches("^[0-2][0-3]:[0-5][0-9]$")) 
//            throw new IllegalArgumentException("Bad time format: "+time+" should match \"hh:mm\"");
        String error = "Bad time format: "+time+" should match \"hh:mm\"";
        if(time.length() != 5) throw new IllegalArgumentException(error);
        if(!time.contains(":")) throw new IllegalArgumentException(error);
        String _hour = time.split(":")[0];
        String _min = time.split(":")[1];
        try{
            this. hour = Integer.parseInt(_hour);
            this.min = Integer.parseInt(_min);
        } catch(NumberFormatException e){
            throw new IllegalArgumentException(error);
        }
    }

    public int getHour() {
        return hour;
    }

    public int getMin() {
        return min;
    }

    @Override
    public String toString() {
        String _hour = String.format("%02d", hour);
        String _min = String.format("%02d", min);
        return _hour + ":" + _min;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (getClass() != other.getClass()) {
            return false;
        }
        final Time otherTime = (Time) other;
        if (this.hour != otherTime.hour) {
            return false;
        }
        return this.min == otherTime.min;
    }
    

    @Override
    public int compareTo(Time other) {
        if(other.getHour() == this.getHour())
            return this.getMin() - other.getMin();
        else 
            return this.getHour() - other.getHour();
    }
    
    
}
