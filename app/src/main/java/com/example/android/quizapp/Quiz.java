package com.example.android.quizapp;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Collections;

public class Quiz extends MainActivity {

    Context context;
    RelativeLayout relativeLayout;
    static double score;
    final ArrayList<ArrayList<Object>> cards = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        context = getApplicationContext();
        relativeLayout = findViewById(R.id.relativelayout);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        cards.add(addArray(R.string.question_one, "radiobutton", R.array.question_one_options));
        cards.add(addArray(R.string.question_two, "multichoice", R.array.question_two_options));
        cards.add(addArray(R.string.question_three, "input", 0));
        cards.add(addArray(R.string.question_four, "input", 0));
        cards.add(addArray(R.string.question_five, "radiobutton", R.array.question_five_options));
        cards.add(addArray(R.string.question_six, "radiobutton", R.array.question_six_options));
        cards.add(addArray(R.string.question_seven, "radiobutton", R.array.question_seven_options));
        cards.add(addArray(R.string.question_eight, "input", 0));
        cards.add(addArray(R.string.question_nine, "radiobutton", R.array.question_nine_options));
        cards.add(addArray(R.string.question_ten, "radiobutton", R.array.question_ten_options));


//      Numbering the questions
        ArrayList<Integer> index = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            index.add(i);
        }
        Collections.shuffle(index);

//       ArrayList to store the cardView id's
        ArrayList<Integer> ids = new ArrayList<>();

        final ArrayList<CardView> cardViews = new ArrayList<>();
        for(int i = 0; i < cards.size(); i++){
            cardViews.add(new CardView(context));
            setCardView(cardViews.get(i));

            setQuestion(cardViews.get(i), i + 1, (int)cards.get(index.get(i)).get(0),
                    (String)cards.get(index.get(i)).get(1),
                    (int)cards.get(index.get(i)).get(2));

            ids.add(cardViews.get(i).getId());
        }

//        An ArrayList to hold question id's and answers
        ArrayList<ArrayList<Object>> answers = new ArrayList<>();

        Button submitButton = new Button(context);
        submitButton.setText(getString(R.string.submit));
        submitButton.setBackgroundColor(Color.GREEN);
        RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        buttonParams.setMargins(10, 10, 10, 10);


        buttonParams.addRule(RelativeLayout.BELOW, cardViews.get(cardViews.size() - 1).getId());
        submitButton.setLayoutParams(buttonParams);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                score = 0;
                for (int i = 0; i < cardViews.size(); i++){
                    setAnswers(cardViews.get(i), String.valueOf(cardViews.get(i).getTag()));
                }
                Toast finalScore = Toast.makeText(context, "You got " + String.valueOf(score) + " out of 10.", Toast.LENGTH_LONG);
                finalScore.show();

                Intent showScoreScreen = new Intent(Quiz.this, ScoreScreen.class);
                startActivity(showScoreScreen);
            }
        });

        showCards(cardViews, ids);
        relativeLayout.addView(submitButton, buttonParams);

    }

//    method to add values in answers arraylist
    void setAnswers(CardView cardView, String mode) {
        LinearLayout cardLinearLayout = (LinearLayout)cardView.getChildAt(0);
        LinearLayout questionLinearLayout = (LinearLayout) cardLinearLayout.getChildAt(0);
        TextView question = ((TextView)questionLinearLayout.getChildAt(1));
        CharSequence questionText;

        switch(mode){
            case "input":
                EditText answerEditView = (EditText)cardLinearLayout.getChildAt(1);
                questionText = question.getText();
                if (getString(R.string.question_three).equals(questionText)) {
                    if (answerEditView.getText().toString().toLowerCase().equals(getString(R.string.question_three_answer).toLowerCase())) {
                        score += 1;
                    }

                } else if (getString(R.string.question_four).equals(questionText)) {
                    if (answerEditView.getText().toString().toLowerCase().equals(getString(R.string.question_four_answer).toLowerCase())) {
                        score += 1;
                    }

                } else if (getString(R.string.question_eight).equals(questionText)) {
                    if (answerEditView.getText().toString().toLowerCase().equals(getString(R.string.question_eight_answer).toLowerCase())) {
                        score += 1;
                    }
                }
                break;

            case "radiobutton":
                RadioGroup groupOptions = (RadioGroup)cardLinearLayout.getChildAt(1);
                String[] options;
                String selectedOption;
                questionText = question.getText();
                try {
                    if (getString(R.string.question_one).equals(questionText)) {
                        options = getResources().getStringArray(R.array.question_one_options);
                    selectedOption = String.valueOf(((RadioButton) findViewById(groupOptions.getCheckedRadioButtonId())).getText());
                        if (selectedOption.toLowerCase().equals(options[1].toLowerCase())) {
                            score += 1;
                        }

                    } else if (getString(R.string.question_five).equals(questionText)) {
                        options = getResources().getStringArray(R.array.question_five_options);
                    selectedOption = String.valueOf(((RadioButton) findViewById(groupOptions.getCheckedRadioButtonId())).getText());
                        if (selectedOption.toLowerCase().equals(options[2].toLowerCase())) {
                            score += 1;
                        }

                    } else if (getString(R.string.question_six).equals(questionText)) {
                        options = getResources().getStringArray(R.array.question_six_options);
                    selectedOption = String.valueOf(((RadioButton) findViewById(groupOptions.getCheckedRadioButtonId())).getText());
                        if (selectedOption.toLowerCase().equals(options[0].toLowerCase())) {
                            score += 1;
                        }

                    } else if (getString(R.string.question_seven).equals(questionText)) {
                        options = getResources().getStringArray(R.array.question_seven_options);
                    selectedOption = String.valueOf(((RadioButton) findViewById(groupOptions.getCheckedRadioButtonId())).getText());
                        if (selectedOption.toLowerCase().equals(options[0].toLowerCase())) {
                            score += 1;
                        }

                    } else if (getString(R.string.question_nine).equals(questionText)) {
                        options = getResources().getStringArray(R.array.question_nine_options);
                    selectedOption = String.valueOf(((RadioButton) findViewById(groupOptions.getCheckedRadioButtonId())).getText());
                        if (selectedOption.toLowerCase().equals(options[2].toLowerCase())) {
                            score += 1;
                        }

                    } else if (getString(R.string.question_ten).equals(questionText)) {
                        options = getResources().getStringArray(R.array.question_ten_options);
                    selectedOption = String.valueOf(((RadioButton) findViewById(groupOptions.getCheckedRadioButtonId())).getText());
                        if (selectedOption.toLowerCase().equals(options[2].toLowerCase())) {
                            score += 1;
                        }

                    }

                } catch (Exception e){
                    Log.e("RadioButton error ", e.getMessage());
                }
                break;

            case "multichoice":
                questionText = question.getText();
                if (getString(R.string.question_two).equals(questionText)) {
                    CheckBox[] answers = new CheckBox[2];
                    answers[0] = (CheckBox) cardLinearLayout.getChildAt(1);
                    answers[1] = (CheckBox) cardLinearLayout.getChildAt(3);

                    if (answers[0].isChecked()) {
                        score += 0.5;
                    }
                    if (answers[1].isChecked()) {
                        score += 0.5;
                    }
                }
                break;
        }
    }


