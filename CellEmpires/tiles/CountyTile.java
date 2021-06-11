import java.awt.Color;

public class CountyTile extends OccupiedTile{

	public CountyCapital countyCapital;
	public boolean isEngaged = false;
	
	
	
	public CountyTile(int xPos, int yPos, int tileWidth, int tileHeight, String type, int wood, int iron, int food,
			int pop, Color tileColor, CountyCapital countyCapital) {
		super(xPos, yPos, tileWidth, tileHeight, type, wood, iron, food, pop, tileColor);
		
		this.countyCapital = countyCapital;
	}

	
	
	
}
