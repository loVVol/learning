package com.example.planewar;

import java.util.HashMap;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.AudioManager;
import android.media.SoundPool;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Welcome extends SurfaceView implements SurfaceHolder.Callback {

	private PanlewarActivity activity;
	private TutorialThread thread;// ˢ֡���߳�
	SoundPool soundPool;// ����
	int status = 1;// ��ǰ��״ֵ̬
	Bitmap background2;// ����ͼƬ
	Bitmap startGame;// ��ʼ��Ϸ�˵�
	Bitmap result;// �����˵�
	Bitmap openSound;// �������˵�
	Bitmap closeSound;// �ر������˵�
	Bitmap exit;// �˳��˵�
	HashMap<Integer, Integer> soundPoolMap;
	int width, height;

	Paint paint2;// ��������

	public Welcome(PanlewarActivity activity, int width, int height) {// ������
		super(activity);
		this.activity = activity;// �õ�activity������
    
		this.width = width;
		this.height = height;
		getHolder().addCallback(this);
		this.thread = new TutorialThread(getHolder(), this);
		// this.welcomeThread = new WelcomeViewThread(this);
//		initSounds();// ��ʼ������
	//	playSound(1);
		initBitmap();// ��ʼ��ͼƬ��Դ
	}

	private void playSound(int i) {
		// TODO Auto-generated method stub
		AudioManager mgr = (AudioManager) getContext().getSystemService(
				Context.AUDIO_SERVICE);
		float streamVolumeCurrent = mgr
				.getStreamVolume(AudioManager.STREAM_MUSIC);
		float streamVolumeMax = mgr
				.getStreamMaxVolume(AudioManager.STREAM_MUSIC);// �����������
		float volume = streamVolumeCurrent / streamVolumeMax; // �豸������
		soundPool.play(soundPoolMap.get(i), volume, volume, 1, 0, 1f);// ����
	}

	private void initBitmap() {
		// TODO Auto-generated method stub

		paint2 = new Paint();
		startGame = BitmapFactory.decodeResource(getResources(),
				R.drawable.startgame);// ��ʼ����ʼ��Ϸ
		background2 = BitmapFactory.decodeResource(getResources(),R.drawable.lianxi);
		result = BitmapFactory
				.decodeResource(getResources(), R.drawable.result);// ����
		openSound = BitmapFactory.decodeResource(getResources(),
				R.drawable.opensound);// ������
		closeSound = BitmapFactory.decodeResource(getResources(),
				R.drawable.closesound);// �ر�����
		exit = BitmapFactory.decodeResource(getResources(), R.drawable.exit);// �˳���Ϸ
	}

	private void initSounds() {
		// TODO Auto-generated method stub
		soundPool = new SoundPool(4, AudioManager.STREAM_MUSIC, 100);// ��ʼ��SoundPool
		soundPoolMap = new HashMap<Integer, Integer>();// ��ʼ�� HashMap
		soundPoolMap.put(1, soundPool.load(getContext(), R.raw.welcome1, 1));
	}

	public void onDraw(Canvas canvas) {

		canvas.drawColor(Color.WHITE);// ����ɫ
		Rect rect1 = new Rect(0, 0, width, height);
		Rect rect2 = new Rect(0, 0, background2.getWidth(),
				background2.getHeight());
		canvas.drawBitmap(background2, rect2, rect1, null);// ���Ʊ���ͼ
		canvas.drawBitmap(startGame, width/2-startGame.getWidth()/2, 3*(height-4*startGame.getHeight())/20, paint2);// ���ƿ�ʼ��Ϸ��ť
		canvas.drawBitmap(result, width/2-startGame.getWidth()/2, (height-2*(height-4*startGame.getHeight())/5-2*startGame.getHeight())*3/4, paint2);// ����Ӣ�۰񵥰�ť
		canvas.drawBitmap(exit, width/2-startGame.getWidth()/2,(height-(height-4*startGame.getHeight())/5-startGame.getHeight())*3/4, paint2);// �����˳���ť
		if (activity.isSound) {
			canvas.drawBitmap(closeSound, width/2-startGame.getWidth()/2, (2*(height-4*startGame.getHeight())/5+startGame.getHeight())*3/4 , paint2);// ���ƹر������˵�
		} else {
			canvas.drawBitmap(openSound, width/2-startGame.getWidth()/2, (2*(height-4*startGame.getHeight())/5+startGame.getHeight())*3/4 , paint2);// ���ƴ�����
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		this.thread.setFlag(true);// ����ѭ�����λ
		this.thread.start();// ���������߳�
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		// TODO Auto-generated method stub
		boolean retry = true;// ѭ�����
		thread.setFlag(false);
		while (retry) {
			try {
				thread.join();// �ȴ��̵߳Ľ���
				retry = false;// ����ѭ�����ֹͣѭ��
			} catch (InterruptedException e) {
			}// ���ϵ�ѭ����ֱ��ˢ֡�߳̽���
		}
	}

	public boolean onTouchEvent(MotionEvent event) {// ��Ļ����
		if (event.getAction() == MotionEvent.ACTION_DOWN) {// ��Ļ������
			/*
			 * if(this.status != 4){//�����ǲ˵�״̬ʱ���� return false; }
			 */
			double x = event.getX();// �õ�X����
			double y = event.getY();// �õ�Y����
			if (x > width/2-startGame.getWidth()/2 && 
					x < width/2-startGame.getWidth()/2 + openSound.getWidth() && y > 
			3*(height-4*startGame.getHeight())/20
					&& y<3*(height-4*startGame.getHeight())/20 +
					openSound.getHeight()) {// ����˿�ʼ��ť
				activity.myhander.sendEmptyMessage(2);// ������Ϣ
			} else if (x > width/2-startGame.getWidth()/2 && x < 
					width/2-startGame.getWidth()/2 + result.getWidth() && y > 
			(height-2*(height-4*startGame.getHeight())/5-2*startGame.getHeight())*3/4
					&& y < (height-2*(height-4*startGame.getHeight())/5-2*startGame.getHeight())*3/4
					+ result.getHeight()) {// �����Ӣ�����а�ť
				activity.myhander.sendEmptyMessage(3);// ������Ϣ
			} else if (x > width/2-startGame.getWidth()/2 && x < 
					width/2-startGame.getWidth()/2 + openSound.getWidth() && y > 
			(2*(height-4*startGame.getHeight())/5+startGame.getHeight())*3/4
					&& y < (2*(height-4*startGame.getHeight())/5+startGame.getHeight())*3/4
					+ openSound.getHeight()) {// �����������ť
				activity.isSound = !activity.isSound;// ��������־λ�÷�
			} else if (x > width/2-startGame.getWidth()/2 && x < 
					width/2-startGame.getWidth()/2+ exit.getWidth() && y >
			(height-(height-4*startGame.getHeight())/5-startGame.getHeight())*3/4
					&& y < (height-(height-4*startGame.getHeight())/5-startGame.getHeight())*3/4
					+ exit.getHeight()) {// ������˳���ť
				System.exit(0);// �˳���Ϸ
			}
		}
		return super.onTouchEvent(event);// ���û���ķ���
	}

	class TutorialThread extends Thread {// ˢ֡�߳�
		private int span = 100;// ˯�ߵĺ�����
		private SurfaceHolder surfaceHolder;
		private Welcome welcomeView;// ��ӭ���������
		private boolean flag = false;

		public TutorialThread(SurfaceHolder surfaceHolder, Welcome welcomeView) {// ������
			this.surfaceHolder = surfaceHolder;// SurfaceHolder������
			this.welcomeView = welcomeView;// ��ӭ���������
		}

		public void setFlag(boolean flag) {// ���ñ�׼λ
			this.flag = flag;
		}

		public void run() {// ��д��run����
			Canvas c;
			while (this.flag) {// ѭ��
				c = null;
				try {
					// �����������������ڴ�Ҫ��Ƚϸߵ�����£����������ҪΪnull
					c = this.surfaceHolder.lockCanvas(null);
					synchronized (this.surfaceHolder) {// ͬ��
						welcomeView.onDraw(c);// ���û��Ʒ���
					}
				} finally {// ��finally��֤һ����ִ��
					if (c != null) {
						// ������Ļ��ʾ����
						this.surfaceHolder.unlockCanvasAndPost(c);
					}
				}
				try {
					Thread.sleep(span);// ˯��ָ��������
				} catch (Exception e) {// �����쳣
					e.printStackTrace();// ��ӡ�쳣��Ϣ
				}
			}
		}
	}
}
