package com.example.komputer.crudmvvm.view;

import android.arch.lifecycle.ViewModelProviders;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.komputer.crudmvvm.viewmodel.*;
import com.example.komputer.crudmvvm.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.Iterator;

public class DeleteActivity extends AppCompatActivity {

    TextView editName, editRelease, editGenre, editPlatform, editPrice;
    ViewModelGame mViewModel;
    Bundle bundle;
    JsonArray jsonArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        mViewModel = ViewModelProviders.of(this).get(ViewModelGame.class);
        //try {
        bundle = getIntent().getExtras();

        try {
            jsonArray = mViewModel.OdbiorPokaz(bundle.getString("id"));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            //Toast.makeText(this, "Brak takiego rekordu w bazie", Toast.LENGTH_LONG).show();
        }
        Iterator<JsonElement> iterator = jsonArray.iterator();
        JsonObject object = (JsonObject) iterator.next();
        editName = findViewById(R.id.NameGameEditTxt);
        editRelease = findViewById(R.id.ReleaseDateEditTxt);
        editGenre = findViewById(R.id.GenreEditTxt);
        editPlatform = findViewById(R.id.PlatformEditTxt);
        editPrice = findViewById(R.id.PriceEditTxt);
        editName.setText(object.get("Title").getAsString());
        editRelease.setText(object.get("Place").getAsString());
        editGenre.setText(object.get("Genre").getAsString());
        editPlatform.setText(object.get("Platform").getAsString());
        editPrice.setText(object.get("Price").getAsString());
        /*editName.setText(cursor.getString(1));
        editRelease.setText(cursor.getString(2));
        editGenre.setText(cursor.getString(3));
        editPlatform.setText(cursor.getString(4));
        editPrice.setText(cursor.getString(5));*/
        //}catch (Exception e) {Toast.makeText(this, "Brak takiego rekordu w bazie", Toast.LENGTH_LONG).show();
        //this.finish();}
    }
    public void SendDeleteRecordToVM(View view){
        mViewModel.idg = bundle.getString("id");
        mViewModel.WyslijUsun();
        Toast.makeText(this,"Pomyślnie usunięto rekord",Toast.LENGTH_LONG).show();
        this.finish();
    }
}
