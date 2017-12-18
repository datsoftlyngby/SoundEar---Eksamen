package impl;

import first_semester_eksamen.Handler;
import first_semester_eksamen.Sample;
import first_semester_eksamen.TimeFormatException;
import java.io.IOException;
import java.util.ArrayList;

public class HandlerImpl implements Handler {
    public static final String FILENAME = "Samples.csv";

    @Override
    public String readFile(String filename) throws IOException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<Sample> getSamples(String data) throws TimeFormatException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Sample getHighestAmplitude(ArrayList<Sample> samples) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Sample getBiggestRise(ArrayList<Sample> samples) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public boolean isTooLoud(int limit, ArrayList<Sample> samples) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void sortByTime(ArrayList<Sample> samples) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void sortByAmplitude(ArrayList<Sample> samples) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<Sample> getLoudSamples(int limit, ArrayList<Sample> samples) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public ArrayList<Sample> getSamplesBefore(Time limit, ArrayList<Sample> samples) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
