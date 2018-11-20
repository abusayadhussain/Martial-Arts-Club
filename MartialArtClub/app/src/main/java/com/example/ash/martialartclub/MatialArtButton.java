package com.example.ash.martialartclub;

import android.content.Context;
import android.support.v7.widget.AppCompatButton;

import com.example.ash.martialartclub.Module.MartialArt;

public class MatialArtButton extends AppCompatButton {

    private MartialArt martialArtObject;
    public MatialArtButton(Context context, MartialArt martialArt){

        super(context);
        martialArtObject = martialArt;

    }

    public String getMartialArtColor(){
        return martialArtObject.getMartialArtColor();

    }

    public Double getMarialArtPrice(){

        return  martialArtObject.getMartialArtPrice();
    }

}
