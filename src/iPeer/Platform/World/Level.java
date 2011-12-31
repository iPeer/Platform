package iPeer.Platform.World;

import iPeer.Platform.Engine.Main;
import iPeer.Platform.Entities.Entity;
import iPeer.Platform.Misc.Utils;
import iPeer.Platform.Blocks.Block;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Level {

	private int x, y, level;
	public ArrayList<Entity> entities, removeList;
	//public byte[] map;
	public byte[] blocks;
	public static int blockCount = 0;

	public Level(int x, int y, int level) {
		this.x = x;
		this.y = y;
		this.level = level;
		entities = new ArrayList<Entity>();
		removeList = new ArrayList<Entity>();
		byte[] map;
		//if (level == 0) {
			map = LevelGen.generateTestMap(x, y);
		//}
		blocks = map;
	}

	public void add(Entity entity) {
		entities.add(entity);
	}

	public void remove(Entity entity) {
		removeList.add(entity);
	}

	public void tick() {
		for (int i = 0; i < entities.size(); i++) {
			Entity e = (Entity) entities.get(i);
			e.tick();
		}
		if (removeList.size() > 0)
			entities.removeAll(removeList);
	}

	public void renderOnScreenBlocks(int width, int height, Graphics g, Level l) {
		Rectangle screen = new Rectangle(new Dimension(width, height));
		for (int i = 0; i < width; i++) {
			for (int i2 = 0; i2 < height; i2++) {
				if (Main.isOnScreen(i, i2)) {
					getBlock(i, i2).render(g, i, i2);
				}
			}
		}
	}

	public void renderOnScreenEntities(Graphics g) {
		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			if (e.isOnScreen()) {
				e.render(g);
			}
		}
	}

	public void setBlock(int px, int py, Block b) {
		if (px < 0 || py < 0 || px > x || py > y) return;
		blocks[px + py * x] = b.id;
	}
	
	public Block getBlock(int px, int py) {
		if (px < 0 || py < 0 || px >= x || py >= y) return Block.testblock;
		return Block.blocks[blocks[px + py * x]];
	}
	
	// public Level testlevel = new TestLevel(100, 100, 0);

}
