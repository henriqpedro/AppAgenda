package com.example.etapa1;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class FragmentoDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private int dia;
    private int mes;
    private  int ano;

    public String getFormattedDate() {
        String day = String.format("%02d", dia);
        String month = String.format("%02d", mes);
        String year = String.format("%04d", ano);
        return day + '/' + month + '/' + year;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(requireContext(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        dia = day;
        mes = month + 1;
        ano = year;

        EditText txt = getActivity().findViewById(R.id.edtData);
        if (txt != null)
            txt.setText(getFormattedDate());
    }
}
