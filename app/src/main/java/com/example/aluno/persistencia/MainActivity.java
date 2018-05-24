package com.example.aluno.persistencia;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private final int SEGUNDA = 1;
    private final String TEXT_VISIBLE = "textVisible";
    private final String TEXT_CONTENT = "textContent";
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.textView);

        if(savedInstanceState != null){
            boolean isVisible = savedInstanceState.getBoolean(TEXT_VISIBLE);
            if(isVisible) {
                String mensagem = savedInstanceState.getString(TEXT_CONTENT);

                mTextView.setVisibility(View.VISIBLE);
                mTextView.setText(mensagem);
            }

        }
    }

    public void proxActivity(View view){
        Intent intent = new Intent(this, SegundaActivity.class);
        startActivityForResult(intent, SEGUNDA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            String mensagem = data.getStringExtra(SegundaActivity.RESPOSTA);

            mTextView.setVisibility(View.VISIBLE);
            mTextView.setText(mensagem);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if(mTextView.getVisibility() == View.VISIBLE){
            outState.putBoolean(TEXT_VISIBLE, true);
            outState.putString(TEXT_CONTENT , mTextView.getText().toString());
        }
    }
}
