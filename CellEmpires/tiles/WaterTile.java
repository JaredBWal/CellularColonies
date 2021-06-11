import java.awt.Color;
import java.awt.Graphics;

public class WaterTile extends Tile{

	public WaterTile(int xPos, int yPos, int tileWidth, int tileHeight, String type) {
		super(xPos, yPos, tileWidth, tileHeight, type);
	}
	
	
	@Override
	public void drawText(Graphics g, int xOffset, int yOffset) {
		int newXPos = xPos - xOffset;
		int newYPos = yPos - yOffset;
		
		
		g.setColor(Color.black);
		g.drawString("Water", newXPos+5, newYPos+10);
	}

	@Override
	public void drawTileAndText(Graphics g, int xOffset, int yOffset) {

		
		g.setColor(Color.blue);
		this.drawTile(g, xOffset, yOffset);
		
		this.drawText(g, xOffset, yOffset);

		
	}

}
