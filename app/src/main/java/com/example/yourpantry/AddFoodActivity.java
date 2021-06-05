package com.example.yourpantry;

import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.Calendar;

import static com.example.yourpantry.Notification.CHANNEL_1_ID;
import static java.util.Calendar.DAY_OF_MONTH;
import static java.util.Calendar.MONTH;
import static java.util.Calendar.YEAR;

public class AddFoodActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private NotificationManagerCompat notificationManager;
    private EditText foodName_input, foodType_input, foodExpiry_input;
    Button add_button, notify_button;
    ImageButton calender_button;
    public static FoodDetails foodDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_food);

        notificationManager = NotificationManagerCompat.from(this);
        foodName_input = findViewById(R.id.editText_foodName);
        foodType_input = findViewById(R.id.editText_foodType);
        foodExpiry_input = findViewById(R.id.editText_foodExpiry);
        notify_button = findViewById(R.id.btn_notify);
        calender_button = findViewById(R.id.btn_calender);
        add_button = findViewById(R.id.btn_add);
        add_button.setOnClickListener(v -> {

            String FoodName = foodName_input.getText().toString();
            String FoodType = foodType_input.getText().toString();
            String FoodExpiry = foodExpiry_input.getText().toString();
            MyDatabaseHelper myDB = new MyDatabaseHelper(AddFoodActivity.this);
            if (validate(FoodName, FoodType, FoodExpiry)) {
                foodDetails = new FoodDetails(FoodName, FoodType, FoodExpiry);
                startActivity(new Intent(AddFoodActivity.this,HomePageActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                myDB.addFood(foodName_input.getText().toString().trim(),
                        foodType_input.getText().toString().trim(),
                        foodExpiry_input.getText().toString().trim());
            }


        });
        calender_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddFoodActivity.this.showDatePickerDialog();
            }
        });

    }

    public void showDatePickerDialog() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, this, Calendar.getInstance().get(YEAR), Calendar.getInstance().get(MONTH), Calendar.getInstance().get(DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        int newMonth = month + 1;
        String date = dayOfMonth + "/" + newMonth + "/" + year;
        foodExpiry_input.setText(date);
    }

    private boolean validate(String FoodName, String FoodType, String FoodExpiry) {
        if (FoodName.isEmpty() || FoodType.isEmpty() || FoodExpiry.isEmpty()) {
            Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void sendOnChannel1(View v){
        String title = foodName_input.getText().toString();
        String message = foodExpiry_input.getText().toString();

        Intent activityIntent = new Intent(this, HomePageActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this,0,activityIntent,0);

        Intent broadcastIntent = new Intent(this,NotificationReceiver.class);
        broadcastIntent.putExtra("toastMessage", message);
        PendingIntent actionIntent = PendingIntent.getBroadcast(this,0,broadcastIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        android.app.Notification notification = new NotificationCompat.Builder(this, CHANNEL_1_ID)
                .setSmallIcon(R.drawable.ic_baseline_notifications_24)
                .setContentTitle(title)
                .setContentText(message)
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setColor(Color.RED)
                .setContentIntent(contentIntent)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true)
                .addAction(R.mipmap.ic_launcher,"Check Expiry Date",actionIntent)
                .build();

        notificationManager.notify(1,notification);

    }
}