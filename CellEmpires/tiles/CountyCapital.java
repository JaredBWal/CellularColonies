import java.awt.Color;
import java.util.ArrayList;

public class CountyCapital extends CapitalTile  {

	public ArrayList<CountyTile> countyList = new ArrayList<CountyTile>();

	public CountyCapital(int xPos, int yPos, int tileWidth, int tileHeight, String type, int wood, int iron, int food,
			int pop, Color tileColor) {
		super(xPos, yPos, tileWidth, tileHeight, type, wood, iron, food, pop, tileColor);
	
	}
}