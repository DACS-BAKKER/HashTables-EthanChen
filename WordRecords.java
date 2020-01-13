/*  WordRecords
    Name: Ethan Chen
    Date Started: December 14, 2020
*/

import java.util.ArrayList;

// used for Sonnet, has a word and a list of its occurrences in sonnets
public class WordRecords {
    String value; // word value
    ArrayList<Occurrence> occurrences; // where it occurs

    public WordRecords(String value) {
        this.value = value;
        this.occurrences = new ArrayList<Occurrence>();
    }


    public void addOccurence(Occurrence occ) {
        occurrences.add(occ);
    }

}
