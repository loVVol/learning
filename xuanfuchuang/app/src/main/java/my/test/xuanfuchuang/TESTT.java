package my.test.xuanfuchuang;
import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.widget.LinearLayout.*;
import android.graphics.*;
import android.view.WindowManager.LayoutParams;

public class TESTT extends Service
{
	private static final int UPDATE_PIC=0x100;
	private int statusBarHeight;
	private View view;
	private TextView textview=null;
	private Button hideBtn=null;
	private Button updateBtn=null;
	private HandlerUI handler=null;
	private Thread updateThread=null;
	private boolean viewAdded=false;
	private boolean viewHide=false;
	private WindowManager windowManager;
	private WindowManager.LayoutParams layoutParams;
	private String SysInfoUtils="hello";
	
	
	
	
	

	@Override
	public IBinder onBind(Intent p1)
	{
		// TODO: Implement this method
		return null;
	}

	@Override
	public void onCreate()
	{
		// TODO: Implement this method
		super.onCreate();
		createFloatView();
	}

	@Override
	public void onStart(Intent intent, int startId)
	{
		// TODO: Implement this method
		super.onStart(intent, startId);
		System.out.println("===========onStart");
		viewHide=false;
		refresh();
		
		
		
	}

	@Override
	public void onDestroy()
	{
		// TODO: Implement this method
		super.onDestroy();
		removeView();
	}
	
	public void removeView()
	{
		if(viewAdded)
		{
			windowManager.removeView(view);
			viewAdded=false;
		}
	}
	private void createFloatView() {  
        handler = new HandlerUI();  
        UpdateUI update = new UpdateUI();  
        updateThread = new Thread(update);  
        updateThread.start(); // 开户线程  

        view = LayoutInflater.from(this).inflate(R.layout.main, null);  
        textview = (TextView) view.findViewById(R.id.usage);  
        hideBtn = (Button) view.findViewById(R.id.hideBtn);  
        updateBtn = (Button) view.findViewById(R.id.updateBtn);  
        windowManager = (WindowManager) this.getSystemService(WINDOW_SERVICE);  
		textview.setTextColor(Color.WHITE);
        /* 
         * LayoutParams.TYPE_SYSTEM_ERROR：保证该悬浮窗所有View的最上层 
         * LayoutParams.FLAG_NOT_FOCUSABLE:该浮动窗不会获得焦点，但可以获得拖动 
         * PixelFormat.TRANSPARENT：悬浮窗透明 
         */  
        layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT,  
										LayoutParams.WRAP_CONTENT, LayoutParams.TYPE_SYSTEM_ERROR,  
										LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSPARENT);  
        // layoutParams.gravity = Gravity.RIGHT|Gravity.BOTTOM; //悬浮窗开始在右下角显示  
        layoutParams.gravity = Gravity.LEFT | Gravity.TOP;  

        /** 
         * 监听窗体移动事件 
         */  
        view.setOnTouchListener(new OnTouchListener() {  
				float[] temp = new float[] { 0f, 0f };  

				public boolean onTouch(View v, MotionEvent event) {  
					layoutParams.gravity = Gravity.LEFT | Gravity.TOP;  
					int eventaction = event.getAction();  
					switch (eventaction) {  
						case MotionEvent.ACTION_DOWN: // 按下事件，记录按下时手指在悬浮窗的XY坐标值  
							temp[0] = event.getX();  
							temp[1] = event.getY();  
							break;  

						case MotionEvent.ACTION_MOVE:  
							refreshView((int) (event.getRawX() - temp[0]),  
										(int) (event.getRawY() - temp[1]));  
							break;  

					}  
					return true;  
				}  
			});  

        hideBtn.setOnClickListener(new OnClickListener() {  

				@Override  
				public void onClick(View v) {  
					// TODO Auto-generated method stub  
					viewHide = true;  
					removeView();  
					System.out.println("----------hideBtn");  
				}  
			});  

        updateBtn.setOnClickListener(new OnClickListener() {  

				@Override  
				public void onClick(View v) {  
					// TODO Auto-generated method stub  
					Toast.makeText(getApplicationContext(), "you click UpdateBtn",  
								   Toast.LENGTH_SHORT).show();  
					System.out.println("mom "  
									   + SysInfoUtils);  
				}  
			});  
    }  

    /** 
     * 刷新悬浮窗 
     *  
     * @param x 
     *            拖动后的X轴坐标 
     * @param y 
     *            拖动后的Y轴坐标 
     */  
    private void refreshView(int x, int y) {  
        // 状态栏高度不能立即取，不然得到的值是0  
        if (statusBarHeight == 0) {  
            View rootView = view.getRootView();  
            Rect r = new Rect();  
            rootView.getWindowVisibleDisplayFrame(r);  
            statusBarHeight = r.top;  
        }  

        layoutParams.x = x;  
        // y轴减去状态栏的高度，因为状态栏不是用户可以绘制的区域，不然拖动的时候会有跳动  
        layoutParams.y = y - statusBarHeight;// STATUS_HEIGHT;  
        refresh();  
    }  

    /** 
     * 添加悬浮窗或者更新悬浮窗 如果悬浮窗还没添加则添加 如果已经添加则更新其位置 
     */  
    private void refresh() {  
        // 如果已经添加了就只更新view  
        if (viewAdded) {  
            windowManager.updateViewLayout(view, layoutParams);  
        } else {  
            windowManager.addView(view, layoutParams);  
            viewAdded = true;  
        }  
    }  

    /** 
     * 接受消息和处理消息 
     *  
     * @author Administrator 
     *  
     */  
    class HandlerUI extends Handler {  
        public HandlerUI() {  

        }  

        public HandlerUI(Looper looper) {  
            super(looper);  
        }  

        /** 
         * 接收消息 
         */  
        @Override  
        public void handleMessage(Message msg) {  
            // TODO Auto-generated method stub  
            // 根据收到的消息分别处理  
            if (msg.what == UPDATE_PIC) {  
                textview.setText(SysInfoUtils  
							 
							 + "  t = "  
							 + SysInfoUtils 
							 + "  a = "  
							 + SysInfoUtils  
							 );  
                if (!viewHide)  
                    refresh();  
            } else {  
                super.handleMessage(msg);  
            }  

        }  

    }  

    /** 
     * 更新悬浮窗的信息 
     *  
     * @author Administrator 
     *  
     */  
    class UpdateUI implements Runnable {  

        @Override  
        public void run() {  
            // TODO Auto-generated method stub  
            // 如果没有中断就一直运行  
            while (!Thread.currentThread().isInterrupted()) {  
                Message msg = handler.obtainMessage();  
                msg.what = UPDATE_PIC; // 设置消息标识  
                handler.sendMessage(msg);  
                // 休眠1s  
                try {  
                    Thread.sleep(1000);  
                } catch (InterruptedException e) {  
                    // TODO Auto-generated catch block  
                    e.printStackTrace();  
                }  
            }  
        }  
    } 
}
