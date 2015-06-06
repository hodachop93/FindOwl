package hodachop.mygame;


import android.graphics.Canvas;
import android.graphics.Rect;


public class Ship {
	//Position of a space ship on the screen
	public int x;
	public int y;
	//Speed
	private boolean beRemoved;

	public Ship(int x)
	{
		this.x = x;
		this.y = Game.screenHeight*1/2;
		beRemoved=false;
		
		
	}
	
	//Draw the spaceship
	public void draw(Canvas canvas)
	{
		canvas.drawBitmap(Game.spaceship, x, y, null);
		
	}
	
	public boolean wasItShoot(int touchX, int touchY){
		Rect duckRect = new Rect((int)this.x, (int)this.y, (int)this.x + Game.spaceship.getWidth(), (int)this.y + Game.spaceship.getHeight());
		
		return duckRect.contains(touchX, touchY);
	}
	public void RemoveObject()
	{
		beRemoved = true;
	}
	public boolean BeRemoved()
	{
		return beRemoved;
	}
}
