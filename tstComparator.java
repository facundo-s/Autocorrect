import java.util.Comparator;

public class tstComparator implements Comparator<TSTNode> {
    /**
     * This function takes in two TSTNodes and compares them For implementation
     * of a PQ based on weights
     */
    public int compare(TSTNode me, TSTNode n) {
        double difference = me.weight - n.weight;
        if (difference < 0) {
            return -1;
        } else if (difference > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
