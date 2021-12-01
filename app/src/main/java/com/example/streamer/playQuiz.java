package com.example.streamer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.streamer.adapter.adapter_main_category;
import com.example.streamer.custom.question;

import java.util.ArrayList;
import java.util.Arrays;

import static com.example.streamer.Question.*;

public class playQuiz extends AppCompatActivity {

    ArrayList<question> customQuestion = new ArrayList<>();
    int arr [ ];
    TextView ques, option_1, option_2, option_3, option_4;
    TextView questionCounter;
    int index = 0;
    question question;
    Button nextBtn;
    int correctAnswers = 0;
    CountDownTimer timer;
    TextView time;
    boolean b;

    private long backPressedTime;
    private Toast backToast;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_quiz);

        ques = findViewById(R.id.ques);
        option_1 = findViewById(R.id.option_1);
        option_2 = findViewById(R.id.option_2);
        option_3 = findViewById(R.id.option_3);
        option_4 = findViewById(R.id.option_4);
        questionCounter = findViewById(R.id.questionCounter);
        nextBtn = findViewById(R.id.nextBtn);
        time = findViewById(R.id.timer);
        b = false;


        customQuestion = (ArrayList<question>) getIntent().getSerializableExtra(QuesToplayquiz);
        arr = new int [customQuestion.size()];
        Arrays.fill(arr, 2);
        // 0 for correct    //1 for wrong     //2 for null


        resetTimer();
        timer.start();
        setNextQuestion();

    }


    void resetTimer()
    {
        timer = new CountDownTimer(7800000,1000) {
            @Override
            public void onTick(long millisUntilFinished)
            {
                int totalSecs = (int) (millisUntilFinished/1000);
                int hours = totalSecs / 3600;
                int minutes = (totalSecs % 3600) / 60;
                int seconds = totalSecs % 60;
                String min = (minutes > 9) ? minutes + "" : "0" + minutes ;
                time.setText( hours + ":" + min + ":" + seconds );
            }

            @Override
            public void onFinish()
            {
                reset();
                if (index+1 < customQuestion.size()) {
                    index++;
                    setNextQuestion();
                }
                else {
                    Intent intent = new Intent(playQuiz.this, Result.class);
                    intent.putExtra("correct", correctAnswers);
                    intent.putExtra("total", customQuestion.size());
                    intent.putExtra("options", arr);
                    startActivity(intent);
                    Toast.makeText(playQuiz.this, "Quiz Finished.", Toast.LENGTH_SHORT).show();
                }

            }
        };
    }

    void showAnswer()
    {
        String op = question.getAnswer();
        String answer = "";
        switch(op)
        {
            case "A":
                answer = question.getOption_one();
                break;

            case "B":
                answer = question.getOption_two();
                break;

            case "C":
                answer = question.getOption_three();
                break;

            case "D":
                answer = question.getOption_four();
                break;
        }

        if(answer.equals(option_1.getText().toString()))
            option_1.setBackground(getResources().getDrawable(R.drawable.option_right));
        else if(answer.equals(option_2.getText().toString()))
            option_2.setBackground(getResources().getDrawable(R.drawable.option_right));
        else if(answer.equals(option_3.getText().toString()))
            option_3.setBackground(getResources().getDrawable(R.drawable.option_right));
        else if(answer.equals(option_4.getText().toString()))
            option_4.setBackground(getResources().getDrawable(R.drawable.option_right));
    }

    void setNextQuestion() {

        b=false;

        //if(timer != null)
            //timer.cancel();
        //timer.start();

        if (index < customQuestion.size()) {
            questionCounter.setText(String.format("%d/%d", (index + 1), customQuestion.size()));
            question = customQuestion.get(index);
            ques.setText(question.getQuestion().replaceAll("\\<.*?>", "")
            .replaceAll("&nbsp;", "").replaceAll("&quot;", "")
            .replaceAll("&amp", ""));
            option_1.setText(question.getOption_one());
            option_2.setText(question.getOption_two());
            option_3.setText(question.getOption_three());
            option_4.setText(question.getOption_four());
        }
        ques.setMovementMethod(new ScrollingMovementMethod()); //to scroll fetched questions
    }

    void checkAnswer(TextView textView) {

        String op = question.getAnswer();
        String answer = "";
        switch(op)
        {
            case "A":
                answer = question.getOption_one();
                break;

            case "B":
                answer = question.getOption_two();
                break;

            case "C":
                answer = question.getOption_three();
                break;

            case "D":
                answer = question.getOption_four();
                break;
        }

        String selectedAnswer = textView.getText().toString();
        if(selectedAnswer.equals(answer) && b == false)
        {
            b = true;
            correctAnswers++;
            arr [index] = 0;
            textView.setBackground(getResources().getDrawable(R.drawable.option_selected));
        }
        else if ( b == false)
        {
            b = true;
            arr [index] = 1;
            //showAnswer();
            textView.setBackground(getResources().getDrawable(R.drawable.option_selected));
        }
    }

    void reset()
    {
        option_1.setBackground(getResources().getDrawable(R.drawable.option_unselected));

        option_2.setBackground(getResources().getDrawable(R.drawable.option_unselected));

        option_3.setBackground(getResources().getDrawable(R.drawable.option_unselected));

        option_4.setBackground(getResources().getDrawable(R.drawable.option_unselected));
    }


    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.option_1:
            case R.id.option_2:
            case R.id.option_3:
            case R.id.option_4:
                //if (timer != null)
                   //timer.cancel();
                TextView selected = (TextView) view;
                checkAnswer(selected);

                break;

            case R.id.nextBtn:
                reset();
                if (index+1 < customQuestion.size()) {
                    index++;
                    setNextQuestion();
                }
                else {
                    Intent intent = new Intent(playQuiz.this, Result.class);
                    intent.putExtra("correct", correctAnswers);
                    intent.putExtra("total", customQuestion.size());
                    intent.putExtra("options", arr);
                    startActivity(intent);
                    Toast.makeText(this, "Quiz Finished.", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void quit(View view)
    {
        Intent intent = new Intent(playQuiz.this, Result.class);
        intent.putExtra("correct", correctAnswers);
        intent.putExtra("total", customQuestion.size());
        intent.putExtra("options", arr);
        startActivity(intent);
        Toast.makeText(this, "You have left the Quiz.", Toast.LENGTH_SHORT).show();
    }


    // double press of back button to exit from quiz screen.
    @Override
    public void onBackPressed() {

        if(backPressedTime + 3000 > System.currentTimeMillis())
        {
            backToast.cancel();
            super.onBackPressed();
            return;
        }
        else
        {
            backToast = Toast.makeText(getBaseContext(), "Press Back Again to Exit from this Page", Toast.LENGTH_SHORT);
            backToast.show();
        }
        backPressedTime = System.currentTimeMillis();
    }
}