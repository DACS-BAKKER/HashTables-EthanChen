/*  ShakespeareConcordance
    Name: Ethan Chen
    Date Started: January 7, 2020
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

// Shakespeare Concordance / Model
public class ShakespeareConcordance {

    private SonnetHashTable shakespeareTable;
    public int totalWords;

    public ShakespeareConcordance() throws IOException {
        totalWords = 0;
        shakespeareTable = loadShakespeare();
    }

    public WordRecords findWord(String word) {
        return shakespeareTable.find(word);
    }

    //loads the text file in same way as commented in hashrunner
    private SonnetHashTable loadShakespeare() throws IOException { // loads a AnimalBT from the txt file
        SonnetHashTable shb = new SonnetHashTable(100000);

        FileReader fr = new FileReader("/Users/echen20/IdeaProjects/HashTable/src/sonnets.txt");
        BufferedReader buf = new BufferedReader(fr);

        boolean space = true;
        boolean alternator = true;
        boolean end = false;
        String sNum = "";
        int lineNum = 0;

        String s = "";
        while (!end) {
            s = buf.readLine();
            if (s == null) {
                end = true;
            } else if (s.equals("") && !space) {
                space = true;
                alternator = !alternator;
            } else if (space && alternator) {
                if (!s.equals("")) {
                    space = false;
                    sNum = s.substring(2);
                    lineNum = 0;
                }

            } else {
                lineNum++;
                space = false;
                StringTokenizer tok = new StringTokenizer(s, ",.;?!:() ");
                while (tok.hasMoreTokens()) {
                    String word = tok.nextToken();
                    word = word.toUpperCase();
                    WordRecords existingWord = shb.find(word);
                    if (!existingWord.value.equals("DNE")) {
                        existingWord.addOccurence(new Occurrence(sNum, lineNum));
                        totalWords++;
                    } else {
                        WordRecords newWord = new WordRecords(word);
                        newWord.addOccurence(new Occurrence(sNum, lineNum));
                        shb.add(newWord);
                        totalWords++;
                    }
                }
            }
        }

        return shb;
    }

}
