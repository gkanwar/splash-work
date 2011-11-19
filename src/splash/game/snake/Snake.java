package splash.game.snake;

import java.util.LinkedList;

import android.graphics.Point;

public class Snake
{
	//LinkedList of Points that make up the snake
	private LinkedList<Point> mPointsQueue;
	//The current direction the snake is travelling in (see constants below)
	private int curDir;
	
	//Direction constants
	public static final int UP = 0;
	public static final int LEFT = 1;
	public static final int DOWN = 2;
	public static final int RIGHT = 3;
	
	public Snake(int startX, int startY, int startLen, int dir)
	{
		mPointsQueue = new LinkedList<Point>();
		
		//TODO: Initialize mPointsQueue with the initial snake
	}

	public boolean moveAndCheck(int boardWidth, int boardHeight)
	{
		//TODO: Move snake in curDir and check for collisions
		//Return true if no collisions exist, false otherwise
		
		return true;
	}
	
	public void add()
	{
		//TODO: Extend the snake length by one
		//Hint: you can add a duplicate point at the tail of the snake, i.e. if your snake was
		//HEAD-(0,0) (1,0) (2,0)-TAIL, you could make it HEAD-(0,0) (1,0) (2,0) (2,0)-TAIL
	}
	
	public int getDirDx(int dir)
	{
		//TODO: It may be helpful to write these functions
		//It should return -1, 0, or 1 depending on what the change in x direction should be for the given direction

		return 0;
	}
	
	public int getDirDy(int dir)
	{
		//TODO: It may be helpful to implement
		
		return 0;
	}
	
	//Setter
	public void setDir(int dir)
	{
		curDir = dir;
	}
	
	//Getter
	public LinkedList<Point> getPoints()
	{
		return mPointsQueue;
	}
}
