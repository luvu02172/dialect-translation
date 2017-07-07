package com.example.nalnal.dialecttranslation;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class VoiceRecognitionActivity extends Activity {

    private final int GOOGLE_STT = 1;

    private TextView resultTitle;
    private TextView resultIndicator;
    private TextView textLog;
    private ArrayList<String> arrListResult;
    private String selectedString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voice_recognition);

        resultTitle = (TextView) findViewById(R.id.resultTitle);
        resultIndicator = (TextView) findViewById(R.id.resultIndicator);
        textLog = (TextView) findViewById(R.id.textLog);

        resultTitle.setVisibility(View.INVISIBLE);
        resultIndicator.setVisibility(View.INVISIBLE);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                resultTitle.setVisibility(View.INVISIBLE);
                resultIndicator.setVisibility(View.INVISIBLE);
                resultTitle.setText("");
                textLog.setText("");
                voice();
            }
        });
    }

    public void voice() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "삑- 소리가 나면\n말씀하세요.");
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 20);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "ko-KR");
        startActivityForResult(intent, GOOGLE_STT);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == GOOGLE_STT && resultCode == RESULT_OK) {
            showSelectDialog(requestCode, data);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void showSelectDialog(int requestCode, Intent data) {
        String key = "";

        if (requestCode == GOOGLE_STT)
            key = RecognizerIntent.EXTRA_RESULTS;
        arrListResult = data.getStringArrayListExtra(key);

        String[] result = new String[arrListResult.size()];
        arrListResult.toArray(result);

        AlertDialog ad = new AlertDialog.Builder(this).setTitle("선택하세요.")
                .setSingleChoiceItems(result, -1, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selectedString = arrListResult.get(which);
                    }
                })
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        resultTitle.setText("인식 결과");
                        textLog.setText(selectedString);
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        resultTitle.setText("인식 실패");
                        textLog.setText("인식 결과 선택을 취소하였습니다.");
                        selectedString = null;
                    }
                }).create();
        resultTitle.setVisibility(View.VISIBLE);
        resultIndicator.setVisibility(View.VISIBLE);
        ad.show();
    }
}