//    setting relativeLayout below rule and displaying them
    void showCards(ArrayList<CardView> cards, ArrayList<Integer> id){
        for(int i = 0; i < cards.size(); i++){
            RelativeLayout.LayoutParams param = (RelativeLayout.LayoutParams)cards.get(i).getLayoutParams();
            if(i == 0){
                relativeLayout.removeView(cards.get(i));
                relativeLayout.addView(cards.get(i));
            } else {
                param.addRule(RelativeLayout.BELOW, id.get(i - 1));
            }
            relativeLayout.removeView(cards.get(i));
            relativeLayout.addView(cards.get(i),param);

        }
    }


//    returns arrayList with id of the question , options and the mode as a string
    ArrayList<Object> addArray(int q, String mode, int opts){
        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add(q);
        arrayList.add(mode);
        arrayList.add(opts);
        return arrayList;
    }


//  Creates CardView with default attributes
    void setCardView(CardView cardView){
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(10, 10, 10, 10);

        cardView.setId(View.generateViewId());
        cardView.setContentPadding(20, 20, 20, 20);
        cardView.setBackgroundColor(Color.parseColor("#E3F2FD"));
        cardView.setRadius(40);
        cardView.setLayoutParams(params);
    }


/*  Creates LinearLayout within the CardView
    with TextView and RadioGroup where applicable
    @param q -> string resource id of the question
    @param opts -> string resource of the options
    @param mode -> options type (radio button, checkbox, edit text)
*/
    void setQuestion(CardView cardView, int number, int q, String mode, int opts){

        //      LinearLayout for both question and options/edittext

        LinearLayout cardLinearLayout = new LinearLayout(context);
        cardLinearLayout.setOrientation(LinearLayout.VERTICAL);
        cardLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

//      LinearLayout for index number and question
        LinearLayout questionLinearLayout = new LinearLayout(context);
        questionLinearLayout.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        questionLinearLayout.setOrientation(LinearLayout.HORIZONTAL);

//      index -> question number
        TextView index = new TextView(context);
        index.setTextColor(Color.BLACK);
        index.setText(getString(R.string.index, number));

        TextView question = new TextView(context);
        question.setTextColor(Color.BLACK);
        question.setText(getString(q));

        questionLinearLayout.addView(index);
        questionLinearLayout.addView(question);
        cardLinearLayout.addView(questionLinearLayout);


        switch(mode){
            case "radiobutton":
                RadioGroup group = new RadioGroup(context);
                String[] options = getResources().getStringArray(opts);
                int pos = 0;
                while(pos < options.length){
                    RadioButton button = new RadioButton(context);
                    button.setText(options[pos]);
                    button.setId(View.generateViewId());
                    group.addView(button);
                    cardView.setTag("radiobutton");
                    pos++;
                }
                cardLinearLayout.addView(group);
                break;

            case "multichoice":
                options = getResources().getStringArray(opts);
                pos = 0;
                while(pos < options.length) {
                    CheckBox box = new CheckBox(context);
                    box.setText(options[pos]);
                    box.setId(View.generateViewId());
                    cardLinearLayout.addView(box);
                    cardView.setTag("multichoice");
                    pos++;
                }
                break;

            case "input":
                EditText answer = new EditText(context);
                answer.setHint("Answer");
                answer.setTextColor(Color.BLACK);
                answer.setHintTextColor(Color.GRAY);
                answer.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
                cardView.setTag("input");
                cardLinearLayout.addView(answer);
                break;
        }

        cardView.addView(cardLinearLayout);
    }
}
