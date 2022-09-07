import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Window extends JFrame implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Graphics2D g2D;
	KeyHandle key_listener = new KeyHandle();
	Rect player, bot, ball_rect;
	Ball ball;
	AI Ai;

	public Window() {
		this.setSize(Const.SCREEN_WIDTH, Const.SCREEN_HEIGHT);
		this.setResizable(false);
		this.setTitle(Const.SCREEN_TITLE);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		Const.TOOLBAR_TOP = this.getInsets().top;
		Const.TOOLBAR_BOTTOM = this.getInsets().bottom;
		
		this.addKeyListener(key_listener);
		
		g2D = (Graphics2D)this.getGraphics();
		
		player = new Rect(20, Const.SCREEN_HEIGHT/2, Const.PADDLE_WIDTH, Const.PADDLE_HEIGHT, Color.GREEN);
		bot = new Rect(Const.SCREEN_WIDTH-20-Const.PADDLE_WIDTH, 50, Const.PADDLE_WIDTH, Const.PADDLE_HEIGHT, Color.RED);
		ball_rect = new Rect(Const.SCREEN_WIDTH/2, Const.SCREEN_HEIGHT/2, Const.BALL_RADIUS*2, Const.BALL_RADIUS*2, Color.WHITE);
		
		ball = new Ball(ball_rect, player, bot);
		Ai = new AI(ball_rect, bot);
	}
	
	public void update(double deltatime) {
		
		Image db_image = createImage(getWidth(), getHeight());
		Graphics dbg = db_image.getGraphics();
		draw(dbg);
		
		g2D.drawImage(db_image, 0, 0, this);
		
		ball.update(deltatime);
		//System.out.println(1/deltatime + "fps");
		
		// movement
		if (key_listener.key_is_pressed(KeyEvent.VK_UP)) {
			player.move(-deltatime);
		} else if (key_listener.key_is_pressed(KeyEvent.VK_DOWN)) {
			player.move(deltatime);
		}
		// AI movement
		Ai.move(deltatime);
		System.out.println(Const.TOOLBAR_BOTTOM);
		System.out.println(Const.TOOLBAR_TOP);
		
	}
	
	public void draw(Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		
		g2D.setColor(Color.BLACK);
		g2D.fillRect(0, 0, Const.SCREEN_WIDTH, Const.SCREEN_HEIGHT);
		
		player.draw(g2D, false);
		bot.draw(g2D, false);
		ball_rect.draw(g2D, true);
	}

	@Override
	public void run() {
		double lastframe_time = 0.0;
		while (true) {
			// calculate delta time
			double time = Time.get_time();
			double delta_time = time - lastframe_time;
			lastframe_time = time;
			
			update(delta_time);
			
			
			// 30fps
			try {
				Thread.sleep(15);
			} catch (Exception e) {
				
			}
			
		}
		
	}
	
}
