package splash.game.pong;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;

public class PongActivity extends Activity {
	
	Point screenSize = new Point();
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Display display = getWindowManager().getDefaultDisplay(); 
        screenSize.x = display.getWidth();
        screenSize.y = display.getHeight();
        PongView pongView = (PongView) findViewById(R.id.pongView);
        //if ( pongView != null ){
    }
  
}