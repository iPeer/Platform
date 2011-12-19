package iPeer.Platform.Engine;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class InputHandler implements KeyListener {

	public ArrayList<Key> keys = new ArrayList<Key>();

	public class Key {

		public boolean down, pressed, pressed1;
		public int presses, multi;

		public Key() {
			keys.add(this);
		}

		public void toggle(boolean flag) {
			if (down != pressed)
				down = pressed;
			if (pressed)
				presses++;
		}

		public void tick() {
			if (multi < presses) {
				multi++;
				pressed1 = true;
			} else {
				pressed1 = false;
			}
		}

	}

	public Key left = new Key();
	public Key right = new Key();
	public Key up = new Key();
	public Key down = new Key();
	public Key jump = new Key();

	public InputHandler(Main main) {
		main.addKeyListener(this);
	}
	
	private void toggle(Key ke, boolean flag) {
		ke.toggle(flag);	
	}

	public void keyPressed(KeyEvent e) {
		int k = e.getKeyCode();
		if (k == KeyEvent.VK_W) toggle(up, true);
		if (k == KeyEvent.VK_A) toggle(right, true);
		if (k == KeyEvent.VK_D) toggle(left, true);
		if (k == KeyEvent.VK_S) toggle(down, true);
		if (k == KeyEvent.VK_UP) toggle(up, true);
		if (k == KeyEvent.VK_RIGHT) toggle(right, true);
		if (k == KeyEvent.VK_LEFT) toggle(left, true);
		if (k == KeyEvent.VK_DOWN) toggle(down, true);
		
		if (k == KeyEvent.VK_SPACE) toggle(jump, true);
		if (k == KeyEvent.VK_J) toggle(jump, true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int k = e.getKeyCode();
		if (k == KeyEvent.VK_W) toggle(up, false);
		if (k == KeyEvent.VK_A) toggle(right, false);
		if (k == KeyEvent.VK_D) toggle(left, false);
		if (k == KeyEvent.VK_S) toggle(down, false);
		if (k == KeyEvent.VK_UP) toggle(up, false);
		if (k == KeyEvent.VK_RIGHT) toggle(right, false);
		if (k == KeyEvent.VK_LEFT) toggle(left, false);
		if (k == KeyEvent.VK_DOWN) toggle(down, false);
		
		if (k == KeyEvent.VK_SPACE) toggle(jump, false);
		if (k == KeyEvent.VK_J) toggle(jump, false);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
