import java.util.Scanner;

/**
 * Sorts words in alphabetical order
 * 
 * @author Facundo Severi
 */

public class AlphabetSort {

    /**
     * This is a javadoc because I don't know where else to put the javadoc that
     * the autograder is asking me for
     */

    static ATrie words;
    String alphabet;
    int numWords;

    /**
     * A dictionary that stores words in alphabetical order given an alphabet
     * 
     * @param abc
     *            Alphabet to sort everything with
     */
    public AlphabetSort(String abc) {
        words = new ATrie(abc);
        alphabet = abc;
        numWords = 0;
    }

    /**
     * add into the dictionary to be considered into alphabetization
     * 
     * @param word
     *            Word to be added
     */
    public void add(String word) {
        words.insert(word);
        numWords += 1;
    }

    /**
     * This function prints out all the words in the dictionary
     */
    public void printAll() {
        words.sortedTrie(alphabet);
    }

    /**
     * Reads in the alphabet, and the words to be added to the dictionary. It
     * adds them and then prints all of them out in order
     * 
     * @param args
     *            The dictionary, and all the words
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String alphabet = sc.nextLine();
        AlphabetSort myA = new AlphabetSort(alphabet);
        while (sc.hasNext()) {
            myA.add(sc.nextLine());
        }
        if (myA.numWords < 1) {
            throw new IllegalArgumentException("No words were added for sorting");
        }
        myA.printAll();

    }

}
