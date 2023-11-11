package com.example.englishapplication.controller.game;

import javafx.event.Event;
import javafx.event.EventType;

public class GameEvent extends Event {
    public static final EventType<GameEvent> GAME_OVER = new EventType<>(Event.ANY, "GAME_OVER");
    private int score;
    private int time;
    public GameEvent(EventType<? extends Event> eventType) {
        super(eventType);
    }
}
