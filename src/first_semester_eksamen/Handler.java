package first_semester_eksamen;

import impl.Time;
import java.io.IOException;
import java.util.ArrayList;

public interface Handler {
    /**
     * Convert a file to a String
     * @param filename The name of the file to be read
     * @return The file content as a String
     * @throws IOException if file is missing or locked
     */
    public String readFile(String filename) throws IOException;
    
    /**
     * Converts file content to proper objects
     * @param data The file content as a String
     * @return a list of Sample objects
     * @throws TimeFormatException if time is formated badly
     */
    public ArrayList<Sample> getSamples(String data) throws TimeFormatException;
    
    /**
     * Identifies the Sample with the highest amplitude. Peaks are ignored.
     * @param samples The list of Samples based on file content
     * @return the identified Sample object
     */
    public Sample getHighestAmplitude(ArrayList<Sample> samples);
    
    /**
     * Identifies the sample which have increased the most in amplitude 
     * compared to its predecessor. 
     * The compared values are signed - It is NOT the numerical difference 
     * that counts.
     * @param samples The list of Samples based on file content - At least 
     * 2 values are required
     * @return the identified Sample object
     */
    public Sample getBiggestRise(ArrayList<Sample> samples);
    
    /**
     * Determines whether or not any sample surpasses the set limit. 
     * Peaks matter
     * @param limit The highest allowed value
     * @param samples The list of Samples based on file content
     * @return true if any amplitude or peak is greater than the limit,
     * false in any other case.
     */
    public boolean isTooLoud(int limit, ArrayList<Sample> samples);
    
    /**
     * Sorts samples by time
     * @param samples The list that will be sorted
     */
    public void sortByTime(ArrayList<Sample> samples);
    
    /**
     * sorts samples by amplitude (ignore peak)
     * @param samples The list that will be sorted
     */
    public void sortByAmplitude(ArrayList<Sample> samples);
    
    /**
     * Retrieves a subset containing only the samples that have an amplitude 
     * higher than or equal to the limit.
     * @param limit The maximum allowed amplitude
     * @param samples The list from which to get the subset
     * @return a new list containing the subset
     */
    public ArrayList<Sample> getLoudSamples(int limit, ArrayList<Sample> samples);
    
    /**
     * Retrieves a subset containing only the samples that have a timestamp 
     * earlier than the limit. (NOT equal to the limit!)
     * @param limit The time limit
     * @param samples The list from which to get the subset
     * @return a new list containing the subset
     */
    public ArrayList<Sample> getSamplesBefore(Time limit, ArrayList<Sample> samples);    
}
