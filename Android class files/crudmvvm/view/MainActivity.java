package com.example.komputer.crudmvvm.view;

import android.arch.lifecycle.ViewModelProviders;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.StrictMode;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.komputer.crudmvvm.viewmodel.*;
import com.example.komputer.crudmvvm.R;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.Iterator;


public class MainActivity extends AppCompatActivity {
    public String edtxt;
    ViewModelGame mViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewModel = ViewModelProviders.of(this).get(ViewModelGame.class);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public void GoToCreate(View view){
        Intent i = new Intent (getApplicationContext(), CreateActivity.class);
        startActivity(i);
    }

    public void pokazwiadomosc(String title,String Message){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }

    public void GoToRead(View view) throws IOException {
        JsonArray jsonArray = mViewModel.PokazWszystkie();
        ;
        if (jsonArray.size() == 0) {
            pokazwiadomosc("Baza danych pusta", "Niczego nie znaleziono");
            return;
        }
        StringBuffer buffer = new StringBuffer();
        Iterator<JsonElement> iterator = jsonArray.iterator();
        while (iterator.hasNext()) {
            JsonObject object = (JsonObject) iterator.next();
            buffer.append("ID : " + object.get("Id") + "\n");
            buffer.append("Tytuł : " + object.get("Title") + "\n");
            buffer.append("Miejsce/półka: "+object.get("Place") + "\n");
            buffer.append("Gatunek : " + object.get("Genre") + "\n");
            buffer.append("Platforma : " + object.get("Platform") + "\n");
            buffer.append("Cena : " + object.get("Price") + "\n");
            buffer.append("Ostatnia modyfikacja: "+object.get("last_modified")+ "\n");
        }
            pokazwiadomosc("", buffer.toString());
    }



    public void GoToUpdate(View view){
        final EditText edittext = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(edittext);
        builder.setMessage("Podaj ID rekordu który chcesz edytować")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        edtxt=edittext.getText().toString();
                        if (edtxt.isEmpty()){
                            Toast.makeText(MainActivity.this, "Nie podano ID", Toast.LENGTH_LONG).show();
                        } else {
                        Intent i = new Intent (getApplicationContext(), EditActivity.class);
                        i.putExtra("id",edtxt);
                        startActivity(i);}
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void GoToDelete(View view){
        final EditText edittext = new EditText(this);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(edittext);
        builder.setMessage("Podaj ID rekordu który chcesz usunąć")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        edtxt=edittext.getText().toString();
                        if (edtxt.isEmpty()){
                            Toast.makeText(MainActivity.this, "Nie podano ID", Toast.LENGTH_LONG).show();
                        } else {
                        Intent i = new Intent (getApplicationContext(), DeleteActivity.class);
                        i.putExtra("id",edtxt);
                        startActivity(i);}
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    }
