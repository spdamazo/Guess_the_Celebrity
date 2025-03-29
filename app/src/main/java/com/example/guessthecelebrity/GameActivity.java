package com.example.guessthecelebrity;

import android.os.Bundle;
import android.util.TypedValue;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class GameActivity extends AppCompatActivity {

    ImageView imageView;
    RadioGroup optionsGroup;
    Button nextButton, prevButton;
    TextView feedbackText;

    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(ThemeUtils.currentTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        imageView = findViewById(R.id.imageView);
        optionsGroup = findViewById(R.id.optionsGroup);
        nextButton = findViewById(R.id.btnNext);
        prevButton = findViewById(R.id.btnPrevious);
        feedbackText = findViewById(R.id.feedbackText);

        index = GameData.currentLevel;
        loadQuestion();
    }

    void loadQuestion() {
        optionsGroup.setOnCheckedChangeListener(null); // Clear previous listener
        optionsGroup.removeAllViews();
        Question q = GameData.questions.get(index);
        imageView.setImageResource(q.imageResId);
        feedbackText.setText("");

        for (String option : q.options) {
            RadioButton radioButton = new RadioButton(this);
            radioButton.setText(option);
            radioButton.setTextSize(18f);

            // ✅ Dynamically apply themed text color
            int textColor = resolveThemeColor(R.attr.colorOnBackground);
            radioButton.setTextColor(textColor);

            optionsGroup.addView(radioButton);

            if (GameData.selectedAnswers[index] != null &&
                    GameData.selectedAnswers[index].equals(option)) {
                radioButton.setChecked(true);
            }
        }


        if (GameData.answered[index]) {
            checkAnswer();
            disableOptions(); // Prevent changing answers
        } else {
            optionsGroup.setOnCheckedChangeListener((group, checkedId) -> {
                RadioButton selected = findViewById(checkedId);
                if (selected != null && !GameData.answered[index]) {
                    GameData.selectedAnswers[index] = selected.getText().toString();
                    GameData.answered[index] = true;
                    checkAnswer();
                    disableOptions(); // Lock the options
                }
            });
        }
        // ✅ Show a toast if this is the last question
        if (index == GameData.numQuestions - 1) {
            Toast.makeText(this, "You've reached the last question!", Toast.LENGTH_SHORT).show();
        }
    }

    private int resolveThemeColor(int attrId) {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(attrId, typedValue, true);
        return typedValue.data;
    }

    void disableOptions() {
        for (int i = 0; i < optionsGroup.getChildCount(); i++) {
            optionsGroup.getChildAt(i).setEnabled(false);
        }
    }

    void checkAnswer() {
        String selected = GameData.selectedAnswers[index];
        String correct = GameData.questions.get(index).correctAnswer;

        for (int i = 0; i < optionsGroup.getChildCount(); i++) {
            RadioButton rb = (RadioButton) optionsGroup.getChildAt(i);
            if (rb.getText().toString().equals(correct)) {
                rb.setTextColor(getColor(android.R.color.holo_green_dark));
            } else if (rb.getText().toString().equals(selected)) {
                rb.setTextColor(getColor(android.R.color.holo_red_dark));
            }
        }

        feedbackText.setText(selected.equals(correct) ? "Correct!" : "Wrong!");

        // ✅ Show Game Over when all questions are answered (regardless of index)
        if (allQuestionsAnswered()) {
            showGameOverDialog();
        }
    }



    public void nextQuestion(android.view.View view) {
        if (index < GameData.numQuestions - 1) {
            index++;
            GameData.currentLevel = index;
            loadQuestion();
        }
    }

    public void previousQuestion(android.view.View view) {
        if (index > 0) {
            index--;
            GameData.currentLevel = index;
            loadQuestion();
        }
    }

    private boolean allQuestionsAnswered() {
        for (int i = 0; i < GameData.numQuestions; i++) {
            if (!GameData.answered[i]) return false;
        }
        return true;
    }

    private void showGameOverDialog() {
        int score = 0;
        for (int i = 0; i < GameData.numQuestions; i++) {
            if (GameData.questions.get(i).correctAnswer.equals(GameData.selectedAnswers[i])) {
                score++;
            }
        }

        String message = "You got " + score + " out of " + GameData.numQuestions + " correct!";

        new android.app.AlertDialog.Builder(this)
                .setTitle("Game Over")
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("Play Again", (dialog, which) -> {
                    GameData.resetGame();
                    index = 0;
                    GameData.currentLevel = 0;
                    loadQuestion();
                })
                .setNegativeButton("Main Menu", (dialog, which) -> {
                    finish(); // return to MainActivity
                })
                .show();
    }


}
