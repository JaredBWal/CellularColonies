import java.awt.Color;
import java.awt.Graphics;

abstract class Tile {

	public int xPos;
	public int yPos;
	public int tileWidth;
	public int tileHeight;
	
	int xIndex;
	int yIndex;
	
	
	// type of tile (ex: water, land, county...)
	public String type;
	
	public Tile(int xPos, int yPos, int tileWidth, int tileHeight, String type) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.tileHeight = tileHeight;
		this.tileWidth = tileWidth;
		this.type = type;
	}
	
	
	public boolean withinScreen(int windowWidth, int windowHeight, int xOffset, int yOffset) {
		
		// if within screen
		
		
		if (this.xPos - xOffset<= windowWidth
				&& this.xPos - xOffset >= 0
				&& this.yPos - yOffset <= windowHeight
				&& this.yPos - yOffset >= 0) {
				
			// tile is in within screen
			return true;
		}else {
			// tile is not within the screen
			return false;
		}
		
		
		

	}
	
	
	public void setIndexs(int x, int y) {
		this.xIndex = x;
		this.yIndex = y;
	}
	
	public int getXIndex() {
		return this.xIndex;
	}
	
	public int getYIndex() {
		return this.yIndex;
	}
	
	public void drawTile(Graphics g, int xOffset, int yOffset) {
		
		g.fillRect(xPos - xOffset, yPos - yOffset, tileWidth, tileHeight);
	}
	
	public void drawText(Graphics g, int xOffset, int yOffset) {
		g.setColor(Color.black);
		g.drawString("Tile", xPos+5, yPos+10);
	}
	
	public void drawTileAndText(Graphics g, int xOffset, int yOffset) {
		
		
		drawTile(g, xOffset, yOffset);
		drawText(g, xOffset, yOffset);
	
	}
	
	
	public CivCapital convertToCivCapital(int pop, Color tileColor) {
		
		
		CivCapital newCivCap = new CivCapital(this.xPos, this.yPos, this.tileWidth, this.tileHeight, "civCapital", -1, -1, -1, pop, tileColor);
		
		newCivCap.setIndexs(this.xIndex, this.yIndex);
		
		System.out.println("New Civ Cap" + newCivCap.xIndex + ", " + newCivCap.yIndex);
		
		
		return newCivCap;
	}


}
