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
	private Random mRand = new Random();
	private int speed = 4;
	private final int heliWidth  = 60;
	private final int heliHeight = 20;
	private int heliStartX,heliStartY;
	private int score = 0;
	private int heliX = 10,heliY;
	private float heliVel = 0;
	private Point screenSize = new Point();
	private LinkedList<Wall> walls = new LinkedList<Wall>(); //x current, y current, length
	private LinkedList<Block> blocks = new LinkedList<Block>();
	private boolean accel = false;
	
	private Paint mPaint = new Paint();
	
	public HeliView(Context context, AttributeSet attrs)
	{
		super(context,attrs);
	}
	
	@Override
	public void onSizeChanged(int width, int height, int oldw, int oldh)
	{
		screenSize.x = width;
		screenSize.y = height;
		
		heliStartX = 10;
		heliStartY = screenSize.y/2 - heliHeight/2;
		resetGame();
	}
	
	@Override
	protected void onDraw (Canvas canvas)
    {
    	mPaint.setColor(Color.GREEN);
    	Block tempBlock;
    	Wall tempWall;
		for (int i = 0; i < blocks.size(); i++)
		{
			tempBlock = blocks.get(i);
			canvas.drawRect(tempBlock.getX(), tempBlock.getY(), tempBlock.getX() + Block.BLOCK_WIDTH, tempBlock.getY() + Block.BLOCK_HEIGHT, mPaint);
		}
		for (int j = 0; j < walls.size(); j++)
		{
			tempWall = walls.get(j);
			canvas.drawRect(tempWall.getX(), 0, tempWall.getX() + Wall.WALL_WIDTH, tempWall.getHeight(), mPaint);
			canvas.drawRect(tempWall.getX(), screenSize.y - tempWall.getHeight(), tempWall.getX() + Wall.WALL_WIDTH, screenSize.y,  mPaint);
		}
		
		mPaint.setColor(Color.BLUE);
		canvas.drawRect(heliX, heliY, heliX + heliWidth, heliY + heliHeight, mPaint);
		
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
			tempWall.step(speed);
			
			if (i == wallsLen - 1 && tempWall.getX() + Wall.WALL_WIDTH < screenSize.x)
			{
				int height;
				if(tempWall.getHeight() > screenSize.y/3)
				{
					height = tempWall.getHeight() - screenSize.y/10;
				}
				else if(tempWall.getHeight() < 0)
				{
					height = tempWall.getHeight() + screenSize.y/10;
				}
				else
				{
					height = tempWall.getHeight() + mRand.nextInt(screenSize.y / 10) - screenSize.y/20;
				}
				int prevLoc = tempWall.getX();
				
				walls.addLast(new Wall(prevLoc + Wall.WALL_WIDTH, height));
			}
		}
		
		if (walls.size() > 0 && walls.get(0).getX() + Wall.WALL_WIDTH <= 0)
		{
			walls.removeFirst();
		}
		
		Block tempBlock;
		for (int i = 0; i < blocks.size(); i++)
		{
			tempBlock = blocks.get(i);
			tempBlock.step(speed);
		}
		
		if(blocks.size() > 0 && blocks.get(0).getX() + Block.BLOCK_WIDTH <= 0)
		{
			blocks.removeFirst();
		}
		
		// 1/100 chance of adding a block
		if (mRand.nextInt(100) == 0)
		{
			blocks.addLast(new Block(screenSize.x, mRand.nextInt((7/6) * screenSize.y) - screenSize.y / 6));
		}
		
		if (accel)
		{
			heliVel -= 0.5;
		}
		else
		{
			heliVel += 0.5;
		}

		if (checkCollision())
		{
			resetGame();
		}
		else
		{
			score++;
		}
		
		heliY += heliVel;
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
			Rect blockRect = new Rect(tempBlock.getX(), tempBlock.getY(), tempBlock.getX() + Block.BLOCK_WIDTH, tempBlock.getY() + Block.BLOCK_HEIGHT);
			if (Rect.intersects(heliRect, blockRect))
			{
				return true;
			}
		}
		
		Wall tempWall;
		for (int j = 0; j < walls.size(); j++)
		{
			tempWall = walls.get(j);
			Rect wallRectOne = new Rect(tempWall.getX(), 0, tempWall.getX() + Wall.WALL_WIDTH, tempWall.getHeight());
			Rect wallRectTwo = new Rect(tempWall.getX(), screenSize.y - tempWall.getHeight(), tempWall.getX() + Wall.WALL_WIDTH, screenSize.y);
			if (Rect.intersects(heliRect, wallRectOne) || Rect.intersects(heliRect, wallRectTwo))
			{
				return true;
			}
		}
		
		if (heliY > screenSize.y)
		{
			return true;
		}
		
		return false;
	}
	
	public boolean onTouchEvent(MotionEvent event) 
    {
    	if (event.getAction() == MotionEvent.ACTION_DOWN)
    	{
    		accel = true;
    	}
    	else if (event.getAction() == MotionEvent.ACTION_UP)
    	{
    		accel = false;
    	}
    	return true;
    }

}
