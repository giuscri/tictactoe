import java.util.*;
import java.util.regex.*;
import java.io.*;

public class Engine {

    public Engine() {
        this.player = new Player(new Cross());
	this.grid = new Grid();
	this.opponentFigure = new Circle();
    }
    
    public Grid getGrid() {
        return this.grid;
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
		
		if (out!=null) {
		    return new Coordinate(i,j);
		}
		
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
	
	// DEBUG
	System.out.println("DEBUG: playerGoesFirst: " + playerGoesFirst);
	
	if (playerGoesFirst) {
	
            for (;!grid.isFull();) {
	    
	        // Ask for input ...
		int rowIndex = 0;
		int colIndex = 0;
		Pattern p = Pattern.compile("([1-3]{1})(\\D+)([1-3]{1})");
		Matcher m = p.matcher(c.readLine("<row_index>, <col_index>: "));
		for (;m.find();) {
		    // Parse input ...
		    rowIndex = Integer.parseInt(m.group(1));
		    colIndex = Integer.parseInt(m.group(3));
		}
		
		// DEBUG:
		System.out.println("DEBUG: rowIndex, " + rowIndex);
		System.out.println("DEBUG: colIndex, " + colIndex);
		
		// Update table with
		// player's choice ...
		grid.set(new Coordinate(rowIndex-1,colIndex-1), player.getFigure());
		
		// Print current state of grid ...
		System.out.println(grid);
		
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
		System.out.println(grid);
		
		if (anyWinningPathFor(opponentFigure)) {playerLoses(); return;}
		
		
	    }
	    
	} else {
	    
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
		System.out.println(grid);
		
		if (anyWinningPathFor(opponentFigure)) {playerLoses(); return;}
		
                // Ask for input ...
		int rowIndex = 0;
		int colIndex = 0;
		Pattern p = Pattern.compile("([1-3]{1})(\\D+)([1-3]{1})");
		Matcher m = p.matcher(c.readLine("<row_index>, <col_index>: "));
		for (;m.find();) {
		    // Parse input ...
		    rowIndex = Integer.parseInt(m.group(1));
		    colIndex = Integer.parseInt(m.group(3));
		}
		
		// DEBUG
		System.out.println("DEBUG: rowIndex, " + rowIndex);
		System.out.println("DEBUG: colIndex, " + colIndex);
		
		// Update table with
		// player's choice ...
		grid.set(new Coordinate(rowIndex-1,colIndex-1), player.getFigure());
		
		// Print current state of grid ...
		System.out.println(grid);
		
		if (anyWinningPathFor(player.getFigure())) {playerWins(); return;}
		
	    }
	    
	}
    
    }

    private Player player;
    private Grid grid;
    private Figure opponentFigure;

}
