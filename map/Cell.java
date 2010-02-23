package map;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

import org.syrus.animotion.FxEvent;
import org.syrus.animotion.FxListener;


import world.WorldObject;
import world.WorldObjectException;

public class Cell {
	public Vector<WorldObject> objects = new Vector<WorldObject>();
	public int x;
	public int y;
	private ArrayList<CellListener> cellListeners = new ArrayList<CellListener>();
	public Cell (int x, int y) {
		this.x = x;
		this.y = y;
	}
	public Point getPosition () {
		return new Point(this.x,this.y);
	}
	public void addObject (WorldObject object) {
		try {
			object.addCell(this);
			this.objects.add(object);
			this.fireCellAddedEvent(new CellEvent(this,object));
		} catch (WorldObjectException e) {
			e.printStackTrace();
		}
	}
	public void removeObject (WorldObject object) {
		this.objects.remove(object);
		object.removeCell(this);
		this.fireCellRemovedEvent(new CellEvent(this,object));
	}
	public boolean contains (Class classObj) {
		for (WorldObject object: this.objects) {
			if (object.is(classObj)) return true;
		}
		return false;
	}
	public boolean containsAll (Class classObj) {
		for (WorldObject object: this.objects) {
			if (!object.is(classObj)) return false;
		}
		return (!this.objects.isEmpty());
	}
	public WorldObject lastObject () {
		if (this.objects.size()>0) return this.objects.lastElement();
		return null;
	}
	public void addCellListener (CellListener c) {
		this.cellListeners.add(c);
	}
	public void removeCellListener (CellListener c) {
		this.cellListeners.remove(c);
	}
	private void fireCellAddedEvent (CellEvent e) {
		for (CellListener c: this.cellListeners) {
			c.cellAdded(e);
		}
	}
	private void fireCellRemovedEvent (CellEvent e) {
		for (CellListener c: this.cellListeners) {
			c.cellRemoved(e);
		}
	}
	public String toString () {
		return "Cell ["+this.x+","+this.y+"]";
	}
}
