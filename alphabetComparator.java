import java.util.ArrayList;
import java.util.Comparator;

public class alphabetComparator implements Comparator<Character> {
    private ArrayList<Character> alphabet;

    public alphabetComparator(ArrayList<Character> abc) {
        alphabet = abc;
    }

    @Override
    public int compare(Character me, Character you) {
        return alphabet.indexOf(me) - alphabet.indexOf(you);
    }
}
