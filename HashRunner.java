/*  HashTester Runner
    Name: Ethan Chen
    Date Started: December 14, 2019
*/

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

public class HashRunner {

    // Test Client
    public static void main(String[] args) throws IOException, InterruptedException { //WORK ON THIS MORE
        SonnetHashTable sh = loadShakespeare();
        boolean searchAgain = true;

        while (searchAgain) {
            StdOut.println("What word would you like to search for?");
            String word = StdIn.readString();
            StdOut.println(sh.find(word.toUpperCase()).value);
            for (Occurrence x : sh.find(word.toUpperCase()).occurrences) {
                StdOut.println(x);
            }

            StdOut.println();
            StdOut.println("Would you like to try another word? 0: Yes, 1: No");
            int again = StdIn.readInt();
            if(again == 1) {
                searchAgain = false;
            }

        }
    }


    // loads the shakespearean sonnets from the given format and puts it into the hash table
    public static SonnetHashTable loadShakespeare() throws IOException {
        SonnetHashTable shb = new SonnetHashTable(100000); // arbitrary size

        FileReader fr = new FileReader("/Users/echen20/IdeaProjects/HashTable/src/sonnets.txt");
        BufferedReader buf = new BufferedReader(fr);

        boolean space = true;
        boolean alternator = true;
        boolean end = false;
        String sNum = ""; // sonnet number
        int lineNum = 0; // line number

        String s = ""; // carries text from each line from text file
        while (!end) {
            s = buf.readLine();
            if (s == null) { // end of text file
                end = true;
            } else if (s.equals("") && !space) { // space after sonnet ends before next sonnet number
                space = true;
                alternator = !alternator;
            } else if (space && alternator) { // if last was space after sonnet ended, next line is sonnet number
                if (!s.equals("")) {
                    space = false;
                    sNum = s;
                    sNum = sNum.substring(2); // cuts off the first two blank spaces
                    lineNum = 0;
                }

            } else {
                lineNum++; // increases the line
                space = false;
                StringTokenizer tok = new StringTokenizer(s, ",.;?!: "); // breaks down the line
                while (tok.hasMoreTokens()) {
                    String word = tok.nextToken();
                    word = word.toUpperCase();
                    WordRecords existingWord = shb.find(word);
                    if (!existingWord.value.equals("DNE")) { // checking if it already exists
                        existingWord.addOccurence(new Occurrence(sNum, lineNum)); // if it does, add a new occurrence
                    } else {
                        WordRecords newWord = new WordRecords(word); // otherwise add it to table
                        newWord.addOccurence(new Occurrence(sNum, lineNum));
                        shb.add(newWord);
                    }
                }
            }
        }

        return shb;
    }

}
