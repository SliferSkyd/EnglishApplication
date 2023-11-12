package com.example.englishapplication.controller.game;

import javafx.event.Event;
import javafx.event.EventType;

public class GameEvent extends Event {
    public static final EventType<GameEvent> GAME_OVER = new EventType<>(Event.ANY, "GAME_OVER");
    public static final EventType<GameEvent> PLAY_AGAIN = new EventType<>(Event.ANY, "PLAY_AGAIN");
    private int score;
    private String time;
    public int getScore() {
        return score;
    }
    public String getTime() {
        return time;
    }
    public GameEvent(EventType<? extends Event> eventType, int score, String time) {
        super(eventType);
        this.score = score;
        this.time = time;
    }
}
