package my.test.xuanfuchuang;
import android.app.*;
import android.os.*;
import android.content.*;





public class MainActivity extends Activity
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		// TODO: Implement this method
		super.onCreate(savedInstanceState);
		Intent intent=new Intent(MainActivity.this,TESTT.class);
		startService(intent);
		finish();
	}
	
	
}

