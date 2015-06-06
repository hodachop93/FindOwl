package hodachop.mygame;

import java.util.ArrayList;
import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import hodachop.animation.*;
import java.lang.Math;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;



import android.media.Image;
import android.media.MediaPlayer;

import android.os.Bundle;



/**
 * Actual game.
 * 
 * @author hop
 */

public class Game{
	//Screen info
	public static int screenWidth;
	public static int screenHeight;
	public static float screenDensity;
	private int unit;


	
	private static final int DistanceShip = 20;
	private int gameTimeSec;

	public static int alpha;
	public static int BirdInShip;
	private int level;
	private boolean gameOver;
	private boolean ViewBird;
	private boolean ViewBirdAgain;

	// We will need this to draw background image full screen.
	private Rect destBackgroundImage;
	private Rect destNextButton;
	private Rect destGameOverImage;
	private Paint paintForImages;
	private Paint paintForText;
	
	//We will need this for spaceship and animal.
	//private Rect destSpaceShip;
	//private Rect destAnimal;
	
	//Images
	private static Bitmap background;
	public static Bitmap spaceship;
	public static Bitmap bird;
	private Bitmap NextButton;
	private Bitmap GalaxyBackground;
	private Bitmap GameOverImage;
	private Bitmap ResetImage;
	
	//Mang Random
	private int rd[];

	
	// List of all spaceship on a screen.
	private ArrayList<Ship> Ships;
	private Animal Bird;
	
	//Constant
	private double RAD = 0.017453293;
	//Variable rotate
	private static int countRotate;
	
	//progress bar Sprite
	private boolean ViewProgressBar;
	private ElaineAnimated ProgressBar;
	
	//Sound
	private static MediaPlayer mp1,mp2,mp3,mp4;
	
	// Luu trang thai game
	private static SharedPreferences prefs;
	private static int score,highscore;
	private boolean GameOverDisplay, GameDisplay;
	
	


	public Game(int screenWidth, int screenHeight, Resources resources,Context context){
		Game.screenWidth = screenWidth;
		Game.screenHeight = screenHeight;
		Game.screenDensity = resources.getDisplayMetrics().density;
		unit = Game.screenWidth/20;
		
		
		this.LoadContent(resources,context);
		
		destBackgroundImage = new Rect(0, 0, screenWidth, screenHeight);
		destNextButton = new Rect(screenWidth-2*unit,screenHeight-2*unit,screenWidth,screenHeight);
		destGameOverImage =  new Rect(screenWidth/2-GameOverImage.getWidth()/2,
										screenHeight/2 - GameOverImage.getHeight()/2,
										screenWidth/2 + GameOverImage.getWidth()/2,
										screenHeight/2 + GameOverImage.getHeight()/2);
		paintForImages = new Paint();
		paintForImages.setFilterBitmap(true);
		

		countRotate=0;
		alpha=0;
		rd = new int[20];
		Ships =  new ArrayList<Ship>();
		ViewProgressBar = false;
		GameOverDisplay = false;
		GameDisplay = true;
		this.resetGame();
	}
	

