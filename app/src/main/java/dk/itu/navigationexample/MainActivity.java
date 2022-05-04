package dk.itu.navigationexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    // Model: Database of items
    private final static ItemsViewModel itemsDB= new ItemsViewModel();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Context is needed to set up SQLite
        itemsDB.initialize(this);
    }
}