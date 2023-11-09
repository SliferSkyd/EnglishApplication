package com.example.englishapplication.Game;

import com.example.englishapplication.core.DictionaryManagement;
import javafx.scene.control.Button;

public class GameManager {

    private int score = 0;
    boolean EndGame = false;
    private int question = 0;
    private int wrongAnswer = 0;
    private int numberQuestion = 10;
    public MediaManager mediaManager = new MediaManager("/fxml/Sound/cute.mp3");
    TextAnimation textAnimation =new TextAnimation();


    public void initializeGame() {
        mediaManager.playBackgroundMusic(0.25);
        textAnimation.Text();
    }

    void calculateScore() {
        boolean existed = (DictionaryManagement.trie.searchWord(textAnimation.getFinalword()) != null);
        if (existed) {
            setScore(score+10);
            mediaManager.playSoundEffect("/fxml/Sound/correct.mp3");
        }
        else {
            setWrongAnswer(++wrongAnswer);
            mediaManager.playSoundEffect("/fxml/Sound/DuckWrong.wav");
        }
        setQuestion(++question);
    }

    public void checkSubmit() {
        if (!isEndGame() && textAnimation.getFinalword().length()==textAnimation.getWord().length()) {
            calculateScore();
            textAnimation.Text();
        }
    }

    public void checkSound(Button sound) {
        mediaManager.ButtonCheck(sound);
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getQuestion() {
        return question;
    }

    public void setQuestion(int question) {
        this.question = question;
    }

    public int getWrongAnswer() {
        return wrongAnswer;
    }

    public void setWrongAnswer(int wrongAnswer) {
        this.wrongAnswer = wrongAnswer;
    }

    public int getNumberQuestion() {
        return numberQuestion;
    }

    public void setNumberQuestion(int numberQuestion) {
        this.numberQuestion = numberQuestion;
    }

    public boolean isEndGame() {
        return EndGame;
    }

    public void setEndGame(boolean endGame) {
        EndGame = endGame;
    }


    public TextAnimation getTextAnimation() {
        return textAnimation;
    }

// Thêm các phương thức khác cho logic trò chơi ở đây
}