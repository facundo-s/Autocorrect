/** This code was found on this website http://www.sanfoundry.com/java-program-ternary-search-tree/
 ** And extracted for use 4/29/2015
 ** Modified to carry values for weights
 ** Java Program to Implement Ternary Search Tree
 **/

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Stack;

/** class TSTNode **/
class TSTNode implements Comparable<TSTNode> {
    char data;
    double weight;
    double max;
    String word;
    TSTNode left, middle, right;

    /** Constructor **/
    public TSTNode(char data) {
        this.data = data;
        this.weight = 0;
        this.max = 0;
        this.word = null;

        this.left = null;
        this.middle = null;
        this.right = null;
    }

    public int compareTo(TSTNode c) {
        double difference = this.max - c.max;
        if (difference < 0) {
            return 1;
        } else if (difference > 0) {
            return -1;
        } else {
            return 0;
        }
    }

}

/** class TernarySearchTree **/
class TernarySearchTree {
    private TSTNode root;
    private ArrayList<String> al; // for root transversing and printing

    /** Constructor **/
    public TernarySearchTree() {
        root = null;
    }

    /** function to check if empty **/
    public boolean isEmpty() {
        return root == null;
    }

    /** function to clear **/
    public void makeEmpty() {
        root = null;
    }

    /** function to insert for a word **/
    public void insert(String word, double ww) {
        root = insert(root, word.toCharArray(), 0, ww);
    }

    /** function to insert for a word **/
    public TSTNode insert(TSTNode r, char[] word, int ptr, double ww) {
        // ptr is the index in the char array that represents the word
        if (r == null) {
            r = new TSTNode(word[ptr]);
        }

        if (word[ptr] < r.data) {
            if (r.max < ww) {
                r.max = ww;
            }
            r.left = insert(r.left, word, ptr, ww);
        } else if (word[ptr] > r.data) {
            if (r.max < ww) {
                r.max = ww;
            }
            r.right = insert(r.right, word, ptr, ww);
        } else {
            if (ptr + 1 < word.length) {
                if (r.max < ww) {
                    r.max = ww;
                }
                r.middle = insert(r.middle, word, ptr + 1, ww);
            } else {
                if (r.max < ww) {
                    r.max = ww;
                }
                r.weight = ww;
                if (r.word != null) {
                    throw new IllegalArgumentException("You tried inserting a duplicate word");
                }
                r.word = new String(word);
            }
        }
        return r;
    }

    /** function to retrieve a single word's weight **/
    public double wordWeight(String word) {
        return search(word).weight;
    }

    /** function to retrieve k best matches in full tree **/
    public LinkedList<String> topKMatches(String prefix, int k) {
        TSTNode branchingPoint = search(prefix);
        if (branchingPoint == null) {
            return null;
        }
        PriorityQueue<TSTNode> matches;
        if (prefix.isEmpty()) {
            matches = topMatches(branchingPoint, k, true);
        } else {
            matches = topMatches(branchingPoint, k, false);
        }

        Stack<String> words = new Stack<String>();
        while (!matches.isEmpty()) {
            words.push(matches.remove().word);
        }
        LinkedList<String> rv = new LinkedList<String>();
        while (!words.isEmpty()) {
            rv.add(words.pop());
        }
        return rv;
    }

    /** function to retrieve k best matches starting at specific node **/
    private PriorityQueue<TSTNode> topMatches(TSTNode r, int k, boolean searchAll) {
        PriorityQueue<TSTNode> toCheck = new PriorityQueue<TSTNode>();
        PriorityQueue<TSTNode> matchNodes = new PriorityQueue<TSTNode>(k, new tstComparator());

        if (searchAll) {
            toCheck.add(root);
        } else {
            if (r.word != null) {
                k -= 1;
                matchNodes.add(r);
            }
            if (r.middle != null) {
                toCheck.add(r.middle);
            } else {
                return matchNodes;
            }
        }

        while (!toCheck.isEmpty()) {

            TSTNode current = toCheck.remove();
            addChildren(current, toCheck);

            if ((k == 0) && (!matchNodes.isEmpty()) && (current.max > matchNodes.peek().weight)) {
                k += 1;
                matchNodes.remove();
            }

            if ((current.word != null) && (k > 0)) {
                k -= 1;
                matchNodes.add(current);
                continue;
            }
            if ((k == 0) && (!matchNodes.isEmpty()) && (current.max < matchNodes.peek().weight)) {
                return matchNodes;
            }

        }
        return matchNodes;
    }

    /** adds the children of a given TSTNode into the PQ **/
    private static void addChildren(TSTNode n, PriorityQueue<TSTNode> toAdd) {
        if (n.left != null) {
            toAdd.add(n.left);
        }
        if (n.right != null) {
            toAdd.add(n.right);
        }
        if (n.middle != null) {
            toAdd.add(n.middle);
        }
    }

    /** function to search for a word **/
    private TSTNode search(String word) {
        if (word.isEmpty()) {
            return root;
        }
        return search(root, word.toCharArray(), 0);
    }

    /** function to search for a word **/
    private TSTNode search(TSTNode r, char[] word, int ptr) {
        if ((r == null) || (word.length == ptr)) {
            return null;
        }

        if (word[ptr] < r.data) {
            return search(r.left, word, ptr);
        } else if (word[ptr] > r.data) {
            return search(r.right, word, ptr);
        } else {
            if ((r.word != null) && (ptr == word.length - 1)) {
                return r;
            } else if (ptr == word.length - 1) {
                return r;
            } else {
                return search(r.middle, word, ptr + 1);
            }
        }
    }

    /** function to print tree **/
    public String toString() {
        al = new ArrayList<String>();
        traverse(root, "");
        return "\nTernary Search Tree : " + al;
    }

    /** function to traverse tree **/
    private void traverse(TSTNode r, String str) {
        if (r != null) {
            traverse(r.left, str);

            str = str + r.data;
            if (r.word != null)
                al.add(str);

            traverse(r.middle, str);
            str = str.substring(0, str.length() - 1);

            traverse(r.right, str);
        }
    }

}
