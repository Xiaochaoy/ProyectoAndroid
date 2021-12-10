package com.company.room;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.Update;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.List;

@Database(entities = {Champion.class}, version = 1, exportSchema = false)
public abstract class ChampionsBaseDeDatos extends RoomDatabase {

    public abstract ElementosDao obtenerElementosDao();

    private static volatile ChampionsBaseDeDatos INSTANCIA;

    static ChampionsBaseDeDatos obtenerInstancia(final Context context) {
        if (INSTANCIA == null) {
            synchronized (ChampionsBaseDeDatos.class) {
                if (INSTANCIA == null) {
                    INSTANCIA = Room.databaseBuilder(context,
                            ChampionsBaseDeDatos.class, "champions.db")
                            .fallbackToDestructiveMigration()
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);

                                }
                            })
                            .build();
                }
            }
        }
        return INSTANCIA;
    }

    @Dao
    interface ElementosDao {
        @Query("SELECT * FROM Champion")
        LiveData<List<Champion>> obtener();

        @Insert
        void insertar(Champion champion);

        @Update
        void actualizar(Champion champion);

        @Delete
        void eliminar(Champion champion);

        @Query("SELECT * FROM Champion ORDER BY nombre")
        LiveData<List<Champion>> alfabetico();

    }
}
