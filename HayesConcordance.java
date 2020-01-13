/*  Terrance Hayes Concordance
    Name: Ethan Chen
    Date Started: December 13, 2019
*/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

// Hayes Concordance / Second Model

public class HayesConcordance {

    private SonnetHashTable hayesTable;
    public int totalWords;

    public HayesConcordance() throws IOException {
        totalWords = 0;
        hayesTable = loadHayes();
    }

    public WordRecords findWord(String word) {
        return hayesTable.find(word);
    }

    // loads the text file the same way as commented in HashRunner()
    private SonnetHashTable loadHayes() throws IOException { // loads a AnimalBT from the txt file
        SonnetHashTable shb = new SonnetHashTable(100000);

        FileReader fr = new FileReader("/Users/echen20/IdeaProjects/HashTable/src/TerranceHayesSonnets");
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
                    sNum = s;
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
