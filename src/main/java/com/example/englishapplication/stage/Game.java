package com.example.englishapplication.stage;

import com.example.englishapplication.controller.game.GameEvent;
import com.example.englishapplication.controller.game.GameOver;
import com.example.englishapplication.controller.game.MainScene;
import javafx.stage.Stage;

import java.util.concurrent.atomic.AtomicReference;

public class Game extends BaseStage {
    public Game() {
        Stage stage = newStage("Rearrange Letters");
        AtomicReference<MainScene> uiManager = new AtomicReference<>(new MainScene(stage));
        stage.addEventFilter(GameEvent.GAME_OVER, event -> {
            if (!isClosed) {
                GameOver gameOver = new GameOver(stage, event.getScore(), event.getTime());
            }
        });
        stage.addEventFilter(GameEvent.PLAY_AGAIN, event -> {
            if (!isClosed) {
                uiManager.set(new MainScene(stage));
            }
        });
    }
}
