package ke.co.ablabs.onsight;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class MainActivity extends AppCompatActivity {

    Button buttonSaveData;
    TextView textViewNoteData;
    SharedPreferences onSightSharedPreferences;
    SharedPreferences.Editor onSightSharedPreferencesEditor;
    AdView myAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        onSightSharedPreferences = getSharedPreferences("ke.co.ablabs.onsight", MODE_PRIVATE);
        onSightSharedPreferencesEditor = onSightSharedPreferences.edit();

        buttonSaveData = findViewById(R.id.materialButtonSaveData);
        textViewNoteData = findViewById(R.id.editTextNoteData);
        myAdView = findViewById(R.id.myAdView);

        buttonSaveData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                onSightSharedPreferencesEditor.putString("the_note_data", textViewNoteData.getText().toString());
                onSightSharedPreferencesEditor.apply();
                updateTheWidgetTextView();
                Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_SHORT).show();
            }
        });

        // Initialize Ads
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {

            }
        });

        AdRequest adRequest = new AdRequest.Builder().build();
        myAdView.loadAd(adRequest);
    }

    private void updateTheWidgetTextView() {
        Intent updateWidgetIntent = new Intent(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getApplicationContext());
        int[] ids = appWidgetManager.getAppWidgetIds(new ComponentName(getApplicationContext(), TheAppWidgetProvider.class));
        AppWidgetManager.getInstance(getApplicationContext()).notifyAppWidgetViewDataChanged(ids, R.id.theWidgetTextView);
        updateWidgetIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, ids);
        sendBroadcast(updateWidgetIntent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.my_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.menuItemHowToUse){
            Intent openTutorialIntent = new Intent(this, TutorialActivity.class);
            startActivity(openTutorialIntent);
        }
        return super.onOptionsItemSelected(item);
    }
}