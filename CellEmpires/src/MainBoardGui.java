import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.JPanel;

public class MainBoardGui  extends JPanel {

    public Random rand = new Random();

	

	final int windowWidth = Main.windowWidth;
	final int windowHeight = Main.windowHeight;
	
	//	final int mapXSize = 200;
	//	final int mapYSize = 300;
	
	
    public int xOffset = 0;
    public int yOffset = 0;
    
    int mapWidth = 2000;
    int mapHeight = 1500;
    
    public static int mouseX = 0;
    public static int mouseY = 0;
    
    int tileSize = 50;
    
    int tileTextSpacer = tileSize/5;
    
    int startingCivs = 40;
    int startingPop = 30;
    
    int boardTileWidth = mapWidth/tileSize;
    int boardTileHeight = mapHeight/tileSize;
    
    
    
    Tile[][] boardList = new Tile[boardTileWidth][boardTileHeight];
    ArrayList<Tile> civList = new ArrayList<Tile>();
    
    ArrayList<LandTile> landTileList = new ArrayList<LandTile>();
    
    
    public MainBoardGui() {
    	// TODO create constructor for MainBoardGui class
    	createStartingBoard();
    	createStartingColonies();
    	
    	System.out.println("======================== \n");
    	printBoard();
    	
    }
    
    
    //creates starting colonies
    public void createStartingColonies() {
    	
    	int randX;
    	int randY;
    	
    	
    	LandTile newTile;
    	LandTile oldTile;
    	
    	
    	for (int i = 0; i < startingCivs; i++) {
    	
    			// gets random land tile from board
    			oldTile = landTileList.get(rand.nextInt(landTileList.size()));
	    		
    			// creates a random color for new civ
		    	int randColorNum = rand.nextInt(0xffffff + 1);
				Color civColor = new Color(randColorNum);
    			
    			// creates new CivCapital Object
		    	CivCapital civCap = new CivCapital(oldTile.xPos, oldTile.yPos, oldTile.tileWidth, oldTile.tileHeight, "civCapital", oldTile.wood, oldTile.iron, oldTile.food, 500, civColor );
		    	
		    	
		    	civCap.setTileId(i, -1, -1);
		    	civCap.setPopLimit(ThreadLocalRandom.current().nextInt(130, 301));
		    	civCap.setIndexs(oldTile.xIndex, oldTile.yIndex);
		    	
				
		    	newTile = civCap;
		    	
		    	
		    	System.out.println(newTile.toString());
		    	
		    	int[] cords = getBoardListIndexOfObject(oldTile);
		    	int row = cords[0];
		    	int col = cords[1];
		    	
		    	
		    	// sets new tile onto board
		    	boardList[row][col] = newTile;
		    	
		    	landTileList.remove(oldTile);
		    	
		    	// adds new tile to civ list
		    	civList.add(newTile);
		    	
    	}
    }
    
    public int[] getBoardListIndexOfObject(LandTile tile) {
    	
    	for (int row=0; row<boardTileWidth; row++) {
    		for (int col=0; col < boardTileHeight; col++) {
    			if (boardList[row][col].equals(tile)){
    				int[] cords = new int[2];
    				cords[0] = row;
    				cords[1] =col;
    				
    				System.out.println("Index Found" + cords.toString());

    				return cords;
    			}
    		}
    	}
    	return null;
    }
    
    public void printBoard() {
    	for (Tile[] tileArray : boardList) {
    		for (Tile tile : tileArray) {
    			System.out.print(tile.xIndex + ", " + tile.yIndex + " | ");
    		}
    		System.out.println();
    	}
    }
    
