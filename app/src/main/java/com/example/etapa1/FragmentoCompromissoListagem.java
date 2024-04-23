package com.example.etapa1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Calendar;
import java.util.List;

public class FragmentoCompromissoListagem extends Fragment {

    public static Button btnCompromissosHoje, btnCompromissosOutroDia;
    public static TextView txtCompromissos;

    private static void exibirCompromissos(AppDatabase db, boolean dataDeHoje) {

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        int day = c.get(Calendar.DAY_OF_MONTH);

        String dataAtual = day + "/" + month + "/" + year;
        List<CompromissoEntity> compromissos;
        if (dataDeHoje)
            compromissos = db.compromissoDao().getCompromissosByDate(dataAtual);
        else
            compromissos = db.compromissoDao().getCompromissosOutroDia(dataAtual);

        StringBuilder sb = new StringBuilder();
        for (CompromissoEntity compromisso : compromissos) {
            sb.append("Data: ").append(compromisso.getData()).append("\n");
            sb.append("Hora: ").append(compromisso.getHora()).append("\n");
            sb.append("Descrição: ").append(compromisso.getDescricao()).append("\n\n");
        }

        txtCompromissos.setText(sb.toString());
    }

    public static void setDbButtonsBehavior(AppDatabase db) {
        btnCompromissosHoje.setOnClickListener(v ->
                exibirCompromissos(db, true)
        );
        btnCompromissosOutroDia.setOnClickListener(v ->
                exibirCompromissos(db, false)
        );
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

        return view;
    }
}
