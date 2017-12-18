package impl;

import java.io.IOException;
import java.util.ArrayList;
import org.junit.Test;
import first_semester_eksamen.Sample;
import first_semester_eksamen.Handler;
import first_semester_eksamen.SlowSample;
import first_semester_eksamen.TimeFormatException;
import java.util.Collections;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;

public class HandlerImplTest {
    private final Handler h = new HandlerImpl();
    private String data;
    private ArrayList<Sample> samples;
    private static int score = 0;
        
    // 1
    @Test
    public void testReadFile() {
        try {
            data = h.readFile("Samples.csv");
            
            int expected = 1478;
            int actual = data.length();
            assertEquals(expected, actual);

            String _expected = "24-12-2017,10:00,63";
            String _actual = data.split(System.lineSeparator())[0];
            assertEquals(_expected, _actual);

            _expected = "24-12-2017,10:02,72";
            _actual = data.split(System.lineSeparator())[16]; // Magic number
            assertEquals(_expected, _actual);

            expected = -483060556;
            actual = data.hashCode();
            assertEquals(expected, actual);
        } catch (IOException ex) {
            fail("File not found / not read!");
        } catch (Exception e){
            fail(e.getMessage());
        }
        score += 10;
    }

    // 2
    @Test
    public void testGetSamples() throws Exception {
        samples = h.getSamples(data);
        assertNotNull(samples);
        assertFalse(samples.isEmpty());
        
        int expected = 24;
        int actual = samples.size();
        assertEquals(expected, actual);

        expected = 63;
        actual = samples.get(0).getAmplitude();
        assertEquals(expected, actual);
        
        // Single sample
        samples = h.getSamples("24-12-2017,10:00,9999");
        assertNotNull(samples);
        assertFalse(samples.isEmpty());
        assertNotNull(samples.get(0));
        expected = 9999;
        actual = samples.get(0).getAmplitude();
        assertEquals(expected, actual);
        
        score += 13;
    }
    
    // 3
    @Test
    public void testSlowSample() throws Exception{
        // Slow sample
        samples = h.getSamples("24-12-2017,10:00, 50, 100");
        assertNotNull(samples);
        assertNotNull(samples.get(0));
        
        int expected = 50;
        int actual = samples.get(0).getAmplitude();
        assertEquals(expected, actual);
        
        assertTrue(samples.get(0) instanceof SlowSample);
        
        expected = 100;
        actual = ((SlowSample)samples.get(0)).getPeak();
        assertEquals(expected, actual);
        
        score += 9;
    }
    
    // 4
    @Test
    public void testTimeException(){
        try{
            samples = h.getSamples("24-12-2017, 10:0, 50, 100");
            fail();
        } catch (TimeFormatException e){
            
        } catch (Exception e){
            fail();
        }
        
        score += 7;
    }
    
    // 5
    @Test
    public void testGetHighestAmplitude() {
        int expected = 79;
        int actual = h.getHighestAmplitude(samples).getAmplitude();
        assertEquals(expected, actual);
        
        score += 8;
    }

    // 6
    @Test
    public void testGetBiggestRise() {
        Time expected = new Time("12:10");
        Time actual = h.getBiggestRise(samples).getTime();
        assertEquals(expected, actual);
        
        score += 11;
    }

    // 7
    @Test
    public void testIsTooLoud() {
        boolean expected = true;
        boolean actual = h.isTooLoud(78, samples);
        assertEquals(expected, actual);

        expected = false;
        actual = h.isTooLoud(80, samples);
        assertEquals(expected, actual);
        
        score += 8;
    }
    
    // 8
    @Test
    public void testSortByTime(){
        Collections.shuffle(samples);
        h.sortByTime(samples);
       
        for(int i = 1; i < samples.size(); i++){
            Sample s = samples.get(i);
            Sample prev = samples.get(i-1);
            assertTrue(s.getTime().compareTo(prev.getTime()) >= 0);
        }
        
        score += 8;
    }
    
    // 9
    @Test
    public void testSortByAmplitude(){
        Collections.shuffle(samples);
        h.sortByAmplitude(samples);
        
        for(int i = 1; i < samples.size(); i++){
            Sample s = samples.get(i);
            Sample prev = samples.get(i-1);
            assertTrue(s.getAmplitude() <= prev.getAmplitude());
        }
        
        score += 7;
    }
    