    // creates all tiles for board
    public void createStartingBoard() {
    	
    	int xPos;
    	int yPos;
    	
    	for (int row = 0; row < boardTileWidth; row++) {
    		for (int col = 0; col < boardTileHeight; col++) {
    			// choose intial tile type
    			String type = chooseTileType();
    			
    			xPos = row * tileSize;
    			yPos = col * tileSize;
    			
    			// sets board tile to the chosen type
    			//TODO work on drawing shown tiles
    			if (type.equals("Emptyland")) {
    				
    				int woodCount = ThreadLocalRandom.current().nextInt(0, 9 + 1);
    				int ironCount = ThreadLocalRandom.current().nextInt(0, 9 + 1);
    				int foodCount = ThreadLocalRandom.current().nextInt(0, 9 + 1);
    				
    				EmptyLandTile tileToAdd = new EmptyLandTile(xPos, yPos, tileSize, tileSize, "Emptyland", woodCount, ironCount, foodCount);
    				
    				// add tiles indexs
    				tileToAdd.setIndexs(row, col);
    				
    				// sets board position to new tile
    		    	boardList[row][col] = tileToAdd;
    		    	
    		    	// adds new tile to the land tile list
    		    	landTileList.add(tileToAdd);

    				
    				
    			}else if (type.equals("water")){
    				// creates a water tile
    				boardList[row][col] = new WaterTile(xPos, yPos, tileSize, tileSize, type);
    				
    				// adds tile indexs
    				boardList[row][col].setIndexs(row, col);
    				
    			}
    			//System.out.println("New Tile Index: " + boardList[row][col].xIndex + ", " + boardList[row][col].yIndex);
    		}
    	}
    	
    }
    
    public void iterateThroughBoard() {
    
    	Tile tile;
    	for (int row = 0; row < boardTileWidth; row++) {
    		for (int col = 0; col < boardTileHeight; col++) {
    		
    			tile = boardList[row][col];
    			
    			System.out.println("Index (" + tile.xIndex + ", " + tile.yIndex + ")");
    		}
    	}
    }
    
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
    	
    	tileTurns();
    	
    	
    	// checks if the screen should scroll based on mouse position
    	checkMouseScroll();
    	
