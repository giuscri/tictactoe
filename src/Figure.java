public abstract class Figure {

    public Figure(String value) {
        this.value = value;
    }
    
    @Override
    public boolean equals(Object other) {
	if (other != null && other instanceof Figure) {
	    return value.equals(((Figure)other).getValue());
	} else {
	    return false;
	}
    }
    
    public String getValue() {
        return value;
    }
    
    @Override
    public String toString() {
        return value;
    }

    private String value;

}
