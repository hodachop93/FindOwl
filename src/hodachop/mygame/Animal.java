package hodachop.mygame;

import android.graphics.Canvas;

public class Animal {
	//Position of a space ship on the screen
	public int x;
	public int y;
	//Speed
	private float speed;

	public Animal(int x)
	{
		this.x = x;
		this.y = Game.screenHeight*1/2;
		
	}
	
	//Draw the spaceship
	public void draw(Canvas canvas)
	{
		canvas.drawBitmap(Game.bird, x, y, null);
		
	}

}
