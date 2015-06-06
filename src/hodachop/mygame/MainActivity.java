package hodachop.mygame;



import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.media.MediaPlayer;

import java.lang.Math;








public class MainActivity extends Activity {
	private boolean	showingMainActivity;
	private GamePanel gamePanel;
	ImageView guide;
	MediaPlayer mpButton;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mpButton = MediaPlayer.create(getBaseContext(), R.raw.button);
		guide = (ImageView) findViewById(R.id.guide);
		guide.setVisibility(View.GONE);
		showingMainActivity = true;
		
	}


    @Override
	public void onBackPressed() {
    	if (!showingMainActivity)
    	{
    		showingMainActivity =  true;
    		// Stop Game Loop
    		gamePanel.surfaceDestroyed(null);
    		setContentView(R.layout.activity_main);
    		guide = (ImageView) findViewById(R.id.guide);
    		guide.setVisibility(View.GONE);
    	}
    	else
    	{
    		
    		super.onBackPressed();
    		
    	}
		
		
	}
    
    public void onClickStartGame(View v)
    {
    	showingMainActivity = false;
    	mpButton.start();
    	gamePanel = new GamePanel(this);
    	setContentView(gamePanel);
    	
    }
    
    public void onClickGuide(View v){
    	guide.setVisibility(View.VISIBLE);
    	mpButton.start();
    }
    
    public void onClickCloseGuide(View v){
    	guide.setVisibility(View.GONE);
    	mpButton.start();
    }


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

	


}
