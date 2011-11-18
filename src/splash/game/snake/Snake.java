package splash.game.snake;

import java.util.LinkedList;

import android.graphics.Point;

public class Snake
{
	private LinkedList<Point> mPointsQueue;
	private int curDir;
	
	public static final int UP = 0;
	public static final int LEFT = 1;
	public static final int DOWN = 2;
	public static final int RIGHT = 3;
	
	public Snake(int startX, int startY, int startLen, int dir)
	{
		mPointsQueue = new LinkedList<Point>();
		Point tempPoint;
		int dx = getDirDx(dir), dy = getDirDy(dir);
		for (int i = 0; i < startLen; i++)
		{
			tempPoint = new Point(startX + i*dx, startY + i*dy);
			mPointsQueue.addLast(tempPoint);
		}
		
		curDir = dir;
	}

	public boolean moveAndCheck(int boardWidth, int boardHeight)
	{
		int dx = getDirDx(curDir), dy = getDirDy(curDir);
		
		Point frontPoint = mPointsQueue.getLast();
		
		if (frontPoint.x + dx >= boardWidth || frontPoint.x + dx < 0 || frontPoint.y + dy >= boardHeight || frontPoint.y + dy < 0)
		{
			return false;
		}
		
		for (int i = 0; i < mPointsQueue.size() - 1; i++)
		{
			//We have a collision
			if(frontPoint.x + dx == mPointsQueue.get(i).x && frontPoint.y + dy == mPointsQueue.get(i).y)
			{
				return false;
			}
		}
		
		//No collisions, move
		Point backPoint = mPointsQueue.remove();
		backPoint.x = frontPoint.x + dx;
		backPoint.y = frontPoint.y + dy;
		mPointsQueue.addLast(backPoint);
		
		return true;
	}
	
	public void add()
	{
		Point newPoint = new Point(mPointsQueue.getLast().x, mPointsQueue.getLast().y);
		mPointsQueue.addFirst(newPoint);
	}
	
	public int getDirDx(int dir)
	{
		if (dir == UP || dir == DOWN)
		{
			return 0;
		}
		else if (dir == RIGHT)
		{
			return 1;
		}
		else
		{
			return -1;
		}
	}
	
	public int getDirDy(int dir)
	{
		if (dir == LEFT || dir == RIGHT)
		{
			return 0;
		}
		else if (dir == DOWN)
		{
			return 1;
		}
		else
		{
			return -1;
		}
	}
	
	public void setDir(int dir)
	{
		curDir = dir;
	}
	
	public LinkedList<Point> getPoints()
	{
		return mPointsQueue;
	}
}
