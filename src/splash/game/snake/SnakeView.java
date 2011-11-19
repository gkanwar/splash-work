package splash.game.snake;

import java.util.LinkedList;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class SnakeView extends View
{
	//Start off with a snake of length 5 at (1, 1) facing right
	private Snake mSnake = new Snake(1, 1, 5, Snake.RIGHT);
	//Variables to keep track of how large our screen and board are
	private int width, height, boardWidth, boardHeight;
	//Current location of the food
	private int foodX, foodY;
	//Current score
	private int score = 0;
	
	//Constant that determines grid size in pixels
	public static final int GRID_SIZE = 20;
	//Constant that determines the time step (for updating) in number of frames
	public static final int TIME_STEP = 5;
	
	//Our current frame in the timestep
	private int curFrame = 0;
	
	//A random number generator
	private Random mRand = new Random();
	
	//Our paint
	private Paint mPaint = new Paint();
	
	public SnakeView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}
	
	//TODO: Override onSizeChanged to set width and height to be
	//the screen width and height rounded down to the nearest GRID_SIZE.
	//Also set boardWidth and boardHeight to be those values divided by
	//GRID_SIZE. We're also going to generate our initial food here
	//instead of the constructor, because we need the boardWidth and height.

	@Override
	public void onDraw(Canvas canvas)
	{
		//TODO: Draw the snake (loop through the points the snake has and draw circles)
		
		//TODO: Draw the food (you can just draw a rectangle in the right location)
		
		//TODO: In the case where curFrame is the last one of the time step update locations:
		//Call the moveAndCheck function on the snake (go to game over if this fails)
		//Check for a food collision (see function below), and resolve appropriately
	    
		//Increment the current frame and invalidate so that it redraws
	    curFrame++;
	    invalidate();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		if(event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE)
		{
			//TODO: Check which region of the screen the touch happened in and set the direction accordingly
		}
		
		return true;
	}
	
	private void generateFood()
	{
		//TODO: Try to generate the food until there's no food collision (see function below)
	}
	
	private boolean foodCollision()
	{
		//TODO: Return true if the food collides with the head of the snake
		
		return true;
	}
}
