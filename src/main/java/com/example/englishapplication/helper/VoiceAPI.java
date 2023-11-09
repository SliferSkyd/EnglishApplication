package com.example.englishapplication.helper;

import com.example.englishapplication.core.Utils;
import com.voicerss.tts.AudioCodec;
import com.voicerss.tts.AudioFormat;
import com.voicerss.tts.Languages;
import com.voicerss.tts.VoiceParameters;
import com.voicerss.tts.VoiceProvider;

import javax.sound.sampled.*;
import java.io.ByteArrayInputStream;

import static com.example.englishapplication.helper.AudioManager.startPlaying;

public class VoiceAPI extends Utils {
    public static void getTextToSpeech(String text, String language) {
        parallelProcessing(() -> {
            try {
                VoiceProvider tts = new VoiceProvider("600b8fac7a214e5e91fff8c9baf69a4a");
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
}