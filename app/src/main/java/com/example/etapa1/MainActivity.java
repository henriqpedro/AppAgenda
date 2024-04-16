package com.example.etapa1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.room.Room;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private EditText edtData, edtHora, edtDescricao;
    private Button btnOK, btnCompromissosHoje, btnCompromissosOutroDia;
    private TextView txtCompromissos;
    private AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtData = findViewById(R.id.edtData);
        edtHora = findViewById(R.id.edtHora);
        edtDescricao = findViewById(R.id.edtDescricao);
        btnOK = findViewById(R.id.btnOK);
        btnCompromissosHoje = findViewById(R.id.btnCompromissosHoje);
        btnCompromissosOutroDia = findViewById(R.id.btnCompromissosOutroDia);
        txtCompromissos = findViewById(R.id.txtCompromissos);

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").allowMainThreadQueries().build();

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = edtData.getText().toString();
                String hora = edtHora.getText().toString();
                String descricao = edtDescricao.getText().toString();
                CompromissoEntity compromisso = new CompromissoEntity(data, hora, descricao);
                db.compromissoDao().insertCompromisso(compromisso);
                edtData.setText("");
                edtHora.setText("");
                edtDescricao.setText("");
            }
        });

        btnCompromissosHoje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String data = sdf.format(new Date());
                List<CompromissoEntity> compromissosHoje = db.compromissoDao().getCompromissosByDate(data);
                exibirCompromissos(compromissosHoje);
            }
        });

        btnCompromissosOutroDia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String data = sdf.format(new Date());
                List<CompromissoEntity> compromissosOutroDia = db.compromissoDao().getCompromissosOutroDia(data);
                exibirCompromissos(compromissosOutroDia);
            }
        });
    }

    private void exibirCompromissos(List<CompromissoEntity> compromissos) {
        StringBuilder sb = new StringBuilder();
        for (CompromissoEntity compromisso : compromissos) {
            sb.append("Data: ").append(compromisso.getData()).append("\n");
            sb.append("Hora: ").append(compromisso.getHora()).append("\n");
            sb.append("Descrição: ").append(compromisso.getDescricao()).append("\n\n");
        }
        txtCompromissos.setText(sb.toString());
    }
}