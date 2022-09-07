
public class AI {
	Rect ball, paddle;
	
	public AI (Rect ball, Rect paddle) {
		this.ball = ball;
		this.paddle = paddle;
	}
	
	public void move(double deltatime) {
		if ((this.ball.y < (this.paddle.y + this.paddle.height / 3))) {
			this.paddle.y -= Const.PADDLE_SPEED * deltatime;
		} else if ((this.ball.y > (this.paddle.y + this.paddle.height - this.paddle.height / 3))) {
			this.paddle.y += Const.PADDLE_SPEED * deltatime;
		}
		if (this.paddle.y < Const.TOOLBAR_TOP) this.paddle.y += Const.PADDLE_SPEED * deltatime;
		if (this.paddle.y + Const.PADDLE_HEIGHT > Const.SCREEN_HEIGHT - Const.TOOLBAR_BOTTOM) 
			this.paddle.y -= Const.PADDLE_SPEED * deltatime;
	}
	
}
