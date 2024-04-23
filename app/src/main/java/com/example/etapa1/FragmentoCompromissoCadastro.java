package com.example.etapa1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

public class FragmentoCompromissoCadastro extends Fragment {

    private static EditText edtData, edtHora, edtDescricao;
    private static Button btnData, btnHora, btnOK;

    private static void showToast(String text) {
        Toast toast = new Toast(edtData.getContext());
        toast.setText(text);
        toast.show();
    }

    private static boolean isValid() {
        if (edtData.getText().toString().trim().isEmpty()) {
            showToast("Data não informada!");
            return false;
        }
        if (edtHora.getText().toString().trim().isEmpty()) {
            showToast("Hora não informada!");
            return false;
        }
        if (edtDescricao.getText().toString().trim().isEmpty()) {
            showToast("Descrição não informada!");
            return false;
        }
        return true;
    }

    public static void setDbButtonsBehavior(AppDatabase db) {
        btnOK.setOnClickListener(v -> {
            String data = edtData.getText().toString();
            String hora = edtHora.getText().toString();
            String descricao = edtDescricao.getText().toString();

            if (!isValid())
                return;

            CompromissoEntity compromisso = new CompromissoEntity(data, hora, descricao);
            db.compromissoDao().insertCompromisso(compromisso);
            edtData.setText("");
            edtHora.setText("");
            edtDescricao.setText("");
        });
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.cadastro_compromisso, container, false);

        edtData = view.findViewById(R.id.edtData);
        btnData = view.findViewById(R.id.btnData);
        edtHora = view.findViewById(R.id.edtHora);
        btnHora = view.findViewById(R.id.btnHora);
        edtDescricao = view.findViewById(R.id.edtDescricao);
        btnOK = view.findViewById(R.id.btnOK);

        btnData.setOnClickListener(v -> {
            FragmentoDatePicker datePicker = new FragmentoDatePicker();
            datePicker.show(getParentFragmentManager(), "datePicker");
        });

        btnHora.setOnClickListener(v -> {
            FragmentoTimePicker timePicker = new FragmentoTimePicker();
            timePicker.show(getParentFragmentManager(), "timePicker");
        });

        return view;
    }
}
