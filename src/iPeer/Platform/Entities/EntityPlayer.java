package iPeer.Platform.Entities;

import iPeer.Platform.Engine.InputHandler;
import iPeer.Platform.Engine.Main;
import iPeer.Platform.Graphics.SpriteHandler;

import java.awt.Image;

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

		setSprite(SpriteHandler.getSpriteFromSheet(0, 0, 16, 16));
		if ((input.left.down) && (getX() >= 1)) {
			xa--;
			setSprite(SpriteHandler.getSpriteFromSheet(1, 0, 16, 16));
		}
		if ((input.right.down) && (getX() <= main.getWidth() - spriteWidth()-1)) {
			xa++;
			setSprite(SpriteHandler.getSpriteFromSheet(2, 0, 16, 16));
		}
		;
		if ((input.up.down) && (getY() >= 1)) {
			ya--;
		}
		if ((input.down.down) && (getY() <= main.getHeight() - spriteHeight()-1)) {
			ya++;
		}
		if ((input.jump.down)) {
		}
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
