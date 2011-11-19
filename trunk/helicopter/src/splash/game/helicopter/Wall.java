package splash.game.helicopter;

public class Wall
{
	private int xLoc, height;
	public static final int WALL_WIDTH = 20;
	
	public Wall(int x, int h)
	{
		xLoc = x;
		height = h;
	}
	
	public void step(int speed)
	{
		xLoc -= speed;
	}
	
	public int getX()
	{
		return xLoc;
	}
	public int getHeight()
	{
		return height;
	}
}
