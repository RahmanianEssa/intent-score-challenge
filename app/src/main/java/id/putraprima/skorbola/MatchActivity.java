package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MatchActivity extends AppCompatActivity {
    private static final String HASIL_KEY = "hasil";
    private TextView homeText;
    private TextView awayText;
    private ImageView imageHome;
    private ImageView imageAway;
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
        startActivity(intent);
    }

    public void addawayscore(View view) {
        Intent intent = new Intent(this, ScorerActivity.class);
        startActivity(intent);
    }

    public void cekhasil(View view) {
        Intent intent = new Intent(this, ResultActivity.class);
        startActivity(intent);
    }
}