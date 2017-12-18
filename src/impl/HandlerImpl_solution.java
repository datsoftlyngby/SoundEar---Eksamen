package impl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.stream.Stream;
import first_semester_eksamen.*;
import first_semester_eksamen.Handler;
import java.util.stream.Collectors;

public class HandlerImpl_solution implements Handler {
    public static final String FILENAME = "Samples.csv";
    
    @Override
    public String readFile(String filename) throws IOException {
        StringBuilder builder = new StringBuilder();
        String br = System.lineSeparator();
        Stream<String> stream = Files.lines(Paths.get(filename)).skip(1);
        stream.forEach(s -> builder.append(s).append(br));
        return builder.toString();
    }

    @Override
    public ArrayList<Sample> getSamples(String data) throws TimeFormatException {
        ArrayList<Sample> samples = new ArrayList<>();
        for(String line : data.split("\n")){
            String[] values = line.split(",");
            String date = values[0].trim();
            String time = values[1].trim();
            int amp = Integer.parseInt(values[2].trim());
            try{
                if(values.length == 4){
                    int peak = Integer.parseInt(values[3].trim());
                    samples.add(new SlowSampleImpl(date, new Time(time), amp, peak));
                } else 
                    samples.add(new SampleImpl(date, new Time(time), amp));
            } catch(IllegalArgumentException e){
                throw new TimeFormatException(e.getMessage());
            }
        }
        return samples;
    }

    @Override
    public Sample getHighestAmplitude(ArrayList<Sample> samples) {
        if(samples == null || samples.isEmpty()) return null;
        return Collections.max(samples, Comparator.comparing(Sample::getAmplitude));
    }

    @Override
    public Sample getBiggestRise(ArrayList<Sample> samples) {
        if(samples == null || samples.isEmpty()) return null;
        if(samples.size() == 1) return samples.get(0);
        int maxRise = samples.get(1).getAmplitude() - samples.get(0).getAmplitude();
        Sample result = samples.get(1);
        for(int i = 2; i < samples.size(); i++){
            Sample current = samples.get(i);
            Sample previous = samples.get(i-1);
            int rise = current.getAmplitude() - previous.getAmplitude();
            if(rise > maxRise) {
                maxRise = rise;
                result = current;
            }
        }
        return result;
    }

    @Override
    public boolean isTooLoud(int limit, ArrayList<Sample> samples) {
        for(Sample s : samples){
            if(s instanceof SlowSample && ((SlowSample)s).getPeak() > limit) return true;
            if(s.getAmplitude() > limit) return true;
        }
        return false;
    }

    @Override
    public void sortByTime(ArrayList<Sample> samples) {
        Collections.sort(samples, Comparator.comparing(Sample::getTime));
    }

    @Override
    public void sortByAmplitude(ArrayList<Sample> samples) {
        Collections.sort(samples, Comparator.comparing(Sample::getAmplitude).reversed());
    }

    @Override
    public ArrayList<Sample> getLoudSamples(int limit, ArrayList<Sample> samples) {
        return samples.stream()
                      .filter((s) -> (s.getAmplitude() >= limit))
                      .collect(Collectors.toCollection(ArrayList::new));
    }
    
    @Override
    public ArrayList<Sample> getSamplesBefore(Time limit, ArrayList<Sample> samples) {
        return samples.stream()
                      .filter((s) -> (limit.compareTo(s.getTime()) > 0))
                      .collect(Collectors.toCollection(ArrayList::new));
    }
}
