package com.micro0.rewardsprogram.app;

/**
 * Created by Micro0 on 4/23/14.
 */
public class Calculate {
    int total;
    public  void calculatee(int ex , int vg , int g , int fa ){
        ex = ex * 40;
        vg = vg * 30;
        g = g * 20;
        fa = fa * -20;
        total = ex + vg + g + fa;
    }
    public  int totall(){
        return total ;
    }
}
