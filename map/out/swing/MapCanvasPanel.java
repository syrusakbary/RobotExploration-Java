/*package map.out.swing;

import map.Cell;
import map.Map;
import map.out.CellEvent;
import map.out.CellListener;

import java.awt.event.*;
import javax.swing.JPanel;

import world.WorldObject;
import world.objects.*;

import java.awt.*;

public class MapCanvasPanel extends JPanel implements MouseListener {
	private Map map;
	//private CellListener ce;
	protected javax.swing.event.EventListenerList listenerList = new javax.swing.event.EventListenerList();
	public MapCanvasPanel (Map map) {
		this.map = map;
		addMouseListener(this);
	}
	public Map getMap () {
		return this.map;
	}
	public void paintComponent(Graphics g) {
	    int width = getWidth();
	    int height = getHeight();
	    
	    int mapWidth = this.map.getWidth();
	    int mapHeight = this.map.getHeight();
	    System.out.println(mapWidth);
	    double cellWidth = this.getWidth()/this.map.getWidth();
	    double cellHeight = this.getHeight()/this.map.getHeight();
	    for (int i=0;i<mapWidth;i++) {
	    	for (int j=0;j<mapWidth;j++) {
	    		 
	    		 g.setColor(this.getCellColor(this.map.getCell(i, j)));
	    		 int c1 = (int)(cellWidth*(i+1))-(int)(cellWidth*(i));
	    		 int c2 = (int)(cellHeight*(i+1))-(int)(cellHeight*(i));
	    		 g.fillRect((int)(cellWidth*i), (int)(cellHeight*j), c1, c2);
	    	}
	    }
	}
	public Color getCellColor (Cell cell) {
		//WorldObject last = cell.lastObject();
		//if (last != null) System.out.println("not null! x"+cell.x+"; y"+cell.y+". Otro: "+cell.contains(BasicRobot.class));
		if (cell.contains(BasicRobot.class))
			return new Color (0xFF0000);
		
		else return new Color (0xFFFFFF);
	}
	public Cell cellInPosition (int x, int y) {
		return this.map.getCell (
			(int)x*this.map.getWidth()/this.getWidth(),
			(int)y*this.map.getHeight()/this.getHeight()
		);
	}
	synchronized public void addCellListener (CellListener listener) {
		listenerList.add(CellListener.class, listener);
	}
	synchronized public void removeCellListener (CellListener listener) {
		listenerList.remove(CellListener.class, listener);
	}
	public void mouseClicked(MouseEvent e) {
        Cell cell = cellInPosition(e.getX(),e.getY());
        //ce.cellClicked(cell.x,cell.y)
        Object[] listeners = listenerList.getListenerList();
        CellEvent evt = new CellEvent(this,cell);
        for (int i=0; i<listeners.length; i+=2) {
        	
        	 if (listeners[i]==CellListener.class)
        		 ((CellListener)listeners[i+1]).cellClicked(evt);
        }
    }

	public void mousePressed(MouseEvent e){}
	public void mouseReleased(MouseEvent e){}
	public void mouseEntered(MouseEvent e){}
	public void mouseExited(MouseEvent e){}
}*/
