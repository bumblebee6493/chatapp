package com.example.pankajkumar.letschat;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * Created by pankajkumar on 6/5/16.
 */
public class ChatHeadService extends Service {

    private WindowManager windowManagerChatHead;
    private WindowManager windowManagerCross;
    private ImageView chatHead;
    private ImageView crossIcon;
    WindowManager.LayoutParams paramsChatHead;
    WindowManager.LayoutParams paramsCross;

    @Override
    public void onCreate() {
        super.onCreate();

        windowManagerChatHead = (WindowManager) getSystemService(WINDOW_SERVICE);
        windowManagerCross = (WindowManager) getSystemService(WINDOW_SERVICE);

        chatHead = new ImageView(this);
        chatHead.setImageResource(R.drawable.cicular_user_image);

        crossIcon = new ImageView(this);
        crossIcon.setImageResource(R.drawable.cross);

        paramsCross = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                //These windows are normally placed above all applications, but behind the status bar
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        paramsCross.gravity = Gravity.TOP | Gravity.LEFT;

        final Point p = new Point();
        windowManagerCross.getDefaultDisplay().getSize(p);
        paramsCross.width = 150;
        paramsCross.width = 150;
        paramsCross.x = (int)(p.x/2.5);
        paramsCross.y = p.y - 400;


        paramsChatHead = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.WRAP_CONTENT,
                //These windows are normally placed above all applications, but behind the status bar
                WindowManager.LayoutParams.TYPE_PHONE,
                WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);

        paramsChatHead.gravity = Gravity.TOP | Gravity.LEFT;
        paramsChatHead.width = 200;
        paramsChatHead.height = 200;
        paramsChatHead.x = 0;
        paramsChatHead.y = 100;

        //this code is for dragging the chat head
        chatHead.setOnTouchListener(new View.OnTouchListener() {
            private int initialX;
            private int initialY;
            private float initialTouchX;
            private float initialTouchY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        windowManagerCross.addView(crossIcon, paramsCross);
                        windowManagerCross.updateViewLayout(crossIcon, paramsCross);
                        initialX = paramsChatHead.x;
                        initialY = paramsChatHead.y;
                        initialTouchX = event.getRawX();//relative to device screen = absolute
                        initialTouchY = event.getRawY();
                        return true;
                    case MotionEvent.ACTION_UP:
                        if(paramsChatHead.x > p.x/2){
                            paramsChatHead.x = p.x;
                        }else{
                            paramsChatHead.x = 0;
                        }
                        windowManagerCross.removeView(crossIcon);
                        windowManagerChatHead.updateViewLayout(chatHead, paramsChatHead);
                        return true;
                    case MotionEvent.ACTION_MOVE:
                        paramsChatHead.x = initialX
                                + (int) (event.getRawX() - initialTouchX);
                        paramsChatHead.y = initialY
                                + (int) (event.getRawY() - initialTouchY);

                        if(Math.abs(paramsChatHead.x - paramsCross.x) < 40 && Math.abs(paramsChatHead.y - paramsCross.y) < 40){
                            stopSelf();
                        }
                        windowManagerChatHead.updateViewLayout(chatHead, paramsChatHead);
                        return true;
                }
                return false;
            }
        });

        chatHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("hi","hi");
                Intent i = new Intent(ChatHeadService.this, ContactsLoaderActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });
        windowManagerChatHead.addView(chatHead, paramsChatHead);

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if (chatHead != null)
            windowManagerChatHead.removeView(chatHead);
            windowManagerCross.removeView(crossIcon);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }
}
