package com.filipkesteli.horizontalslider;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ProgressBar;

import java.util.List;

/**
 * Created by programer on 8.6.2016..
 */

//Korisnicka komponenta
public class HorizontalSlider extends ProgressBar {

    private OnProgressChangedListener onProgressChangedListener;

    private List<OnProgressChangedListener> onProgressChangedListenerList; //puno pretplatnika na severinu

    //mozemo ga definirati kroz java code
    public HorizontalSlider(Context context) {
        super(context);
    }

    //AttributeSet je skup atributa iz xml sucelja (kao nekakva mapa)
    //Presrecemo ovaj konstruktor
    public HorizontalSlider(Context context, AttributeSet attrs) {
        super(context, attrs, android.R.attr.progressBarStyleHorizontal);
    }

    //Ovdje mozemo dodati i stil -> Recimo kako smo imali progress dialog
    public HorizontalSlider(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setOnProgressChangedListener(OnProgressChangedListener onProgressChangedListener) {
        this.onProgressChangedListener = onProgressChangedListener;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //Moram izracunati koliki je progress
        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
            float x = event.getX(); //iz Activity-ja
            float width = getWidth(); //iz View-a
            float percent = x / width; //postotak u vrijednosti izmedu 0 i 1
            //frajer ima svoj interni value u sebi
            int progress = Math.round(getMax() * percent);

            //rijesen problem
            if (progress < 0) {
                progress = 0;
            } else if (progress > getMax()) {
                progress = getMax();
            }

            this.setProgress(progress);

            notifyListener(progress); //oglasimo
        }
        return true;
    }

    private void notifyListener(int progress) {
        if (onProgressChangedListener != null) {
            onProgressChangedListener.onProgressChanged(this, progress);
        }
    }
}
