package world;

import java.awt.Point;

import map.*;

public class World {
	protected Map map;
	public World (Map map) {
		this.map = map;
	}
	public Cell getCell (int x, int y) {
		return this.map.getCell(x, y);
	}
	public Cell getCell (Point p) {
		return this.getCell(p.x,p.y);
	}
}
