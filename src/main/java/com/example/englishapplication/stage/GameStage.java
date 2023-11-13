package com.example.englishapplication.stage;

import com.example.englishapplication.controller.game.GameEvent;
import com.example.englishapplication.controller.game.GameOverScene;
import com.example.englishapplication.controller.game.MainScene;

import java.util.concurrent.atomic.AtomicReference;

public class GameStage extends BaseStage {
    public GameStage() {
        super("Rearrange Letters", 150, 10);
        AtomicReference<MainScene> uiManager = new AtomicReference<>(new MainScene(stage));
        stage.addEventFilter(GameEvent.GAME_OVER, event -> {
            GameOverScene gameOverScene = new GameOverScene(stage, event.getScore(), event.getTime());
        });
        stage.addEventFilter(GameEvent.PLAY_AGAIN, event -> {
            uiManager.set(new MainScene(stage));
        });
    }
}
