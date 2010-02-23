package map;

import java.util.EventObject;

import world.WorldObject;


public class CellEvent extends EventObject {
	public WorldObject worldObject;
	public CellEvent(Cell source, WorldObject worldObject) {
		super(source);
		this.worldObject = worldObject;
	}
}
