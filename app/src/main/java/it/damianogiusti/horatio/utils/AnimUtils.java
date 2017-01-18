package it.damianogiusti.horatio.utils;

import android.animation.Animator;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewPropertyAnimator;

/**
 * Created by Damiano Giusti on 14/01/17.
 */
public class AnimUtils {

    public static void fadeOutView(View view, @Nullable final Animator.AnimatorListener listener) {
        final ViewPropertyAnimator animator = view.animate().setDuration(500).alpha(0f);
        if (listener != null)
            animator.setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    listener.onAnimationStart(animation);
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    listener.onAnimationEnd(animation);
                    animator.setListener(null);
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    listener.onAnimationCancel(animation);
                    animator.setListener(null);
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                    listener.onAnimationStart(animation);
                }
            });
        animator.start();
    }

    public static void fadeInView(View view, @Nullable final Animator.AnimatorListener listener) {
        final ViewPropertyAnimator animator = view.animate().setDuration(500).alpha(1f);
        if (listener != null)
            animator.setListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    listener.onAnimationStart(animation);
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    listener.onAnimationEnd(animation);
                    animator.setListener(null);
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                    listener.onAnimationCancel(animation);
                    animator.setListener(null);
                }

                @Override
                public void onAnimationRepeat(Animator animation) {
                    listener.onAnimationStart(animation);
                }
            });
        animator.start();
    }
}
