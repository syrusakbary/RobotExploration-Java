package map;

import map.Cell;

public class Map {
	private Cell map[][];
	private int width;
	private int height;
	public Map () {
	}
	public Map (int width, int height) {
		this.width = width;
		this.height = height;
		this.map = new Cell[width][height];
		for (int x=0;x<width;x++) {
	    	for (int y=0;y<height;y++) {
	    		// this.setCell(x,y,new CellPanel(x,y));
	    		this.map[x][y] = new Cell(x+1,y+1);
	    	}
		}
		//map = new CellPanel[width][height];
	}
	public Cell getCell (int x, int y) {
		if (x <= width && x>=1 && y <= height && y>=1) return map[x-1][y-1];
		return null;
	}
	public void setCell (int x, int y, Cell cell) {
		map[x+1][y+1] = cell;
	}
	public void setSize (int width,int height) {
		this.width = width;
		this.height = height;
	}
	public int getWidth () {
		return this.width;
	}
	public int getHeight () {
		return this.height;
	}
	public void addCellListener (CellListener c) {
		for (int x=0;x<width;x++) {
	    	for (int y=0;y<height;y++) {
	    		this.map[x][y].addCellListener(c);
	    	}
		}
	}
}
