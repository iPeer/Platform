package iPeer.Platform.Graphics;

import iPeer.Platform.Engine.Debug;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteHandler {

	static BufferedImage spritesheet;

	public SpriteHandler() {
	}

	public void createSpriteSheet(String r) {
		try {
			Debug.p(r);
			//Debug.p(this.getClass().getResource(r));
			//Debug.p(this.getClass().getClassLoader().getResource(r).getFile());
			
			BufferedImage image = ImageIO.read(this.getClass().getClassLoader().getResource(r));
			spritesheet = image;
		} catch (IOException e) {
			Debug.p("Unable to create Sprite sheet!");
			e.printStackTrace();
		}
	}

	public static Image getSpriteFromSheet(int x, int y, int r, int c) {
		BufferedImage sprite = spritesheet.getSubimage(x*r, y*c, r, c);
		return (Image)sprite;
	}

}
