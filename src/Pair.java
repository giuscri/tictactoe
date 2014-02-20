public abstract class Pair<T> {

    public Pair(T left, T right) {
        this.left = left;
	this.right = right;
    }
    
    @Override
    public boolean equals(Object other) {
        if (other != null && other instanceof Pair) {
            return left.equals(((Pair)other).left) &&
	           right.equals(((Pair)other).right);
	} else {
	    return false;
	}
    }
    
    @Override
    public String toString() {
        return "(" + left + "," + right + ")";
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    public final T left;
    public final T right;

}
