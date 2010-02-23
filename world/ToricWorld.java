package world;

import map.*;

public class ToricWorld extends World{
	public ToricWorld (Map map) {
		super(map);
	}
	public Cell getCell (int x, int y) {
		int x1 = (x-1)%this.map.getWidth();
		int y1 = (y-1)%this.map.getHeight();
		x1 = x1<0?x1+this.map.getWidth():x1;
		y1 = y1<0?y1+this.map.getHeight():y1;
		return this.map.getCell(x1+1, y1+1);
	}
}
