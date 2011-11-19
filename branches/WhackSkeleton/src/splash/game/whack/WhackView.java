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

		//TODO: Initialize the array of Holes
	}
	
	@Override
	public void onSizeChanged(int width, int height, int oldw, int oldh)
	{
		//TODO: Set the x spacing and the y spacing
	}
	
	@Override
	public void onDraw(Canvas canvas)
	{
		//TODO: Loop through the hole array and draw different colored squares in the correct location based on states
		
		//TODO: Draw the score text somewhere on the screen
		
		//Invalidate so that it redraws continuously
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
			
			//TODO: Loop through the hole array and check for collisions, trigger the correct hit() function if need be
		}
		
		return true;
	}
}
