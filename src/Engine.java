import java.util.*;
import java.util.regex.*;
import java.io.*;

public class Engine {

    public Engine() {
        this.player = new Player(new Cross());
	this.grid = new Grid();
	this.opponentFigure = new Circle();
    }
    
    public Coordinate findCrucialCoordinateFor(Figure f) {
    
        if (anyWinningPathFor(f)) {return null;}
    
        Coordinate out = null;
    
        for (int i = 0; i < 3; i++) {
	    for (int j = 0; j < 3; j++) {
		
		if (grid.get(new Coordinate(i,j)).isUnset()) {
		
		    grid.get(new Coordinate(i,j)).setFigure(f);
		    
		    if (anyWinningPathFor(f)) {
		        out = new Coordinate(i,j);
		    }
		    
		    grid.get(new Coordinate(i,j)).deleteFigure();
		    
		}
		
		if (out!=null) {return new Coordinate(i,j);}
		
	    }
	}
	
	return null;
    }
    
    public boolean anyWinningPathFor(Figure f) {
        
	boolean out = false;
	
	// Checks orizontal directions ...
	for (int i = 0; i < 3; i++) {
	    out = f.equals(grid.get(new Coordinate(i,0)).getFigure()) &&
	          grid.get(new Coordinate(i,0)).getFigure().equals(grid.get(new Coordinate(i,1)).getFigure()) &&
		  grid.get(new Coordinate(i,1)).getFigure().equals(grid.get(new Coordinate(i,2)).getFigure());
	    
            if (out!=false) {return out;}
	}
	
	// Checks vertical directions ...
	for (int j = 0; j < 3; j++) {
	    out = f.equals(grid.get(new Coordinate(0,j)).getFigure()) &&
	          grid.get(new Coordinate(0,j)).getFigure().equals(grid.get(new Coordinate(1,j)).getFigure()) &&
		  grid.get(new Coordinate(1,j)).getFigure().equals(grid.get(new Coordinate(2,j)).getFigure());
		  
            if (out!=false) {return out;}
	}
	
	// Checks from upper-left corner to lower-right ...
	out = f.equals(grid.get(new Coordinate(0,0)).getFigure()) &&
	      grid.get(new Coordinate(0,0)).getFigure().equals(grid.get(new Coordinate(1,1)).getFigure()) &&
	      grid.get(new Coordinate(1,1)).getFigure().equals(grid.get(new Coordinate(2,2)).getFigure());
	
	if (out!=false) {return out;}
	
	// Checks from lower-left corner to upper-right ...
	out = f.equals(grid.get(new Coordinate(2,0)).getFigure()) &&
	      grid.get(new Coordinate(2,0)).getFigure().equals(grid.get(new Coordinate(1,1)).getFigure()) &&
	      grid.get(new Coordinate(1,1)).getFigure().equals(grid.get(new Coordinate(0,2)).getFigure());
	      
	return out; 
    }
    
    public void playerWins() {
        System.out.println(player + " has won.");
    }
        
    public void playerLoses() {
        System.out.println(this + " has won.");
    }

    public void noOneWins() {
        System.out.println("No one has won.");
    }

