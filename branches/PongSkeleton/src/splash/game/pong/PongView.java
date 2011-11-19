package splash.game.pong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class PongView extends View
{
	Point screenSize = new Point();
	final int paddleVelMax = 6;
	final int ballRad = 4;
	final int paddleXDim = 10;
	final int paddleYDim = 40;
	int ballXStart;
	int ballYStart;
	int ballStartVelX = 2;
	int ballStartVelY = 2;
	int firstPaddleLoc = 0,secondPaddleLoc =0,ballXLoc = 50,ballYLoc = 50;
	float ballXVel = 2,ballYVel = 2;
	int lastCollision = 0;
	int PlayerOneScore = 0;
	int PlayerTwoScore = 0;
	
	private Paint mPaint = new Paint();
	
	
    public PongView(Context context, AttributeSet attrs)
    {
		super(context,attrs);
	}
    
	protected void onDraw (Canvas canvas)
    {
    	updatePositions();
    	if (checkScore())
    	{
    		resetBall();
    	}
    	
    	//Draw the paddles and ball
    	mPaint.setColor(Color.GREEN);
    	canvas.drawRect(new Rect(0, paddleYDim + firstPaddleLoc, paddleXDim, firstPaddleLoc), mPaint);
    	canvas.drawRect(new Rect(screenSize.x - paddleXDim, secondPaddleLoc, screenSize.x, secondPaddleLoc + paddleYDim),mPaint);
    	canvas.drawCircle(ballXLoc, ballYLoc, ballRad, mPaint);
    	canvas.drawText(""+PlayerOneScore, screenSize.x/4, screenSize.y/4, mPaint);
    	canvas.drawText(""+PlayerTwoScore, 3*screenSize.x/4, screenSize.y/4, mPaint);
    	
    	invalidate();
    }
	
	@Override
	public void onSizeChanged(int width, int height, int oldw, int oldh)
	{
		screenSize.x = width;
		screenSize.y = height;
	}
	
	@Override
    public boolean onTouchEvent(final MotionEvent event) 
    {
    	firstPaddleLoc = (int) event.getY();
    	return true;
    }
	
    private void resetBall()
    {
    	ballXLoc = ballXStart;
    	ballYLoc = ballYStart;
    	ballXVel = ballStartVelX;
    	ballYVel = ballStartVelY;
    }
    
    private void updatePositions()
    {
    	//Pseudo-AI -> computer always moves towards the ball
	    secondPaddleLoc += (ballYLoc > secondPaddleLoc + paddleYDim/2 ? Math.min( Math.abs(ballYLoc - secondPaddleLoc - paddleYDim/2), paddleVelMax ) 
	    						: -Math.min( Math.abs(ballYLoc - secondPaddleLoc - paddleYDim/2), paddleVelMax ));
    	
    	
    	ballXLoc += ballXVel;
    	ballYLoc += ballYVel;
    	
    	if ((checkCollision(0) || checkCollision(1)) && lastCollision == 0)
    	{
    		ballXVel *= -(1.2);
        	
    		lastCollision = 20;
    	}
    	if (lastCollision > 0 )
    	{
    		lastCollision--;
    	}
    	
    	if (ballYLoc > screenSize.y  || ballYLoc < 0 )
    	{
    		ballYVel*=-1;
    	}
    }
    
    private boolean checkCollision(int paddle) //Paddle should be 0 or 1
    {
    	if(paddle == 0) //Computer (higher x) paddle
    	{
    		if(ballYLoc >= secondPaddleLoc && ballYLoc <= secondPaddleLoc + paddleYDim && ballXLoc + ballRad >= screenSize.x - paddleXDim)
    		{
    			return true;
    		}
    	}
    	else
    	{
    		if(ballYLoc >= firstPaddleLoc && ballYLoc <= firstPaddleLoc + paddleYDim && ballXLoc - ballRad <= paddleXDim)
    		{
    			return true;
    		}
    	}

    	return false;
    }
    
    private boolean checkScore()
    {
    	if (ballXLoc > screenSize.x)
    	{
    		PlayerOneScore++;
    		return true;
    	}
    	else if (ballXLoc < 0)
    	{
    		PlayerTwoScore++;
    		return true;
    	}
    	
    	return false;
    }
}
