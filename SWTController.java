import java.lang.Thread.State;
import java.util.HashSet;

import map.Cell;
import map.CellEvent;
import map.CellListener;
import map.Map;
import map.out.CellPanel;
import map.out.ColorCell;
import map.out.MapPanel;

import org.eclipse.swt.SWT;

import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;


import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;

import org.eclipse.swt.widgets.*;
import org.syrus.animotion.*;
import org.syrus.animotion.fx.interpolators.*;
import org.syrus.animotion.fx.setters.ElemotionSetter;

import world.ToricWorld;
import world.World;
import world.objects.*;


 
public class SWTController {
	public static void main(String[] args) {
		Animotion.addInterpolator(new IntegerInterpolator());
		Animotion.addInterpolator(new DoubleInterpolator());
		Animotion.addInterpolator(new ColorSWTInterpolator());	
		
		Animotion.addSetter(new ElemotionSetter());
		
		final Display display = new Display();
		Shell shell = new Shell(display);
		shell.setSize(600, 600);
		GridLayout layout = new GridLayout();
	    layout.numColumns = 1;
	    shell.setLayout(layout);
	    
	    
	   
	    
		Button toggleButton = new Button(shell, SWT.TOGGLE);
	    toggleButton.setText("Explorar");  
	    
		final Map map = new Map(15,15);
		
		/*Space space = new Space();
		Wall wall = new Wall();
		for (int i=1;i<=map.getWidth();i++) {
			for (int j=1;j<=map.getHeight();j++) {
				if (i==1 || i==map.getWidth() || j==1 || j== map.getHeight()) map.getCell(i,j).addObject(wall);
				else map.getCell(i,j).addObject(space);
			}
		}
		for (int p=5;p<=map.getWidth();p++) {
			map.getCell(4,p).addObject(wall);
		}
		final World world = new World(map);
		*/
		Space space = new Space();
		Wall wall = new Wall();
		for (int i=1;i<=map.getWidth();i++) {
			for (int j=1;j<=map.getHeight();j++) {
				if ((i==1 || i==map.getWidth())/* && (j!=5 && j!=6)*/) map.getCell(i,j).addObject(wall);
				else map.getCell(i,j).addObject(space);
			}
		}
		for (int p=5;p<=map.getWidth();p++) {
			map.getCell(4,p).addObject(wall);
		}
		for (int p=8;p<=13;p++) {
			map.getCell(p,10).addObject(wall);
		}
		
		final World world = new ToricWorld(map);
		
		//Point p = new Point(10,10);
		ColorCell colors = new ColorCell();
		
		
		colors.addColor(RobotSyrus.class, new Color(null,0,0,255));
		colors.addColor(RobotOne.class, new Color(null,0,0,255));
		colors.addColor(Space.class, new Color(null,230,230,230));
		colors.addColor(Wall.class, new Color(null,0,0,0));
		final MapPanel mapPanel = new MapPanel(shell, map,colors);
		GridData spec = new GridData();
	    spec.horizontalAlignment = GridData.FILL;
	    spec.grabExcessHorizontalSpace = true;
	    spec.verticalAlignment = GridData.FILL;
	    spec.grabExcessVerticalSpace = true;
	    mapPanel.setLayoutData(spec);
	    map.addCellListener(new CellListener(){
			public void cellAdded(CellEvent e) {
				if (e.worldObject.is(BasicRobot.class)) {
					BasicRobot robotx = ((BasicRobot)e.worldObject);
					HashSet<Cell> views = robotx.getViews(robotx.getCell());
					//Properties p2 = new Properties();
					//p2.set(Animotion.Property.OPACITY, 255);
					for (Cell view: views) {
						mapPanel.getCellPanel(view.getPosition()).property(200,true);
						//mapPanel.getCellPanel(view.getPosition()).composite.redraw();
					}
					mapPanel.centerCell(robotx.getCell());
					
					Properties p = new Properties();
					p.set(Animotion.Property.WIDTH, 80);
					
					//p.set(30, composite.getDisplay().getSystemColor(SWT.COLOR_RED));
					
					CellPanel c = mapPanel.getCellPanel(robotx.getCell().getPosition());
					if ((Integer)c.property(100) == 0) c.property(Animotion.Property.WIDTH, 50).animate(p, 100, Animotion.Easing.EaseOutQuad);
				}
					
			}

			public void cellRemoved(CellEvent e) {
				CellPanel c = mapPanel.getCellPanel(((Cell)e.getSource()).getPosition());

				c.property(Animotion.Property.WIDTH, 100).property(100, ((Integer)c.property(100))+1);
				
			}
			
		});
		
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.open();
		final BasicRobot robot = new RobotSyrus(world);
		map.getCell(8, 4).addObject(robot);	
		final class exploreThread extends Thread{
				public boolean explore = true;
			      public void run() {
			    	  while (this.explore) {
			    		  Display.getDefault().syncExec(new Runnable() {
			    	        public void run() {
			    	      	   explore = robot.explore();
			    	        }
			    		  });
				            try { sleep(200); } catch (Exception e) { }
				         }
			         
			      }
			   };
			   MouseListener listener = new MouseListener() {
				   public Thread explore;
					@Override
					public void mouseDoubleClick(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}

					@Override
					public void mouseDown(MouseEvent arg0) {

						if (explore == null) {
							explore = new exploreThread();
							explore.start();
						}
						else {
							explore.stop();

							explore = null;
						}
						
					}

					@Override
					public void mouseUp(MouseEvent arg0) {
						// TODO Auto-generated method stub
						
					}
			    	
			    };
		   toggleButton.addMouseListener(listener);
		//explore.start();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		
		display.dispose();
		
	}
}
