/*  Occurrence
    Name: Ethan Chen
    Date Started: January 7, 2020
*/

// class that covers the basics of describing where a word is located
public class Occurrence {

    String sonnetNum;
    int lineNum;

    public Occurrence(String sNum, int lNum) {
        this.sonnetNum = sNum;
        this.lineNum = lNum;
    }

    @Override
    // easier access for printing out the occurrence
    public String toString() {
        return "Sonnet " + sonnetNum + ": Line " + lineNum;
    }

}
