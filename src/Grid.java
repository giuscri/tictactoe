import java.util.*;

public class Grid extends HashMap<Coordinate,Cell> implements Iterable<Cell> {

    public Grid() {
    
        super();
	
        for (int i = 0; i < 3; i++) {
	    for (int j = 0; j < 3; j++) {
	        this.put(new Coordinate(i,j), new Cell());
	    }
	}
    }
    
    public boolean set(Coordinate c, Figure f) {
        return this.get(c).setFigure(f);
    }
    
    public boolean isFull() {
        int counter = 0;
	for (Cell c : this) {
	    if (c.isUnset()) {
	        counter++;
	    }
	}
	
	return counter <= 2;
    }


    @Override
    public Iterator<Cell> iterator() {
    
        final Grid tmp = this;
    
        return new Iterator<Cell>() {
	    private int i = 0;
	    private int j = 0;
	
            @Override
	    public boolean hasNext() {
	        if (i < 3 && j < 3) {
		    return true;
		} else {
		    return false;
		}
	    }
	
	    @Override
	    public Cell next() {
	        Cell out = tmp.get(new Coordinate(i,j++));
		
		if (j >= 3) {
		    j = 0;
		    i++;
		}
		
		return out;
	    }
	
	    @Override
	    public void remove() {}
	};
    
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
	
	for (int i = 0; i < 3; i++) {
	    for (int j = 0; j< 3; j++) {
	        sb.append(" " + get(new Coordinate(i,j)) + " ");
		if (j < 2) {
		    sb.append("|");
		}
	    }
	    sb.append("\n");
	    if (i < 2) {
	        for (int counter = 0; counter++ < 3*3 +2; sb.append("-"));
	    }
	    sb.append("\n");
	}
	
	return sb.toString();
    }
 
}
