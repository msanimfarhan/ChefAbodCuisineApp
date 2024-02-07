package ca.dal.cs.csci3130.a2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static String WELCOME_MESSAGE = "ca.dal.csci3130.a2.welcome";
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://a2-ec2f3-default-rtdb.firebaseio.com/");
    FirebaseCRUD crud = null;
    private DatabaseReference firebaseDBRef;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirebaseApp.initializeApp(this);
        this.loadRoleSpinner();
        this.setupRegistrationButton();
        this.initializeDatabaseAccess();


    }

    protected void loadRoleSpinner() {
        Spinner roleSpinner = findViewById(R.id.roleSpinner);
        List<String> roles = new ArrayList<>();
        roles.add("Select your role");
        roles.add("Buyer");
        roles.add("Seller");
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, roles);
        spinnerAdapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        roleSpinner.setAdapter(spinnerAdapter);
    }

    protected void setupRegistrationButton() {
        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(this);
    }

    private void connectFirebase(){
        firebaseDBRef = database.getReference("message");
    }

    protected void initializeDatabaseAccess() {
        database = FirebaseDatabase.getInstance(getResources().getString(R.string.FIREBASE_DB_URL));
        crud = new FirebaseCRUD(database);
    }

    protected String getNetID() {
        EditText netIDBox = findViewById(R.id.netIDBox);
        return netIDBox.getText().toString().trim();
    }

    protected String getEmailAddress() {
        EditText emailBox = findViewById(R.id.emailBox);
        return emailBox.getText().toString().trim();
    }

    protected String getRole() {
        Spinner roleSpinner = findViewById(R.id.roleSpinner);
        return roleSpinner.getSelectedItem().toString().trim();
    }


    protected void setStatusMessage(String message) {
        TextView statusLabel = findViewById(R.id.statusLabel);
        statusLabel.setText(message.trim());
    }

    protected void move2WelcomeWindow(String netID, String emailAddress, String role) {

        //Incomplete method, add your implementation
            Intent intent = new Intent(MainActivity.this, WelcomeActivity.class);
            intent.putExtra("netId",netID);
            intent.putExtra("emailAddress",emailAddress);
            intent.putExtra("role",role);

            startActivity(intent);

    }


    protected void saveInfoToFirebase(String netID, String emailAddress, String role) {
        if (crud != null) {

            crud.setNetID(netID);
            crud.setEmail(emailAddress);
            crud.setRole(role);
        } else {

            Toast.makeText(this, "Database connection not initialized", Toast.LENGTH_SHORT).show();
        }
    }





    @Override
    public void onClick(View view) {
        //Incomplete, buggy method, fix it!

        String netID = getNetID();
        String emailAddress = getEmailAddress();
        String role = getRole();
        String errorMessage;
        CredentialValidator validator = new CredentialValidator();
        if (validator.isEmptyNetID(netID)) {
            errorMessage = getResources().getString(R.string.EMPTY_NET_ID).trim();           //getResources().getString(R.string.EMPTY_NET_ID).trim();
        }else if (!validator.isValidNetID(netID)) {
            errorMessage = getResources().getString(R.string.EMPTY_STRING).trim();

        } else if(!validator.isValidEmailAddress(emailAddress)){
            errorMessage = getResources().getString(R.string.INVALID_EMAIL_ADDRESS).trim();
        } else if (!validator.isDALEmailAddress(emailAddress)) {
            errorMessage = getResources().getString(R.string.INVALID_DAL_EMAIL).trim();
        }else if(!validator.isValidRole(role)){
            errorMessage = getResources().getString(R.string.INVALID_ROLE).trim();

        }else if(validator.isInvalidEmail(emailAddress)) {
            errorMessage= getResources().getString(R.string.INVALID_EMAIL_ADDRESS).trim();
        }else{
            errorMessage= getResources().getString(R.string.EMPTY_STRING).trim();
        }
        setStatusMessage(errorMessage);
        this.move2WelcomeWindow(netID,emailAddress,role);
        this.saveInfoToFirebase( netID, emailAddress, role);



    }

}