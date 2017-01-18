package it.damianogiusti.horatio;

import android.os.Bundle;
import android.widget.Toast;

import java.io.File;

import it.damianogiusti.horatio.utils.AudioPlayerManager;
import it.damianogiusti.horatio.utils.FileManager;
import it.damianogiusti.horatio.utils.SPManager;

public class MainActivity extends HoratioActivity implements AudioPlayerManager.AudioPlayingListener, AudioPlayerManager.SeekChangeListener {

    private AudioPlayerManager audioPlayerManager;
    private SPManager spManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spManager = new SPManager(getApplicationContext());
    }

    @Override
    protected int getCountOfAwesomeMoments() {
        return spManager.getAwesomeMomentsCount();
    }

    @Override
    protected void onNeedToBeAwesome() {
        File horatioFile = FileManager.getHoratioFile(getApplicationContext());
        if (horatioFile == null) {
            Toast.makeText(MainActivity.this, getString(R.string.cannot_get_horatio), Toast.LENGTH_SHORT).show();
            return;
        }
        spManager.incrementAwesomeMomentsCount();
        audioPlayerManager = AudioPlayerManager.getInstance()
                .init(horatioFile.getAbsolutePath())
                .setSeekChangeListener(this)
                .setAudioPlayingListener(this);
        onAwesomenessStarted(audioPlayerManager.getAudioDuration());
        audioPlayerManager.startPlaying();
    }

    @Override
    protected void onAwesomenessNeedsToChangeMoment(int progress) {
        if (audioPlayerManager != null)
            audioPlayerManager.changeSeek(progress);
    }

    @Override
    protected void onAwesomenessNotMoreNeeded() {
        if (audioPlayerManager != null) {
            audioPlayerManager.stopPlaying();
            audioPlayerManager = null;
        }
    }

    @Override
    public void onPlayingFinish() {
        onAwesomenessCompleted();
        audioPlayerManager = null;
    }

    @Override
    public void onSeekChanged(int position) {
        if (audioPlayerManager != null)
            onAwesomenessInProgress(position);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (audioPlayerManager != null)
            audioPlayerManager.resumePlaying();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (audioPlayerManager != null)
            audioPlayerManager.pausePlaying();
    }
}