    // 10
    @Test
    public void testGetLoudSamples(){
        ArrayList<Sample> result = h.getLoudSamples(70, samples);
        
        int expected = 5;
        int actual = result.size();
        assertEquals(expected, actual);
        
        expected = 70;
        actual = result.get(1).getAmplitude();
        assertEquals(expected, actual);
        
        expected = 21;
        actual = result.get(4).getTime().getMin();
        assertEquals(expected, actual);
        
        score += 9;
    }
    
    // 11
    @Test
    public void testGetSamplesBefore(){
        ArrayList<Sample> result = h.getSamplesBefore(new Time("12:05"), samples);
        
        int expected = 4;
        int actual = result.size();
        assertEquals(expected, actual);
        
        expected = 79;
        actual = result.get(1).getAmplitude();
        assertEquals(expected, actual);
        
        expected = 04;
        actual = result.get(3).getTime().getMin();
        assertEquals(expected, actual);
        
        score += 10;
    }
    
    @Before
    public void setUp() {
        data =    "24-12-2017,12:01,63\n"
                + "24-12-2017,12:02,79\n"
                + "24-12-2017,12:03,41\n"
                + "24-12-2017,12:04,70\n"
                + "24-12-2017,12:05,64\n"
                + "24-12-2017,12:06,66\n"
                + "24-12-2017,12:07,72\n"
                + "24-12-2017,12:08,47\n"
                + "24-12-2017,12:09,20\n"
                + "24-12-2017,12:10,52\n"
                + "24-12-2017,12:11,41\n"
                + "24-12-2017,12:12,64\n"
                + "24-12-2017,12:13,44\n"
                + "24-12-2017,12:14,65\n"
                + "24-12-2017,12:15,45\n"
                + "24-12-2017,12:16,47\n"
                + "24-12-2017,12:17,72\n"
                + "24-12-2017,12:18,59\n"
                + "24-12-2017,12:19,47\n"
                + "24-12-2017,12:20,56\n"
                + "24-12-2017,12:21,71\n"
                + "24-12-2017,12:21,65\n"
                + "24-12-2017,12:21,54\n"
                + "24-12-2017,12:21,65";
        
        samples = new ArrayList<>();
        samples.add(new SampleImpl("24-12-2017", new Time("12:01"), 63));
        samples.add(new SampleImpl("24-12-2017", new Time("12:02"), 79));
        samples.add(new SampleImpl("24-12-2017", new Time("12:03"), 41));
        samples.add(new SampleImpl("24-12-2017", new Time("12:04"), 70));
        samples.add(new SampleImpl("24-12-2017", new Time("12:05"), 64));
        samples.add(new SampleImpl("24-12-2017", new Time("12:06"), 66));
        samples.add(new SampleImpl("24-12-2017", new Time("12:07"), 72));
        samples.add(new SampleImpl("24-12-2017", new Time("12:08"), 47));
        samples.add(new SampleImpl("24-12-2017", new Time("12:09"), 20));
        samples.add(new SampleImpl("24-12-2017", new Time("12:10"), 52));
        samples.add(new SampleImpl("24-12-2017", new Time("12:11"), 41));
        samples.add(new SampleImpl("24-12-2017", new Time("12:12"), 64));
        samples.add(new SampleImpl("24-12-2017", new Time("12:13"), 44));
        samples.add(new SampleImpl("24-12-2017", new Time("12:14"), 65));
        samples.add(new SampleImpl("24-12-2017", new Time("12:15"), 45));
        samples.add(new SampleImpl("24-12-2017", new Time("12:16"), 47));
        samples.add(new SampleImpl("24-12-2017", new Time("12:17"), 72));
        samples.add(new SampleImpl("24-12-2017", new Time("12:18"), 59));
        samples.add(new SampleImpl("24-12-2017", new Time("12:19"), 47));
        samples.add(new SampleImpl("24-12-2017", new Time("12:20"), 56));
        samples.add(new SampleImpl("24-12-2017", new Time("12:21"), 71));
        samples.add(new SampleImpl("24-12-2017", new Time("12:21"), 65));
        samples.add(new SampleImpl("24-12-2017", new Time("12:21"), 54));
        samples.add(new SampleImpl("24-12-2017", new Time("12:21"), 65));
    }

    @AfterClass
    public static void tearDownClass() {
        System.out.println("Score: "+score+"%\t(max = 100%)");
    }
}
