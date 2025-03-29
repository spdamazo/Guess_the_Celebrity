package com.example.guessthecelebrity;

public class Question {
    public int imageResId;
    public String correctAnswer;
    public String[] options;

    public Question(int imageResId, String correctAnswer, String[] options) {
        this.imageResId = imageResId;
        this.correctAnswer = correctAnswer;
        this.options = options;
    }
}
