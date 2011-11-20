package splash.game.helicopter;

public class Block
{
	//Constants
	public static final int BLOCK_WIDTH = 20;
	public static final int BLOCK_HEIGHT = 100;
	
	private int xLoc, yLoc;
	
	public Block(int x, int y)
	{
		xLoc = x;
		yLoc = y;
	}
	
	public void step(int speed)
	{
		xLoc += speed;
	}
	
	//Getters
	public int getX()
	{
		return xLoc;
	}
	public int getY()
	{
		return yLoc;
	}
}
