package splash.game.helicopter;

import java.util.ArrayList;
import java.util.Random;

import android.R.drawable;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class HeliView extends View{
	
	Random random = new Random();
	int speed = 4;
	int difficulty  = 1;
	final int blockHeight = 100;
	final int blockWidth = 20;
	final int heliWidth  = 100;
	final int heliHeight = 20;
	int heliStartX,heliStartY;
	int score = 0;
	private Bitmap heliImage;
	int heliX = 10,heliY;
	float heliVel = 0;
	boolean firstRun = true;
	Point screenSize = new Point();
	ArrayList<Integer> walls = new ArrayList<Integer>(); //x current, y current, length
	ArrayList<Integer> blockers = new ArrayList<Integer>();
	boolean accel = false;
	public HeliView(Context context, AttributeSet attrs) {
		super(context,attrs);
		// TODO Auto-generated constructor stub
		heliImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.helicopter);
	}
	protected void onDraw ( Canvas canvas )
    {
		screenSize.x = canvas.getWidth();
		Log.v("screenSize",""+screenSize.x);
		screenSize.y = canvas.getHeight();
		if ( firstRun )
		{
			firstRun = false;
			resetGame();
			heliStartX = 10;
			heliStartY = screenSize.y/2;
		}
		Paint paint = new Paint();
    	paint.setColor(Color.GREEN);
		for ( int i = 0; i < blockers.size()/2;i++ )
		{
			canvas.drawRect(blockers.get(2*i),blockers.get(2*i + 1),blockers.get(2*i) + blockWidth, blockers.get(2*i+1) + blockHeight, paint);
		}
		for ( int j = 0; j < walls.size()/3;j++ )
		{
			canvas.drawRect( walls.get(3*j),0,walls.get(3*j) + walls.get(3*j + 2),walls.get(3*j + 1),paint );
			canvas.drawRect( walls.get(3*j),walls.get(3*j + 1) + (screenSize.y/3 * 2),walls.get(3*j) + walls.get(3*j +  2),screenSize.y,  paint);
		}
		canvas.drawBitmap(heliImage,null,new Rect( heliX, heliY, heliX + heliWidth, heliY + heliHeight ),paint);
		updatePhysics(); 	
		invalidate();
    }
	private void updatePhysics()
	{
		for ( int i = 0; i < walls.size()/3; i++ )
		{
			walls.set( 3*i, walls.get(3*i) - speed );
			if ( i == walls.size()/3 - 1)
			{
			if ( walls.get(3*i) + walls.get(3*i + 2) <= screenSize.x )
			{
				int yLoc = random.nextInt(screenSize.y/3);
				int width = random.nextInt(screenSize.x/2);
				walls.add(walls.get(3*i) + walls.get(3*i + 2 ));
				walls.add(yLoc);
				walls.add(width);
			}
			}
			
		}
		for ( int i = 0; i < blockers.size()/32;i++ )
		{
			blockers.set(2*i, blockers.get(2*i) -  speed);
		}
		if ( random.nextInt(difficulty * 5) == 0 )
		{
			Log.v("adding block","helicopter");
			blockers.add( screenSize.x );
			blockers.add( random.nextInt(7*screenSize.y/6) - screenSize.y/6);
		}
		if (accel)
		{
			heliVel -= 2;
		}
		else
		{
			heliVel += 2	;
		}
		if ( checkCollision() )
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
		blockers.clear();
		walls.add(0);
		walls.add(screenSize.y/5);
		walls.add(screenSize.x);
	}
	private boolean checkCollision()
	{
		Rect heliRect = new Rect ( heliX, heliY, heliX+ heliWidth, heliY+heliHeight);
		for ( int i = 0; i < blockers.size()/2;i++)
		{
			if ( Rect.intersects(heliRect, 
					new Rect(blockers.get(2*i),blockers.get(2*i + 1),blockers.get(2*i) + blockHeight, blockers.get(2*i) + blockWidth )))
			{
				return true;
			}
		}
		for ( int j = 0; j < walls.size()/3;j++ )
		{
			if ( Rect.intersects(heliRect, new Rect( walls.get(3*j),0,walls.get(3*j) + walls.get(3*j + 2),walls.get(3*j + 1))) 
					|| Rect.intersects(heliRect, 
							new Rect(walls.get(3*j),walls.get(3*j + 1) + (screenSize.y/3 * 2),walls.get(3*j) + walls.get(3*j +  2),screenSize.y) ))
			{
				return true;
			}
		}
		return false;
	}
	public boolean onTouchEvent(final MotionEvent event) 
    {
    	if ( event.getAction() == MotionEvent.ACTION_DOWN)
    	{
    		accel = true;
    	}
    	else if ( event.getAction() == MotionEvent.ACTION_UP)
    	{
    		accel = false;
    	}
    	return true;
    }

}
