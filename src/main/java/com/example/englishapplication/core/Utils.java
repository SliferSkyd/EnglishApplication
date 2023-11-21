package com.example.englishapplication.core;

import animatefx.animation.AnimationFX;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.io.FileNotFoundException;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class Utils {
    protected interface DoSomeThing {
        void process() throws ClassNotFoundException, FileNotFoundException;
    }
    protected static void copyToClipboard(String text) {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(text);
        clipboard.setContent(content);
    }

    protected static void concurrentProcess(DoSomeThing concurrentProcessing) {
        Thread thread = new Thread(() -> {
            try {
                concurrentProcessing.process();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    protected static boolean confirm(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText(header);
        alert.setContentText(content);
        AtomicBoolean result = new AtomicBoolean(false);
        alert.showAndWait().ifPresent(buttonType -> {
            if (buttonType == ButtonType.OK) {
                result.set(true);
            } else {
                result.set(false);
            }
        });
        return result.get();
    }

    protected static String shuffle(String text) {
        char[] characters = text.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            int randomIndex = (int)(Math.random() * characters.length);
            char temp = characters[i];
            characters[i] = characters[randomIndex];
            characters[randomIndex] = temp;
        }
        return new String(characters);
    }
    protected static void playAnimation(AnimationFX animation, double speed) {
        animation.setSpeed(speed);
        animation.play();
    }
}
