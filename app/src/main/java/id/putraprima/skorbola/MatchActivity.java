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
    private ImageView homeImage;
    private ImageView awayImage;
    private TextView score_Home;
    private TextView score_Away;
    int scoreHome = 0;
    int scoreAway = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        homeText = findViewById(R.id.txt_home);
        awayText = findViewById(R.id.txt_away);
        homeImage = findViewById(R.id.home_logo);
        awayImage = findViewById(R.id.away_logo);
        score_Home = findViewById(R.id.score_home);
        score_Away = findViewById(R.id.score_away);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String home = extras.getString(MainActivity.HOMETEAM_KEY);
            homeText.setText(home);

            String away = extras.getString(MainActivity.AWAYTEAM_KEY);
            awayText.setText(away);

            Bitmap bmp1=(Bitmap) extras.get("imagehome");
           homeImage.setImageBitmap(bmp1);

            Bitmap bmp2 =(Bitmap) extras.get("imageaway");
         awayImage.setImageBitmap(bmp2);
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
            String returnString = data.getStringExtra("keyName");
            TextView TVhome = (TextView) findViewById(R.id.textView4);
            String ScorerHome = "";
            ScorerHome += " "+returnString;
            TVhome.setText(""+ScorerHome);
            scoreHome++;
            score_Home.setText(""+scoreHome);
        }
        else if(requestCode == SECOND_ACTIVITY_REQUEST_CODE_AWAY){
            TextView TVaway = (TextView) findViewById(R.id.textView5);
            String returnString = data.getStringExtra("keyName");
            String ScorerAway = "";
            ScorerAway += " "+returnString;
            TVaway.setText(""+ScorerAway);
            scoreAway++;
            score_Away.setText(""+scoreAway);
        }
    }

    public void cekhasil(View view) {
        String hasil = null;
        if (scoreHome==scoreAway){
           hasil = "DRAW";
        }else if (scoreHome>scoreAway){
            hasil = homeText.getText().toString();
        }else if (scoreAway>scoreHome){
         hasil = awayText.getText().toString();
        }
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(HASIL_KEY, hasil);
        startActivity(intent);


    }
}