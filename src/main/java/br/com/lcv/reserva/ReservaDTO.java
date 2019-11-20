package br.com.lcv.reserva;

import br.com.lcv.hospede.FaixaEtaria;
import br.com.lcv.hospede.Hospede;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 04/07/19.
 */
public class ReservaDTO {

    private DadosBusca dadosBusca;
    private List<Hospede> hospedes;

    ReservaDTO() {
        this.dadosBusca = new DadosBusca();
        this.hospedes = new ArrayList<>();
    }

    public DadosBusca getDadosBusca() {
        return dadosBusca;
    }

    public List<Hospede> getHospedes() {
        return hospedes;
    }

    public void defineQtdHospedesADT(int qtdHospedesADT) {
        adicionaHospedes(qtdHospedesADT, FaixaEtaria.ADT);
    }

    public void defineQtdHospedesCHD(int qtdHospedesCHD) {
        adicionaHospedes(qtdHospedesCHD, FaixaEtaria.CHD);
    }

    public void defineQtdHospedesINF(int qtdHospedesINF) {
        adicionaHospedes(qtdHospedesINF, FaixaEtaria.INF);
    }

    private void adicionaHospedes(int qtdHospedes, FaixaEtaria faixaEtaria) {
        while (qtdHospedes > 0) {
            Hospede hospede = new Hospede(faixaEtaria);
            hospedes.add(hospede);
            qtdHospedes--;
        }
    }

}
