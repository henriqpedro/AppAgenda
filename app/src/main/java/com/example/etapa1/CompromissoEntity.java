package com.example.etapa1;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "compromissos")
public class CompromissoEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String data;
    private String hora;
    private String descricao;

    public CompromissoEntity(String data, String hora, String descricao) {
        this.data = data;
        this.hora = hora;
        this.descricao = descricao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
