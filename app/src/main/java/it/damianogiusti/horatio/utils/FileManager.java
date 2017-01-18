package it.damianogiusti.horatio.utils;

import android.content.Context;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import it.damianogiusti.horatio.R;

/**
 * Created by Damiano Giusti on 18/01/17.
 */
public final class FileManager {

    private static final String HORATIO_FILENAME = "horatio.mp3";

    public static File getHoratioFile(Context context) {
        try {
            final String FILE_PATH = context.getCacheDir().getAbsolutePath() + "/" + HORATIO_FILENAME;
            File horatioFile = new File(FILE_PATH);
            if (horatioFile.exists())
                return horatioFile;
            if (!horatioFile.createNewFile())
                return null;

            InputStream inputStream = context.getResources().openRawResource(R.raw.theme);
            // store raw in cached file
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            OutputStream outputStream = new FileOutputStream(horatioFile);
            outputStream.write(buffer);
            outputStream.close();
            inputStream.close();
            return horatioFile;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
