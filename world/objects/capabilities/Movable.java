package world.objects.capabilities;

import java.awt.Point;
import java.util.HashSet;

import map.Cell;

import world.WorldObjectException;

public interface Movable {
	public void move (Cell cell) throws WorldObjectException;
	public HashSet<Point> getAllMovements();
	public HashSet<Cell> getMovements(Cell cell);
	public void addMovement (Point p);
	//private String[] movements;
}