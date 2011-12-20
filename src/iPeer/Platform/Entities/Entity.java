package iPeer.Platform.Entities;

import iPeer.Platform.Engine.Main;
import iPeer.Platform.Graphics.SpriteHandler;
import iPeer.Platform.Graphics.SpriteImage;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public abstract class Entity {

	private String r /* I added this, but now I can't remember why :| */, name;
	private double x, y, xa, ya;
	
	private Main main;

	private Rectangle player = new Rectangle();
	private Rectangle other = new Rectangle();

	private Image sprite;

	public Entity(String name, int x, int y, int spritex, int spritey, int spritexw, int spritexh) {
		this.x = x;
		this.y = y;
		this.name = name;
		this.sprite = SpriteHandler.getSpriteFromSheet(spritex, spritey, spritexw, spritexh);
	}

	public void setSprite(Image sprite) {
		this.sprite = sprite;
	}
	
	public Image getSprite() {
		return sprite;
	}
	public int spriteWidth() {
		return sprite.getWidth(null);
	}
	
	public int spriteHeight() {
		return sprite.getHeight(null);
	}
	
	public String getName() {
		return name;
	}

	public int getX() {
		return (int) x;
	}

	public int getY() {
		return (int) y;
	}

	public int getXA() {
		return (int) xa;
	}

	public int getYA() {
		return (int) ya;
	}
	
	public void setX(double d) {
		this.x = d;
	}

	public void setY(double d) {
		this.y = d;
	}

	public void setXA(double d) {
		this.xa = d;
	}

	public void setYA(double d) {
		this.ya = d;
	}

	public void move(int xa, int ya) {
		x += xa;
		y += ya;
	}
	
	public void tick() {
	}

	public void render(Graphics g) {
		g.drawImage(sprite, (int)x, (int)y, null);
		//sprite.draw(g, (int) x, (int) y);
	}
	
	public boolean entityCanMoveHere() {
		return !((getX() <= 0) && (getX() >= main.getWidth()-spriteWidth()) && (getY() <= 0) && (getY() >= main.getWidth()-spriteHeight()));
	}


	public boolean collidesWith(Entity other) {
		return true;
	}

	public abstract void collidedWith(Entity other);

}
