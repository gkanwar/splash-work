package splash.game.whack;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class WhackView extends View
{
	private int xSpacing, ySpacing;
	private static final int RADIUS = 20;
	private Paint mPaint = new Paint();
	private Hole[][] hole = new Hole[2][3];
	
	public static int score = 0;
	public static int antiScore = 0;
	
	
	public WhackView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		for(int i = 0; i < 2; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				hole[i][j] = new Hole();
			}
		}
		
	}
	
	@Override
	public void onSizeChanged(int width, int height, int oldw, int oldh)
	{
		xSpacing = width/3;
		ySpacing = height/4;
	}
	
	@Override
	public void onDraw(Canvas canvas)
	{
		int holeState;
		
		for (int i = 0; i < 2; i++)
		{
			for (int j = 0; j < 3; j++)
			{
				hole[i][j].step();
				holeState = hole[i][j].getState();
				if(holeState == Hole.EMPTY)
				{
					mPaint.setColor(Color.GRAY);
				}
				else if(holeState == Hole.UP)
				{
					mPaint.setColor(Color.RED);
				}
				else if(holeState == Hole.HIT)
				{
					mPaint.setColor(Color.GREEN);
				}
				canvas.drawRect(xSpacing*(i+1)-RADIUS, ySpacing*(j+1)-RADIUS, xSpacing*(i+1)+RADIUS, ySpacing*(j+1)+RADIUS, mPaint);
			}
		}
		
		mPaint.setColor(Color.GRAY);
		canvas.drawText("Score: " + score + ", Missed: " + antiScore, 10, 10, mPaint);
		
		invalidate();
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		int mx, my;
		if (event.getAction() == MotionEvent.ACTION_DOWN)
		{
			mx = (int) event.getX();
			my = (int) event.getY();
			
			for(int i = 0; i < 2; i++)
			{
				for(int j = 0; j < 3; j++)
				{
					if (xSpacing*(i+1)-RADIUS <= mx && xSpacing*(i+1)+RADIUS >= mx && ySpacing*(j+1)-RADIUS <= my && ySpacing*(j+1)+RADIUS >= my)
					{
						hole[i][j].hit();
					}
				}
			}
		}
		
		return true;
	}
}
