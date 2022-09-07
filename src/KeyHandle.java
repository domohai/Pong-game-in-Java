import java.awt.event.*;

public class KeyHandle implements KeyListener {
	
	private boolean key_pressed[] = new boolean[128];
	
	@Override
	public void keyTyped(KeyEvent e) {}

	@Override
	public void keyPressed(KeyEvent e) {
		key_pressed[e.getKeyCode()] = true;
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		key_pressed[e.getKeyCode()] = false;
		
	}
	
	public boolean key_is_pressed(int keycode) {
		return key_pressed[keycode];
	}

}
