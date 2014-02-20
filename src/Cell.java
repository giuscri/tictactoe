public class Cell {

    public Cell() {
        this.figure = null;
    }
    
    public boolean isUnset() {
        return figure == null;
    }
    
    public boolean setFigure(Figure f) {
        if (isUnset()) {
	    this.figure = f;
	    return true;
	} else {
	    return false;
	}
    }
    
    public void deleteFigure() {
        this.figure = null;
    }
    
    public Figure getFigure() {
        return figure;
    }
    
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
