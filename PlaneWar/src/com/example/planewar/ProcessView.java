package com.example.planewar;


import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;

public class ProcessView extends SurfaceView implements SurfaceHolder.Callback
{
	Bitmap processbackground;

	private TutorialThread thread;//ˢ֡���߳�
	Bitmap process;
	int proces=0;
	int startx,starty;
	private Paint paint;
	private boolean flag=true;
	private PanlewarActivity activity;
	int scren_width=400;
	int scren_heigt=800;
	private int type;
    public ProcessView(PanlewarActivity activity, int type, int screenwidth, int screenheight)
    {
        super(activity);
        this.activity = activity;//�õ�activity������
        getHolder().addCallback(this);
        this.thread = new TutorialThread(getHolder(), this);//��ʼ���ػ��߳�
        this.type = type;
        paint = new Paint();//��������
        /* */
        this.scren_width = screenwidth;
        this.scren_heigt = screenheight;

		paint.setTextSize(12);//���������С
		process = BitmapFactory.decodeResource(getResources(), R.drawable.process);
		processbackground = BitmapFactory.decodeResource(getResources(), R.drawable.background_prg);
		this.startx = scren_width / 2 - process.getWidth() / 2;
		this.starty = scren_heigt / 2 - process.getHeight() / 2 - process.getHeight() / 4;

	}
    public void onDraw(Canvas canvas)
    {

        Rect rect1 = new Rect(0, 0, processbackground.getWidth(), processbackground.getHeight());
        Rect rect2 = new Rect(0, 0, scren_width, scren_heigt);
        canvas.drawBitmap(processbackground, rect1, rect2, null);
        canvas.drawBitmap(process, startx, starty, paint);
        canvas.drawRect(startx + proces * (process.getWidth() / 100), starty, startx + process.getWidth(),
                        starty + process.getHeight(), paint);
        canvas.drawText("�����С���" + scren_width + "+" + scren_heigt
                        , startx + (process.getWidth() / 2) - 20,
                        starty + (process.getHeight() + 20), paint);
    }


    private WindowManager getWindowManager()
    {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3)
    {
        // TODO Auto-generated method stub

    }

    @Override
    public void surfaceCreated(SurfaceHolder arg0)
    {
        // TODO Auto-generated method stub
        this.thread.setFlag(true);//�����̱߳�־λ
        this.thread.start();//���߳�
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder arg0)
    {
        // TODO Auto-generated method stub
        boolean retry = true;//ѭ����־λ
        thread.setFlag(false);//����ѭ����־λ
        while (retry)
        {
            try
            {
                thread.join();//�ȴ��߳̽���
                retry = false;
            } 
            catch (InterruptedException e)
            {}//���ϵ�ѭ����ֱ��ˢ֡�߳̽���
        }
    }
    class TutorialThread extends Thread
    {//ˢ֡�߳�
        private int span = 400;//˯�ߵĺ����� 
        private SurfaceHolder surfaceHolder;
        private ProcessView processView;//processView����
        private boolean flag = false;//ѭ����־λ
        public TutorialThread(SurfaceHolder surfaceHolder, ProcessView processView)
        {//������
            this.surfaceHolder = surfaceHolder;
            this.processView = processView;//�õ����ؽ���
        }
        public void setFlag(boolean flag)
        {//���ñ�־λ
            this.flag = flag;
        }
        public void run()
        {//��д��run����
            Canvas c;//����
            while (this.flag)
            {//ѭ��
                c = null;
                try
                {
                    // �����������ڴ�Ҫ��Ƚϸߵ�����£��������ҪΪnull
                    c = this.surfaceHolder.lockCanvas(null);
                    synchronized (this.surfaceHolder)
                    {
                        processView.onDraw(c);//���û��Ʒ���
                    }
                }
                finally
                {//ʹ��finally��䱣֤�������һ����ִ��
                    if (c != null)
                    {
                        //������Ļ��ʾ����
                        this.surfaceHolder.unlockCanvasAndPost(c);
                    }
                }

                try
                {
                    Thread.sleep(span);//˯��ָ��������
                }
                catch (Exception e)
                {//�����쳣��Ϣ
                    e.printStackTrace();//��ӡ�쳣��Ϣ
                }
                proces += 20;
                if (proces == 100)
                {
                    if (processView.type == 1)
                    {//�е�WelcomeView
                        processView.activity.myhander.sendEmptyMessage(4);//����activity����Handler��Ϣ
                    }

                }
            }
        }
    }



}
