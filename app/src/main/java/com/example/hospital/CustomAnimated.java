package com.example.hospital;


import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import java.util.ArrayList;

public class CustomAnimated {
    Context context;
    Animation ttb1;
    Animation ttb2;
    Animation ttb3;
    Animation ttb4;
    Animation ttb5;
    Animation ttb6;
    Animation ttb7;
    Animation stb;
    ArrayList<Animation> animArray=new ArrayList<>();

    public CustomAnimated(Context context) {
        this.context = context;
        ttb1 = AnimationUtils.loadAnimation(context, R.anim.textttb);
        ttb2 = AnimationUtils.loadAnimation(context, R.anim.textttb2);
        ttb3 = AnimationUtils.loadAnimation(context, R.anim.textttb3);
        ttb4 = AnimationUtils.loadAnimation(context, R.anim.textttb4);
        ttb5 = AnimationUtils.loadAnimation(context, R.anim.textttb5);
        ttb6 = AnimationUtils.loadAnimation(context, R.anim.textttb6);
        ttb7 = AnimationUtils.loadAnimation(context, R.anim.textttb7);

        stb = AnimationUtils.loadAnimation(context, R.anim.img_stb);
        for (int i = 0; i < 8; i++) {
//            animArray.add(ttb1);
            animArray.add(ttb2);
            animArray.add(ttb3);
            animArray.add(ttb4);
            animArray.add(ttb5);
            animArray.add(ttb6);
            animArray.add(ttb7);
            animArray.add(stb);
        }

    }

    public void AnimatedScreen(boolean isViewPaired,View... ids) {
if (isViewPaired) {
    for (int i = 0; i < ids.length; i++) {
        ids[i].startAnimation(animArray.get(i / 2));
    }
}else {
    for (int i = 0; i < ids.length; i++) {
        ids[i].startAnimation(animArray.get(i));
    }

}
    }

    public Context getContext() {

        return context;
    }

    public void setContext(Context context) {

        this.context = context;
    }

    public Animation getTtb1() {
        return ttb1;
    }

    public void setTtb1(Animation ttb1) {
        this.ttb1 = ttb1;
    }

    public Animation getTtb2() {
        return ttb2;
    }

    public void setTtb2(Animation ttb2) {
        this.ttb2 = ttb2;
    }

    public Animation getTtb3() {
        return ttb3;
    }

    public void setTtb3(Animation ttb3) {
        this.ttb3 = ttb3;
    }

    public Animation getTtb4() {
        return ttb4;
    }

    public void setTtb4(Animation ttb4) {
        this.ttb4 = ttb4;
    }

    public Animation getTtb5() {
        return ttb5;
    }

    public void setTtb5(Animation ttb5) {
        this.ttb5 = ttb5;
    }

    public Animation getTtb6() {
        return ttb6;
    }

    public void setTtb6(Animation ttb6) {
        this.ttb6 = ttb6;
    }

    public Animation getTtb7() {
        return ttb7;
    }

    public void setTtb7(Animation ttb7) {
        this.ttb7 = ttb7;
    }

    public Animation getStb() {
        return stb;
    }

    public void setStb(Animation stb) {
        this.stb = stb;
    }


}
