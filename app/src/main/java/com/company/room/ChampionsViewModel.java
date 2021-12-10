package com.company.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class ChampionsViewModel extends AndroidViewModel {

    ChampionsRepositorio championsRepositorio;


    MutableLiveData<Champion> elementoSeleccionado = new MutableLiveData<>();


    public ChampionsViewModel(@NonNull Application application) {
        super(application);

        championsRepositorio = new ChampionsRepositorio(application);
    }



    LiveData<List<Champion>> obtener(){
        return championsRepositorio.obtener();
    }

    LiveData<List<Champion>> alfabetico(){
        return championsRepositorio.alfabetico();
    }

    void insertar(Champion champion){
        championsRepositorio.insertar(champion);
    }

    void eliminar(Champion champion){
        championsRepositorio.eliminar(champion);
    }

    void seleccionar(Champion champion){
        elementoSeleccionado.setValue(champion);
    }

    MutableLiveData<Champion> seleccionado(){
        return elementoSeleccionado;
    }

}