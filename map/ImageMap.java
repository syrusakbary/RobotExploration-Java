package map;
import world.WorldObject;
import world.objects.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class ImageMap extends Map {

	private BufferedImage image;

	public ImageMap (String urlImage) {
		//super(this.image.getWidth(),this.image.getHeight());
	}
	public Map loadImage (String urlImage) {
		try {
			this.image = ImageIO.read(new File(urlImage));
			//map = new WorldObject[this.image.getWidth()][this.image.getHeight()];
			
			for (int x=0;x<this.image.getWidth();x++) {
				for (int y=0;y<this.image.getHeight();y++) {
					
					//setObjectInPosition(x,y,objectFromColor(new Color(this.image.getRGB(x,y))));
				}
			}
		}
		catch (IOException e) {}
		/*int  red = (c & 0x00ff0000) >> 16;
		int  green = (c & 0x0000ff00) >> 8;
		int  blue = c & 0x000000ff;
		// and the Java Color is ...*/
		//Color color = new Color(red,green,blue);
		return this;
	}
	
	public WorldObject objectFromColor (Color color) {
		/*for (int i=0;i<this.colorWorldObjects.length;i++) {
			if (this.colorWorldObjects[i].isColor(color)) {
				return this.colorWorldObjects[i].getWorldObject();
			}
		}*/
		return null;
	}
}
