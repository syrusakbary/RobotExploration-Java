package world;

import java.util.Vector;

import map.Cell;

public abstract class WorldObject {
	private Vector<Cell> cells;
	public WorldObject () {
		 this.cells = new Vector<Cell>();
	}
	public boolean is (Class classObj) {
		//boolean a= (this instanceof classObj);
		return classObj.isInstance(this);
	}
	public void addCell (Cell cell) throws WorldObjectException {
		int maxCells = this.maxCells();
		if (maxCells != -1 && this.cells.size() >= maxCells) throw new WorldObjectException("You can't put this in more than "+maxCells+" cells. Please remove from anyone first.");
		this.cells.add(cell);
	}
	public void removeCell (Cell cell) {
		this.cells.remove(cell);
	}
	public Vector<Cell> getCells () {
		return this.cells;
	}
	public int maxCells () {
		return -1;
	}
}