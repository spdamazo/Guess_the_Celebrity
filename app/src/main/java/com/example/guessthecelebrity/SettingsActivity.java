package com.example.guessthecelebrity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    RadioGroup themeGroup, questionCountGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(ThemeUtils.currentTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        themeGroup = findViewById(R.id.themeGroup);
        questionCountGroup = findViewById(R.id.questionCountGroup);

        if (ThemeUtils.currentTheme == R.style.Theme_GuessTheCelebrity_Light) {
            themeGroup.check(R.id.radioLight);
        } else {
            themeGroup.check(R.id.radioDark);
        }

        if (GameData.numQuestions == 5) {
            questionCountGroup.check(R.id.radio5);
        } else {
            questionCountGroup.check(R.id.radio10);
        }

    }

    public void applySettings(View view) {
        int selectedTheme = themeGroup.getCheckedRadioButtonId();
        int selectedCount = questionCountGroup.getCheckedRadioButtonId();

        if (selectedTheme == R.id.radioLight)
            ThemeUtils.currentTheme = R.style.Theme_GuessTheCelebrity_Light;
        else if (selectedTheme == R.id.radioDark)
            ThemeUtils.currentTheme = R.style.Theme_GuessTheCelebrity_Dark;


        if (selectedCount == R.id.radio5)
            GameData.numQuestions = 5;
        else if (selectedCount == R.id.radio10)
            GameData.numQuestions = 10;

        GameData.resetGame();

        Toast.makeText(this, "Settings Applied", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
