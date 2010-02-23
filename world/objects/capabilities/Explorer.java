package world.objects.capabilities;

import map.Cell;

public interface Explorer extends Movable, Viewer {
	public boolean explore ();
	public int canDiscover (Cell cell);
}
