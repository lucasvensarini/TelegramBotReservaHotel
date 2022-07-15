package br.com.lcv.hospede;

import br.com.lcv.bot.CampoVazioException;
import br.com.lcv.bot.DataIncompativelComFaixaEtariaException;
import br.com.lcv.bot.DataInvalidaException;
import br.com.lcv.bot.DataUtil;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

/**
 * Created by lucas on 04/07/19.
 */
@Entity
public class Hospede {

    public static final String NOME = "Nome";
    public static final String CPF = "CPF";
    public static final String SEXO = "Sexo";
    public static final String DATA_NASCIMENTO = "Data de nascimento";
    public static final List<String> DADOS_HOSPEDES = Arrays.asList(NOME, CPF, SEXO, DATA_NASCIMENTO);
    public static final List<String> DADOS_HOSPEDES_INF = Arrays.asList(NOME, SEXO, DATA_NASCIMENTO);

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column
    private String cpf;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Sexo sexo;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private FaixaEtaria faixaEtaria;

    @JoinColumn(referencedColumnName = "id")
    private Long reservaId;

    public Hospede() {

    }

    public Hospede(FaixaEtaria faixaEtaria) {
        this.faixaEtaria = faixaEtaria;
    }

    public String getNome() {
        return nome;
    }

    private void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    private void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    private LocalDate getDataNascimento() {
        return dataNascimento;
    }

    private void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public FaixaEtaria getFaixaEtaria() {
        return faixaEtaria;
    }

    void setReservaId(Long reservaId) {
        this.reservaId = reservaId;
    }

    public void defineNome(String nome) {
        if (!nome.isEmpty()) {
            setNome(nome);
        } else {
            throw new CampoVazioException(NOME);
        }
    }

    public void defineCPF(String cpf) {
        try {
            Long.parseLong(cpf);
            if (!cpf.isEmpty()) {
                setCpf(cpf);
            } else {
                throw new CampoVazioException(CPF);
            }
        } catch (Exception e) {
            throw new CPFInvalidoException();
        }
    }

    public void defineDataNascimento(String dataNascimentoString) {
        if (DataUtil.isDataNascimentoValida(dataNascimentoString)) {
            if (DataUtil.isDataNascimentoCompativelComFaixaEtaria(dataNascimentoString, getFaixaEtaria())) {
                LocalDate dataNascimento = DataUtil.parseData(dataNascimentoString);
                setDataNascimento(dataNascimento);
            } else {
                throw new DataIncompativelComFaixaEtariaException();
            }
        } else {
            throw new DataInvalidaException();
        }
    }

    private boolean isNomePreenchido() {
        return getNome() != null && !getNome().isEmpty();
    }

    private boolean isCPFPreenchido() {
        return getCpf() != null && !getCpf().isEmpty();
    }

    private boolean isSexoPreenchido() {
        return getSexo() != null && !getSexo().equals(Sexo.UNDEF);
    }

    private boolean isDataNascimentoPreenchida() {
        return getDataNascimento() != null;
    }

    private boolean isFaixaEtariaPreenchida() {
        return getFaixaEtaria() != null;
    }

    public boolean isTodosDadosPreenchidos() {
        if (getFaixaEtaria().equals(FaixaEtaria.INF)) {
            return isNomePreenchido() && isSexoPreenchido() && isDataNascimentoPreenchida() && isFaixaEtariaPreenchida();
        } else {
            return isNomePreenchido()
                    && isCPFPreenchido()
                    && isSexoPreenchido()
                    && isDataNascimentoPreenchida()
                    && isFaixaEtariaPreenchida();
        }
    }

    public String listaDadosHospede() {
        String texto = "<b>Nome: </b>" + getNome() + "\n";

        if (!getFaixaEtaria().equals(FaixaEtaria.INF)) {
            texto += "<b>CPF: </b>" + getCpf() + "\n";
        }

        texto +=
                "<b>Sexo: </b>" + getSexo().getValor() + "\n" + "<b>Data de nascimento: </b>" + DataUtil.formataData(
                        getDataNascimento()) + "\n" + "<b>Faixa etária: </b>" + getFaixaEtaria().getValor();

        return texto;
    }

}
