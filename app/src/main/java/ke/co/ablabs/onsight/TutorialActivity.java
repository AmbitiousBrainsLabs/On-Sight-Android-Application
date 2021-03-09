package ke.co.ablabs.onsight;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class TutorialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        String theInstructionsText = "" +
                "Here is How To use the App\n" +
                "Add a note and save it\n" +
                "Go to your home screen and long press\n" +
                "Select widgets and scroll to find the OnSight widget\n" +
                "Drag it to the screen and resize it to the size you want\n\n" +
                "On Sight On Mind";

        TextView textViewInstructions = findViewById(R.id.instructionsTextView);
        textViewInstructions.setText(theInstructionsText);
    }
}