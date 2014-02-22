public class Cell {

    public Cell() {
        this.figure = null;
    }
    
    // Return true if this Cell is unset ...
    public boolean isUnset() {
        return figure == null;
    }
    
    // Set, if unset, this cell at Figure f ...
    public boolean setFigure(Figure f) {
        if (isUnset()) {
	    this.figure = f;
	    return true;
	} else {
	    return false;
	}
    }
    
    // Unset this cell ...
    public void deleteFigure() {
        this.figure = null;
    }
    
    // Get the figure set on this Cell ...
    public Figure getFigure() {
        return figure;
    }
    
    // Return a text representation
    // of this Cell ...
    @Override
    public String toString() {
        if (figure != null) {
	    return figure.toString();
	} else {
	    return " ";
	}
    }
    
    private Figure figure;

}

