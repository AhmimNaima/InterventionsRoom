package com.example.interventionroom;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Intervention  implements Serializable {

    public Intervention(String numero, String nom, String type,String date) {
        this.nom = nom;
        this.numero = numero;
        this.type = type;
        this.date=date;
    }
    @PrimaryKey(autoGenerate = true)
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    @ColumnInfo(name = "nom")
    private String nom;

    @ColumnInfo(name = "numero")
    private String numero;

    @ColumnInfo(name = "type")
    private String type;

    @ColumnInfo(name = "date")
    private String date;

    /*
     * Getters and Setters
     *
     * */
    public int getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date= date;
    }


}

