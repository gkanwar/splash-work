package splash.game.helicopter;

import java.util.LinkedList;
import java.util.Random;

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

public class HeliView extends View
{
	//Random number generator
	private Random mRand = new Random();
	//The speed of the helicopter
	private int speed = 4;
	//Some helicopter dimensions
	private final int heliWidth  = 60;
	private final int heliHeight = 20;
	//Starting state constants
	private int heliStartX,heliStartY;
	//Score variable
	private int score = 0;
	//Helicopter location (this is actually fixed)
	private int heliX = 10,heliY;
	//Velocity of the helicopter
	private float heliVel = 0;
	//The screenSize variable (defines width and height)
	private Point screenSize = new Point();
	//List of walls and blocks
	private LinkedList<Wall> walls = new LinkedList<Wall>();
	private LinkedList<Block> blocks = new LinkedList<Block>();
	//Variable to keep track of if we're accelerating upwards
	private boolean accel = false;
	
	//Our paint
	private Paint mPaint = new Paint();
	
	public HeliView(Context context, AttributeSet attrs)
	{
		super(context,attrs);
	}
	
	//TODO: Implement onSizeChanged such that it sets screenSize and sets the helicopter location
	//(we can't set this before, because we need to know the dimensions to not place it off the screen)
	//Also call resetGame() here... this is basically our setup function
	
	@Override
	protected void onDraw (Canvas canvas)
    {
    	mPaint.setColor(Color.GREEN);
    	Block tempBlock;
		Rect r;
    	Wall tempWall;
		for (int i = 0; i < blocks.size(); i++)
		{
			tempBlock = blocks.get(i);
			r = new Rect(tempBlock.getX(), tempBlock.getY() - Block.BLOCK_HEIGHT,
					tempBlock.getX() + Block.BLOCK_WIDTH, tempBlock.getY());
		}
		for (int j = 0; j < walls.size(); j++)
		{
			tempWall = walls.get(j);
			r = new Rect(tempWall.getX(), canvas.getHeight() - tempWall.getHeight(),
					tempWall.getX() + Wall.WALL_WIDTH, canvas.getHeight());
		}
		
		//TODO: Draw the helicopter
		
		updatePhysics();
		invalidate();
    }

	private void updatePhysics()
	{
		Wall tempWall;
		int wallsLen = walls.size();
		for (int i = 0; i < wallsLen; i++)
		{
			tempWall = walls.get(i);
			//TODO: Step the wall
			
			//TODO: Check to see if the last wall is one WALL_WIDTH away, and if so create the next wall
		}
		
		//TODO: Check to see if the first wall is off the screen, if so remove it from the linked list
		
		Block tempBlock;
		for (int i = 0; i < blocks.size(); i++)
		{
			tempBlock = blocks.get(i);
			//TODO: Step the block
		}
		
		//TODO: Check to see if the first block is off the screen, if so remove it from the linked list
		
		// 1/100 chance of adding a block
		if (mRand.nextInt(100) == 0)
		{
			//TODO: Add a block
		}
		
		//TODO: Increment/decrement the velocity depending on whether accel is true

		//TODO: Use checkCollision() to determine whether the game has ended, if not increment score
		
		//TODO: Update the helicopter's y position
	}
	
	private void resetGame()
	{
		heliX = heliStartX;
		heliY = heliStartY;
		heliVel = 0;
		score = 0;
		walls.clear();
		blocks.clear();
		
		for (int i = 0; i < screenSize.x + Wall.WALL_WIDTH; i += Wall.WALL_WIDTH)
		{
			walls.addLast(new Wall(i, screenSize.y / 10));
		}
	}
	
	private boolean checkCollision()
	{
		Rect heliRect = new Rect (heliX, heliY, heliX + heliWidth, heliY + heliHeight);
		
		Block tempBlock;
		for (int i = 0; i < blocks.size()/2;i++)
		{
			tempBlock = blocks.get(i);
			Rect r = new Rect(tempBlock.getX(), tempBlock.getY() - Block.BLOCK_HEIGHT,
					tempBlock.getX() + Block.BLOCK_WIDTH, tempBlock.getY());
			if(r.intersect(heliRect)) return true;
		}
		
		Wall tempWall;
		for (int j = 0; j < walls.size(); j++)
		{
			tempWall = walls.get(j);
			//TODO: Create two Rect objects for the pair of walls corresponding to this Wall object, then use Rect.intersects
		}

		//TODO: Check for the helicopter leaving the screen
		
		return false;
	}
	
	//TODO: Override onTouchEvent to set accel based on whether finger is down or not

}
