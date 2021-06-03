package com.example.yourpantry;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class UpdateDeleteActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    EditText foodName_input, foodType_input, foodExpiry_input;
    Button update_button, delete_button;
    String id, title, type, date;
    ImageButton calender_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        foodName_input = findViewById(R.id.editText_foodName2);
        foodType_input = findViewById(R.id.editText_foodType2);
        foodExpiry_input = findViewById(R.id.editText_foodExpiry2);
        update_button = findViewById(R.id.btn_update);
        delete_button = findViewById(R.id.btn_delete);
        calender_button = findViewById(R.id.btn_calender);

        getAndSetIntentData();

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(title);
        }

        update_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateDeleteActivity.this);
                title = foodName_input.getText().toString().trim();
                type = foodType_input.getText().toString().trim();
                date = foodExpiry_input.getText().toString().trim();
                myDB.updateData(id, title, type, date);
            }

        });
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialog();
            }
        });

        calender_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
    }

    void getAndSetIntentData() {
        if (getIntent().hasExtra("id") && getIntent().hasExtra("title") &&
                getIntent().hasExtra("type") && getIntent().hasExtra("date")) {

            id = getIntent().getStringExtra("id");
            title = getIntent().getStringExtra("title");
            type = getIntent().getStringExtra("type");
            date = getIntent().getStringExtra("date");

            foodName_input.setText(title);
            foodType_input.setText(type);
            foodExpiry_input.setText(date);

        } else {
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }
    }

   public void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + title + " ?");
        builder.setMessage("Are you sure you want to delete " + title + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateDeleteActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.create().show();
    }

   public void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this, Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        int newMonth = month +1;
        String date = dayOfMonth + "/" + newMonth + "/" + year;
        foodExpiry_input.setText(date);
    }
}