package com.xiaoheng.qidongtu;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.view.View.*;

public class MainActivity extends Activity 
{
	CountDownTimer s;
	LinearLayout l;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.requestWindowFeature( Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		
		final TextView t=(TextView)findViewById(R.id.mainTextView1);
		
		s=new CountDownTimer(6000,1)
		{
			public void onTick(long millisUntilFinished)
			{
				t.setText("跳过｜"+millisUntilFinished/1000);
			}
			public void onFinish()
			{
				startActivity(new Intent(MainActivity.this,xiaohengactivity.class));
				finish();
			}
		};
		s.start();
		
    }
	
	public void l(View view)
	{
		startActivity(new Intent(MainActivity.this,xiaohengactivity.class));
		finish();
		s.cancel();
	}
	
}



    /****************************************
	 *      2017.9.10                       *
	 *                                      *
	 *      微信号：heng1919196455           *
	 *      小亨QQ：1919196455               *
	 *      QQ群聊：234257176                *
	 *                                 


AIDE技术网aidezy.com     *
	 ****************************************/
