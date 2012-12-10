package com.example.du_clock;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import java.util.Calendar;
import java.util.TimerTask;
import java.util.Timer;
import android.os.Handler;

public class MainActivity extends Activity {

	/*
	 * 現在時刻の更新状態
	 */
	enum State
	{
		Start,		/* 実施 */
		Stop,		/* 停止 */
	}

	State st = State.Start;
	Timer timer_clock = new Timer();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        timer_clock.schedule(new TimerTask_Clock(), 1000, 1000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    @Override
    public boolean onTouchEvent(MotionEvent me) {

    	/*
    	 * 画面のタッチにより、現在時刻の更新の実施と停止を切り替える
    	 */
    	if ( me.getAction() == MotionEvent.ACTION_DOWN )
    	{
	    	View v_start, v_stop;
	    	v_start = findViewById(R.id.text_start);
	    	v_stop = findViewById(R.id.text_stop);


	    	if ( st == State.Start)
	    	{
	    		st = State.Stop;
	    		v_start.setVisibility(View.INVISIBLE);
	    		v_stop.setVisibility(View.VISIBLE);
	    	}
	    	else
	    	{
	    		st = State.Start;
	    		v_start.setVisibility(View.VISIBLE);
	    		v_stop.setVisibility(View.INVISIBLE);
	    	}
    	}

    	return true;
    }
    
    Handler handler_clock = new Handler();
    
    public class TimerTask_Clock extends TimerTask {

		@Override
		public void run() {
			handler_clock.post(new Runnable() {
				public void run() {
					/*
					 * 現在時刻の更新
					 */
					if ( st == State.Start ) {
						Calendar calendar = Calendar.getInstance();
				    	
				    	int hour = calendar.get(Calendar.HOUR_OF_DAY);
				    	int minute = calendar.get(Calendar.MINUTE);
				    	int second = calendar.get(Calendar.SECOND);
		
				    	TextView v_timer;
				    	v_timer = (TextView) findViewById(R.id.text_timer);
				    	v_timer.setText(hour + ":" + minute + ":" + second);
					}
				}
			});
		}
    	
    }
}
