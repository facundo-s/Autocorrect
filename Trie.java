/**
 * Prefix-Trie. Supports linear time find() and insert(). Should support
 * determining whether a word is a full word in the Trie or a prefix.
 * 
 * @author Facundo Severi
 */

public class Trie {

    private static final int R = 255;

    /**
     * This class was developed with the help from lecture code (CS61B - 2015)
     */
    private class Node {
        // R links
        boolean exists;
        Node[] links;

        /** Node constructor */
        public Node() {
            links = new Node[R];
            exists = false;
        }
    }

    /** Trie backbone */
    private Node root = new Node();

    /**
     * This method adds a node to the given trie
     * 
     * @param x
     *            node you're adding
     * @param key
     *            word you're adding
     * @param d
     *            current position down the word you're adding
     * @return last node
     */
    private Node put(Node x, String key, int d) {
        if (x == null) {
            x = new Node();
        }
        if (d == key.length()) {
            x.exists = true;
            return x;
        }

        char c = key.charAt(d);
        x.links[c] = put(x.links[c], key, d + 1);
        return x;
    }

    /**
     * This function finds the node that represents the last letter of a given
     * string
     * 
     * @param x
     *            node to search in
     * @param s
     *            word you're looking for
     * @return node of the last letter of S
     */
    private Node findLast(Node x, String s) {
        /*
         * Given string S, this method goes down the trie until it runs out of
         * letters to search for, and returns the current node. It returns null
         * if the trie doesn't contain the word
         */

        if ((x == null) || (s == null)) {
            return null;
        }
        if (s.isEmpty()) {
            // if the string is empty or null
            return x;
        }
        if (x.links[s.charAt(0)] != null) {
            return findLast(x.links[s.charAt(0)], s.substring(1));
        } else {
            return null;
        }
    }



    /**
     * Finds the given word of part of word and tells you if it exists
     * 
     * @param s
     *            String to be searched for
     * @param isFullWord
     *            Are you searching for a full word or a prefix?
     * @return True if it exists. False otherwise
     */

    public boolean find(String s, boolean isFullWord) {
        if (!(s == null) && !(s.isEmpty())) {
            Node lastNode = findLast(root, s);
            if (lastNode == null) {
                return false;
            }
            if (isFullWord) {
                return lastNode.exists;
            } else {
                return true;
            }
        } else {
            throw new IllegalArgumentException("You searched for an empty string or a null");
        }
    }

    /**
     * Inserts a word into the trie
     * 
     * @param s
     *            The string to be inserted
     */
    public void insert(String s) {
        if (!(s == null) && !(s.isEmpty())) {
            put(root, s, 0);
        } else {
            throw new IllegalArgumentException("You tried to insert an empty string or a null");
        }
    }

}
