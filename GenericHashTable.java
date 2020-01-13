/*  StringHashTable API
    Name: Ethan Chen
    Date Started: December 13, 2019
*/

import edu.princeton.cs.algs4.StdOut;

public class GenericHashTable<Item> {

    // Variables

    private String[] list;
    private int listLength;
    private int hashMethod;

    // Constructor

    public GenericHashTable(int listLength, int hashMethod) {
        list = new String[listLength];
        this.listLength = listLength;
        this.hashMethod = hashMethod;
    }

    // Methods

    // adds a word to the hash table at the location given by the selected hash method
    public void add(String word) {
        boolean added = false;
        int hashedString;

        // chooses hash method
        if (hashMethod == 0) {
            hashedString = hashByFirstIndex(word);
        } else if (hashMethod == 1) {
            hashedString = hashByFirstLetter(word);
        } else if (hashMethod == 2) {
            hashedString = hashByAllLetters(word);
        } else if (hashMethod == 3) {
            hashedString = hashByNumberLetters(word);
        } else {
            hashedString = ethanHash(word);
        }

        int originalHashedString = hashedString;
        while (!added) {
            if (hashedString == list.length) { // end of list, circles to beginning
                hashedString = 0;
            }
            if (list[hashedString] == null) { // found null spot to add
                list[hashedString] = word;
                added = true;
            }
            if (originalHashedString == hashedString) { // case where the entire list is filled, doesn't add and returns
                return;
            }
            hashedString++; // increases index while looking for null location
        }
    }

    // finds the given word and returns the object in the hash table (makes more sense in the context of the sonnets)
    public String find(String word) {
        int hashedString;


        // which hash method
        if (hashMethod == 0) {
            hashedString = hashByFirstIndex(word);
        } else if (hashMethod == 1) {
            hashedString = hashByFirstLetter(word);
        } else if (hashMethod == 2) {
            hashedString = hashByAllLetters(word);
        } else if (hashMethod == 3) {
            hashedString = hashByNumberLetters(word);
        } else {
            hashedString = ethanHash(word);
        }

        int originalHashedString = hashedString-1;

        while (list[hashedString] != null && originalHashedString != hashedString) { // if it reaches a null the word is not in the list
            if (list[hashedString].equals(word)) { // if equal statement, return that object
                return list[hashedString];
            }
            hashedString++; // traverse
            if (hashedString == list.length) { // circle
                hashedString = 0;
            }
        }

        return "DNE";
    }

    // Different hash algorithms

    // puts at first index, least efficient
    private int hashByFirstIndex(String word) {
        return 0;
    }

    // breaks into 26 blocks, at each is each word with each starting letter e.g. a, b, c, d...
    private int hashByFirstLetter(String word) {
        word = word.toUpperCase();
        char firstLetter = word.charAt(0); // first letter
        int hashLetter = firstLetter;
        hashLetter = (hashLetter - 65) * (listLength / 26);
        if (hashLetter < 0) {
            hashLetter = -hashLetter; // for cases with special first characters e.g. 'tis
        }
        if (hashLetter > listLength) {
            hashLetter = hashLetter % listLength; // for cases with special first characters e.g. 'tis
        }
        return hashLetter;
    }

    // adds up ascii value of all the words and then multiplies by a constant to more evenly spread
    private int hashByAllLetters(String word) {
        int hashLetter = 0;
        for (char x : word.toCharArray()) {
            hashLetter += x * (listLength / 500);
        }
        return hashLetter % listLength;
    }

    // same hash value for all words with 1 letter, same value for all with 3 letters, e.g.  hello and happy have same hash value
    private int hashByNumberLetters(String word) {
        int hashLetter = ((listLength / 10) * word.length()) % listLength;
        return hashLetter;
    }

    // better explained on paper
    private int ethanHash(String word) {
        int hashLetter = 1;
        for (char x : word.toCharArray()) {
            hashLetter *= x; // multiply
            if(hashLetter > listLength) {
                hashLetter = hashLetter % listLength; // mod if too large
            }
        }
        hashLetter += word.length(); // add length of word
        if(hashLetter > listLength) {
            hashLetter = hashLetter % listLength; // mod if too large
        }
        return hashLetter;
    }

