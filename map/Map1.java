package map;

import world.objects.Space;
import world.objects.Wall;

public class Map1 extends Map{
	public Map1 (int width, int height) {
		super(width,height);
		Space space = new Space();
		Wall wall = new Wall();
		for (int i=1;i<=this.getWidth();i++) {
			for (int j=1;j<=this.getHeight();j++) {
				if (i==1 || i==this.getWidth() || j==1 || j== this.getHeight()) this.getCell(i,j).addObject(wall);
				else this.getCell(i,j).addObject(space);
			}
		}
		for (int p=5;p<=this.getWidth();p++) {
			this.getCell(4,p).addObject(wall);
		}
	}
}
