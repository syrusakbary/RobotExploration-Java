package world.objects;

import java.awt.Point;
import java.util.Collections;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.Vector;

import map.Cell;

import world.World;
import world.WorldObjectException;

public class RobotOne extends DijkstraRobot {
	public RobotOne (World world) {
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
		
		this.lastMovement = new Point(0,1);
	}
	@Override
	public boolean explore() {
		HashSet<Cell> cells = new HashSet<Cell>();
		cells.add(this.getCell());
		TreeMap candiscover = this.canDiscoverCells(cells);
			HashSet<Cell> celd = (HashSet)candiscover.lastEntry().getValue();
			//System.out.println(celd);
			
			System.out.println("Celda actual: "+this.getCell());
			System.out.println("Posibilidades celda: "+candiscover);
			Iterator<Cell> it = celd.iterator();
			int preference;
			Point movement;
			
			Cell cell = null;
			Cell c = null;
			while (it.hasNext()) {
				c = it.next();
				movement = this.getMovement(this.getCell(), c);
				System.out.println("Movimiento: "+movement+", celda: "+c);
				if (movement.equals(this.getLastMovement())) cell=c;
			}
			
			try {
				if (cell != null) this.move(cell);
				else this.move(c);
			} catch (WorldObjectException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//this.move();
		return true;
	}
}
