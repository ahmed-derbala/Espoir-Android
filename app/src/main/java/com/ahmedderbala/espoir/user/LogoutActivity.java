package com.ahmedderbala.espoir.user;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;

import com.ahmedderbala.espoir.MainActivity;
import com.ahmedderbala.espoir.R;
import com.ahmedderbala.espoir.helper.SQLiteHandler;
import com.ahmedderbala.espoir.helper.SessionManager;

public class LogoutActivity extends Activity {

    private TextView txtName;
    private TextView txtEmail;
    private Button btnLogout,btnMainActivity;

    private SQLiteHandler db;
    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        txtName = findViewById(R.id.name);
        txtEmail = findViewById(R.id.email);
        btnLogout = findViewById(R.id.btnLogout);
        btnMainActivity = findViewById(R.id.btnMainActivity);


        // SqLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // session manager
        session = new SessionManager(getApplicationContext());

        if (!session.isLoggedIn()) {
            logoutUser();
        }

        // Fetching user details from SQLite
        HashMap<String, String> user = db.getUserDetails();

        String name = user.get("name");
        String email = user.get("email");

        // Displaying the user details on the screen
        txtName.setText(name);
        txtEmail.setText(email);

        // Logout button click event
        btnLogout.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                logoutUser();
            }
        });
        // Home button click event
        btnMainActivity.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Launching the main activity
                Intent intent = new Intent(LogoutActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    /**
     * Logging out the user. Will set isLoggedIn flag to false in shared
     * preferences Clears the user data from sqlite users table
     * */
    private void logoutUser() {
        session.setLogin(false);

        db.deleteUsers();

        // Launching the login activity
        Intent intent = new Intent(LogoutActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }
}
