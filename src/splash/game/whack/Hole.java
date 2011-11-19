package splash.game.whack;

import java.util.Random;

public class Hole
{
	public static final int EMPTY = 0;
	public static final int UP = 1;
	public static final int HIT = 2;
	private int curState = EMPTY;
	private int len = 0;
	private int curStep = 0;
	private Random mRand = new Random();
	
	public Hole()
	{
		//Nothing to see here... move along
	}
	
	public void step()
	{
		if (curState == EMPTY && mRand.nextInt(100) == 0)
		{
			curState = UP;
			len = mRand.nextInt(50);
			curStep = 0;
		}
		else if(curState == UP)
		{
			curStep++;
			if(curStep > len)
			{
				curState = EMPTY;
				WhackView.antiScore ++;
			}
		}
		else if(curState == HIT)
		{
			curStep++;
			if(curStep > len)
			{
				curState = EMPTY;
			}
		}
	}
	
	public void hit()
	{
		if(curState == UP)
		{
			curState = HIT;
			curStep = 0;
			len = 20;
			WhackView.score++;
		}
	}
	
	public int getState()
	{
		return curState;
	}
}
