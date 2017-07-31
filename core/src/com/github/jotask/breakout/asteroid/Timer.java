package com.github.jotask.breakout.asteroid;

import com.badlogic.gdx.utils.TimeUtils;

/**
 * Timer
 *
 * @author Jose Vives Iznardo
 * @since 31/07/2017
 */
public class Timer {

    private long time;
    private long next;

    public Timer(float seg){

        time = this.toSec(seg);

        reset();
    }

    private long toSec(float seg){

        Float f = seg;

        f *= 1000000000;

        return f.longValue();

    }

    public void reset(){
        this.next = TimeUtils.nanoTime() + time;
    }

    public boolean isPassed(){ return this.isPassed(false);}
    public boolean isPassed(boolean reset){

        boolean b = (TimeUtils.nanoTime() > this.next);

        if(reset && b)
            reset();

        return b;

    }

}