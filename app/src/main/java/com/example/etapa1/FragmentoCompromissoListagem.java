package com.example.etapa1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class FragmentoCompromissoListagem extends Fragment {

    public static Button btnCompromissosHoje, btnCompromissosOutroDia;
    public static TextView txtCompromissos;

    public static void exibirCompromissos(String date) {

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);

        List<CompromissoEntity> compromissos = new ArrayList<>();
        AppDatabase db = MainActivity.db;
        if (db != null) {
            if (date == null) {
                String dataAtual = day + "/" + month + "/" + year;
                compromissos = db.compromissoDao().getCompromissosByDate(dataAtual);
            } else
                compromissos = db.compromissoDao().getCompromissosByDate(date);
        }

        StringBuilder sb = new StringBuilder();
        for (CompromissoEntity compromisso : compromissos)
            sb.append(compromisso.toString());
        txtCompromissos.setText(sb.toString());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.listagem_compromisso, container, false);

        btnCompromissosHoje = view.findViewById(R.id.btnCompromissosHoje);
        btnCompromissosOutroDia = view.findViewById(R.id.btnCompromissosOutroDia);
        txtCompromissos = view.findViewById(R.id.txtCompromissos);

        btnCompromissosOutroDia.setOnClickListener(v -> {
            FragmentoAnotherDatePicker datePicker = new FragmentoAnotherDatePicker();
            datePicker.show(getParentFragmentManager(), "anotherDatePicker");
        });

        btnCompromissosHoje.setOnClickListener(v ->
                exibirCompromissos(null)
        );

        return view;
    }
}
