package com.example.android.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewSwitcher;

public class MainActivity extends AppCompatActivity {

    static String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText name_edit = findViewById(R.id.edit_name);

        final ViewSwitcher switcher = findViewById(R.id.view_switcher);

//      next button to the description layout
        Button next = findViewById(R.id.next_button);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = name_edit.getText().toString();
                if(name.equals("")){
                    name_edit.setError("Please enter your name!");
                } else {
                    switcher.showNext();
                    TextView description = findViewById(R.id.description_textview);
                    description.setText(getString(R.string.description, name));
                }
            }
        });

//        back button to activity_main layout
        Button back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switcher.showPrevious();
            }
        });

//        start quiz button
        Button startQuiz = findViewById(R.id.start_quiz);
        startQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent quizIntent = new Intent(MainActivity.this, Quiz.class);
                startActivity(quizIntent);
            }
        });
    }
}