    // efficiencyFinderMethods, current unknown issues with these methods

    // finds the average block size across entire hash table (items in a row = a block)
    public double getAverageBlockSize() {
        double totalCounts = 0;
        double totalBlocks = 0;
        double currentCount = 0;
        boolean lastNull = true;
        boolean firstSet = true;
        double firstCount = 0;

        for (String l : list) {
            if (l == null) {
                if (!lastNull) { // checking to see if in string of nulls, only want to add to totalsets once for each row of nulls
                    if(firstSet) { // important for the wrap-around, not yet added to totalcount
                        firstCount = currentCount;
                        currentCount = 0;
                        lastNull = true;
                        firstSet = false;
                    } else {
                        totalCounts += currentCount; // total number of items
                        totalBlocks++; // number of blocks to cover all items (the larger the better
                        currentCount = 0;
                        lastNull = true;
                    }
                }
            } else { // there is an item there, starts counting length of block
                lastNull = false;
                currentCount += 1;
            }
        }

        if(list[list.length-1] != null) { // wraparound, only need to add 1 block
            totalCounts = totalCounts + firstCount + currentCount;
            totalBlocks++;
        } else {
            totalCounts = totalCounts + firstCount + currentCount; // no wraparound, need to account for block at beginning and end
            totalBlocks += 2;
        }

        return totalCounts / totalBlocks;
    }

    // worst case block sizes
    public int getWorstCaseBlockSize() {
        int worstCase = -1;
        int currentCount = 0;
        boolean lastNull = true;
        boolean firstSet = true;
        int firstCount = 0;

        for (String l : list) { // same as above, except no cumulative total
            if (l == null) {
                if(!lastNull) {
                    if(firstSet) {
                        firstCount = currentCount;
                        currentCount = 0;
                        lastNull = true;
                        firstSet = false;
                    } else {
                        if (worstCase == -1) {
                            worstCase = currentCount;
                        } else if (currentCount > worstCase) { // checks to see if block size worse
                            worstCase = currentCount;
                        }
                        currentCount = 0;
                        lastNull = true;
                    }
                }
            } else {
                lastNull = false;
                currentCount++;
            }
        }

        if(list[list.length-1] != null) {
            firstCount += currentCount;
        }
        if(firstCount > worstCase) { // checks for first/last block as well
            worstCase = firstCount;
        }
        if(currentCount > worstCase) {
            worstCase = currentCount;
        }

        return worstCase;
    }

    // best case block sizes
    public int getBestCaseBlockSize() {

        int bestCase = -1;
        int currentCount = 0;
        boolean lastNull = true;
        boolean firstSet = true;
        int firstCount = 0;

        for (String l : list) {
            if (l == null) {
                if(!lastNull) {
                    if(firstSet) {
                        firstCount = currentCount;
                        currentCount = 0;
                        lastNull = true;
                        firstSet = false;
                    } else {
                        if (bestCase == -1) {
                            bestCase = currentCount;
                        } else if (currentCount < bestCase) {
                            bestCase = currentCount;
                        }
                        currentCount = 0;
                        lastNull = true;
                    }
                }
            } else {
                lastNull = false;
                currentCount++;
            }
        }

        if(list[list.length-1] != null) {
            firstCount += currentCount;
        }

        if(firstCount < bestCase || bestCase == -1) { // checks for first/last block as well
            bestCase = firstCount;
        }

        return bestCase;

    }

    // toString

    @Override
    // lists out the items in the string in order of the hash, index and then item, skipping over big spaces
    public String toString() {
        String cumString = "";
        for (int i = 0; i < list.length; i++) {
            if (list[i] != null) {
                if (!cumString.equals("")) {
                    cumString += " => ";
                }
                cumString += (i + ":" + list[i]);
            }
        }
        return cumString;
    }


}
