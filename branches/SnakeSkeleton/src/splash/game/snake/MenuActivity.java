package splash.game.snake;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MenuActivity extends Activity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
    	startActivity(new Intent(getBaseContext(), GameActivity.class));
    	finish();
    	return true;
    }
}