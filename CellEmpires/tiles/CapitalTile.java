import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

abstract class CapitalTile extends OccupiedTile {

	
	
	public int storedWood = 0;
	public int storedIron = 0;
	public int storedPop = 0;
	
	public int troopCount=0;
	
	
	public CapitalTile(int xPos, int yPos, int tileWidth, int tileHeight, String type, int wood, int iron, int food, int pop, Color tileColor) {
		super(xPos, yPos, tileWidth, tileHeight, type, wood, iron, food, pop, tileColor);
		// TODO Auto-generated constructor stub
	}

	
	

	
	
	// TODO build buildings
	
	
	// TODO create defenses
	
	
	// TODO create troops
	public void createTroops() {
		
	}
	
	
	
	public void setTroops(int troopCnt) {
		this.troopCount = troopCnt;
	}
	
	
	@Override
	public void drawText(Graphics g, int xOffset, int yOffset) {
		
		int newXPos = this.xPos - xOffset;
		int newYPos = this.yPos - yOffset;
		
    	g.setFont(new Font("TimesRoman", Font.PLAIN, 8)); 
    	g.drawString(String.format("Civ: %s", this.civId ), newXPos+2, newYPos+5);
    	g.drawString(String.format("County: %s", this.countyId ), newXPos+2, newYPos+15);
    	g.drawString(String.format("Troops: %s", this.countyId ), newXPos+2, newYPos+25);
    	g.drawString(String.format("Pop: %s / %s", this.pop, this.popLimit ), newXPos+2, newYPos+35);
    	g.drawString(String.format("F:%s W:%s I: %s", this.food, this.wood, this.iron), newXPos+5, newYPos+45);



    	
    	
    	g.setFont(new Font("TimesRoman", Font.PLAIN, 7)); 
		
	
	}

	@Override
	public void drawTileAndText(Graphics g, int xOffset, int yOffset) {

		
		g.setColor(this.tileColor);
		this.drawTile(g, xOffset, yOffset);
		
		g.setColor(Color.black);
		this.drawText(g, xOffset, yOffset);
		
	}

}
