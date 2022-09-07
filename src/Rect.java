import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

public class Rect {
	public double x, y, width, height;
	public Color color;
	
	public Rect(double x, double y, double width, double height, Color color) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
	}
	
	public void draw(Graphics2D g2D, boolean draw_oval) {
		g2D.setColor(this.color);
		if (!draw_oval) {
			g2D.fill(new Rectangle2D.Double(this.x, this.y, this.width, this.height));
		} else {
			g2D.fillOval((int)this.x, (int)this.y, (int)this.width, (int)this.height);
			
		}
	}
	
	public void move(double deltatime) {
		double check = this.y + Const.PADDLE_SPEED * deltatime;
		if (check > Const.TOOLBAR_TOP && check + Const.PADDLE_HEIGHT < Const.SCREEN_HEIGHT - Const.TOOLBAR_BOTTOM)
		this.y += Const.PADDLE_SPEED * deltatime;
		
	}
}
