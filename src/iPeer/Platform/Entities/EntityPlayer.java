package iPeer.Platform.Entities;

import java.awt.Image;

import iPeer.Platform.Engine.Debug;
import iPeer.Platform.Engine.InputHandler;
import iPeer.Platform.Engine.Main;
import iPeer.Platform.Graphics.SpriteHandler;

public class EntityPlayer extends Entity {

	private InputHandler input;
	private int x, y;
	private Image sprite;
	private Main main;
	
	public EntityPlayer(Main main, InputHandler input, String n, int x, int y, int spritex, int spritey, int spritexw, int spritexh) {
		super(n, x, y, spritex, spritey, spritexw, spritexh);
		this.input = input;
		this.x = x;
		this.y = y;
		this.main = main;
		this.sprite = getSprite();
	}
	
	public void tick() {
		
		super.tick();
	
		int ya = 0;
		int xa = 0;
		
		if ((input.left.down) && (getX() >= 1)) { xa--; }
		if ((input.right.down) && (getX() <= main.getWidth()-spriteWidth())) { xa++; }
		if ((input.up.down) && (getY() >= 1)) { ya--; }
		if ((input.down.down) && (getY() <= main.getHeight()-spriteHeight())) { ya++; }
		if ((input.jump.down)) { }
		move(xa, ya);
		
	}
	
	public void setSprite(Image image) {
		super.setSprite(image);
	}
	public Image getSprite() {
		return this.sprite;
	}

	@Override
	public void collidedWith(Entity other) {
		// TODO Auto-generated method stub

	}

}
