package br.com.lcv.hospede;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by lucas on 31/07/19.
 */
@Service
public class HospedeService {

    private HospedeRepository hospedeRepository;

    @Autowired
    public HospedeService(HospedeRepository hospedeRepository) {
        this.hospedeRepository = hospedeRepository;
    }

    public String qtdHospedesToString(List<Hospede> hospedes) {
        long qtdADT = defineQtdHospedesPorFaixaEtaria(hospedes, FaixaEtaria.ADT);
        long qtdCHD = defineQtdHospedesPorFaixaEtaria(hospedes, FaixaEtaria.CHD);
        long qtdINF = defineQtdHospedesPorFaixaEtaria(hospedes, FaixaEtaria.INF);

        StringBuilder sb = new StringBuilder();
        sb.append(qtdADT).append(" ").append(FaixaEtaria.ADT.getValor());

        if (qtdCHD > 0) {
            if (qtdINF > 0) {
                sb.append(", ").append(qtdCHD).append(" ").append(FaixaEtaria.CHD.getValor());
            } else {
                sb.append(" e ").append(qtdCHD).append(" ").append(FaixaEtaria.CHD.getValor());
            }
        }

        if (qtdINF > 0) {
            sb.append(" e ").append(qtdINF).append(" ").append(FaixaEtaria.INF.getValor());
        }

        return sb.toString();
    }

    public boolean isTodosDadosHospedesPreenchidos(List<Hospede> hospedes) {
        for (Hospede hospede : hospedes) {
            if (!hospede.isTodosDadosPreenchidos()) {
                return false;
            }
        }

        return true;
    }

    public String dadosHospoedesToString(List<Hospede> hospedes) {
        StringBuilder sb = new StringBuilder();

        for (Hospede hospede : hospedes) {
            sb.append(hospede.listaDadosHospede()).append("\n\n");
        }

        return sb.toString().trim();
    }

    public Hospede selecionaHospedePorCPF(List<Hospede> hospedes, String cpf) {
        return hospedes.stream().filter(hospede -> hospede.getCpf().equals(cpf)).findFirst().get();
    }

    public Hospede selecionaHospedeAdulto(List<Hospede> hospedes) {
        return hospedes.stream().filter(hospede -> hospede.getFaixaEtaria().equals(FaixaEtaria.ADT)).findFirst().get();
    }

    public long defineQtdHospedesPorFaixaEtaria(List<Hospede> hospedes, FaixaEtaria faixaEtaria) {
        return hospedes.stream().filter(hospede -> hospede.getFaixaEtaria().equals(faixaEtaria)).count();
    }

    public List<Hospede> salvaHospedes(Long reservaId, List<Hospede> hospedes) {
        hospedes.stream().forEach(hospede -> hospede.setReservaId(reservaId));
        return hospedeRepository.saveAll(hospedes);
    }

}
