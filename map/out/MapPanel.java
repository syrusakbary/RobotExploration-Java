package map.out;

import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.TreeSet;

import map.Cell;
import map.CellEvent;
import map.CellListener;
import map.Map;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import world.WorldObject;
import world.objects.BasicRobot;
import world.objects.RobotZero;
import world.objects.Space;

public class MapPanel extends ScrolledComposite {
	protected Map map;
	private CellPanel[][] cells;
	private Point cellSize = new Point(25,25);
	private Point cellSpacing = new Point(5,5);
	public MapPanel (Composite parent, Map map, ColorCell colors) {
		super(parent, SWT.H_SCROLL | SWT.V_SCROLL | SWT.BORDER);
		final Composite centered = new Composite(this, SWT.NONE);
	    this.setContent(centered);
	    centered.setLayout(new GridLayout());
	    centered.setBackground(this.getDisplay().getSystemColor(SWT.COLOR_WHITE));
	    Composite c = new Composite(centered, SWT.NONE);
	    c.setBackground(this.getDisplay().getSystemColor(SWT.COLOR_WHITE));

		GridData gridData = new GridData();
		gridData.verticalAlignment = SWT.CENTER;
		gridData.grabExcessVerticalSpace = true;
		gridData.grabExcessHorizontalSpace = true;
		gridData.horizontalAlignment = SWT.CENTER;
		c.setLayoutData(gridData);
		
				
		//this.setBackground(this.getDisplay().getSystemColor(SWT.COLOR_BLUE));
		this.map = map;
		this.cells = new CellPanel[this.map.getWidth()][this.map.getHeight()];
		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = this.map.getWidth();
		gridLayout.makeColumnsEqualWidth = true;
		gridLayout.verticalSpacing = this.cellSpacing.x;
		gridLayout.horizontalSpacing = this.cellSpacing.y;
		
		GridData gridDataC = new GridData();
		gridDataC.horizontalAlignment = gridData.CENTER;
		gridDataC.grabExcessVerticalSpace = false;
		gridDataC.grabExcessHorizontalSpace = false;
		gridDataC.heightHint = this.cellSize.x;
		gridDataC.widthHint = this.cellSize.y;
		c.setLayout(gridLayout);
		
		for (int i=0;i<this.map.getWidth();i++) {
			for (int j=0;j<this.map.getHeight();j++) {
				this.cells[i][j] = new CellPanel(c,this.map.getCell(i+1, j+1),colors);
				this.cells[i][j].composite.setLayoutData(gridDataC);
			}
		}
		
		map.addCellListener(new CellListener() {
			public void cellAdded(CellEvent e) {
				MapPanel.this.updateCell((Cell)e.getSource());
			}
			public void cellRemoved(CellEvent e) {
				MapPanel.this.updateCell((Cell)e.getSource());
			}
		});
		this.setExpandHorizontal(true);
		this.setExpandVertical(true);
		this.setMinSize(centered.computeSize(SWT.DEFAULT, SWT.DEFAULT));
		this.pack();
	}
	public CellPanel getCellPanel (Point position) {
		return this.cells[position.x-1][position.y-1];
	}
	protected void updateCell (Cell cell) {
		Point pos = cell.getPosition();
		MapPanel.this.getCellPanel(pos).redraw();
	}
	public void centerCell (Cell cell) {
		Point position = cell.getPosition();
		Point origin = new Point();
		origin.x = (position.x)*(this.cellSize.x+this.cellSpacing.x)-((this.getSize().y)/2)-this.cellSpacing.x;
		origin.y = (position.y)*(this.cellSize.y+this.cellSpacing.y)-((this.getSize().x)/2)-this.cellSpacing.y;
		this.setOrigin(origin.y,origin.x);
	}
	public CellPanel[][] getCells() {
		return this.cells;
	}
}
