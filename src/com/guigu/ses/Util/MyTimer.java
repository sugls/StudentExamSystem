package com.guigu.ses.Util;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Lsc on 2016/12/30 10:04.
 */
public class MyTimer {
    private  final Timer timer = new Timer();
     final long start = System.currentTimeMillis();
    // long end =start+ 2*60*60*1000;
     final long end = start+1*60*1000;
    StringBuffer buffer = new StringBuffer();
    public  String countDown(){

        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                MyTimer.this.buffer.delete(0, buffer.length());
                long show = end - System.currentTimeMillis();
                long h = show/1000/60/60;
                long m = show/1000/60%60;
                long s = show/1000%60;
                buffer.append("倒计时：").append(h<10?"0":"").append(h).append(":").append(m<10?"0":"").append(m).append(":").append(s<10?"0":"").append(s);
            }
        }, 0, 1000);
        return buffer.toString();
    }

    public  String RemainTime(){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                MyTimer.this.buffer.delete(0, buffer.length());
                buffer.append("距离考试结束还有5分钟");
            }
        },new Date(end-1*30*1000));  //end-5*60*1000
        return buffer.toString();
    }

    public String EndTime(){
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                buffer.delete(0,buffer.length());
                timer.cancel();
                buffer.append("考试结束！");
            }
        },new Date(end));
        return buffer.toString();
    }

}
