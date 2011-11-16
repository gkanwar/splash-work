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
	
	
    public PongView(Context context, AttributeSet attrs ) {
		super(context,attrs);
		// TODO Auto-generated constructor stub
		
	}
    public void setScreenSize( Point ss )
    {
    	screenSize = ss;
    	ss.y -= 50;
    	ballXStart = screenSize.x/2;
    	ballYStart = screenSize.y/2;
    }
	protected void onDraw ( Canvas canvas )
    {
    	Paint paint = new Paint();
    	paint.setColor(Color.GREEN);
    	updatePositions();
    	if ( checkScore() )
    	{
    		resetBall();
    	}
    	setScreenSize( new Point( canvas.getWidth(), canvas.getHeight() ) );
    	//draw the paddles and ball
    	canvas.drawRect(new Rect(0,paddleYDim + firstPaddleLoc,paddleXDim,firstPaddleLoc), paint);
    	canvas.drawRect( new Rect(screenSize.x - paddleXDim, secondPaddleLoc, screenSize.x,secondPaddleLoc + paddleYDim),paint);
    	canvas.drawCircle(ballXLoc, ballYLoc, ballRad, paint);
    	canvas.drawText(""+PlayerOneScore, screenSize.x/4, screenSize.y/4, paint);
    	canvas.drawText(""+PlayerTwoScore, 3*screenSize.x/4,screenSize.y/4,paint);
    	invalidate();
    }
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


	    secondPaddleLoc += ballYLoc > secondPaddleLoc + paddleYDim/2 ? Math.min( Math.abs(ballYLoc - secondPaddleLoc - paddleYDim/2), paddleVelMax ) 
	    						: -Math.min( Math.abs(ballYLoc - secondPaddleLoc - paddleYDim/2), paddleVelMax );

    	if ( (checkCollision(0) || checkCollision(1)) && lastCollision == 0)
    	{
    		ballXVel *= -1;
    		ballXVel*=1.1;
        	ballXVel*=1.1;
        	
    		lastCollision = 20;
    	}
    	ballXLoc += ballXVel;
    	ballYLoc += ballYVel;
    	if ( lastCollision > 0 )
    	{
    		lastCollision--;
    	}
    	if ( ballYLoc > screenSize.y  || ballYLoc < 0 )
    	{
    		ballYVel*=-1;
    	}
    }
    private boolean checkCollision( int paddle) //paddle should be 0 or 1
    {
    	if ( ballYLoc >= firstPaddleLoc && ballYLoc <= firstPaddleLoc + paddleYDim )
    	{
    		if ( (paddle != 0 && ballXLoc - ballRad <= paddleXDim) )
    		{
    			return true;
    		}
    	}
    	if ( ballYLoc >= secondPaddleLoc && ballYLoc <= secondPaddleLoc + paddleYDim )
    	{
    		if ( ballXLoc + ballRad >= screenSize.x -paddleXDim )
    		{
    			return true;
    		}
    	}
    	return false;
    }
    private boolean checkScore ()
    {
    	if(screenSize.x > 50 )
	    {
	    	if ( ballXLoc > screenSize.x )
	    	{
	    		PlayerOneScore++;
	    		return true;
	    	}
	    	else if ( ballXLoc < 0 )
	    	{
	    		PlayerTwoScore++;
	    		return true;
	    	}
	    }
    	return false;
    }

}
