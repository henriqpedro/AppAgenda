package com.example.etapa1;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class FragmentoTimePicker extends DialogFragment implements TimePickerDialog.OnTimeSetListener {

    private int hora;
    private int minutos;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int hora = c.get(Calendar.HOUR_OF_DAY);
        int minutos = c.get(Calendar.MINUTE);

        return new TimePickerDialog(requireContext(), this, hora, minutos, true);
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        hora = hourOfDay;
        minutos = minute;

        EditText txt = (EditText) getActivity().findViewById(R.id.edtHora);
        if (txt != null)
            txt.setText(hora + ":" + minutos);
    }
}
