package it.damianogiusti.horatio;

import android.animation.Animator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import it.damianogiusti.horatio.utils.AnimUtils;

/**
 * Created by Damiano Giusti on 18/01/17.
 */
public abstract class HoratioActivity extends AppCompatActivity {

    private ImageView imgHoratioDefault;
    private ImageView imgHoratioPressed;
    private SeekBar seekBar;

    private boolean canGetHoratio = true;
    private boolean canDisposeHoratio = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindViews();

        // hide horatio
        imgHoratioPressed.setVisibility(View.GONE);
        imgHoratioPressed.setAlpha(0f);
        // hide seekBar
        seekBar.setVisibility(View.GONE);
        seekBar.setAlpha(0f);

        imgHoratioDefault.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!canGetHoratio)
                    return;
                canGetHoratio = false;
                // show horatio
                imgHoratioPressed.setVisibility(View.VISIBLE);
                AnimUtils.fadeInView(imgHoratioPressed, new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {
                        canDisposeHoratio = true;
                        imgHoratioDefault.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
                // show seekBar
                seekBar.setVisibility(View.VISIBLE);
                AnimUtils.fadeInView(seekBar, null);
                // request awesomeness
                onNeedToBeAwesome();
            }
        });

        imgHoratioPressed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!canDisposeHoratio)
                    return;
                onAwesomenessNotMoreNeeded();
                onAwesomenessCompleted();
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                onAwesomenessNeedsToChangeMoment(seekBar.getProgress());
            }
        });
    }

    private void bindViews() {
        imgHoratioDefault = (ImageView) findViewById(R.id.imgHoratio);
        imgHoratioPressed = (ImageView) findViewById(R.id.imgHoratioPressed);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
    }

    protected abstract void onNeedToBeAwesome();

    protected void onAwesomenessStarted(int durationOfAwesome) {
        seekBar.setMax(durationOfAwesome);
    }

    protected void onAwesomenessInProgress(int progress) {
        seekBar.setProgress(progress);
    }

    protected abstract void onAwesomenessNeedsToChangeMoment(int progress);

    protected abstract void onAwesomenessNotMoreNeeded();

    protected void onAwesomenessCompleted() {
        imgHoratioDefault.setVisibility(View.VISIBLE);
        AnimUtils.fadeOutView(seekBar, null);
        AnimUtils.fadeOutView(imgHoratioPressed, new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                seekBar.setVisibility(View.GONE);
                seekBar.setProgress(0);
                imgHoratioPressed.setVisibility(View.GONE);
                canGetHoratio = true;
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }
}