    public void play() {
    
        boolean playerGoesFirst = false;
    
        if (Math.random() > 0.5) {
	    playerGoesFirst = true;
	} else {
	    playerGoesFirst = false;
	}
	
	Console c = System.console();
	if (c == null) {
	    System.err.println("No console found.");
	    System.exit(1);
	}
	
	if (playerGoesFirst) {
	
	    System.out.println(player + " goes first.");
	
            for (;!grid.isFull();) {

	        // Ask for input ...
		int rowIndex = 0;
		int colIndex = 0;
		
		for (;;) {

		    System.out.print("Type coordinate of cell "
		    + "you want to set (row[1-3] col[1-3]):\n"
		    + ">>> ");
		    
		    Scanner in = new Scanner(System.in);
		    Scanner lineBuffer = null;
		    
		    if (in.hasNextLine()) {
		        lineBuffer =
			new Scanner(in.nextLine()).useDelimiter("[\\s,;\\-]+");
	            } else {
		        System.err.println("\n<<<**Interpreting keyboard "
			+ "signal as EOF. Bye.**");
			System.exit(1);
		    }
		    
		    try {rowIndex = lineBuffer.nextInt(); colIndex =lineBuffer.nextInt();}
		    catch (NoSuchElementException ex) {
		        System.err.println("<<< No valid input found.");
			continue;
		    }
		    
		    if (rowIndex > 3 || colIndex > 3) {
		        System.err.println("<<< Coordinate "
			+ new Coordinate(rowIndex,colIndex) + " "
			+ "is out of range.");
			continue;
		    }
		    
		    else if (!grid.get(new Coordinate(rowIndex-1,colIndex-1)).isUnset()) {
		        System.err.println("<<< Cell at coordinate "
			+ new Coordinate(rowIndex,colIndex) + " "
			+ "is already set.");
			continue;
		    }
		    
		    else {break;}
		}
		
		// Update table with
		// player's choice ...
		grid.set(new Coordinate(rowIndex-1,colIndex-1), player.getFigure());
		
		/*
		// Print current state of grid ...
		System.out.println(grid);
		*/
		
		if (anyWinningPathFor(player.getFigure())) {playerWins(); return;}
		
		// Find, if any,
		// the Cell that once
		// set will make player
		// win ...
		Coordinate crucialCoordinate = findCrucialCoordinateFor(player.getFigure());
		
		// If so, set the opponent's
		// figure at that cell ...
		if (crucialCoordinate != null) {
		    grid.set(crucialCoordinate, opponentFigure);
		}
		
		// else, find, if any,
		// the Cell that once set
		// will make player
		// lose ...
		else if (
		         (crucialCoordinate=findCrucialCoordinateFor(opponentFigure)) != null
			)
	        {
		    grid.set(crucialCoordinate, opponentFigure);   
		}
		
		// else, set a Cell
		// on the center of
		// the Grid ...
		else if (grid.get(new Coordinate(1,1)).isUnset()) {
		    grid.set(new Coordinate(1,1), opponentFigure);
		}
		
		// else, make a
		// random choice ...
		else {
		    Random r = new Random();
		    for (;;) {
		        Coordinate tmpCoordinate = new Coordinate(r.nextInt(3),r.nextInt(3));
		        if (grid.get(tmpCoordinate).isUnset()) {
			    grid.set(tmpCoordinate, opponentFigure);
			    break;
			}
	            }
		}
		
		// Print current state of grid ...
		System.out.println();
		System.out.println(grid);
		
		if (anyWinningPathFor(opponentFigure)) {playerLoses(); return;}
		
	    }
	    
	} else {
	
	    System.out.println(this + " goes first.");
	    
	    for (;!grid.isFull();) {
	    
	        // Find, if any,
		// the Cell that once
		// set will make player
		// lose ...
		Coordinate crucialCoordinate = findCrucialCoordinateFor(opponentFigure);
		
		// If so, set the opponent
		// figure at that cell ...
		if (crucialCoordinate != null) {
		    grid.set(crucialCoordinate, opponentFigure);
		}
		
		// else, set one of Cells
		// once the corner ...
		else if (grid.get(new Coordinate(0,0)).isUnset()) {
		    grid.set(new Coordinate(0,0), opponentFigure);
		} else if (grid.get(new Coordinate(0,2)).isUnset()) {
		    grid.set(new Coordinate(0,2), opponentFigure);
		} else if (grid.get(new Coordinate(2,0)).isUnset()) {
		   grid.set(new Coordinate(2,0), opponentFigure);
		} else if (grid.get(new Coordinate(2,2)).isUnset()) {
		   grid.set(new Coordinate(2,2), opponentFigure);
		}
		
		// else make a random choice...
	        else {
		    Random r = new Random();
		    for (;;) {
		        Coordinate tmpCoordinate = new Coordinate(r.nextInt(3),r.nextInt(3));
			if (grid.get(tmpCoordinate).isUnset()) {
			    grid.set(tmpCoordinate, opponentFigure);
			    break;
			}
		    }
		}
		
		// Print current state of grid ...
		System.out.println();
		System.out.println(grid);
		
		if (anyWinningPathFor(opponentFigure)) {playerLoses(); return;}
	
                /*
                 * We should cycle of to the
                 * time player provides the
                 * coordinate of a unset Cell.
                 */
	
                // Ask for input ...
		int rowIndex = 0;
		int colIndex = 0;

		for (;;) {

		    System.out.print("Type coordinate of cell "
		    + "you want to set (row[1-3] col[1-3]):\n"
		    + ">>> ");
		    
		    Scanner in = new Scanner(System.in);
		    Scanner lineBuffer = null;
		    
		    if (in.hasNextLine()) {
		        lineBuffer =
			new Scanner(in.nextLine()).useDelimiter("[\\s,;\\-]+");
	            } else {
		        System.err.println("\n<<<**Interpreting keyboard "
			+ "signal as EOF. Bye.**");
			System.exit(1);
		    }
		    
		    try {rowIndex = lineBuffer.nextInt(); colIndex = lineBuffer.nextInt();}
		    catch (NoSuchElementException ex) {
		        System.err.println("<<< No valid input found.");
			continue;
		    }
		    
		    if (rowIndex > 3 || colIndex > 3) {
		        System.err.println("<<< Coordinate "
			+ new Coordinate(rowIndex,colIndex) + " "
			+ "is out of range.");
			continue;
		    }
		    
		    else if (!grid.get(new Coordinate(rowIndex-1,colIndex-1)).isUnset()) {
		        System.err.println("<<< Cell at coordinate "
			+ new Coordinate(rowIndex,colIndex) + " "
			+ "is already set.");
			continue;
		    }
		    
		    else {break;}
		}
		
		// Update table with
		// player's choice ...
		grid.set(new Coordinate(rowIndex-1,colIndex-1), player.getFigure());
		
		/*
		// Print current state of grid ...
		System.out.print(grid + "\r");
		*/
		
		if (anyWinningPathFor(player.getFigure())) {playerWins(); return;}
		
	    }
	    
	}

        // If we reach this point,
        // no one has won ...
        noOneWins();
    
    }

    private Player player;
    private Grid grid;
    private Figure opponentFigure;

}
