package com.example.englishapplication.base;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

public abstract class Utils {
    protected interface ParallelProcessing {
        void process() throws ClassNotFoundException;
    }
    protected void copyToClipboard(String text) {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent content = new ClipboardContent();
        content.putString(text);
        clipboard.setContent(content);
    }

    protected void parallelProcessing(ParallelProcessing parallelProcessing) {
        Thread thread = new Thread(() -> {
            try {
                parallelProcessing.process();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
        thread.setDaemon(true);
        thread.start();
    }
}
