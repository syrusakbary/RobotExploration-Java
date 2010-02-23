package world.objects;

import java.awt.Point;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.Vector;

import map.Cell;

import world.*;
import world.objects.capabilities.*;

import org.jgrapht.alg.*;
import org.jgrapht.*;
import org.jgrapht.graph.*;

import graph.*;

abstract public class BasicRobot extends WorldObject implements Explorer {
	private HashSet<Point> movements = new HashSet<Point>();
	private HashSet<Cell> explored = new HashSet<Cell>();
	private HashSet<Point> views = new HashSet<Point>();
	protected Point lastMovement = new Point();
	protected World world;
	public BasicRobot (World world) {
		this.world = world;
	}
	protected Point getLastMovement () {
		return this.lastMovement;
	}
	protected Point getMovement (Cell c1, Cell c2) {
		Point p1 = c1.getPosition();
		Point p2 = c2.getPosition();
		return new Point(p2.y-p1.y,p2.x-p1.x);
	}
	protected void moveTo (Cell cell) {
		lastMovement = this.getMovement(this.getCell(),cell);
		System.out.println("Move to: "+cell);
		this.getCell().removeObject(this);
		cell.addObject(this);
	}
	abstract public void move(Cell cell) throws WorldObjectException;
	public void addCell (Cell cell) throws WorldObjectException {
		this.setExplored(cell);
		super.addCell(cell);
	}
	public Cell getCell () {
		return this.getCells().firstElement();
	}
	abstract public void setExplored (Cell cell);
	public HashSet<Cell> getExplored() {
		return this.explored;
	}
	public boolean isVisitable (Point p) {
		return this.isVisitable(this.world.getCell(p));
	}
	public boolean isVisitable (Cell c) {
		return c!=null && c.containsAll(Visitable.class);
	}
	public Point movePoint (Point p, Point move) {
		return new Point(p.x+move.x, p.y+move.y);
	}
	public Cell moveCell (Cell cell, Point move) {
		if (cell == null) return null;
		return this.world.getCell(this.movePoint(cell.getPosition(), move));
	}
	public HashSet<Point> getAllMovements () {
		return this.movements;
	}
	public HashSet<Cell> getMovements (Cell cell) {
		HashSet<Cell> movements = new HashSet<Cell>();
		Cell c;
		for (Point move: this.movements) {
			c = this.moveCell(cell, move);
			if (this.isVisitable(c)) movements.add(c);
		}
		return movements;
	}
	public void addMovement (Point p) {
		this.movements.add(p);
	}
	public HashSet<Point> getAllViews() {
		return this.views;
	}
	public HashSet<Cell> getViews(Cell cell) {
		HashSet<Cell> views = new HashSet<Cell>();
		for (Point view:this.views) {
			Cell move = this.moveCell(cell, view);
			if (move != null) views.add(move);
		}
		return views;
	}
	public void addView (Point p) {
		this.views.add(p);
	}
	public boolean isExplored (Point p) {
		//System.out.println(this.world.getCell(p));
		return this.isExplored(this.world.getCell(p));
	}
	public void addExplored (Cell cell) {
		this.explored.add(cell);
	}
	public boolean isExplored (Cell cell) {
		//if (cell == null) return false;
		return this.explored.contains(cell);
	}
	public int canDiscover (Cell cell) {
		int canDiscover = 0;
		for (Cell view: this.getViews(cell)) {
			if (!this.isExplored(view)) canDiscover++;
		}
		return canDiscover;
	}
	public TreeMap<Integer,HashSet<Cell>> canDiscoverCells (HashSet<Cell> cells) {
		HashSet<Cell> movements;
		int canDiscover;
		TreeMap<Integer,HashSet<Cell>> tree = new TreeMap<Integer,HashSet<Cell>>();
		for (Cell cell: cells) {
			movements = this.getMovements(cell);
			//System.out.println("cell"+cell+", movements: "+movements);
			for (Cell movement: movements) {
				if (movement != null) {
					canDiscover = this.canDiscover(movement);
					if (!tree.containsKey(canDiscover)) tree.put(canDiscover, new HashSet<Cell>());
					tree.get(canDiscover).add(movement);
				}
			}
		}
		
		return tree;
	}
	public int maxCells () {
		return 1;
	}
}
