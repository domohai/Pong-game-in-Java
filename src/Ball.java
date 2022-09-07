
public class Ball {
	public Rect rect, paddle_left, paddle_right;
	private double vx = -200.0, vy = 0.0;
	
	Ball(Rect ball_rect, Rect paddle_left, Rect paddle_right) {
		this.rect = ball_rect;
		this.paddle_left = paddle_left;
		this.paddle_right = paddle_right;
		
	}
	
	public double calculate_new_v_angle(Rect paddle) {
		double intersect_relative_y = (paddle.y + (paddle.height / 2.0)) - (this.rect.y + (this.rect.height / 2.0));
		double normal_y = intersect_relative_y / (paddle.height / 2.0);
		double theta = normal_y * Const.MAX_ANGLE;
		return Math.toRadians(theta);
		
	}
	
	public void update(double deltatime) {
		//double check = this.rect.x + vx * deltatime;
		if (vx <= 0.0) {
			if ((this.rect.x <= (this.paddle_left.x + this.paddle_left.width)) && (this.rect.x >= this.paddle_left.x) &&
				(this.rect.y + this.rect.height >= this.paddle_left.y && 
				(this.rect.y <= (this.paddle_left.y + this.paddle_left.height)))) {
				double theta = calculate_new_v_angle(paddle_left);
				double newVx = Math.abs((Math.cos(theta)) * Const.BALL_SPEED);
				double newVy = (-Math.sin(theta)) * Const.BALL_SPEED;
				
				double old_sign = Math.signum(vx);
				this.vx = newVx * (-1.0 * old_sign);
				this.vy = newVy;
				
			} else if (this.rect.x < this.paddle_left.x) {
				
			}
		} else if (vx >= 0.0) {
			if ((this.rect.x + this.rect.width >= this.paddle_right.x) &&
				(this.rect.x + this.rect.width <= this.paddle_right.x + this.paddle_right.width) &&
				(this.rect.y + this.rect.height >= this.paddle_right.y && 
				(this.rect.y <= this.paddle_right.y + this.paddle_right.height))) {
				double theta = calculate_new_v_angle(paddle_right);
				double newVx = Math.abs((Math.cos(theta)) * Const.BALL_SPEED);
				double newVy = (-Math.sin(theta)) * Const.BALL_SPEED;
				
				double old_sign = Math.signum(vx);
				this.vx = newVx * (-1.0 * old_sign);
				this.vy = newVy;
				
			} else if (this.rect.x > this.paddle_right.x + this.paddle_right.width) {
				
			}
		}
		
		if (vy <= 0.0) {
			if (this.rect.y <= Const.TOOLBAR_TOP) {
				this.vy *= -1.0;
			}
			
		} else if (vy >= 0.0) {
			if (this.rect.y + this.rect.height >= Const.SCREEN_HEIGHT - Const.TOOLBAR_BOTTOM) {
				this.vy *= -1.0;
			}
		}
		
		this.rect.x += vx * deltatime;
		this.rect.y += vy * deltatime;
	}
	
}
