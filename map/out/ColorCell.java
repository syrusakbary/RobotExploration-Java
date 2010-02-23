package map.out;

import java.util.HashMap;
import java.util.Iterator;

import map.Cell;

import org.eclipse.swt.graphics.Color;

public class ColorCell {
	private HashMap<Class,Color> colors = new HashMap<Class,Color>();
	public void addColor (Class classx, Color color) {
		this.colors.put(classx,color);
	}
	public Color getColor (Class classx) {
		if (this.colors.containsKey(classx)) return this.colors.get(classx);
		/*Iterator it = this.colors.keySet().iterator();
		while (it.hasNext()) {
			Class classxx = (Class) it.next();
			if (cell.lastObject().is(classxx))
				return (Color) this.colors.get(classx);
		}*/
		return new Color(null,255,255,255);

	}
	public Color getColor (Cell cell) {
		return this.getColor(cell.lastObject().getClass());
	}
}