    	drawShownTiles(g);
    	
    	
    	g.drawString("rdadadsd",0,0);
    }
    
    public void tileTurns() {
    	
    	// goes through each civ capital in array and takes its turn
    	for (Tile civ : civList) {
    		// casts the tile as a CivCapital
    		civCapitalTurn((CivCapital) civ);
    	}

    }
    
    public void drawShownTiles(Graphics g) {
    	for (Tile[] tileArray : boardList) {
    		for (Tile tile : tileArray) {
    			
    			// checks if tile should be drawn 
    			if (tile.withinScreen(windowWidth, windowHeight, xOffset, yOffset)) {
    				tile.drawTileAndText(g, xOffset, yOffset);
    				
    			}	
    		}
    	}
 
    	
    }
    
    public String chooseTileType() {
    	
    	// creates a random number that will choose tile type
    	int numType = ThreadLocalRandom.current().nextInt(0, 10 + 1);
    	
    	// picks random tile type based on a random number numType
    	if (numType <= 9) {
    		return "Emptyland";
    	}else {
    		return "water";
    	}
    }
    
    //changes the xOffset
    public void changeXOffset(int xOffsetChange) {
    		xOffset += xOffsetChange;
    }
    
    
    //changes the yOffset
    public void changeYOffset(int yOffsetChange) {
    	yOffset += yOffsetChange;

    }
    
    // checks if screen should scroll ( if mouse on side of screen)
    public void checkMouseScroll() {
    	if (mouseX > windowWidth -100) {
			changeXOffset(10);

		}
    	if (mouseX < 100) {
			changeXOffset(-10);

		}
    	if (mouseY > windowHeight -100) {
			changeYOffset(10);

		}
    	if (mouseY < 100) {
			changeYOffset(-10);

		}
    }
 
    
    /// Civ capital turn ///
    
    
    public void civCapitalTurn(CivCapital capital) {
    	
    	System.out.println("CivTurn: " + capital.getXIndex() + ", " + capital.getYIndex());
    	
    	// checks if capital is engaged
    	if(capital.isEngaged) {
    		// capital is engaged
    		System.out.println("Capital Engaged");
    		capitalEngaged(capital);
    		
    	}else {
    		// the civ capital is not engaged
    		System.out.println("Capital Not Engaged");
    		capitalNotEngaged(capital);
    	}
    	
    	// go through tile t
    	// TODO go through all county tiles in civ
    	
    	
    	
    }
    
    public void capitalNotEngaged(CivCapital capital) {
    	//checks if capital can expand
    	
    	if (capital.canExpand()) {
    		System.out.println("Capital Expanding");
    		// capital can expand
    		capitalExpands(capital);
    		
    	}
    }
    
    // method for when the capital is engaged
    public void capitalEngaged(CivCapital capital) {

    	
    }
    
    // capital expands
    public void capitalExpands(CivCapital capital) {
    	// get surrounding tiles
    	ArrayList<LandTile> surroundingTiles = getSurroundingLandTiles(capital);
    	
    	
    	//System.out.println(surroundingTiles);
    	
    	// creates an ArrayList of tiles to expand to
      	ArrayList<LandTile> movableTiles = getMovableToTiles(capital, surroundingTiles);
    
      	//checks if there are any movable tiles
      	if (movableTiles.size() > 0) {
          	// picks random tile to expand to
          	LandTile toExpandTo = getRandomMovableTile(movableTiles);
          	
          	// capital expands using new random neighbor tile
          	capitalExpandsWithTile(capital, toExpandTo);
      	}
     
    }
    
    public void capitalExpandsWithTile(CapitalTile capTile, LandTile settleTile) {
      	
      	// check if toExpandTo tile is occupied or not
      	if (settleTile instanceof OccupiedTile) {
      		// engagement
      		
      	}else {
      		//TODO check if tile should settle county of capital
      		// empty tile
      		// settle tile
      		//settleTile
      	}
    }
    
    /// common functions ///
    
    
    public ArrayList<Tile> getSurroundingTiles(Tile tile) {
    	int xIndex = tile.xIndex;
    	int yIndex = tile.yIndex;
    	
    	ArrayList<Tile> surroundingTiles = new ArrayList<Tile>();
    	
    	surroundingTiles.add(boardList[xIndex-1][yIndex-1]); // top left
    	surroundingTiles.add(boardList[xIndex][yIndex-1]); // top mid
    	surroundingTiles.add(boardList[xIndex+1][yIndex-1]); // top right 
    	surroundingTiles.add(boardList[xIndex-1][yIndex]); // center left
    	surroundingTiles.add(boardList[xIndex+1][yIndex]); // center right
    	surroundingTiles.add(boardList[xIndex-1][yIndex+1]); // bottom left
    	surroundingTiles.add(boardList[xIndex+1][yIndex+1]); // bottom right


    	return surroundingTiles;
    	
    }
    
    public ArrayList<LandTile> getSurroundingLandTiles(Tile tile) {
    	ArrayList<LandTile> landTiles = new ArrayList<LandTile>();
    	
    	
    	for(int row=-1; row<=1; row++) {
    		for (int col=-1; col<=1; col++ ){
    			
    			// checks if tile is within board
    			if(tile.xIndex - 1 > 0 
    				&& tile.yIndex - 1 > 0 
    				&& tile.xIndex + 1 < boardTileWidth
    				&& tile.yIndex + 1 < boardTileHeight) {
    				
    				// check if tile is a land tile
    				if (tile instanceof LandTile) {
    					// adds land tile to arrayList
    					landTiles.add((LandTile)tile);
    				}
    				
    				
    			}

    		}
    	}
    	
    	
    	
    	
    	return landTiles;
    }
    
    public ArrayList<LandTile> getMovableToTiles(Tile fromTile,ArrayList<LandTile> landLst){
    	
    	ArrayList<LandTile> surroundingLandTiles = new ArrayList<LandTile>();
    	// TODO  find movable to tiles
    	// TODO try sorting out tiles 
    
    	
    	// remove all tiles that are in same civ
    	for (LandTile surndTile : surroundingLandTiles) {
    		if (surndTile instanceof OccupiedTile) {
    			OccupiedTile occTile = (OccupiedTile) surndTile;
    			
    			//checks if surrounding tile is on the same team as expanding tile
    			if (occTile.civId == ((OccupiedTile)fromTile).civId) {
    				surroundingLandTiles.remove(surndTile);
    			}
    			
    		}
    	}
    	
    	
    
    	return surroundingLandTiles;
    }
    
    /*
     * returns a random tile from an ArrayList of movable tiles
     */
    public LandTile getRandomMovableTile(ArrayList<LandTile> movableLst){
    	
    	//TODO why is movableLst.size() == 0?
    	System.out.println(movableLst.size());

    	int randIndex = rand.nextInt(movableLst.size());
    	return movableLst.get(randIndex);
    	
    }

    /*
     * Settles tile with given parent Attributes
     */
    public void	settleCountyTileOnTile(OccupiedTile parentTile, LandTile settleTile) {
    	// 
    }

}
