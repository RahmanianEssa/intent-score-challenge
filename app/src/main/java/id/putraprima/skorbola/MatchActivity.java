package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MatchActivity extends AppCompatActivity {
    public static final int SECOND_ACTIVITY_REQUEST_CODE_HOME= 1;
    public static final int SECOND_ACTIVITY_REQUEST_CODE_AWAY = 2;
    private static final String HASIL_KEY = "hasil";
    private TextView homeText;
    private TextView awayText;
    private ImageView imageHome;
    private ImageView imageAway;
    private TextView scorehome;
    private TextView scoreaway;
    int skorHome = 0;
    int skorAway = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        homeText = findViewById(R.id.txt_home);
        awayText = findViewById(R.id.txt_away);
        imageHome = findViewById(R.id.home_logo);
        imageAway = findViewById(R.id.away_logo);
        scorehome = findViewById(R.id.score_home);
        scoreaway = findViewById(R.id.score_away);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String home = extras.getString(MainActivity.HOMETEAM_KEY);
            homeText.setText(home);

            String away = extras.getString(MainActivity.AWAYTEAM_KEY);
            awayText.setText(away);

            Bitmap bmp1=(Bitmap) extras.get("imagehome");
            imageHome.setImageBitmap(bmp1);

            Bitmap bmp2 =(Bitmap) extras.get("imageaway");
            imageAway.setImageBitmap(bmp2);
        }
    }


    public void addhomescore(View view) {
        Intent intent = new Intent(this, ScorerActivity.class);
        startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE_HOME);
    }

    public void addawayscore(View view) {
        Intent intent = new Intent(this, ScorerActivity.class);
        startActivityForResult(intent, SECOND_ACTIVITY_REQUEST_CODE_AWAY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SECOND_ACTIVITY_REQUEST_CODE_HOME) {
            String returnString = data.getStringExtra(ScorerActivity.KEY_SCORER);
            TextView tvHome = findViewById(R.id.textHome);
            String scorerHome ="";
            scorerHome += "\n"+ returnString;
            tvHome.setText(scorerHome);
            skorHome++;
            scorehome.setText(String.valueOf(scorehome));
        }
        else if(requestCode == SECOND_ACTIVITY_REQUEST_CODE_AWAY){
            String returnString = data.getStringExtra(ScorerActivity.KEY_SCORER);
            TextView tvAway = findViewById(R.id.textAway);
            String scorerAway ="";
            scorerAway += "\n" + returnString;
            tvAway.setText(scorerAway);
            skorAway++;
            scoreaway.setText(String.valueOf(scoreaway));
        }
    }

    public void cekhasil(View view) {
        String hasil = null;
        if (skorHome==skorAway){
            hasil="Draw";
        } else if(skorHome>skorAway){
            hasil = homeText.getText().toString();
        } else if (skorAway>skorHome){
            hasil=awayText.getText().toString();
        }
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(HASIL_KEY, hasil);
        startActivity(intent);


    }
}