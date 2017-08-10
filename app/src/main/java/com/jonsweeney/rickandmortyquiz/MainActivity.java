package com.jonsweeney.rickandmortyquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {
    double numQuestions = 10.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method gets the user's name
     */
    private String getUserName() {
        EditText nameEditText = (EditText) findViewById(R.id.name_edit_text);
        return nameEditText.getText().toString().trim();
    }

    /**
     * This method is called when the submit button is clicked
     */
    public void submitQuiz(View view) {
        String userName = getUserName();
        double quizScore = gradeQuiz();
        String message = generateMessage(userName, quizScore);
        setResultsDisplay(quizScore);
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    /**
     * This method checks if the answers are correct
     */
    private int getNumberCorrect() {
        RadioButton rb1 = (RadioButton) findViewById(R.id.question_1_correct);
        RadioButton rb2 = (RadioButton) findViewById(R.id.question_2_correct);
        RadioButton rb3 = (RadioButton) findViewById(R.id.question_3_correct);
        RadioButton rb4 = (RadioButton) findViewById(R.id.question_4_correct);
        boolean q5Answer = checkQuestion5();
        boolean q6answer = checkQuestion6();
        ToggleButton tb7 = (ToggleButton) findViewById(R.id.question_7);
        ToggleButton tb8 = (ToggleButton) findViewById(R.id.question_8);
        boolean bool8 = !tb8.isChecked();
        ToggleButton tb9 = (ToggleButton) findViewById(R.id.question_9);
        ToggleButton tb10 = (ToggleButton) findViewById(R.id.question_10);

        boolean answers[] = new boolean[]{rb1.isChecked(), rb2.isChecked(), rb3.isChecked(), rb4.isChecked(),
                q5Answer, q6answer, tb7.isChecked(), bool8, tb9.isChecked(),
                tb10.isChecked()};
        return countCorrectAnswers(answers);
    }

    /**
     * This method checks question 5
     */
    private boolean checkQuestion5() {
        EditText q5EditText = (EditText) findViewById(R.id.question_5_edit_text);
        return (q5EditText.getText().toString().trim().toLowerCase().
                equalsIgnoreCase(getText(R.string.q_5_answer).toString()));
    }

    /**
     * This method checks question 6
     */
    private boolean checkQuestion6() {
        CheckBox answer1CheckBox = (CheckBox) findViewById(R.id.q_6_answer_1);
        CheckBox answer2CheckBox = (CheckBox) findViewById(R.id.q_6_answer_2);
        CheckBox answer3CheckBox = (CheckBox) findViewById(R.id.q_6_answer_3);
        if (answer1CheckBox.isChecked() && answer2CheckBox.isChecked() && answer3CheckBox.isChecked()) {
            return true;
        }
        return false;
    }

    /**
     * This method calculates the number of correct answers
     */
    private int countCorrectAnswers(boolean[] answers) {
        int numberCorrect = 0;
        for (int i = 0; i < answers.length; i++) {
            if (answers[i]) {
                numberCorrect++;
            }
        }
        return numberCorrect;
    }

    /**
     * This method grades the quiz
     */
    private double gradeQuiz() {
        int numCorrect = getNumberCorrect();
        double percentage = (numCorrect / numQuestions) * 100;
        return percentage;
    }

    /**
     * This method generates the message to the user
     *
     * @param name  The name of the user
     * @param score The percentage score
     */
    private String generateMessage(String name, double score) {
        String customMessage;
        if (score >= 90.0) {
            customMessage = getString(R.string.high_score);
        } else if (score >= 70.0) {
            customMessage = getString(R.string.med_score);
        } else {
            customMessage = getString(R.string.low_score);
        }
        return customMessage + name + ":\n Your score is: " + score + "%";
    }

    /**
     * This method sets the results in the results text view
     *
     * @param score The percentage score to be used to get the letter grade to display
     */
    private void setResultsDisplay(double score) {
        TextView resultsTextView = (TextView) findViewById(R.id.results_text_view);
        String letterGrade = getLetterGrade(score);
        resultsTextView.setText("Your Grade: " + letterGrade);
    }

    /**
     * This method converts score to letter grade
     *
     * @param score is used to provide the switch statement with the percentage score
     */
    private String getLetterGrade(double score) {
        int i = (int) score;
        switch (i) {
            case 100:
            case 90:
                return "A";
            case 80:
                return "B";
            case 70:
                return "C";
            case 60:
                return "D";
            default:
                return "F";
        }
    }
}
