package com.company.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Champion {
    @PrimaryKey(autoGenerate = true)
    int id;

    int imagen;
    String nombre;
    String descripcion;

    public Champion(int imagen, String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
    }
}
