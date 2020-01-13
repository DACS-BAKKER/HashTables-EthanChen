/*  HashTable API
    Name: Ethan Chen
    Date Started: December 13, 2019
*/

// Hash Table File specific for the Shakespearean Sonnet Concordance Project
public class SonnetHashTable {

    // Variables

    WordRecords[] listOfWordOccurrences; // used in place of array of strings
    int listLength;

    // Constructor

    public SonnetHashTable(int listLength) {
        listOfWordOccurrences = new WordRecords[listLength];
        this.listLength = listLength;
    }

    // Methods

    // adds a WordRecords to hash table
    public void add(WordRecords word) {
        boolean added = false;
        int hashedString = hashByFirstLetter(word.value);
        int originalHashedString = hashedString-1;

        while (!added) {
            if (hashedString == listOfWordOccurrences.length) { // end of list, circles to beginning
                hashedString = 0;
            }
            if(originalHashedString == hashedString) { // list is full
                return;
            }
            if (listOfWordOccurrences[hashedString] == null) { // finds empty spot
                listOfWordOccurrences[hashedString] = word;
                added = true;
            }
            hashedString++; // traversal
        }
    }

    // finds a WordRecords with value word in hashTable
    public WordRecords find(String word) {
        int hashedString = hashByFirstLetter(word);
        int originalHashedString = hashedString-1;

        while (listOfWordOccurrences[hashedString] != null && originalHashedString != hashedString) { // gives up when it reaches a null or comes full circle
            if (listOfWordOccurrences[hashedString].value.equals(word)) { // checks if found word
                return listOfWordOccurrences[hashedString];
            }
            hashedString++;
            if (hashedString == listOfWordOccurrences.length) {
                hashedString = 0;
            }
        }

        return new WordRecords("DNE");
    }

    // Hash algorithm

    // I chose this algorithm, even though it isn't the most efficient, just because it is faster at creating the hash and
    // putting the sonnets in the hash compared to my hash method, which is much faster getting words out
    private int hashByFirstLetter(String word) {
        word = word.toUpperCase();
        char firstLetter = word.charAt(0);
        int hashLetter = firstLetter;
        hashLetter = (hashLetter - 65) * (listLength / 26);
        if(hashLetter < 0) {
            hashLetter = - hashLetter; // in case of special characters
        } if(hashLetter > listLength) {
            hashLetter = hashLetter%listLength; // in case of special characters
        }
        return hashLetter;
    }


}
