package br.com.lcv.passo;

import br.com.lcv.sessao.Sessao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PassoFactory {

    private PassoNomeHotel passoNomeHotel;
    private PassoCidade passoCidade;
    private PassoDataEntrada passoDataEntrada;
    private PassoDataSaida passoDataSaida;
    private PassoNomeHospede passoNomeHospede;
    private PassoCPF passoCPF;
    private PassoDataNascimento passoDataNascimento;

    @Autowired
    public PassoFactory(PassoNomeHotel passoNomeHotel, PassoCidade passoCidade, PassoDataEntrada passoDataEntrada,
                        PassoDataSaida passoDataSaida, PassoNomeHospede passoNomeHospede, PassoCPF passoCPF,
                        PassoDataNascimento passoDataNascimento) {
        this.passoNomeHotel = passoNomeHotel;
        this.passoCidade = passoCidade;
        this.passoDataEntrada = passoDataEntrada;
        this.passoDataSaida = passoDataSaida;
        this.passoNomeHospede = passoNomeHospede;
        this.passoCPF = passoCPF;
        this.passoDataNascimento = passoDataNascimento;
    }

    public Passo definePasso(Sessao sessao) {
        if (sessao.isPassoCorrente(PassoCorrente.PREENCHIMENTO_NOME_HOTEL)) {
            return passoNomeHotel;
        } else if (sessao.isPassoCorrente(PassoCorrente.PREENCHIMENTO_CIDADE)) {
            return passoCidade;
        } else if (sessao.isPassoCorrente(PassoCorrente.PREENCHIMENTO_DATA_ENTRADA)) {
            return passoDataEntrada;
        } else if (sessao.isPassoCorrente(PassoCorrente.PREENCHIMENTO_DATA_SAIDA)) {
            return passoDataSaida;
        } else if (sessao.isPassoCorrente(PassoCorrente.PREENCHIMENTO_NOME)) {
            return passoNomeHospede;
        } else if (sessao.isPassoCorrente(PassoCorrente.PREENCHIMENTO_CPF)) {
            return passoCPF;
        } else if (sessao.isPassoCorrente(PassoCorrente.PREENCHIMENTO_DATA_NASCIMENTO)) {
            return passoDataNascimento;
        } else {
            throw new PassoInvalidoException(sessao.getPassoCorrente().getInstrucao());
        }
    }

}
