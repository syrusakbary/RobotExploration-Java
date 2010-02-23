package map;

import world.objects.Space;
import world.objects.Wall;

public class Map2 extends Map{
	public Map2 (int width, int height) {
		super(width,height);
		Space space = new Space();
		Wall wall = new Wall();
		for (int i=1;i<=this.getWidth();i++) {
			for (int j=1;j<=this.getHeight();j++) {
				if ((i==1 || i==this.getWidth())/* && (j!=5 && j!=6)*/) this.getCell(i,j).addObject(wall);
				else this.getCell(i,j).addObject(space);
			}
		}
		for (int p=5;p<=this.getWidth();p++) {
			this.getCell(3,p).addObject(wall);
		}
		for (int p=8;p<=13;p++) {
			this.getCell(p,10).addObject(wall);
		}
	}
}
