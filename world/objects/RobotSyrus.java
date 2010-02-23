package world.objects;

import java.awt.Point;
import java.util.HashSet;
import java.util.List;

import java.util.Vector;

import map.Cell;

import world.World;
import world.WorldObjectException;

public class RobotSyrus extends DijkstraRobot {
	public RobotSyrus (World world) {
		super(world);
		this.addMovement(new Point(1,0));
		this.addMovement(new Point(-1,0));
		this.addMovement(new Point(0,1));
		this.addMovement(new Point(0,-1));
		//this.addMovement(new Point(1,1));
		
		this.addView(new Point(0,0));
		this.addView(new Point(1,0));
		this.addView(new Point(-1,0));
		this.addView(new Point(0,1));
		this.addView(new Point(0,-1));
		
		this.addView(new Point(1,1));
		this.addView(new Point(1,-1));
		this.addView(new Point(-1,1));
		this.addView(new Point(-1,-1));
		
		
		
	}

	protected int commonVisitableCells (Cell c1, Cell c2) {
		int count = 0;
		HashSet<Cell> movements1 = this.getViews(c1);
		HashSet<Cell> movements2 = this.getExplored();
		for (Cell m: movements1) {
			if (this.isVisitable(m) && this.isExplored(m) && movements2.contains(m)) count++;
		}
		return count;
	}
	protected int distance (Cell c1, Cell c2) {
		return Math.abs(c1.x-c2.x)+Math.abs(c1.y-c2.y);
	}
	protected Cell getBestCelltoExplore () {
		int min = 100000;
		int m;
		Cell toCell = null;
		for (Cell cell: this.getVertexCells()) {
			if (this.isVisitable(cell) && this.canDiscover(cell) > 0) {
				List path = this.findPath(this.getCell(), cell);
				m = path.size()+this.canDiscover(cell);
				if (m<min) {
					min = m;
					toCell = cell;
				}
			}
		}
		return toCell;
	}
	public boolean explore() {
		Vector<HashSet<Cell>> cells = new Vector<HashSet<Cell>>();
		
		cells.add(0,this.getMovements(this.getCell()));
		int var,maxVar,minVar;
		var = 0;
		maxVar = 0;
		
		cells.add(1,new HashSet<Cell>());
		for (Cell cell: cells.get(0)) {
			var = this.canDiscover(cell);
			if (var >= maxVar) {
				if (var != maxVar) {
					cells.get(1).clear();
					maxVar = var;
				}
				cells.get(1).add(cell);
			}
		}
		var = 0;
		minVar = 1000;
		cells.add(2,new HashSet<Cell>());
		for (Cell cell: cells.get(1)) {
			var = this.commonVisitableCells(cell,this.getCell());
			if (var <= minVar) {
				if (var != minVar) {
					cells.get(2).clear();
					minVar = var;
				}
				cells.get(2).add(cell);
			}
		}
		cells.add(3,new HashSet<Cell>());
		Cell c = null;
		/*for (Cell cell: cells.get(2)) {
			if (this.getMovement(this.getCell(), cell).equals(preferredMovement)) c=cell;
		}
		if (c == null) c = cells.get(2).iterator().next();*/
		Cell ce1 = this.moveCell(this.getCell(), new Point(1,0));
		Cell ce2 = this.moveCell(this.getCell(), new Point(0,1));
		Cell ce3 = this.moveCell(this.getCell(), new Point(-1,0));
		Cell ce4 = this.moveCell(this.getCell(), new Point(0,-1));
		if (cells.get(2).contains(ce1)) c = ce1;
		else if (cells.get(2).contains(ce2)) c = ce2;
		else if (cells.get(2).contains(ce3)) c = ce3;
		else if (cells.get(2).contains(ce4)) c = ce4;
		else c = cells.get(2).iterator().next();
		//c = ce1;
		
		//System.out.println("Best cells: "+cells);
		
		if (this.canDiscover(c) == 0) {
			c = this.getBestCelltoExplore();
		}
		
		if (c == null) {
			return false;
		}
		//Cell cell = cells.get(2).iterator().next();
		try {
			this.move(c);
		} catch (WorldObjectException e) {
			e.printStackTrace();
		}
		//System.out.println("Can discover:"+this.canDiscover(c));
		
		return true;
		
	}
}
