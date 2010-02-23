package map;

import java.util.EventListener;

 
public interface CellListener extends EventListener {
	public void cellAdded (CellEvent e);
	public void cellRemoved (CellEvent e);
}
