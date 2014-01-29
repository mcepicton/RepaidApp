package com.example.abdlpottytimer;
/* Training Pants */

import java.util.concurrent.TimeUnit;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.app.Activity;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class OutCome2 extends Activity {
	private CountDownTimer countDownTimer;
	private boolean timerHasStarted = false;
	public static TextView text;
	private final long interval = 1 * 1000;
	EditText editTime1;
	Button startButton;
	
	public static void commonCode() {
	    int num;
	    String outCome = "";
	    for (int ii = 0; ii < 10; ii++) {
	      num = (int) (Math.random() * 100) % 4;
	      switch (num) {
		  case 0:
			  outCome = " Have an accident, clearly you shouldn't be in pullups";
			  break;
		  case 1:
		  case 2:
			  outCome = "Don't make the stars fade! Do a potty dance for a minute then give have an accident.";
			  break;
		  case 3:
			  outCome = "Good baby! Tell your big you need to go potty and fast!";
			  break;
	      }
	      text.setText(outCome);
	    }
	  }
	
	  public void outComeButton(View v){
	    OutCome2.commonCode();
	  }
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_out_come2);
		editTime1 = (EditText)findViewById(R.id.editTime1);     
        startButton = (Button)findViewById(R.id.startButton);
        text = (TextView) this.findViewById(R.id.timer);
        startButton.setOnClickListener(new OnClickListener() { 
        
        	/*Start the timer */
        	public void onClick(View v) {
                // get the name from edittext and storing into string variable
                long timeVal = Long.parseLong(editTime1.getText().toString());
                InputMethodManager imm = (InputMethodManager)getSystemService(
                	      Context.INPUT_METHOD_SERVICE);
                	imm.hideSoftInputFromWindow(startButton.getWindowToken(), 0);
                if (!timerHasStarted) {
                    countDownTimer = new MyCountDownTimer(timeVal * 1000 * 60, interval);
                    text.setText(text.getText() + String.valueOf(timeVal / 1000));
                    countDownTimer.start();
                    timerHasStarted = true;
                    startButton.setText("STOP");
                } else {
                    countDownTimer.cancel();
                    timerHasStarted = false;
                    startButton.setText("RESTART");
                }
            }
        	class MyCountDownTimer extends CountDownTimer {
      		  public MyCountDownTimer(long timeVal, long interval) {
      		   super(timeVal, interval);
      		  }
      		/* Converts tick time to 00:00 need to fix so that 1 min 30 seconds is 01:30 instead of 01:3
      		   * Currently hidden, maybe though do somthing with onTick later.*/      		  
      		  @Override
      		  public void onTick(long millisUntilFinished) {
      		   text.setText(""+String.format("%d:%d", 
      				    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
      				    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - 
      				    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))
      				));
      		  }
      		  
      		  @Override
      		  /* Finish event */
              public void onFinish() {
                  OutCome2.commonCode();
          	    Vibrator vib = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
          	    // Vibrate for 5 seconds
          	    vib.vibrate(5000);
              }
             }
            });
    }	  
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.out_come2, menu);
		return true;
	}

}
