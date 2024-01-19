package ca.dal.cs.csci3130.a2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.FirebaseDatabase;

public class WelcomeActivity extends AppCompatActivity {

    FirebaseDatabase database = null;
    FirebaseCRUD crud;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        this.showWelcomeMessage();
        this.initializeDatabaseAccess();
        this.setupRetrieveButton();
    }

    protected void showWelcomeMessage() {
        //incomplete method, add your implementation
    }

    protected void initializeDatabaseAccess() {
        database = FirebaseDatabase.getInstance(getResources().getString(R.string.FIREBASE_DB_URL));
        this.crud = new FirebaseCRUD(database);
    }

    protected void setupRetrieveButton() {
        Button retrieveButton = findViewById(R.id.retrieveFromDBButton);
        retrieveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //incomplete method, add your implementation
            }
        });
    }

    protected void showRetrievedItems(View retrieveButton, String message) {
        //incomplete method, add your implementation
    }
}