/*  HashMethodAnalyzer
    Name: Ethan Chen
    Date Started: January 8, 2020
*/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class AnalyzeHashMethods {

    // Test Client, mostly used to gather data for analysis
    public static void main(String[] args) throws IOException {
        ArrayList<String> dictionary = new ArrayList<String>();

        BufferedReader buf = new BufferedReader(new FileReader("/Users/echen20/IdeaProjects/HashTable/src/Dict.txt")); // change file path to match your computer
        String line = buf.readLine();
        while(line != null) {
            dictionary.add(line);
            line = buf.readLine();
        }

        StdOut.println("What method would you like to use? 0 : FirstIndex, 1 : First Letter, 2 : All Letters Added, 3 : Number Letters, 4 : My Hash");
        int method = StdIn.readInt();
        StdOut.println("How long would you like your list to be? (Choose a number greater than 121806)");
        int length = StdIn.readInt();

        StdOut.println(testMethod(dictionary, method, length));

    }

    // prints out the average, best, and worst block sizes (as described on paper)
    public static String testMethod(ArrayList<String> dictionary, int method, int length) {

        GenericHashTable hashTable = new GenericHashTable(length, method); // loads words from arraylist to hash table
        for(String word : dictionary) {
            hashTable.add(word);
        }

        int bestCase = hashTable.getBestCaseBlockSize();
        int worstCase = hashTable.getWorstCaseBlockSize();
        double averageCase = hashTable.getAverageBlockSize();

        return " Best Case: " + bestCase + "" + "\n Worst Case: " + worstCase + "\n Average Case: " + averageCase;
    }

}
