package world.objects.capabilities;

import java.awt.Point;
import java.util.HashSet;

import map.Cell;

public interface Viewer {
	public HashSet<Point> getAllViews();
	public HashSet<Cell> getViews(Cell cell);
	public void addView(Point p);
}