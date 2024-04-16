package com.example.etapa1;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface CompromissoDao {
    @Query("SELECT * FROM compromissos WHERE data = :data")
    List<CompromissoEntity> getCompromissosByDate(String data);

    @Query("SELECT * FROM compromissos WHERE data != :data")
    List<CompromissoEntity> getCompromissosOutroDia(String data);

    @Insert
    void insertCompromisso(CompromissoEntity compromisso);
}