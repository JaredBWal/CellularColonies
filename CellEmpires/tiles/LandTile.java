import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

abstract class LandTile extends Tile{
	/* every land tile has:


 	resources:
		wood
		iron
		food

	methods:
		convert methods
	
   */
	
	public int wood;
	public int iron;
	public int food;
	
	public LandTile(int xPos, int yPos, int tileWidth, int tileHeight, String type, int wood, int iron, int food) {
		super(xPos, yPos, tileWidth, tileHeight, type);
		
		this.wood = wood;
		this.iron = iron;
		this.food = food;
		
	}
	
	
	@Override
	public void drawText(Graphics g, int xOffset, int yOffset) {
		
		int newXPos = this.xPos - xOffset;
		int newYPos = this.yPos - yOffset;
		
    	g.setFont(new Font("TimesRoman", Font.PLAIN, 10)); 

		g.drawString(String.format("F: %s", this.food), newXPos+5, newYPos+10);
		g.drawString(String.format("W: %s", this.wood), newXPos+5, newYPos+23);
		g.drawString(String.format("I: %s", this.iron), newXPos+5, newYPos+36);

	}

	@Override
	public void drawTileAndText(Graphics g, int xOffset, int yOffset) {

		
		g.setColor(Color.green);
		this.drawTile(g, xOffset, yOffset);
		
		g.setColor(Color.black);
		this.drawText(g, xOffset, yOffset);


	}
	
	
	public CivCapital convertToCivCapital(int pop, Color tileColor) {
		
		return new CivCapital(this.xPos, this.yPos, this.tileWidth, this.tileHeight, "civCapital", this.wood, this.iron, this.food, pop, tileColor);
	}
	
	public CountyCapital ConvertToCountyCapital(LandTile parentTile) {
		// TODO create a convert for this. and also think on how this would work
		//new CountyCapital(this.pop, this.food, this.iron, this.wood, this.type, food, food, food, food, null);
		
		return null;
		
	}
	
}
