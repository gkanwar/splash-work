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
	private Snake mSnake = new Snake(1, 1, 5, Snake.RIGHT);
	private int width, height, boardWidth, boardHeight;
	private int foodX, foodY;
	private int score = 0;
	
	public static final int GRID_SIZE = 20;
	public static final int TIME_STEP = 5;
	
	private int curFrame = 0;
	
	private Random mRand = new Random();
	
	private Paint mPaint = new Paint();
	
	public SnakeView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}
	
	protected void onSizeChanged (int w, int h, int oldw, int oldh)
	{
		//Set boardWidth and boardHeight
		boardWidth = w/GRID_SIZE;
		boardHeight = h/GRID_SIZE;
		//Get width and height rounded down to the nearest 4
		width = boardWidth*GRID_SIZE;
		height = boardHeight*GRID_SIZE;
		
		generateFood();
	}

	@Override
	public void onDraw(Canvas canvas)
	{
		//Draw snake
		LinkedList<Point> tempPoints = mSnake.getPoints();
		mPaint.setColor(Color.BLACK);
		canvas.drawRect(0, 0, width, height, mPaint);
	    mPaint.setColor(Color.GREEN);
	    for (int i = 0; i < tempPoints.size(); i++)
	    {
	    	canvas.drawCircle(tempPoints.get(i).x*GRID_SIZE + GRID_SIZE/2, tempPoints.get(i).y*GRID_SIZE + GRID_SIZE/2, GRID_SIZE/2, mPaint);
	    }
	    
	    //Draw the food
	    mPaint.setColor(Color.GREEN);
	    canvas.drawRect(foodX*GRID_SIZE, foodY*GRID_SIZE, (foodX+1)*(GRID_SIZE), (foodY+1)*(GRID_SIZE), mPaint);
	    Log.v("SplashSnake", "Food loc, x: " + foodX + ", y: " + foodY);
	    
	    if(curFrame == TIME_STEP)
	    {
	    	if(!mSnake.moveAndCheck(boardWidth, boardHeight))
	    	{
	    		((Activity) getContext()).startActivity(new Intent(getContext(), GameOverActivity.class));
	    		((Activity) getContext()).finish();
	    	}
	    	if(foodCollision())
	    	{
	    		score++;
	    		mSnake.add();
	    		
	    		generateFood();
	    	}
	    	curFrame = 0;
	    }
	    curFrame++;
	    invalidate();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		if(event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE)
		{
			if (Math.abs(event.getX() - width/2) > Math.abs(event.getY() - height/2))
			{
				if (event.getX() - width/2 > 0)
				{
					mSnake.setDir(Snake.RIGHT);
				}
				else
				{
					mSnake.setDir(Snake.LEFT);
				}
			}
			else
			{
				if (event.getY() - height/2 > 0)
				{
					mSnake.setDir(Snake.DOWN);
				}
				else
				{
					mSnake.setDir(Snake.UP);
				}
			}
		}
		
		return true;
	}
	
	private void generateFood()
	{
		foodX = mRand.nextInt(boardWidth);
		foodY = mRand.nextInt(boardHeight);
		while(foodCollision())
		{
			foodX = mRand.nextInt(boardWidth);
			foodY = mRand.nextInt(boardHeight);
		}
	}
	
	private boolean foodCollision()
	{
		Point frontPoint = mSnake.getPoints().getLast();
		if (foodX == frontPoint.x && foodY == frontPoint.y)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
}