	/**
	 * Load files.
	 */
	private void LoadContent(Resources resources,Context context){
		prefs = context.getSharedPreferences("MyPrefsKey", context.MODE_PRIVATE);
		highscore = prefs.getInt("key", 0);
		score = 0;
		
		background = BitmapFactory.decodeResource(resources, R.drawable.background);
		spaceship = BitmapFactory.decodeResource(resources, R.drawable.spaceship);
		bird = BitmapFactory.decodeResource(resources, R.drawable.bird);
		NextButton = BitmapFactory.decodeResource(resources, R.drawable.next_button);
		GalaxyBackground = BitmapFactory.decodeResource(resources, R.drawable.galaxyground);
		GameOverImage = BitmapFactory.decodeResource(resources, R.drawable.gameover);
		ResetImage = BitmapFactory.decodeResource(resources, R.drawable.reset);
		
		ResetImage = Bitmap.createScaledBitmap(ResetImage, 150, 150, true);
		
		ProgressBar = new ElaineAnimated(
				BitmapFactory.decodeResource(resources, R.drawable.progressbar ) 
				, screenWidth/2 -102, screenHeight/2 -102 // initial position
				, 102, 102	// width and height of sprite
				, 60, 8);	// FPS and number of frames in the animation
		
		mp1 = MediaPlayer.create(context, R.raw.game);
		mp2 = MediaPlayer.create(context, R.raw.tada);
		mp3 = MediaPlayer.create(context, R.raw.lose);
		mp4 = MediaPlayer.create(context, R.raw.button);
		mp1.setLooping(true);
		
		Typeface tf = Typeface.createFromAsset(context.getAssets(),"arrusb.ttf");
		paintForText = new Paint();
		paintForText.setStyle(Style.FILL);
		paintForText.setColor(Color.RED);
		paintForText.setTextSize(50);
		paintForText.setTypeface(tf);
	}

	
	/**
	 * Game update method.
	 * 
	 * @param gameTime Elapsed game time in milliseconds.
	 */
	public void Update(long gameTime) {
		
		this.gameTimeSec = (int)(gameTime / 1000);
		if (gameOver)
			return;
		if (GameDisplay){
		if(this.gameTimeSec<1)
			ViewBird=true;
		else
		{
			ViewBird=false;
			int i,j;
			if(this.gameTimeSec>2)
			{
				if (countRotate<180/alpha){
					i=rd[0]; j=rd[1];
					ChangeShips(i, j);
		
				}
				
				else if (countRotate<180/alpha*2){
					i=rd[2]; j=rd[3];
					ChangeShips(i, j);
	
				}
				else if (countRotate<180/alpha*3){
					i=rd[4]; j=rd[5];
					ChangeShips(i, j);
				
				}
				else if (countRotate<180/alpha*4){
					i=rd[6]; j=rd[7];
					ChangeShips(i, j);
			
				}
				else if (countRotate<180/alpha*5){
					i=rd[8]; j=rd[9];
					ChangeShips(i, j);

				}
				else if (countRotate<180/alpha*6){
					i=rd[10]; j=rd[11];
					ChangeShips(i, j);
		
				}
				else if (countRotate<180/alpha*7){
					i=rd[12]; j=rd[13];
					ChangeShips(i, j);
					
				}
				else if (countRotate<180/alpha*8){
					i=rd[14]; j=rd[15];
					ChangeShips(i, j);

				}
				else if (countRotate<180/alpha*9){
					i=rd[16]; j=rd[17];
					ChangeShips(i, j);

				}
				else if (countRotate<180/alpha*10){
					i=rd[18]; j=rd[19];
					ChangeShips(i, j);
		
				}
			}
			
		}
		Bird.x=Ships.get(BirdInShip).x;
		Bird.y=Ships.get(BirdInShip).y;
		}
		if (ViewProgressBar){
			ProgressBar.update(gameTime);
			if (gameTime >500){
				ViewProgressBar = false;
				GameOverDisplay = false;
				GameDisplay = true;
				GameLoopThread.gameTime = 0;
				ProgressBar.reset();
			}
		}
	}
	
	
	/**
	 * Draw the game to the screen.
	 * 
	 * @param canvas Canvas on which we will draw.
	 * @throws Exception 
	 */
	public void Draw(Canvas canvas){
		// First we need to erase everything we draw before.
				canvas.drawColor(Color.BLACK);
		if (GameDisplay){
		
		// Draw background image.
		canvas.drawBitmap(Game.background, null, destBackgroundImage, paintForImages);
		canvas.drawBitmap(NextButton, null, destNextButton, paintForImages);
		canvas.drawText("LEVEL :  "+ level, screenWidth/2 -2*unit, 2*unit, paintForText);
		
		//Draw a bird
		if (ViewBird)
			Bird.draw(canvas);
		else
		//Draw spaceships
			for (int i=0; i < Ships.size(); i++)
			{
				if (Ships.get(i).BeRemoved()==false)
					Ships.get(i).draw(canvas);
			}
				
		if (ViewBirdAgain)
			Bird.draw(canvas);
		
		}
		
		if (ViewProgressBar){
			canvas.drawColor(Color.BLACK);
			canvas.drawBitmap(GalaxyBackground, null	, destBackgroundImage, paintForImages);
			ProgressBar.draw(canvas);
			canvas.drawText("Loading......", screenWidth/2- 2*unit, screenHeight - 3*unit, paintForText);
		}
		
		if (GameOverDisplay){
			// Draw background image.
			canvas.drawBitmap(Game.background, null, destBackgroundImage, paintForImages);
			canvas.drawBitmap(NextButton, null, destNextButton, paintForImages);
			canvas.drawText("LEVEL :  "+ level, screenWidth/2 -2*unit, 2*unit, paintForText);
			//Draw spaceships
			for (int i=0; i < Ships.size(); i++)
			{
				if (Ships.get(i).BeRemoved()==false)
					Ships.get(i).draw(canvas);
			}
			canvas.drawBitmap(GameOverImage, null, destGameOverImage, paintForImages);
			canvas.drawText("Score    "+score, screenWidth/2-2*unit, screenHeight/2+unit, paintForText);
			canvas.drawText("Best      "+highscore, screenWidth/2-2*unit, screenHeight/2+2*unit, paintForText);
			canvas.drawBitmap(ResetImage, screenWidth/2-unit, screenHeight/2+ 3*unit , paintForImages);
			
		}
		
		
		
		
		
		
	
	}
	
