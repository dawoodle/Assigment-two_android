package com.example.yourpantry;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomePageActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton add_button, recipe_button;
    ImageView no_food_image;
    TextView no_food;

    MyDatabaseHelper myDB;
    ArrayList<String> food_id, food_title, food_type, food_date;
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        recyclerView = findViewById(R.id.recyclerView);
        add_button = findViewById(R.id.add_button);
        recipe_button = findViewById(R.id.recipe_button);
        no_food_image = findViewById(R.id.no_food_image);
        no_food = findViewById(R.id.no_food);
        add_button.setOnClickListener(v -> {
            Intent intent = new Intent(HomePageActivity.this,AddFoodActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

        });
        recipe_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomePageActivity.this,WebviewActivity.class);
                startActivity(intent);
            }
        });

        myDB = new MyDatabaseHelper(HomePageActivity.this);
        food_id = new ArrayList<>();
        food_title = new ArrayList<>();
        food_type = new ArrayList<>();
        food_date = new ArrayList<>();

        storeDataInArrays();

        customAdapter = new CustomAdapter(HomePageActivity.this, this , food_id, food_title,
                food_type, food_date);
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(HomePageActivity.this));


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    public void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            no_food_image.setVisibility(View.VISIBLE);
            no_food.setVisibility(View.VISIBLE);
        }else{
            while(cursor.moveToNext()){
                food_id.add(cursor.getString(0));
                food_title.add(cursor.getString(1));
                food_type.add(cursor.getString(2));
                food_date.add(cursor.getString(3));

            }
            no_food_image.setVisibility(View.GONE);
            no_food.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete_all){
            confirmDialog();

        }
        return super.onOptionsItemSelected(item);
    }

    public void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all the food?" );
        builder.setPositiveButton("Yes", (dialog, which) -> {
            MyDatabaseHelper myDB = new MyDatabaseHelper(HomePageActivity.this);
            myDB.deleteAllData();
            Intent intent = new Intent(HomePageActivity.this,HomePageActivity.class);
            startActivity(intent);
            finish();
        });
        builder.setNegativeButton("No", (dialog, which) -> {

        });
        builder.create().show();
    }
}