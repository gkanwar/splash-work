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
		//TODO: Define some system of moving between states (EMPTY, UP, and HIT)
	}
	
	public void hit()
	{
		//TODO: If the curState is UP, this should switch it to HIT and increment WhackView's score variable
	}
	
	//Getter
	public int getState()
	{
		return curState;
	}
}
