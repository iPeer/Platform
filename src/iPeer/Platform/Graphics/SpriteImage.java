package iPeer.Platform.Graphics;

import iPeer.Platform.Engine.Debug;

import java.awt.Graphics;
import java.awt.Image;

public class SpriteImage {
	
	private Image image;
	
	private static SpriteImage[][] spritecache;
	
	public SpriteImage(Image image) {
		this.image = image;
	}
	
	public void draw(Graphics g, int x, int y) {
		g.drawImage(image, x, y, null);
	}
	
	public static SpriteImage getSprite(int x, int y, int sx, int sy) {
		SpriteImage sprite;
		try {
			sprite = spritecache[x][y];
		}
		catch (NullPointerException e) {
			Debug.p("Sprite "+x+", "+y+" is not in cache.");
			sprite = new SpriteImage(SpriteHandler.getSpriteFromSheet(x, y, sx, sy));
			spritecache[x][y] = sprite;
		}
		return sprite;
	}

}
