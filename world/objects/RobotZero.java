package world.objects;

import java.awt.Point;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.TreeMap;
import java.util.Vector;

import map.Cell;

import world.World;
import world.WorldObjectException;

public class RobotZero extends DijkstraRobot {
	public RobotZero (World world) {
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
	@Override
	public boolean explore() {
		HashSet<Cell> cells = new HashSet<Cell>();
		cells.add(this.getCell());
		TreeMap candiscover = this.canDiscoverCells(cells);
		try {
			this.move((Cell)((HashSet)candiscover.lastEntry().getValue()).iterator().next());
		} catch (WorldObjectException e) {
			e.printStackTrace();
		}	
		return true;
	}
}
