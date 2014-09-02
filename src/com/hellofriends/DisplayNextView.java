package com.hellofriends;

import android.app.Activity;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;

public class DisplayNextView implements AnimationListener {  
	  
    Object obj;    
      
    // 动画监听器的构造函数    
    Activity ac;    
    int order;    
    
    public DisplayNextView(Activity ac, int order) {    
        this.ac = ac;    
        this.order = order;    
    }    
    
    public void onAnimationStart(Animation animation) {    
    }    
    
    public void onAnimationEnd(Animation animation) {    
        doSomethingOnEnd(order);    
    }    
    
    public void onAnimationRepeat(Animation animation) {    
    }    
    
    private final class SwapViews implements Runnable {    
        public void run() {    
        	System.out.println("------------->SwapViews");    
        }    
    }    
    
    public void doSomethingOnEnd(int _order) {    
        System.out.println("------------->End"); 
    }    
}    