	/**
     * Create a spaceship
     */
	private void addNewShip(int x)
	{
		this.Ships.add(new Ship(x));
	}
	

	

	
	
	
	/**
	 * Change Ships
	 */
	public void ChangeShips(int i,int j)
	{
		Affine T = new Affine();
		
		Affine A = new Affine();
		Affine A1 = new Affine();
		
		Rotate.QuayTamO(T, alpha*RAD, (Ships.get(i).x+Ships.get(j).x)/2, (Ships.get(i).y+Ships.get(j).y)/2);
		//Shipleft
		
		A.x[0][0]=Ships.get(i).x;
		A.x[0][1]=Ships.get(i).y;
		A.x[0][2]=1;
		Rotate.MatMul(A,T,A1,1,3);
		Ships.get(i).x = Math.round(A1.x[0][0]) ;
		Ships.get(i).y = Math.round(A1.x[0][1]) ;
		
		
		//Shipright
		A.x[0][0]=Ships.get(j).x;
		A.x[0][1]=Ships.get(j).y;
		A.x[0][2]=1;
		Rotate.MatMul(A,T,A1,1,3);
		Ships.get(j).x = Math.round( A1.x[0][0]);
		Ships.get(j).y = Math.round( A1.x[0][1]);
		
		//Hoan doi thu tu 2 con tau
		countRotate++;
		
		
	}
	
	/**
	 * Setting some game variables before game starts.
	 */
	private void resetGame()
	{
		
		gameOver = false;
		
		level +=1;
		mp1.start();
		int i;
		// tao mang random
		
		Random random = new Random();
		BirdInShip = random.nextInt(5);
		rd[0]=BirdInShip;
		for (i=1;i<rd.length;i++)
		{
			do{
			rd[i]=random.nextInt(5);
			}while (rd[i]==rd[i-1]);
		}
		//tao mang tau
		Ships.clear();
		int x = screenWidth/20;
		for (i=0;i<5;i++)
		{
			this.addNewShip(x);
			x = x+ Game.spaceship.getWidth()+ DistanceShip;
			
		}
		//Dat chim vao 1 tau bat ky
		
		Bird = new Animal(Ships.get(BirdInShip).x);
		do{
			alpha+=5;
		}while ((180%alpha)!=0);
	

	}

	
    /**
     * When touch on screen is detected.
     * 
     * @param event MotionEvent
     */
    public void touchEvent_actionDown(MotionEvent event){
    	if(!gameOver){
    		this.checkIfShipTouched(event.getX(), event.getY());
    		if((checkIfNextButtonTouched(event.getX(),event.getY()))&&(GameOverDisplay==false)
    				&&(countRotate>=(180/alpha*10))&&(ViewBirdAgain)){
    			mp4.start();
    			ViewProgressBar = true;
    			GameDisplay = false;
    			GameOverDisplay = false;
    			GameLoopThread.gameTime=0;
    			resetGame();
    			ViewBirdAgain = false;
    			ViewBird = true;
    			countRotate = 0;
    		}
    	}
    	else{
    		this.checkIfRestartGameButtonTouched(event);
    	}
    }
    
    /**
     * When moving on screen is detected.
     * 
     * @param event MotionEvent
     */
    public void touchEvent_actionMove(MotionEvent event){
    	
    }
    
    
    /**
     * When touch on screen is released.
     * 
     * @param event MotionEvent
     */
    public void touchEvent_actionUp(MotionEvent event){
    	
    }
    
    private void checkIfShipTouched(float touchX, float touchY){
    	if (countRotate>=(180/alpha*10)){
    	for(int i=0; i < Ships.size(); i++){
    		Ship ship = Ships.get(i);
    		
    		if(ship.wasItShoot((int)touchX, (int)touchY)){
    			Ships.get(i).RemoveObject();
    			
    			if (i == BirdInShip){
    				ViewBirdAgain = true;
    				mp2.start();
    				score++;
    			}
    			else{
    				mp3.start();
    				GameOverDisplay = true;
    				GameDisplay = false;
    				ViewProgressBar = false;
    				Editor edit = prefs.edit();
    				if(score>highscore)
    				{
    					 highscore=score;
    					 edit.putInt("key", score);
    					 edit.commit();
    				}
    				gameOver = true;
    			}
    		}
    	}
    	}
    }
    
    private boolean checkIfNextButtonTouched(float touchX,float touchY)
    {
    	return destNextButton.contains((int) touchX, (int) touchY);
    }
    
    private void checkIfRestartGameButtonTouched(MotionEvent event){
    	
    	int x,y,getX,getY;
    	x= (int) event.getX();
    	y= (int) event.getY();
    	getX = screenWidth/2 -unit;
    	getY = screenHeight/2 +3*unit;
    	if (((x>=getX)&&(x<=getX+ResetImage.getWidth()))&&((y>=getY)&&(y<=getX+ResetImage.getHeight()))){
    		mp4.start();
    		restartTheGame();
    	}
    	
    }
    
    private void restartTheGame(){
    	
    	level = 0;
    	score = 0;
    	alpha = 0;
    	GameLoopThread.gameTime = 0;
    	ViewProgressBar = false;
    	GameOverDisplay = false;
    	GameDisplay =true;
    	gameOver =false;
    	countRotate = 0;
    	resetGame();
    }
    
    
    public void stopSound(){
    	mp1.stop();
    }
    
}