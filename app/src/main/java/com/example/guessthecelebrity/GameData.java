package com.example.guessthecelebrity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameData {
    public static int currentLevel = 0;
    public static int numQuestions = 5;
    public static boolean[] answered = new boolean[10];
    public static String[] selectedAnswers = new String[10];

    public static List<Question> questions = Arrays.asList(
            new Question(R.drawable.celebrity1, "Selena Gomez", new String[]{"Selena Gomez", "Brad Pitt", "Johnny Depp"}),
            new Question(R.drawable.celebrity2, "Taylor Swift", new String[]{"Emma Stone", "Taylor Swift", "Anne Hathaway"}),
            new Question(R.drawable.celebrity3, "Dwayne Johnson", new String[]{"Jason Momoa", "Dwayne Johnson", "Vin Diesel"}),
            new Question(R.drawable.celebrity4, "Ariana Grande", new String[]{"Katy Perry", "Ariana Grande", "Billie Eilish"}),
            new Question(R.drawable.celebrity5, "Chris Hemsworth", new String[]{"Liam Hemsworth", "Chris Evans", "Chris Hemsworth"}),
            new Question(R.drawable.celebrity6, "Zendaya", new String[]{"Zendaya", "Rihanna", "Doja Cat"}),
            new Question(R.drawable.celebrity7, "Leonardo DiCaprio", new String[]{"Tom Cruise", "Leonardo DiCaprio", "Matt Damon"}),
            new Question(R.drawable.celebrity8, "Scarlett Johansson", new String[]{"Scarlett Johansson", "Margot Robbie", "Jennifer Lawrence"}),
            new Question(R.drawable.celebrity9, "Tom Holland", new String[]{"Timothée Chalamet", "Tom Holland", "Andrew Garfield"}),
            new Question(R.drawable.celebrity10, "Rihanna", new String[]{"Beyoncé", "Rihanna", "Nicki Minaj"})
    );

    public static void resetGame() {
        currentLevel = 0;
        answered = new boolean[10];
        selectedAnswers = new String[10];
    }
}
