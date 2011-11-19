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
	//Point that stores the screen size (use screenSize.x and screenSize.y to get width and height)
	Point screenSize = new Point();
	//Max velocity of the AI paddle
	private static final int AI_MAX_VEL = 6;
	//The ball's radius (in pixels)
	private static final int BALL_RADIUS = 4;
	//The paddle's dimensions
	private static final int PADDLE_WIDTH = 10;
	private static final int PADDLE_HEIGHT = 40;
	//Variables used to set the starting conditions
	private int ballXStart, ballYStart, ballStartVelX = 2, ballStartVelY = 2;
	//Variables to keep track of the paddles and ball
	private int firstPaddleLoc = 0, secondPaddleLoc = 0, ballXLoc = 50, ballYLoc = 50;
	private float ballXVel = 2,ballYVel = 2;
	
	//Keep track of our last collision to make sure the ball doesn't collide twice in a row with the same object
	private int lastCollision = 0;
	
	//Variables to track scores
	private int playerOneScore = 0;
	private int playerTwoScore = 0;
	
	//Our paint
	private Paint mPaint = new Paint();
	
	
    public PongView(Context context, AttributeSet attrs)
    {
		super(context,attrs);
	}
    
	protected void onDraw (Canvas canvas)
    {
		//TODO: Update our position (see functions below)
		//TODO: Update score and check for game over (see functions below)
    	
    	//TODO: Draw the paddles, ball and scores
    	
		//Invalidate so that it repeatedly draws, instead of just once
    	invalidate();
    }
	
	//TODO: Override onSizeChanged so that screenSize.x is set to width and screenSize.y is set to height
	
	//TODO: Override onTouchEvent to set the player's paddle location to the y coordinate of the touch
	
    private void resetBall()
    {
    	ballXLoc = ballXStart;
    	ballYLoc = ballYStart;
    	ballXVel = ballStartVelX;
    	ballYVel = ballStartVelY;
    }
    
    private void updatePositions()
    {
    	//Pseudo-AI -> computer always moves towards the ball with limited velocity
    	//Feel free to improve this :)
	    secondPaddleLoc += (ballYLoc > secondPaddleLoc + PADDLE_HEIGHT/2 ? Math.min( Math.abs(ballYLoc - secondPaddleLoc - PADDLE_HEIGHT/2), AI_MAX_VEL ) 
	    						: -Math.min( Math.abs(ballYLoc - secondPaddleLoc - PADDLE_HEIGHT/2), AI_MAX_VEL ));
    	
    	//TODO: Apply velocities to the ball's position
	    
	    //TODO: Check collisions with paddles (see functions below) and change velocity based on it
	    //Note: Make sure to check last collision to avoid colliding twice in a row
	    //You could implement a system that increments velocity with every paddle collision
    	
    	//TODO: Decrement lastCollision
    	
    	//TODO: Check collisions with walls and change velocity based on it
    }
    
    private boolean checkCollision(int paddle) //Paddle should be 0 or 1
    {
    	if(paddle == 0) //Computer (higher x) paddle
    	{
    		//TODO: Check whether the ball is within the computer's paddle
    	}
    	else
    	{
    		//TODO: Check whether the ball is within the player's paddle
    	}

    	return false;
    }
    
    private boolean checkAndUpdateScore()
    {
    	//TODO: Check whether the ball went off either edge and change scores
    	
    	return false;
    }
}
