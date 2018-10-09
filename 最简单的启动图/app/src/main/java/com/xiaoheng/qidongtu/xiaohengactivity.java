package com.xiaoheng.qidongtu;

import android.app.*;
import android.content.*;
import android.graphics.*;
import android.net.*;
import android.os.*;
import android.view.*;
import android.widget.*;

public class xiaohengactivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.xiaohenglayout);
		
		
		Toast Imagetoast=new Toast(xiaohengactivity.this);
		Imagetoast.setGravity(Gravity.BOTTOM,0,280);
		LinearLayout layout=new LinearLayout(xiaohengactivity.this);
		layout.setBackgroundColor(Color.RED);
		layout.setGravity(Gravity.CENTER);
		layout.setOrientation(0);
		
		/*
		//创建一个ImageView,用来显示Toast的图片
		ImageView image=new ImageView(xiaohengactivity.this);
		//为ImageView设置图片
		image.setImageResource(R.drawable.heng);
		//将image添加进LinearLayout中
		layout.addView(image);
		//创建个TextView用来显示Toast信息
		*/
		
		TextView toastmassage=new TextView(xiaohengactivity.this);
		toastmassage.setText("小亨原创");
		toastmassage.setPadding(10,10,10,10);
		toastmassage.setTextSize(18);
		toastmassage.setTextColor(Color.WHITE);
		layout.addView(toastmassage);
		Imagetoast.setDuration(Toast.LENGTH_LONG);
		Imagetoast.setView(layout);
		Imagetoast.show();
		
		
	}
	public void b1(View view)
	{
		startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("mqqapi://card/show_pslcard?src_type=internal&source=sharecard&version=1&uin=2633544207")));
	}
	
	public void b2(View view)
	{
		startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("mqqwpa://im/chat?chat_type=wpa&uin=2633544207")));
	}
	
	public void b3(View view)
	{
		startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("mqqapi://card/show_pslcard?src_type=internal&version=1&uin=323971864&card_type=group&source=qrcode")));
	}
	
	public void b4(View view)
	{
		try{
			startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("alipays://platformapi/startapp?saId=10000007&clientVersion=3.7.0.0718&qrcode=HTTPS://QR.ALIPAY.COM/FKX04278X8J08KPTCLIG54")));
			Toast.makeText(xiaohengactivity.this,"正在打开支付宝",Toast.LENGTH_LONG).show();
		}catch(Exception e)
		{
			AlertDialog.Builder dg=new AlertDialog.Builder(xiaohengactivity.this);
			dg.setTitle("提示");
			dg.setMessage("亲，您的手机还没有安装支付宝呢！");
			dg.setPositiveButton("去下载", new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface p1, int p2)
					{
						startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://ds.alipay.com/")));
					}
				});
			dg.setNegativeButton("取消",new DialogInterface.OnClickListener(){
					@Override
					public void onClick(DialogInterface p1, int p2)
					{
					}
				});
			dg.show();
		}
	}
}

//这是转跳成功界面的代码，这里的代码不用管

//启动图核心代码都在 MainActivity.java 文件里
