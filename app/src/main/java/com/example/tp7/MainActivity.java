package com.example.tp7;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnStart;
    private EditText textViewName;
    private TextView textView;

    private String playerName;

    public static final String RESULT = "com.example.tp6.RESULT";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewName = findViewById(R.id.editTextName);
        textView = findViewById(R.id.textView);

        btnStart = findViewById(R.id.btnBegin);
        btnStart.setText("Commencez!");
        btnStart.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        playerName = textViewName.getText().toString();
        Intent intent = new Intent(this,Quiz.class);
        intent.putExtra("name",textViewName.getText().toString());
        startActivityForResult(intent,2);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 2){
            Log.d("hassan", "onActivityResult: " + data.getStringExtra(RESULT));
            textView.setText(data.getStringExtra(RESULT)+" "+playerName);
            btnStart.setText("Recommencez!");
        }
    }
}
