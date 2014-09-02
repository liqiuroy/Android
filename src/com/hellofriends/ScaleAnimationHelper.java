package com.hellofriends;

import android.content.Context;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;

public class ScaleAnimationHelper {
    private Context con;    
    private int order;    
    private DisplayNextView listener;    
    private ScaleAnimation myAnimation_Scale;  
   
    public ScaleAnimationHelper(Context con, int order) {    
        this.con = con;    
        this.order = order;    
    }    
     
        //�Ŵ����,����Ҫ���ü�����    
    public void ScaleOutAnimation(View view) {    
        myAnimation_Scale = new ScaleAnimation(0.1f, 1.0f, 0.1f, 1f,    
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,    
                0.5f);    
        myAnimation_Scale.setInterpolator(new AccelerateInterpolator());    
        AnimationSet aa = new AnimationSet(true);    
        aa.addAnimation(myAnimation_Scale);    
        aa.setDuration(500);    
   
        view.startAnimation(aa);    
    }    
   
    public void ScaleInAnimation(View view) {    
        listener = new DisplayNextView((Page3) con, order);    
        myAnimation_Scale = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f,    
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,    
                0.5f);    
        myAnimation_Scale.setInterpolator(new AccelerateInterpolator());    
               //��СLayout����,�ڶ���������Ҫ�Ӹ�View�Ƴ���    
        myAnimation_Scale.setAnimationListener(listener);    
        AnimationSet aa = new AnimationSet(true);    
        aa.addAnimation(myAnimation_Scale);    
        aa.setDuration(500);    
   
        view.startAnimation(aa);    
    }    
}  
