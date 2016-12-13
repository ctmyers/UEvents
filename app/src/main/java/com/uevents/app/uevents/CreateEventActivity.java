package com.uevents.app.uevents;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.datetimepicker.date.DatePickerDialog;
import com.android.datetimepicker.time.RadialPickerLayout;
import com.android.datetimepicker.time.TimePickerDialog;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


/**
 * Created by Andrea on 12/5/2016.
 * https://github.com/CiTuX/datetimepicker
 */

public class CreateEventActivity extends AppCompatActivity implements
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {
    private static final String TIME_PATTERN = "HH:mm";

    private Dialog dateTimePicker;
    private TextView dateTextView;
    private TextView timeTextView;
    private Calendar calendar;
    private DateFormat dateFormat;
    private SimpleDateFormat timeFormat;
    private EditText eventNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_event_form);

        calendar = Calendar.getInstance();
        dateFormat = DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault());
        timeFormat = new SimpleDateFormat(TIME_PATTERN, Locale.getDefault());

        dateTextView = (TextView) findViewById(R.id.dateTextView);
        timeTextView = (TextView) findViewById(R.id.timeTextView);
        dateTextView.setText(dateTextView.getText());
        timeTextView.setText(timeTextView.getText());

        /*dateTimePicker = new Dialog(this);
        View dialogLayout = getLayoutInflater().inflate(R.layout.date_time_picker_dialog,
                (ViewGroup)findViewById(R.id.dateTime_dialog));
        dateTimePicker.setContentView(dialogLayout);*/

        eventNameEditText = (EditText) findViewById(R.id.eventTitle);


        Spinner spinner = (Spinner) findViewById(R.id.categorySpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.categories_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);


    }

    private void update() {
        dateTextView.setText(dateTextView.getText() + dateFormat.format(calendar.getTime()));
        timeTextView.setText(timeTextView.getText() + timeFormat.format(calendar.getTime()));
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.datePickerButton:
                DatePickerDialog.newInstance(this, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show(getFragmentManager(), "datePicker");
                break;
            case R.id.timePickerButton:
                TimePickerDialog.newInstance(this, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show(getFragmentManager(), "timePicker");
                break;
        }
    }

    @Override
    public void onDateSet(DatePickerDialog dialog, int year, int monthOfYear, int dayOfMonth) {
        calendar.set(year, monthOfYear, dayOfMonth);
        //update();
        dateTextView.setText(dateTextView.getText() + dateFormat.format(calendar.getTime()));
    }

    @Override
    public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
        calendar.set(Calendar.MINUTE, minute);
        //update();
        timeTextView.setText(timeTextView.getText() + timeFormat.format(calendar.getTime()));
    }
    public void onCancelClick(View v){
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED,returnIntent);
        finish();
    }

    public void onSaveButtonClick(View v) {
        EditText eventDes = (EditText)findViewById(R.id.eventDescription);
        EditText maxAttend = (EditText)findViewById(R.id.maxAttendees);
        Spinner spinner = (Spinner) findViewById(R.id.categorySpinner);
        EditText eventLocation = (EditText)findViewById(R.id.addEventLocation);
        if(eventNameEditText.getText().toString().equals("") ||eventDes.getText().toString().equals("") ||
                maxAttend.getText().toString().equals("") || dateTextView.getText().equals("") || timeTextView.getText().equals("")
                || spinner.getSelectedItem().equals("- Select One -") || eventLocation.getText().equals("")){
            Toast.makeText(this,"Please fill out all fields",Toast.LENGTH_LONG).show();
            return;
        }
        Intent returnIntent = new Intent();
        returnIntent.putExtra("Title",eventNameEditText.getText().toString());

        returnIntent.putExtra("Description",eventDes.getText().toString());
        returnIntent.putExtra("Attendees",maxAttend.getText().toString());
        returnIntent.putExtra("lat",38.990786f);
        returnIntent.putExtra("long",-76.9388106f);
        returnIntent.putExtra("start",calendar);
        Calendar end = Calendar.getInstance();
        end.set(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH),
                calendar.get(Calendar.HOUR)+1,calendar.get(Calendar.MINUTE),calendar.get(Calendar.SECOND));

        returnIntent.putExtra("end",end);

        returnIntent.putExtra("Category",spinner.getSelectedItem().toString());
        setResult(Activity.RESULT_OK,returnIntent);
        Toast.makeText(this,"Your event was created!",Toast.LENGTH_LONG).show();
        finish();

    }

    /*eventNameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener(){
        public void onFocusChange(View v, boolean hasFocus){
            if(hasFocus)
                eventNameEditText.setHint("");
            else
                eventNameEditText.setHint("Your hint");
        }
    });*/
}
