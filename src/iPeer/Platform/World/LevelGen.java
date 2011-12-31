package iPeer.Platform.World;

import iPeer.Platform.Blocks.Block;
import iPeer.Platform.Engine.Debug;
import iPeer.Platform.Engine.Main;
import iPeer.Platform.Misc.Utils;

public class LevelGen extends Level {

	public LevelGen(int w, int h, int level) {
		super(w, h, level);
	}

	public static byte[] generateTestMap(int w, int h) {
		byte[] map = new byte[w * h];
		blockCount = 0;
		for (int y = 0; y < h; y += 16) {
			for (int x = 0; x < w; x += 16) {
				if (y >= Main.HEIGHT - 20 && !Utils.isBetween(x, 500, 600)) {
					blockCount++;
					Debug.p("Placed testblock @ (" + x + "," + y + ")");
					map[x + y * w] = Block.testblock.id;
				}
			}
		}
		return map;
	}
}
