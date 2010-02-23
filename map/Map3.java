package map;

import world.objects.Space;
import world.objects.Wall;

public class Map3 extends Map{
	public Map3 (int width, int height) {
		super(width,height);
		Space space = new Space();
		Wall wall = new Wall();
		for (int i=1;i<=this.getWidth();i++) {
			for (int j=1;j<=this.getHeight();j++) {
				if (Math.random()>0.7) this.getCell(i,j).addObject(wall);
				else this.getCell(i,j).addObject(space);
			}
		}
	}
}
