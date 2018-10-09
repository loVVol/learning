package com.xiaoheng.QQgengxin;


import android.app.*;
import android.content.*;
import android.net.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import java.io.*;
import org.apache.http.*;
import org.apache.http.client.*;
import org.apache.http.client.methods.*;
import org.apache.http.impl.client.*;
import org.apache.http.util.*;

public class MainActivity extends Activity 
{
	//声明变量
	private Button button,button1;
	String heng1,heng2,heng3,heng4,heng5,heng6,xiaoheng5,xiaoheng6,xiaoheng8,xiaoheng10;
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
		//必须加这个，因为安卓4.0以上没写两行会闪退
		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().detectDiskReads().detectDiskWrites().detectNetwork().penaltyLog().build());
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder().detectLeakedSqlLiteObjects().detectLeakedClosableObjects().penaltyLog().penaltyDeath().build());
		
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xiaohenglayout);
		
		
		//绑定按钮
		button=(Button)findViewById(R.id.xiaohenglayoutButton1);
		button1=(Button)findViewById(R.id.xiaohenglayoutButton2);
		button1.setOnClickListener(new OnClickListener()
			{
				@Override
				public void onClick(View p1)
				{
					//教程按钮点击事件
					Intent intent = new Intent(MainActivity.this,xiaohengactivity.class);
					startActivity(intent); 
				}
			});
		
			
			
		getPDAServerData("https://share.weiyun.com/53c99efc32198605f5793ec1f76ce92b");//这是QQ收藏链接，改为自己的就是了
		
		
		
    }
	
	
	private void getPDAServerData(String xiaoheng)
	{
		
		heng1="判断版本〈";//截取判断版本前
		heng2="〉判断版本";//截取判断版本后
		
		heng3="更新内容【";//截取更新内容前
		heng4="】更新内容";//截取更新内容后
		
		heng5="下载链接〔";//截取下载链接前
		heng6="〕下载链接";//截取下载链接后
		
		
		HttpClient xiaoheng1=new DefaultHttpClient();
		try{
			//异常处理开始
			
			HttpPost xiaoheng2=new HttpPost(xiaoheng);
			HttpResponse xiaoheng3=xiaoheng1.execute(xiaoheng2);
			HttpEntity xiaoheng4=xiaoheng3.getEntity();
			
			xiaoheng5=EntityUtils.toString(xiaoheng4);
			//获取网页源码
			
			xiaoheng6=xiaoheng5.substring(xiaoheng5.indexOf(heng1),xiaoheng5.indexOf(heng2));//截取到判断版本用的字符串
			final String xiaoheng7=xiaoheng6.replace(heng1,"");//替换判断版本〈为空
			
			xiaoheng8=xiaoheng5.substring(xiaoheng5.indexOf(heng3),xiaoheng5.indexOf(heng4));//截取到更新内容中的字符串
			final String xiaoheng9=xiaoheng8.replace(heng3,"");//替换更新内容【为空
			
			
			xiaoheng10=xiaoheng5.substring(xiaoheng5.indexOf(heng5),xiaoheng5.indexOf(heng6));//截取下载链接用的字符串
			final String xiaoheng11=xiaoheng10.replace(heng5,"");//替换下载链接〔为空
			
			button.setOnClickListener(new OnClickListener()
			{
					@Override
					public void onClick(View p1)
					{
						//检测更新按钮的点击事件
						
						int xiao=Integer.valueOf(xiaoheng7).intValue();//String类型变量转int类型变量
						
						if(xiao>1)//判断截取到的是否大于1
						{
							//大于1运行这里
							
							AlertDialog.Builder a=new AlertDialog.Builder(MainActivity.this);//新建个弹窗
							a.setIcon(R.drawable.gengxin);//图标
							a.setCancelable(false);//点击界面其他它地方弹窗不会消失
							a.setTitle("更新提示");//标题
							a.setMessage(xiaoheng9);//弹窗内容
							a.setPositiveButton("前往更新", new DialogInterface.OnClickListener(){
									@Override
									public void onClick(DialogInterface p1, int p2)
									{
										//第一个按钮的点击事件

										startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(xiaoheng11)));//用系统浏览器打开链接
									}
								});

							a.setNegativeButton("狠狠拒绝",new DialogInterface.OnClickListener(){
									@Override
									public void onClick(DialogInterface p1, int p2)
									{
										//第二个按钮的点击事件
									}
								});
							a.show();//显示弹窗
							
						}
						else
						{
							//否则运行这里
							Toast.makeText(MainActivity.this,"没有可更新",Toast.LENGTH_LONG).show();				
						}
					}
				});
		}
		catch (ClientProtocolException e)
		{
			e.printStackTrace();
			//异常后运行这里
		}
		catch (IOException e)
		{
			e.printStackTrace();
			//异常后运行这里
		}

		
	}
	
}


     /****************************************
	 *      2017.9.17                       *
	 *                                      *
	 *      微信号：heng1919196455            *
	 *      小亨QQ：1919196455               *
	 *      QQ群聊：234257176                *
	 *                                      *
	 ****************************************/


