public class Player {

    public Player(Figure f) {
        this.figure = f;
    }

    // Get the Figure owned by this Player ...    
    public Figure getFigure() {
        return figure;
    }
    
    private Figure figure;

}

