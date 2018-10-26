package com.ahmedderbala.espoir.Cases;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ahmedderbala.espoir.R;
import com.ahmedderbala.espoir.app.AppConfig;
import com.ahmedderbala.espoir.app.AppController;
import com.ahmedderbala.espoir.helper.SQLiteHandler;
import com.ahmedderbala.espoir.helper.SessionManager;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import ernestoyaquello.com.verticalstepperform.VerticalStepperFormLayout;
import ernestoyaquello.com.verticalstepperform.interfaces.VerticalStepperForm;

public class AddCaseActivity extends AppCompatActivity implements VerticalStepperForm {

    public static final int NEW_ALARM = 1;
    private final String TAG = "AddCaseActivity";
    private static final String DATA_RECEIVED = "data_received";
    private static final String INFORMATION = "information";
    private static final String DISCLAIMER = "disclaimer";
    private FloatingActionButton fab;
    private TextView information, disclaimer;
    private EditText title,shortDescription,longDescription,thumbnail,governorate,city,place,latitude,longitude;
    private boolean dataReceived = false;
    private VerticalStepperFormLayout verticalStepperForm;
    private ProgressDialog progressDialog;
    private SessionManager session;
    private SQLiteHandler db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_case);

        /*fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewAlarmFormActivity.class);
                startActivityForResult(intent, NEW_ALARM);
            }
        });

        information = (TextView) findViewById(R.id.information);
        disclaimer = (TextView) findViewById(R.id.disclaimer);*/

        String[] mySteps = {"Title", "Short description" ,"Long description","Thumbnail","Governorate","City","Place","Latitude","Longitude"};
        int colorPrimary = ContextCompat.getColor(getApplicationContext(), R.color.red);
        int colorPrimaryDark = ContextCompat.getColor(getApplicationContext(), R.color.red);

        // Finding the view
        verticalStepperForm = (VerticalStepperFormLayout) findViewById(R.id.vertical_stepper_form);

        // Setting up and initializing the form
        VerticalStepperFormLayout.Builder.newInstance(verticalStepperForm, mySteps, this, this)
                .primaryColor(colorPrimary)
                .primaryDarkColor(colorPrimaryDark)
                .displayBottomNavigation(true) // It is true by default, so in this case this line is not necessary
                .init();
    }

   /* @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        dataReceived = savedInstanceState.getBoolean(DATA_RECEIVED, false);
        if (dataReceived) {
            disclaimer.setVisibility(View.VISIBLE);
            information.setText(savedInstanceState.getString(INFORMATION));
            disclaimer.setText(savedInstanceState.getString(DISCLAIMER));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putBoolean(DATA_RECEIVED, dataReceived);
        if (dataReceived) {
            savedInstanceState.putString(INFORMATION, information.getText().toString());
            savedInstanceState.putString(DISCLAIMER, disclaimer.getText().toString());
        }
    }*/

   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == NEW_ALARM && data != null) {
            if (data.hasExtra(NewAlarmFormActivity.NEW_ALARM_ADDED)
                    && data.getExtras().getBoolean(NewAlarmFormActivity.NEW_ALARM_ADDED, false)) {

                // Handling the data received from the stepper form
                dataReceived = true;
                String title = data.getExtras().getString(NewAlarmFormActivity.STATE_TITLE);
                //String description = data.getExtras().getString(NewAlarmFormActivity.STATE_DESCRIPTION);
                int hour = data.getExtras().getInt(NewAlarmFormActivity.STATE_TIME_HOUR);
                int minutes = data.getExtras().getInt(NewAlarmFormActivity.STATE_TIME_MINUTES);
                String time = ((hour > 9) ? hour : ("0" + hour))
                        + ":" + ((minutes > 9) ? minutes : ("0" + minutes));
                //boolean[] weekDays = data.getExtras().getBooleanArray(NewAlarmFormActivity.STATE_WEEK_DAYS);
                //  information.setText("Alarm \"" + title + "\" set up at " + time);
//                disclaimer.setVisibility(View.VISIBLE);
                Snackbar.make(fab, getString(R.string.new_alarm_added), Snackbar.LENGTH_LONG).show();
            }
        }
    }*/

    public void addCase(final String title, final String shortDescription, final String longDescription, final String thumbnail, final String author, final String governorate, final String city, final String place) {
// Tag used to cancel the request
        String tag_string_req = "req_register";


        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_ADD_CASE, new Response.Listener<String>() {


            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jObj = new JSONObject(response);
                    boolean error = jObj.getBoolean("error");
                    if (!error) {
                        Log.d(TAG, "onResponse: ENTERING ERROR");
                        Toast.makeText(getApplicationContext(), jObj.getString("error_msg"), Toast.LENGTH_LONG).show();

                    } else {

                        // Error occurred in registration. Get the error
                        // message
                        String errorMsg = jObj.getString("error_msg");
                        Toast.makeText(getApplicationContext(),errorMsg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(TAG, "ADDCASE_ERROR: " + error.getMessage());
                // Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                Toast.makeText(getApplicationContext(), "you should log in first", Toast.LENGTH_LONG).show();

            }
        }) {

            @Override
            protected Map<String, String> getParams() {
                // Posting params to register url
                Map<String, String> params = new HashMap<String, String>();
                params.put("username", "ahmed");
                params.put("title", title);
                params.put("shortDescription", shortDescription);
                params.put("longDescription", longDescription);
                params.put("thumbnail", thumbnail);
                params.put("author", author);
                params.put("governorate", governorate);
                params.put("city", city);
                params.put("place", place);

                return params;
            }

        };

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(strReq, tag_string_req);
    }

    @Override
    public View createStepContentView(int stepNumber) {
        View view = null;
        switch (stepNumber) {
            case 0:
                view = createTitleStep();
                break;
            case 1:
                view = createShortDescriptionStep();
                break;
            case 2:
                view = createLongDescriptionStep();
                break;
            case 3:
                view = createThumbnailStep();
                break;
            case 4:
                view = createGovernorateStep();
                break;
            case 5:
                view = createCityStep();
                break;
            case 6:
                view = createPlaceStep();
                break;
            case 7:
                view = createGovernorateStep();
                break;
            case 8:
                view = createLatitudeStep();
                break;
            case 9:
                view = createLongitudeStep();
                break;
        }
        return view;
    }

    private View createTitleStep() {
        // Here we generate programmatically the view that will be added by the system to the step content layout
        title = new EditText(this);
        title.setSingleLine(true);
        title.setHint("Title");
        return title;
    }

    /*private View createEmailStep() {
        // In this case we generate the view by inflating a XML file
        LayoutInflater inflater = LayoutInflater.from(getBaseContext());
        LinearLayout emailLayoutContent = (LinearLayout) inflater.inflate(R.layout.email_step_layout, null, false);
        email = (EditText) emailLayoutContent.findViewById(R.id.shortDescriptionStep);

        return emailLayoutContent;
    }*/

    private View createShortDescriptionStep() {
        // Here we generate programmatically the view that will be added by the system to the step content layout
        shortDescription = new EditText(this);
        shortDescription.setSingleLine(true);
        shortDescription.setHint("Short description");
        return shortDescription;
    }

    private View createLongDescriptionStep() {
        // Here we generate programmatically the view that will be added by the system to the step content layout
        longDescription = new EditText(this);
        longDescription.setSingleLine(true);
        longDescription.setHint("Long description");
        return longDescription;
    }

    private View createThumbnailStep() {
        // Here we generate programmatically the view that will be added by the system to the step content layout
        thumbnail = new EditText(this);
        thumbnail.setSingleLine(true);
        thumbnail.setHint("Thumbnail");
        return thumbnail;
    }

    private View createGovernorateStep() {
        // Here we generate programmatically the view that will be added by the system to the step content layout
        governorate = new EditText(this);
        governorate.setSingleLine(true);
        governorate.setHint("Governorate");
        return governorate;
    }

    private View createCityStep() {
        // Here we generate programmatically the view that will be added by the system to the step content layout
        city = new EditText(this);
        city.setSingleLine(true);
        city.setHint("City");
        return city;
    }

    private View createPlaceStep() {
        // Here we generate programmatically the view that will be added by the system to the step content layout
        place = new EditText(this);
        place.setSingleLine(true);
        place.setHint("Place");
        return place;
    }

    private View createLatitudeStep() {
        // Here we generate programmatically the view that will be added by the system to the step content layout
        latitude = new EditText(this);
        latitude.setSingleLine(true);
        latitude.setHint("Latitude");
        return latitude;
    }

    private View createLongitudeStep() {
        // Here we generate programmatically the view that will be added by the system to the step content layout
        longitude = new EditText(this);
        longitude.setSingleLine(true);
        longitude.setHint("Longitude");
        return longitude;
    }

    /*private View createPhoneNumberStep() {
        LayoutInflater inflater = LayoutInflater.from(getBaseContext());
        LinearLayout phoneLayoutContent = (LinearLayout) inflater.inflate(R.layout.phone_step_layout, null, false);

        return phoneLayoutContent;
    }*/


    @Override
    public void onStepOpening(int stepNumber) {
        switch (stepNumber) {
            case 0:
                //checkName();
                verticalStepperForm.setStepAsCompleted(0);

                break;
            case 1:
                //checkEmail();
                verticalStepperForm.setStepAsCompleted(1);

                break;
            case 2:
                // As soon as the phone number step is open, we mark it as completed in order to show the "Continue"
                // button (We do it because this field is optional, so the user can skip it without giving any info)
                verticalStepperForm.setStepAsCompleted(2);
                // In this case, the instruction above is equivalent to:
                // verticalStepperForm.setActiveStepAsCompleted();
                break;
            case 3:
                // As soon as the phone number step is open, we mark it as completed in order to show the "Continue"
                // button (We do it because this field is optional, so the user can skip it without giving any info)
                verticalStepperForm.setStepAsCompleted(3);
                // In this case, the instruction above is equivalent to:
                // verticalStepperForm.setActiveStepAsCompleted();
                break;
            case 4:
                // As soon as the phone number step is open, we mark it as completed in order to show the "Continue"
                // button (We do it because this field is optional, so the user can skip it without giving any info)
                verticalStepperForm.setStepAsCompleted(4);
                // In this case, the instruction above is equivalent to:
                // verticalStepperForm.setActiveStepAsCompleted();
                break;
            case 5:
                // As soon as the phone number step is open, we mark it as completed in order to show the "Continue"
                // button (We do it because this field is optional, so the user can skip it without giving any info)
                verticalStepperForm.setStepAsCompleted(5);
                // In this case, the instruction above is equivalent to:
                // verticalStepperForm.setActiveStepAsCompleted();
                break;
            case 6:
                // As soon as the phone number step is open, we mark it as completed in order to show the "Continue"
                // button (We do it because this field is optional, so the user can skip it without giving any info)
                verticalStepperForm.setStepAsCompleted(6);
                // In this case, the instruction above is equivalent to:
                 //verticalStepperForm.setActiveStepAsCompleted();
                break;
            case 7:
                //checkEmail();
                verticalStepperForm.setStepAsCompleted(7);

                break;
            case 8:
                //checkEmail();
                verticalStepperForm.setStepAsCompleted(8);

                break;
            case 9:
                //checkEmail();
                verticalStepperForm.setActiveStepAsCompleted();


                break;
        }
    }

    private void checkName() {
        if(title.length() >= 7 && title.length() <= 40) {
            verticalStepperForm.setActiveStepAsCompleted();
        } else {
            // This error message is optional (use null if you don't want to display an error message)
            String errorMessage = "The name must have between 3 and 40 characters";
            verticalStepperForm.setActiveStepAsUncompleted(errorMessage);
        }
    }

    private void checkEmail() {

    }



    @Override
    public void sendData() {
       /* progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(true);
        progressDialog.show();
        progressDialog.setMessage(getString(R.string.vertical_form_stepper_form_sending_data_message));
        //executeDataSending();*/
// SQLite database handler
        db = new SQLiteHandler(getApplicationContext());

        // Session manager
        session = new SessionManager(getApplicationContext());
        HashMap<String, String> user = db.getUserDetails();
       // Log.e(TAG, a.toString() );
        //Log.e(TAG, a.get("username"));
        addCase(title.getText().toString(),shortDescription.getText().toString(),longDescription.getText().toString(),thumbnail.getText().toString(),user.get("username"),governorate.getText().toString(),city.getText().toString(),place.getText().toString());
    }
}
