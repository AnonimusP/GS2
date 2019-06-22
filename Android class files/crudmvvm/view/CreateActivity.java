package com.example.komputer.crudmvvm.view;

import android.arch.lifecycle.ViewModelProviders;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.komputer.crudmvvm.viewmodel.*;
import com.example.komputer.crudmvvm.R;

public class CreateActivity extends AppCompatActivity {

    EditText editName, editRelease, editGenre, editPlatform, editPrice;
    ViewModelGame mViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        mViewModel = ViewModelProviders.of(this).get(ViewModelGame.class);
    }

    public void SendNewRecordToVM(View view){
        editName = findViewById(R.id.NameGameEditTxt);
        editRelease = findViewById(R.id.ReleaseDateEditTxt);
        editGenre = findViewById(R.id.GenreEditTxt);
        editPlatform = findViewById(R.id.PlatformEditTxt);
        editPrice = findViewById(R.id.PriceEditTxt);
        mViewModel.nameg=editName.getText().toString();
        mViewModel.releaseg=editRelease.getText().toString();
        mViewModel.genreg=editGenre.getText().toString();
        mViewModel.platformg=editPlatform.getText().toString();
        mViewModel.priceg=editPrice.getText().toString();
        mViewModel.OdbiorDodaj();
        Toast.makeText(this,"Pomyślnie dodano nową pozycję",Toast.LENGTH_LONG).show();
        this.finish();
    }
}
