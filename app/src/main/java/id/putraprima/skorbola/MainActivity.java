package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getCanonicalName();
    public static final int GALERY_REQUEST_CODE_HOME= 1;
    public static final int GALERY_REQUEST_CODE_AWAY= 2;

    public static final String HOMETEAM_KEY = "home";
    public static final String AWAYTEAM_KEY = "away";
    public static final String IMAGEHOME_KEY = "imagehome";
    public static final String IMAGEAWAY_KEY = "imageaway";


    private EditText homeTeam;
    private EditText awayTeam;
    private ImageView imageHome;
    private ImageView imageAway;
    private Bitmap imagehome;
    private Bitmap imageaway;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeTeam = findViewById(R.id.home_team);
        awayTeam = findViewById(R.id.away_team);
        imageHome = findViewById(R.id.home_logo);
        imageAway = findViewById(R.id.away_logo);
        //TODO
        //Fitur Main Activity
        //1. Validasi Input Home Team
        //2. Validasi Input Away Team
        //3. Ganti Logo Home Team
        //4. Ganti Logo Away Team
        //5. Next Button Pindah Ke MatchActivity
    }

    public void handlenext(View view) {
        String home = homeTeam.getText().toString();
        String away = awayTeam.getText().toString();


        if ((home).equals("") || (away).equals("")){
            Toast.makeText(this, "Data tidak boleh kosong", Toast.LENGTH_SHORT).show();
        }else {
            imageHome.setDrawingCacheEnabled(true);
            imagehome = imageHome.getDrawingCache();
            imageAway.setDrawingCacheEnabled(true);
            imageaway = imageAway.getDrawingCache();

            Intent intent = new Intent(this, MatchActivity.class);

            intent.putExtra(HOMETEAM_KEY, home);
            intent.putExtra(AWAYTEAM_KEY, away);
            intent.putExtra(IMAGEHOME_KEY, imagehome);
            intent.putExtra(IMAGEAWAY_KEY, imageaway);

            startActivity(intent);
        }
    }

    public void handleChangeAvatar(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    public void handleChangeAvatar2(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 0) {
            return;
        }
        if (requestCode == GALERY_REQUEST_CODE_HOME) {
            if (data != null) {
                try {
                    Uri imageUri = data.getData();
                    Bitmap bmp = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    imageHome.setImageBitmap(bmp);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
        if (requestCode == GALERY_REQUEST_CODE_AWAY) {
            if (data != null) {
                try {
                    Uri imageUri = data.getData();
                    Bitmap bmp = MediaStore.Images.Media.getBitmap(this.getContentResolver(), imageUri);
                    imageAway.setImageBitmap(bmp);
                } catch (IOException e) {
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }


}
