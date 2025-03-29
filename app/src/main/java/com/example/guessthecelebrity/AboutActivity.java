package com.example.guessthecelebrity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class AboutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(ThemeUtils.currentTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
    }
}
