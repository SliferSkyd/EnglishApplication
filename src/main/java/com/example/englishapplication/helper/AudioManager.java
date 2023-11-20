package com.example.englishapplication.helper;

import com.example.englishapplication.core.Utils;
import com.voicerss.tts.AudioCodec;
import com.voicerss.tts.AudioFormat;
import com.voicerss.tts.Languages;
import com.voicerss.tts.VoiceParameters;
import com.voicerss.tts.VoiceProvider;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;

public class AudioManager extends Utils {
    private static SourceDataLine speaker;
    public static void getTextToSpeech(String text, String language) {
        parallelProcessing(() -> {
            try {
                VoiceProvider tts = new VoiceProvider("545ad787cb434355a3c84ed93f02c88e");
                VoiceParameters params = new VoiceParameters(text, (language.equals("en") ? Languages.English_UnitedStates : Languages.Vietnamese));
                params.setCodec(AudioCodec.WAV);
                params.setFormat(AudioFormat.Format_44KHZ.AF_44khz_16bit_stereo);
                params.setBase64(false);
                params.setSSML(false);
                params.setRate(0);
                byte[] voice = tts.speech(params);
                startPlaying(voice);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
    public static void startPlaying(byte[] audio) {
        try {
            AudioInputStream ais = new AudioInputStream(new ByteArrayInputStream(audio),
                    new javax.sound.sampled.AudioFormat(44100, 16, 2, true, false),
                    audio.length);
            javax.sound.sampled.AudioFormat format = ais.getFormat();
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
            speaker = (SourceDataLine) AudioSystem.getLine(info);
            speaker.open(format);
            speaker.start();
            int buffer;
            byte[] data = new byte[4096];
            while ((buffer = ais.read(data)) != -1) speaker.write(data, 0, buffer);
            speaker.drain();
            speaker.close();
            ais.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void stopPlaying() {
        if (speaker != null) {
            speaker.stop();
            speaker.close();
        }
    }
}
