package iPeer.Platform.Blocks;

import java.awt.Image;

import iPeer.Platform.Entities.*;
import iPeer.Platform.Graphics.SpriteHandler;
import iPeer.Platform.World.Level;

public class NoneBlock extends Block {

	private Image sprite; 
	 /* private double x, y; 
	 * private boolean solid, destroyable;
	 */

	public NoneBlock(int id) {
		super(id);
		//this.sprite = SpriteHandler.getSpriteFromSheet(15, 0, 16, 16);
	}

	public boolean isSolid(Level level, int x, int y, Entity entity) {
		return false;
	}
	
	public Image getSprite() {
		return sprite;
	}
	
	public void tick() {
	}

}
