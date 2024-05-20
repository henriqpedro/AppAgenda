package com.example.etapa1.Helpers;

public class StringFormatter {
    public static String getFormattedDate(int dia, int mes, int ano) {
        String day = String.format("%02d", dia);
        String month = String.format("%02d", mes);
        String year = String.format("%04d", ano);
        return day + '/' + month + '/' + year;
    }

    public static String getFormattedTime(int hora, int minutos) {
        String hour = String.format("%02d", hora);
        String minute = String.format("%02d", minutos);
        return hour + ':' + minute;
    }
}
