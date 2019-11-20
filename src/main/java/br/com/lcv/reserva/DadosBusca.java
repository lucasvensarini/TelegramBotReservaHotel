package br.com.lcv.reserva;

import br.com.lcv.bot.CampoVazioException;
import br.com.lcv.bot.DataInvalidaException;
import br.com.lcv.bot.DataUtil;
import br.com.lcv.hotel.CidadeDesconhecidaException;
import br.com.lcv.hotel.Classificacao;

import java.time.LocalDate;

/**
 * Created by lucas on 12/08/19.
 */
public class DadosBusca {

    private String nomeHotel;
    private String cidade;
    private LocalDate dataEntrada;
    private LocalDate dataSaida;
    private Classificacao classificacao;

    public String getNomeHotel() {
        return nomeHotel;
    }

    private void setNomeHotel(String nomeHotel) {
        this.nomeHotel = nomeHotel;
    }

    public String getCidade() {
        return cidade;
    }

    private void setCidade(String cidade) {
        this.cidade = cidade;
    }

    LocalDate getDataEntrada() {
        return dataEntrada;
    }

    private void setDataEntrada(LocalDate dataEntrada) {
        this.dataEntrada = dataEntrada;
    }

    LocalDate getDataSaida() {
        return dataSaida;
    }

    private void setDataSaida(LocalDate dataSaida) {
        this.dataSaida = dataSaida;
    }

    public Classificacao getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(Classificacao classificacao) {
        this.classificacao = classificacao;
    }

    public void defineNomeHotel(String nomeHotel) {
        if (!nomeHotel.isEmpty()) {
            setNomeHotel(nomeHotel);
        } else {
            throw new CampoVazioException("Nome do hotel");
        }
    }

    public void defineCidade(String cidade) {
        if (!cidade.isEmpty()) {
            setCidade(cidade);
        } else {
            throw new CidadeDesconhecidaException();
        }
    }

    public void defineDataEntrada(String dataEntradaString) {
        if (DataUtil.isDataEntradaValida(dataEntradaString)) {
            LocalDate dataEntrada = DataUtil.parseData(dataEntradaString);
            setDataEntrada(dataEntrada);
        } else {
            throw new DataInvalidaException();
        }
    }

    public void defineDataSaida(String dataSaidaString) {
        if (DataUtil.isDataSaidaValida(dataSaidaString, dataEntrada)) {
            LocalDate dataSaida = DataUtil.parseData(dataSaidaString);
            setDataSaida(dataSaida);
        } else {
            throw new DataInvalidaException();
        }
    }

}
