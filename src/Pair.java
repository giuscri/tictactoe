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

    // Return a text representation of this matrix ...    
    @Override
    public String toString() {
        return "(" + left + "," + right + ")";
    }

    // Return a unique hashcode for
    // this object ...
    @Override
    public int hashCode() {
        return toString().hashCode();
    }

    public final T left;
    public final T right;

}

