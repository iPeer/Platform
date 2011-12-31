package iPeer.Platform.Blocks;

import java.awt.Graphics;
import java.awt.Image;

import iPeer.Platform.Entities.*;
import iPeer.Platform.Graphics.SpriteHandler;
import iPeer.Platform.World.Level;

public class TestBlock extends Block {

	//private Image sprite; 
	 /* private double x, y; 
	 * private boolean solid, destroyable;
	 */

	public TestBlock(int id) {
		super(id);
		this.sprite = SpriteHandler.getSpriteFromSheet(0, 15, 16, 16);
	}

	public boolean isSolid(Level level, int x, int y, Entity entity) {
		if (entity instanceof EntityPlayer) 
			return true;
		return false;
	}
	
	public Image getSprite() {
		return sprite;
	}
	
	public void tick() {
	}

}
