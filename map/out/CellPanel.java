package map.out;
import java.util.Iterator;
import java.util.Map;

import map.Cell;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.graphics.Region;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import org.syrus.animotion.*;
import org.syrus.animotion.fx.interpolators.*;

public class CellPanel extends ElemotionSWT {
	protected Cell cell;
	protected ColorCell colors;
	public CellPanel (Composite parent, Cell cell, ColorCell colors) {
		
		super(parent);
		this.cell = cell;
		this.colors = colors;
		//composite.setBounds(new Rectangle(0,0,200,100));
		composite.setBackground(composite.getDisplay().getSystemColor(SWT.COLOR_WHITE));

		
		this.property(Animotion.Property.WIDTH, 100);
		this.property(Animotion.Property.OPACITY, 100);
		this.property(100, 0);
		this.property(200, false);
		//this.property(30,);
		final Properties pa = (Properties) this.properties.clone();
		/*this.composite.addListener(SWT.MouseExit, new Listener() {
			public void handleEvent(Event e) {
				
				//animateReverse(100, Animotion.Easing.EaseInQuad);
				animate(pa,50,Animotion.Easing.EaseInQuad);
			}
		});
		this.composite.addListener(SWT.MouseEnter, new Listener() {
			public void handleEvent(Event e) {
				Properties p = new Properties();
				p.set(Animotion.Property.WIDTH, 100);
				//p.set(30, composite.getDisplay().getSystemColor(SWT.COLOR_RED));
				animate(p, 50, Animotion.Easing.EaseOutQuad);
			}
		});
		composite.addMouseListener(new MouseListener() {
			int i = 0;
			@Override
			public void mouseDoubleClick(MouseEvent arg0) {
				// TODO Auto-generated method stub
							
			}

			@Override
			public void mouseDown(MouseEvent arg0) {
				// TODO Auto-generated method stub
				Properties p = new Properties();
				if (i%2==0)	p.set(Animotion.Property.WIDTH, 100);
				else p.set(Animotion.Property.WIDTH, 50);
				animate(p, 600, Animotion.Easing.EaseInElastic);
				i++;
			}

			@Override
			public void mouseUp(MouseEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});*/
		
	}
	public void paintControl (PaintEvent e) {
		Rectangle clientArea = composite.getClientArea(); 
		int width = (int) ((clientArea.width)*(Integer)this.property(Animotion.Property.WIDTH)/100); 
		int height = (int) ((clientArea.height)*(Integer)this.property(Animotion.Property.WIDTH)/100);
		//System.out.println(this.property(Animotion.Property.OPACITY));
		/*e.gc.setAdvanced(true);
		e.gc.setAlpha((Integer)this.property(Animotion.Property.OPACITY));*/
		e.gc.setBackground(this.colors.getColor(cell)); 
		
		Rectangle rec = new Rectangle((clientArea.width-width)/2, (clientArea.height-height)/2, width-1, height-1);
		e.gc.fillRectangle(rec);
		e.gc.setLineWidth(2);
		e.gc.setForeground(new Color(null,150,150,150)); 
		e.gc.drawRectangle(rec);
		e.gc.setAlpha(255);
		if ((Integer)this.property(100)>0) {	
			e.gc.setForeground(e.gc.getDevice().getSystemColor(SWT.COLOR_DARK_GREEN));
			/*e.gc.fillPolygon(new int[] {width-2,height-2,width-2,height*4/7,height*4/7,height-2});*/
			e.gc.drawString(((Integer)this.property(100)).toString(), height*3/7, height*3/7, true);
		}
		if ((Boolean)this.property(200)) {	
			e.gc.setBackground(e.gc.getDevice().getSystemColor(SWT.COLOR_DARK_RED));
			e.gc.fillPolygon(new int[] {2,2,2,height*2/7,height*2/7,2});
		}
	}

}
