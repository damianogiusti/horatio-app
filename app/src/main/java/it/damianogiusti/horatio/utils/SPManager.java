package it.damianogiusti.horatio.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Damiano Giusti on 18/01/17.
 */
public final class SPManager {

    private static final String SP_NAME = "horatio";
    private static final String KEY_FOR_AWESOME_MOMENTS = "awesomeMomentsCount";

    private SharedPreferences sharedPreferences;

    public SPManager(Context context) {
        this.sharedPreferences = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
    }

    public int getAwesomeMomentsCount() {
        return sharedPreferences.getInt(KEY_FOR_AWESOME_MOMENTS, 0);
    }

    public boolean incrementAwesomeMomentsCount() {
        return sharedPreferences.edit()
                .putInt(KEY_FOR_AWESOME_MOMENTS, getAwesomeMomentsCount() + 1)
                .commit();
    }
}
