package iPeer.Platform.Entities;

import java.awt.Rectangle;

public abstract class Entity {

	private String r /* I added this, but now I can't remember why :| */, name;
	private double x, y, xa, ya;
	
	private Rectangle player = new Rectangle();
	private Rectangle other = new Rectangle();
	
	public Entity(String name, int x, int y) {
		this.x = x;
		this.y = y;
		this.name = name;
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
	
	public void setXA (double d) {
		xa = d;
	}
	
	public void setYA (double d) {
		ya = d;
	}
	
	public void tick() { }
	
	public void draw() { }
	
	public void move() { tick(); }
	
	public boolean collidesWith(Entity other) { return true; }
	
	public abstract void collidedWith(Entity other);

}
