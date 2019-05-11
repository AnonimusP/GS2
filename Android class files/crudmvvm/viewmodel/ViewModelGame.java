package com.example.komputer.crudmvvm.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.support.annotation.NonNull;
import com.example.komputer.crudmvvm.model.DBHelperMySQL;
import com.google.gson.JsonArray;

import java.io.IOException;


public class ViewModelGame extends AndroidViewModel {
    DBHelperMySQL bazam;
    public String idg;
    public String nameg;
    public String releaseg;
    public String genreg;
    public String platformg;
    public String priceg;

    public ViewModelGame(@NonNull Application application) {
        super(application);
        bazam = new DBHelperMySQL();
    }

    public void OdbiorDodaj () {
        bazam.AddGame(nameg,releaseg,genreg,platformg,priceg);
    }
    public JsonArray OdbiorPokaz(String id) throws IOException {
        return bazam.ShowGame(id);
    }
    public JsonArray PokazWszystkie() throws IOException {
        return bazam.ShowAllGames();
    }
    public void WyslijEdytuj() {
        bazam.UpdateGame(idg,nameg,releaseg,genreg,platformg,priceg);
    }
    public void WyslijUsun(){
        bazam.DeleteGame(idg);
    }

}
