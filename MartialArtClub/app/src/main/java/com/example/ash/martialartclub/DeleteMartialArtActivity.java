package com.example.ash.martialartclub;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.example.ash.martialartclub.Module.DatabaseHandler;
import com.example.ash.martialartclub.Module.MartialArt;

import java.util.ArrayList;

public class DeleteMartialArtActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {

    DatabaseHandler databaseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_martial_art);

        databaseHandler = new DatabaseHandler(DeleteMartialArtActivity.this);
        
        updateTheUserInterface();
    }

    private void updateTheUserInterface() {

        ArrayList<MartialArt> allMartialArtObject = databaseHandler.returnAllTheMartialArtObject();

        RelativeLayout relativeLayout = new RelativeLayout(DeleteMartialArtActivity.this);
        ScrollView scrollView = new ScrollView(DeleteMartialArtActivity.this);
        RadioGroup radioGroup = new RadioGroup(DeleteMartialArtActivity.this);

        for(MartialArt martialArt : allMartialArtObject){

            RadioButton currentRadioButton = new RadioButton(DeleteMartialArtActivity.this);
            currentRadioButton.setId(martialArt.getMartialArtID());
            currentRadioButton.setText(martialArt.toString());
            radioGroup.addView(currentRadioButton);
        }

        radioGroup.setOnCheckedChangeListener(DeleteMartialArtActivity.this);

        Button btnBack = new Button(DeleteMartialArtActivity.this);
        btnBack.setText("Go Back");
        btnBack.setOnClickListener(DeleteMartialArtActivity.this);

        scrollView.addView(radioGroup);
        RelativeLayout.LayoutParams buttonParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        buttonParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        buttonParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        buttonParams.setMargins(0, 0, 0, 70);
        relativeLayout.addView(btnBack, buttonParams);

        ScrollView.LayoutParams scrollviewParams = new ScrollView.LayoutParams(ScrollView.LayoutParams.MATCH_PARENT, ScrollView.LayoutParams.MATCH_PARENT);
        relativeLayout.addView(scrollView,scrollviewParams);

        setContentView(relativeLayout);


    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        databaseHandler.deleteMartialArtObjectFromDatabaseById(checkedId);
        Toast.makeText(DeleteMartialArtActivity.this, "The martial art object is Deleted", Toast.LENGTH_SHORT).show();
        updateTheUserInterface();
    }

    @Override
    public void onClick(View v) {

        finish();
    }
}
