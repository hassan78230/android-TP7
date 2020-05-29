package com.example.tp7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Quiz extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private TextView textViewName;
    private TextView textViewQuestion;
    private ListView listViewAnswer;
    private Button btnValidate;

    private String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        listViewAnswer = findViewById(R.id.listViewAnswer);
        listViewAnswer.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listViewAnswer.setOnItemClickListener(this);

        btnValidate = findViewById(R.id.btntValidate);
        btnValidate.setOnClickListener(this);

        textViewQuestion = findViewById(R.id.textViewQuestion);
        textViewQuestion.setText("Quels acteurs ont joués dans le film Ocean's Eleven (version 2001)");


        textViewName = findViewById(R.id.textViewName);

        Intent intent = getIntent();
        textViewName.setText("Bonjour "+intent.getStringExtra("name"));

        initListAnswer();
    }

    @Override
    public void onClick(View v) {
        verifyAnswer();


    }

    private void verifyAnswer() {
        SparseBooleanArray sp = listViewAnswer.getCheckedItemPositions();
        ArrayList<String> listResult =new ArrayList<String>();
        for (int i=0;i<sp.size();i++){
            if (sp.valueAt(i) == true){
                Answer answer = (Answer) listViewAnswer.getItemAtPosition(i);
                listResult.add(String.valueOf(i));



            }
        }
        ArrayList<String> listResponse = new ArrayList<String>();
        listResponse.add("0");
        listResponse.add("1");

        if (listResponse.equals(listResult)){
            result = "Bonne reponse";
            Toast.makeText(this,"Bonne reponse",Toast.LENGTH_LONG).show();
        }else {
            result = "Mauvaise reponse";
            Toast.makeText(this,"Mauvaise reponse",Toast.LENGTH_LONG).show();
        }
        Log.d("hassan", "verifyAnswer: " + listResponse);
        Log.d("hassan", "verifyResult: " + listResult);

        sendResult();
    }

    private void sendResult() {
        Intent intent = new Intent();
        intent.putExtra(MainActivity.RESULT,result);
        setResult(2,intent);
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        boolean currentCheck = ((CheckedTextView) view).isChecked();
        Answer answer = (Answer) listViewAnswer.getItemAtPosition(position);
        answer.setActive(!currentCheck);

    }

    private void initListAnswer() {
        Answer pitt = new Answer("Bratt Pitt");
        Answer clooney = new Answer("Georges Clooney");
        Answer affleck = new Answer("Ben Affleck");
        Answer elba = new Answer("Idriss Elba");

        Answer[] actors = new Answer[]{pitt,clooney,affleck,elba};
        ArrayAdapter<Answer> arrayAdapter
                = new ArrayAdapter<Answer>(this, android.R.layout.simple_list_item_checked , actors);

        this.listViewAnswer.setAdapter(arrayAdapter);

        for(int i=0;i< actors.length; i++ )  {
            this.listViewAnswer.setItemChecked(i,actors[i].isActive());
        }
    }
}
