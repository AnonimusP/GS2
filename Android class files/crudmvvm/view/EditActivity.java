package com.example.komputer.crudmvvm.view;

import android.arch.lifecycle.ViewModelProviders;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.komputer.crudmvvm.viewmodel.*;
import com.example.komputer.crudmvvm.R;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.Iterator;

public class EditActivity extends AppCompatActivity {

    EditText editName, editRelease, editGenre, editPlatform, editPrice;
    ViewModelGame mViewModel;
    Bundle bundle;
    JsonArray jsonArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        mViewModel = ViewModelProviders.of(this).get(ViewModelGame.class);
            bundle = getIntent().getExtras();
        try {
            jsonArray = mViewModel.OdbiorPokaz(bundle.getString("id"));
        } catch (IOException e) {
            e.printStackTrace();
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

    }
    public void SendEditedRecordToVM(View view){
        mViewModel.idg=bundle.getString("id");
        mViewModel.nameg=editName.getText().toString();
        mViewModel.releaseg=editRelease.getText().toString();
        mViewModel.genreg=editGenre.getText().toString();
        mViewModel.platformg=editPlatform.getText().toString();
        mViewModel.priceg=editPrice.getText().toString();
        mViewModel.WyslijEdytuj();
        Toast.makeText(this,"Pomyślnie edytowano rekord",Toast.LENGTH_LONG).show();
        this.finish();
    }
}
