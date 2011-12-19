package iPeer.Platform.Entities;

import iPeer.Platform.Engine.InputHandler;

public class Player extends Entity {

	private InputHandler input;
	
	public Player(InputHandler input, String n, int x, int y) {
		super(n, x, y);
		this.input = input;
	}
	
	public void tick() {
		if (input.left.down) { }
		if (input.right.down) { }
		if (input.up.down) { }
		if (input.down.down) { }
		if (input.jump.down) { }
	}

	@Override
	public void collidedWith(Entity other) {
		// TODO Auto-generated method stub

	}

}
