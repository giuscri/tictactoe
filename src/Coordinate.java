public class Coordinate extends Pair<Integer> implements Comparable<Coordinate> {

    public Coordinate(Integer x, Integer y) {
        super(x,y);
    }
    
    @Override
    public int compareTo(Coordinate other) {
    
        if (this.equals(other)) {
	    return 0;
	} else if (this.left > other.left) {
	    return 1;
	} else if (this.left < other.left) {
	    return -1;
	} else if (this.right > other.right) {
	    return 1;
	} else {
	    return -1;
	}
    
    }

}
