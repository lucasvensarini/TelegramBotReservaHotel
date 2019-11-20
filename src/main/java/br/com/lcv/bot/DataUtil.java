package br.com.lcv.bot;

import br.com.lcv.hospede.FaixaEtaria;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lucas on 19/07/19.
 */
public class DataUtil {

    private static DateTimeFormatter FORMATADOR_DATA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static DateTimeFormatter FORMATADOR_HORA = DateTimeFormatter.ofPattern("HH:mm");
    private static DateTimeFormatter FORMATADOR_DATA_HORA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    public static boolean isDataEntradaValida(String dataEntradaString) {
        boolean dataFormatoValido = isDataFormatoValido(dataEntradaString);

        LocalDate dataEntrada;
        try {
            dataEntrada = LocalDate.parse(dataEntradaString, FORMATADOR_DATA);
        } catch (Exception e) {
            return false;
        }

        return dataFormatoValido && !dataEntrada.isBefore(LocalDate.now());
    }

    public static boolean isDataSaidaValida(String dataSaidaString, LocalDate dataEntrada) {
        boolean dataFormatoValido = isDataFormatoValido(dataSaidaString);

        LocalDate dataSaida;
        try {
            dataSaida = LocalDate.parse(dataSaidaString, FORMATADOR_DATA);
        } catch (Exception e) {
            return false;
        }

        return dataFormatoValido && !dataSaida.isBefore(dataEntrada);
    }

    public static boolean isDataNascimentoValida(String dataNascimentoString) {
        boolean dataFormatoValido = isDataFormatoValido(dataNascimentoString);

        try {
            LocalDate.parse(dataNascimentoString, FORMATADOR_DATA);
        } catch (Exception e) {
            return false;
        }

        return dataFormatoValido;
    }

    public static boolean isDataNascimentoCompativelComFaixaEtaria(String dataNascimentoString, FaixaEtaria faixaEtaria) {
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataNascimento = LocalDate.parse(dataNascimentoString, FORMATADOR_DATA);
        int diferencaAnos = Period.between(dataNascimento, dataAtual).getYears();

        if (dataNascimento.isAfter(dataAtual)) {
            return false;
        }

        if (faixaEtaria.equals(FaixaEtaria.ADT)) {
            return diferencaAnos >= 12;
        } else if (faixaEtaria.equals(FaixaEtaria.CHD)) {
            return diferencaAnos >= 1 && diferencaAnos < 12;
        } else {
            return diferencaAnos < 1;
        }
    }

    private static boolean isDataFormatoValido(String data) {
        String dataRegex = "^\\d{2}/\\d{2}/\\d{4}$";

        Pattern pattern = Pattern.compile(dataRegex);
        Matcher matcher = pattern.matcher(data);

        return matcher.matches();
    }

    static public LocalDateTime defineDataHoraAtual() {
        return LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES);
    }

    public static String formataData(LocalDate localDate) {
        return localDate.format(FORMATADOR_DATA);
    }

    public static String formataHora(LocalTime localTime) {
        return localTime.format(FORMATADOR_HORA);
    }

    public static String formataDataHora(LocalDateTime localDateTime) {
        return localDateTime.format(FORMATADOR_DATA_HORA);
    }

    public static LocalDate parseData(String data) {
        return LocalDate.parse(data, FORMATADOR_DATA);
    }

}
