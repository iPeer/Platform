package iPeer.Platform.Blocks;

import java.awt.Graphics;
import java.awt.Image;

public class Block {

	public Image sprite;
	private double x, y;
	private boolean solid, destroyable;

	// Image sprite, int x, int y, boolean solid, boolean destroyable

	public final byte id;
	public static Block blocks[] = new Block[256];
	public static Block testblock = new TestBlock(1);
	public static Block none = new NoneBlock(0);

	public Block(int id) {

		this.id = (byte) id;
		if (blocks[id] != null)
			throw new RuntimeException("Duplicate block id " + id);
		blocks[id] = this;
		return;
	}

	public void tick() {

	}

	public void render(Graphics g, int posx, int posy) {
		g.drawImage(sprite, posx, posy, null);
	}


}
