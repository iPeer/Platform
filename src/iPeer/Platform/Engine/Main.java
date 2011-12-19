package iPeer.Platform.Engine;

import iPeer.Platform.Graphics.Colour;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

public class Main extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;
	private int lastticks = 0, lastfps = 0;
	public boolean isRunning = false;

	// InputHandler input;

	public Main() {
		/* input = new InputHandler(this); */

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
		JFrame frame = new JFrame("Game Engine"); // Change Game Engine to your
													// game's name!
		frame.setDefaultCloseOperation(3);
		frame.setLayout(new BorderLayout());
		frame.add(main, "Center");
		// addKeyListener(new InputHandler(this));

		frame.pack();
		frame.setResizable(true); // change to true if you want users to be able
									// to resize the window.
		frame.setVisible(true);
		main.start();
	}

	public void init() {

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
		double nsPerTick = 1000000000 / 59.0;
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
				System.out.println(ticks + "ticks, " + frames + " fps");
				lastfps = frames;
				lastticks = ticks;
				frames = ticks = 0; // reset them both to 0.
			}

		}
	}

	public void tick() {
		// Do stuff that should be only ran so often here.
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
		g.setColor(Colour.PINK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Colour.WHITE);
		g.drawString(lastfps + " (" + lastticks + ")", 0, getHeight() - 1);

		// Do your rendering here. (using g)

		g.dispose();
		bs.show();

	}

}
