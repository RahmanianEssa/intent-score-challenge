package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ScorerActivity extends AppCompatActivity {
public static final String KEY_SCORER = "add";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorer);
    }

    public void handleOk(View view) {
        EditText editText = (EditText) findViewById(R.id.tvScorer);
        String stringToPassBack = editText.getText().toString();

        Intent intent = new Intent();
        intent.putExtra(KEY_SCORER, stringToPassBack);
        setResult(RESULT_OK, intent);
        finish();
    }
}
