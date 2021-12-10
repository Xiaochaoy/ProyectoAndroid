package com.company.room;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ChampionsRepositorio {

    ChampionsBaseDeDatos.ElementosDao elementosDao;
    Executor executor = Executors.newSingleThreadExecutor();

    ChampionsRepositorio(Application application){
        elementosDao = ChampionsBaseDeDatos.obtenerInstancia(application).obtenerElementosDao();
    }


    LiveData<List<Champion>> obtener(){
        return elementosDao.obtener();
    }

    LiveData<List<Champion>> alfabetico() {
        return elementosDao.alfabetico();
    }


    void insertar(Champion champion){
        executor.execute(new Runnable() {
            @Override
            public void run() {
                elementosDao.insertar(champion);
            }
        });
    }

    void eliminar(Champion champion) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                elementosDao.eliminar(champion);
            }
        });
    }
}