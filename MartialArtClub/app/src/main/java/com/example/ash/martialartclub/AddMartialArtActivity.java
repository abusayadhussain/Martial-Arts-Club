package com.example.ash.martialartclub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ash.martialartclub.Module.DatabaseHandler;
import com.example.ash.martialartclub.Module.MartialArt;

public class AddMartialArtActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtName, edtPrice, edtColor;
    Button btnAddMartialArt, btnGoBack;

    DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_martial_art);

        edtName = findViewById(R.id.edtName);
        edtPrice = findViewById(R.id.edtPrice);
        edtColor = findViewById(R.id.edtColor);

        btnAddMartialArt = findViewById(R.id.btnAddMartialArt);
        btnGoBack = findViewById(R.id.btnGoBack);

        databaseHandler = new DatabaseHandler(AddMartialArtActivity.this);

        btnAddMartialArt.setOnClickListener(this);
        btnGoBack.setOnClickListener(this);

    }

    private void addMartialArtObjectToDatabase(){

        String nameValue = edtName.getText().toString();
        String priceValue = edtPrice.getText().toString();
        String colorValue = edtColor.getText().toString();

        try{

            double priceDoubleValue = Double.parseDouble(priceValue);
            MartialArt martialArtObject = new MartialArt(0, nameValue, priceDoubleValue, colorValue);

            databaseHandler.addMartialArt(martialArtObject);

            Toast.makeText(this, martialArtObject.toString() + "Added to the database.", Toast.LENGTH_SHORT).show();

        } catch(Exception e){

            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btnAddMartialArt:
                addMartialArtObjectToDatabase();
                break;

            case R.id.btnGoBack:
                this.finish();
                break;
        }
    }
}
