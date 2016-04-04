/**
 * Alphabet-Trie. It supports the functionality of a Trie, but it also 
 * has functionality to look up words based on weights 
 * @author Facundo Severi
 */

import java.util.ArrayList;
import java.util.TreeMap;

public class ATrie {

    /**
     * For the accurate implementation of a trie, Node class was defined
     */
    private class Node {
        // R links
        boolean key;
        TreeMap<Character, Node> links;
        ArrayList<Character> alphabet;

        /** constructor */
        public Node(ArrayList<Character> abc) {
            this.alphabet = abc;
            this.links = new TreeMap<Character, Node>(new alphabetComparator(alphabet));
            this.key = false;
        }
    }

    private Node root;
    private ArrayList<Character> abc;

    public ATrie(String alphabet) {
        abc = new ArrayList<Character>();
        for (char letter : alphabet.toCharArray()) {
            if (abc.contains(letter)) {
                throw new IllegalArgumentException("There's a repeated letter in your alphabet");
            }
            abc.add(letter);
        }
        root = new Node(abc);
    }

    /**
     * Insert into the node
     * 
     * @param x
     *            node to insert key into
     * @param key
     *            word to be added
     * @param d
     *            position in the word
     */
    private Node put(Node x, String key, int d) {
        if (x == null) {
            x = new Node(abc);
        }
        if (d == key.length()) {
            x.key = true;
            return x;
        }
        char c = key.charAt(d);
        if (!abc.contains(c)) {
            return x;
        }
        x.links.put(c, put(x.links.get(c), key, d + 1));
        // if (x.links.get(c) != null) {
        // put(x.links.get(c), key, d + 1);
        // } else {
        // Node newNode = new Node();
        // x.links.put(c, newNode);
        // put(newNode, key, d + 1);
        // }
        return x;
    }

    /**
     * sorts and returns a list of ordered terms
     * 
     * @param alphabet
     *            order of letters
     * @return list of sorted words
     */
    public void sortedTrie(String alphabet) {
        sort(root, "");
    }

    /**
     * sorts a given trie
     * 
     * @param n
     *            node to sort
     * @param alphabet
     *            alphabet used to sort
     * @return list of ordered items
     */
    private void sort(Node n, String accumulation) {
        String current = accumulation;
        for (Character letter : n.links.keySet()) {
            // the string gets accumulated each iteration
            accumulation = current;
            accumulation += letter.toString();
            if (n.links.get(letter).key) {
                System.out.println(accumulation);
            }

            // sort the remainer of the list
            sort(n.links.get(letter), accumulation);

        }
    }

    /**
     * inserts into the trie
     * 
     * @param s
     *            word to insert into the trie
     */
    public void insert(String s) {
        if (!(s == null) && !(s.isEmpty())) {
            put(root, s, 0);
        } else {
            throw new IllegalArgumentException("You tried to insert an empty string or a null");
        }
    }
}
