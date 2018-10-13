package com.example.planewar;



import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Message;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.ListAdapter;

public class HelpView extends SurfaceView implements SurfaceHolder.Callback {
	PanlewarActivity activity;
	private TutorialThread thread;//ˢ֡���߳�
	Paint paint;
	Bitmap background3;
	int width=768,height=900;
	Bitmap ok;
	Cursor cur;
	String[] name={"","","","",""};
	int[] score={0,0,0,0,0};
	public HelpView(PanlewarActivity activity,int width,int height,Cursor cur) {//������ 
		super(activity);
	     this.cur=cur;
		this.activity = activity;

        getHolder().addCallback(this);
        this.thread = new TutorialThread(getHolder(), this);
        initBitmap();//��ʼ��ͼƬ��Դ
        this.width=width;
        this.height=height;
	}
	public void initBitmap(){//��ʼ��ͼƬ��Դ�ķ���
		paint = new Paint(); 
		
		background3 = BitmapFactory.decodeResource(getResources(), R.drawable.background);
		ok = BitmapFactory.decodeResource(getResources(), R.drawable.sure);
	}
	public void onDraw(Canvas canvas){//�Լ�д�Ļ��Ʒ���
		//����������z��ģ��󻭵ĻḲ��ǰ�滭��
		canvas.drawColor(Color.WHITE);//����Ļˢ�ɰ�ɫ 
		Rect rect1 = new Rect(0,0,background3.getWidth(),background3.getHeight());
		Rect rect2 = new Rect(0,0,width,height);
		canvas.drawBitmap(background3,rect1,rect2, null);
        paint.setTextSize(20);
        paint.setColor(Color.BLUE);
	    canvas.drawText("Ӣ�����а�", width*2/5, height/20, paint);
	    canvas.drawText("ս������", width/4, height/10, paint);
	    canvas.drawText("����", width/4, 6*height/10, paint);
	    canvas.drawText("����", width/4, 5*height/10, paint);
	    canvas.drawText("����", width/4, 4*height/10, paint);
	    canvas.drawText("�ڶ�", width/4, 3*height/10, paint);
	    canvas.drawText("��һ", width/4, 2*height/10, paint);
		 canvas.drawText("Ӣ������", width/2, height/10, paint);
		 for(int i=0;i<5;i++){
				switch(i){
				case 0:
					 canvas.drawText(name[i], width/2,6*height/10, paint);
					break;
					case 1:
						 canvas.drawText(name[i], width/2,5*height/10, paint);
				    break;
					case 2:
						 canvas.drawText(name[i], width/2,4*height/10, paint);
				  
				    break;
					case 3:
						 canvas.drawText(name[i], width/2,3*height/10, paint);
			     	break;
					case 4:
						 canvas.drawText(name[i], width/2,2*height/10, paint);
				   
				    break;
					
				}
			}
		 canvas.drawText("ս������", width*3/4, height/10, paint);
		 for(int i=0;i<5;i++){
				switch(i){
				case 0:
					 canvas.drawText(""+score[i], width*3/4, 6*height/10, paint);
					break;
					case 1:
						 canvas.drawText(""+score[i], width*3/4, 5*height/10, paint);
				    break;
					case 2:
						 canvas.drawText(""+score[i], width*3/4, 4*height/10, paint);
				  
				    break;
					case 3:
						 canvas.drawText(""+score[i], width*3/4, 3*height/10, paint);
			     	break;
					case 4:
						 canvas.drawText(""+score[i], width*3/4, 2*height/10, paint);
				   
				    break;
					
				}
			}
		canvas.drawBitmap(ok, width/2-ok.getWidth()/2, height*7/10, paint);  
 
	}
	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
	}
	public void surfaceCreated(SurfaceHolder holder) {
        this.thread.setFlag(true);
        this.thread.start();
	}
	public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        thread.setFlag(false);
        while (retry) {
            try {
                thread.join();
                retry = false;
            } 
            catch (InterruptedException e) {//���ϵ�ѭ����ֱ��ˢ֡�߳̽���
            }
        }
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {//��Ļ����
		if(event.getAction() == MotionEvent.ACTION_DOWN){
			if(event.getX()>width/2-ok.getWidth()/2 && event.getX()<width/2-ok.getWidth()/2+ok.getWidth()
					&& event.getY()>height*7/10 && event.getY()<height*7/10+ok.getHeight()){//�����ȷ����ť
				send();
				 
			
			}  
		}
		return super.onTouchEvent(event);
	}
	public void send(){
		activity.myhander.sendEmptyMessage(7);
	}
	public HelpView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	class TutorialThread extends Thread{//ˢ֡�߳�
		private int span = 100;//˯�ߵ� ������ 
		private SurfaceHolder surfaceHolder;
		private HelpView helpView;
		private boolean flag = false;
        public TutorialThread(SurfaceHolder surfaceHolder, HelpView helpView) {//������
            this.surfaceHolder = surfaceHolder;
            this.helpView = helpView;
        }
        public void setFlag(boolean flag) {
        	this.flag = flag;
        }
		@Override
		public void run() {
			if(cur!=null&&cur.getCount()>=0){
				int i=0;
				if(cur.moveToFirst()){
					do{
					name[i]=cur.getString(cur.getColumnIndex("name"));
						score[i]=cur.getInt(cur.getColumnIndex("score"));
						
					
						i++;
					 }while(cur.moveToNext());
					i=0;
				}
			}
			Canvas c;
            while (this.flag) {
                c = null;
                try {
                	// �����������������ڴ�Ҫ��Ƚϸߵ�����£����������ҪΪnull
                    c = this.surfaceHolder.lockCanvas(null);
                    synchronized (this.surfaceHolder) {
                    	helpView.onDraw(c);
                    }
                } finally {
                    if (c != null) {
                    	//������Ļ��ʾ����
                        this.surfaceHolder.unlockCanvasAndPost(c);
                    }
                }
                try{
                	Thread.sleep(span);
                }
                catch(Exception e){
                	e.printStackTrace();
                }
            }
		}
	}


}
