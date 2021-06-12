import java.awt.Color;
import java.util.concurrent.ThreadLocalRandom;

abstract class OccupiedTile extends LandTile {

	boolean isEngaged = false;
	
	int pop;
	int popLimit;
	
	
	Color tileColor;
	
	int civId;
	int countyId;
	int countyTileId;



	
	public OccupiedTile(int xPos, int yPos, int tileWidth, int tileHeight, String type, int wood, int iron, int food, int pop, Color tileColor) {
		super(xPos, yPos, tileWidth, tileHeight, type, wood, iron, food);

		this.pop = pop;
		this.tileColor = tileColor;
	}

	
	public void setCivId(int id) {
		this.civId = id;
	}
	
	public void setCountyId(int id) {
		this.countyId = id;
	}
	
	public void setCountyTileId(int id) {
		this.countyTileId = id;
	}
	
	public void setPopLimit(int lim) {
		this.popLimit = lim;
	}
	
	public void setTileId(int civId, int countyId, int countyTileId) {
		this.civId = civId;
		this.countyId = countyId;
		this.countyTileId = countyTileId;
	}

	
	
	// checks if capital can expand
	public boolean canExpand() {
		if (this.pop > this.popLimit) {
			return true;
		}else {
			return false;
		}
	}

	/*
	 * Takes in the parameters needed to create a new civCapital tile.
	 * 
	 * 
	 */
	public CivCapital convertToCivCapital(int xPos, int yPos, int tileWidth, int tileHeight, 
			String type, int wood, int iron, int food, int pop, Color tileColor,
			int xIndex, int yIndex, int civId) {
		CivCapital civCap = new CivCapital(xPos, yPos, tileWidth, tileHeight, type, wood, iron, food, pop, tileColor);
		civCap.setIndexs(xIndex, yIndex);
		
		// sets addition civCapital attributes
		civCap.setTileId(civId, -1, -1);
    	civCap.setPopLimit(ThreadLocalRandom.current().nextInt(130, 301));
		
		return civCap;
		
	}
}
