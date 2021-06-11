import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.ArrayList;

public class CivCapital extends CapitalTile {

	// list of county capitals
	public ArrayList<CountyCapital> countyList = new ArrayList<CountyCapital>();
	

	public CivCapital(int xPos, int yPos, int tileWidth, int tileHeight, String type, int wood, int iron, int food,
			int pop, Color tileColor) {
		super(xPos, yPos, tileWidth, tileHeight, type, wood, iron, food, pop, tileColor);
	}




}
