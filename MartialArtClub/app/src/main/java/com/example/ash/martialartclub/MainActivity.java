package com.example.ash.martialartclub;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.ash.martialartclub.Module.DatabaseHandler;
import com.example.ash.martialartclub.Module.MartialArt;

import java.text.NumberFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

        private DatabaseHandler databaseHandler;
        private double totalMartialArtPrice;
        private ScrollView scrollView;
        private int martialArtButtonWidth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        databaseHandler = new DatabaseHandler(MainActivity.this);
        totalMartialArtPrice = 0.0;
        scrollView = findViewById(R.id.scrollView);

        Point screenSize = new Point();
        getWindowManager().getDefaultDisplay().getSize(screenSize);
        martialArtButtonWidth = screenSize.x / 2;
        modifyUserInterface();





    }

    private void modifyUserInterface() {

        ArrayList<MartialArt> allMartialArtObject = databaseHandler.returnAllTheMartialArtObject();

        scrollView.removeAllViewsInLayout();

        if(allMartialArtObject.size() > 0){


            GridLayout gridLayout = new GridLayout(MainActivity.this);
            gridLayout.setRowCount((allMartialArtObject.size() + 1) / 2);
            gridLayout.setColumnCount(2);
            MatialArtButton[] matialArtButtons = new MatialArtButton[allMartialArtObject.size()];
            int index = 0;

            for(MartialArt martialArtObject : allMartialArtObject){

                matialArtButtons[index] = new MatialArtButton(MainActivity.this, martialArtObject);
                matialArtButtons[index].setText(martialArtObject.getMartialArtID()+ "\n" + martialArtObject.getMartialArtName() + "\n" + martialArtObject.getMartialArtPrice());
                switch (martialArtObject.getMartialArtColor()){

                    case "Red":
                        matialArtButtons[index].setBackgroundColor(Color.RED);
                        break;
                    case "Blue":
                        matialArtButtons[index].setBackgroundColor(Color.BLUE);
                        break;
                    case "Black":
                        matialArtButtons[index].setBackgroundColor(Color.BLACK);
                        matialArtButtons[index].setTextColor(Color.WHITE);
                        break;
                    case "Yellow":
                        matialArtButtons[index].setBackgroundColor(Color.YELLOW);
                        break;
                    case "Purple":
                        matialArtButtons[index].setBackgroundColor(Color.CYAN);
                        break;
                    case "Green":
                        matialArtButtons[index].setBackgroundColor(Color.GREEN);
                        break;
                    case "White":
                        matialArtButtons[index].setBackgroundColor(Color.WHITE);
                        break;
                        default:
                            matialArtButtons[index].setBackgroundColor(Color.GRAY);
                            break;

                }

                matialArtButtons[index].setOnClickListener(MainActivity.this);
                gridLayout.addView(matialArtButtons[index], martialArtButtonWidth, ViewGroup.LayoutParams.WRAP_CONTENT);

            }

            scrollView.addView(gridLayout);


        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        modifyUserInterface();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch(id){

            case R.id.add_martial_art:

                Intent addMartialArtIntent = new Intent(this, AddMartialArtActivity.class);
                startActivity(addMartialArtIntent);
                return true;

            case R.id.delete_martial_art:

                Intent deleteMartialArtIntent = new Intent(this, DeleteMartialArtActivity.class);
                startActivity(deleteMartialArtIntent);
                return  true;

            case R.id.update_martial_art:

                Intent updateMartialArtIntent = new Intent(this, UpdateMartialArtActivity.class);
                startActivity(updateMartialArtIntent);
                return true;
            case R.id.reset_martial_art:

                totalMartialArtPrice = 0.0;
                Toast.makeText(MainActivity.this, totalMartialArtPrice+ "Prices are Reset", Toast.LENGTH_SHORT).show();
                        return true;




        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

        MatialArtButton matialArtButton = (MatialArtButton) v;
        totalMartialArtPrice += matialArtButton.getMarialArtPrice();
        String martialArtPriceFormat = NumberFormat.getCurrencyInstance().format(totalMartialArtPrice);

        Toast.makeText(MainActivity.this,martialArtPriceFormat, Toast.LENGTH_SHORT).show();



    }
}
