package iPeer.Platform.Engine;

import iPeer.Platform.Entities.Entity;
import iPeer.Platform.Entities.EntityPlayer;
import iPeer.Platform.Graphics.Colour;
import iPeer.Platform.Graphics.SpriteHandler;
import iPeer.Platform.World.Level;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Main extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	private int lastticks = 0, lastfps = 0;
	public boolean isRunning = false;
	SpriteHandler spritehandler = new SpriteHandler();
	Entity player;
	InputHandler input;
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private ArrayList<Entity> removeList = new ArrayList<Entity>();
	private boolean showDebugStuff = false, renderingEnabled = true;
	private long debugLastPressed = System.currentTimeMillis();
	private Level level;
	private Level[] levels;
	public int currentLevel;
	public static int HEIGHT = 480, WIDTH = 854;

	public Main() {
		Debug.p("Main Init");
		input = new InputHandler(this);
	}

	// start() & stop() are compatibility for Applets.

	public void start() {
		isRunning = true;
		(new Thread(this)).start();

	}

	public void stop() {
		isRunning = false;
	}

	// So we can run it as an application should we need to do.
	public static void main(String[] args0) {
		Main main = new Main();
		main.setMaximumSize(new Dimension(854, 480));
		main.setPreferredSize(new Dimension(854, 480));
		JFrame frame = new JFrame("Platform"); // Change Game Engine to your
													// game's name!
		frame.setDefaultCloseOperation(3);
		frame.setLayout(new BorderLayout());
		frame.add(main, "Center");
		// addKeyListener(new InputHandler(this));

		frame.pack();
		frame.setResizable(false); // change to true if you want users to be able
									// to resize the window.
		frame.setVisible(true);
		main.start();
	}

	public void init() {

		spritehandler.createSpriteSheet("iPeer/Platform/Graphics/sprites.png");
		resetGame();

	}

	public void resetGame() {
		currentLevel = 0;
		levels = new Level[10];
		levels[0] = new Level(getWidth(), getHeight(), 0);
		player = new EntityPlayer(this, input, "Player", 20, 40, 0, 0, 32, 32);
		level = levels[currentLevel];
		//entities.add(player);
		level.add(player);
		
	}
	
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double toprocess = 0.0;
		/*
		 * 
		 * This will limit the number of renders per second.
		 * 
		 * Dividing by 60, will limit the game to 60 renders/sec 30 will limit
		 * it to 30, 100 to 100 and so on.
		 */
		double nsPerTick = 1000000000 / 60.0;
		int frames = 0;
		int ticks = 0;
		long lastTick = System.currentTimeMillis();
		init(); // Run the init set above.
		while (isRunning) { // will loop while the game is running.
			long now = System.nanoTime();
			toprocess += (double) (now - lastTime) / nsPerTick;
			lastTime = now;
			boolean shouldRender;
			for (shouldRender = true; toprocess >= 1.0; shouldRender = true) {
				ticks++; // count this tick
				tick(); // run the tick.
				toprocess--; // remove 1 from the toprocess queue.
			}
			try {
				Thread.sleep(2); // make the thread sleep for 2ms. Can also be
									// changed if needed.
			} catch (InterruptedException e) {
				// If the thread is interrupted for whatever reason, report it.
				e.printStackTrace();
			}
			if (shouldRender) {
				frames++;
				render();
			}
			if (System.currentTimeMillis() - lastTick > 1000) {
				lastTick = System.currentTimeMillis();
				// Output the tick & FPS counts (debug).
				//System.out.println(ticks + " ticks, " + frames + " fps");
				lastfps = frames;
				lastticks = ticks;
				frames = ticks = 0; // reset them both to 0.
			}

		}
	}

	public void tick() {

		// Perform input ticks

		input.tick();
		level.tick();

		if (input.debug.down && (System.currentTimeMillis() - debugLastPressed > 150)) {
			showDebugStuff = !showDebugStuff;
			debugLastPressed = System.currentTimeMillis();
		}
		
		if (input.rendering.down && (System.currentTimeMillis() - debugLastPressed > 150)) {
			renderingEnabled = !renderingEnabled;
			debugLastPressed = System.currentTimeMillis();
		}

		// Perform the ticks of each entity

		for (int i = 0; i < entities.size(); i++) {
			Entity e = entities.get(i);
			e.tick();
		}

	}

	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(2); // This (the 2) can be changed if needed.
			requestFocus();
			return;
		}
		Graphics g = bs.getDrawGraphics();

		// This will render the game screen black
		g.setColor(Colour.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Colour.WHITE);
		g.drawString(lastfps + " (" + lastticks + ")", 0, getHeight() - 1);

		if (renderingEnabled) {

			// Do your rendering here. (using g)

			// Draw entities
			/*for (int i = 0; i < entities.size(); i++) {
				Entity e = entities.get(i);
				e.render(g);
			}*/
			level.renderOnScreenEntities(g);
			level.renderOnScreenBlocks(WIDTH, HEIGHT, g, level);
		}
		else {
			g.setColor(Colour.RED);
			String str = "Rending is disabled. Press F4 to re-enable rendering.";
			g.drawString(str, (getWidth() - g.getFontMetrics().stringWidth(str)) / 2, 10);
		}
		// Render debug last, so it is always on top.
		if (showDebugStuff) {
			g.setColor(Colour.WHITE);
			g.drawString("B: "+level.blockCount, 2, 10);
			g.drawString("ENTITIES (" + level.entities.size() + ")", 2, 40);
			int strpos = 50;
			for (int e = 0; e < level.entities.size(); e++) {
				Entity e1 = (Entity)level.entities.get(e);
				g.drawString("ID: " + e + ", \"" + e1.getName() + "\", "
						+ e1.getX() + ", " + e1.getY() + ", " + e1.getXA()
						+ ", " + e1.getYA() + ", " + e1.spriteWidth()
						+ ", " + e1.spriteHeight(), 2, strpos);
				strpos += 10;
			}
		}
		g.dispose();
		bs.show();

	}
	
	public static boolean isOnScreen(int x, int y) {
		return ((x <= WIDTH) && (x >= 0)) && ((y <= HEIGHT) && (y >= 0));
	}

}
