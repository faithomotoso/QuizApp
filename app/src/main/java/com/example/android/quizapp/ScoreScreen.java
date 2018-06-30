package com.example.android.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ScoreScreen extends Quiz {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_screen);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);

        TextView nameView = findViewById(R.id.nameView);
        nameView.setText(getString(R.string.name, name));

        double percent = score / 10 * 100;

        TextView percentView = findViewById(R.id.percentage);
        percentView.setText(getString(R.string.percentage, percent));

        TextView questionsAnswered = findViewById(R.id.question_count);
        questionsAnswered.setText(getString(R.string.question_count, score, cards.size()));

        Button tryAgain = findViewById(R.id.try_again_button);
        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startMainActivity = new Intent(ScoreScreen.this, MainActivity.class);
                startActivity(startMainActivity);
            }
        });
    }
}
