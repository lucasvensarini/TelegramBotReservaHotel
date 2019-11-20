package br.com.lcv.passo;

/**
 * Created by lucas on 03/09/19.
 */
public enum PassoCorrente {

    CONFIRMAR_NOME_HOTEL("confirmar se sabe o nome do hotel"),
    PREENCHIMENTO_NOME_HOTEL(""),

    PREENCHIMENTO_CIDADE(""),
    PREENCHIMENTO_ADULTOS("selecionar a quantidade de adultos"),
    PREENCHIMENTO_CRIANCAS("selecionar a quantidade de crianças"),
    PREENCHIMENTO_BEBES("selecionar a quantidade de bebês"),
    PREENCHIMENTO_DATA_ENTRADA(""),
    PREENCHIMENTO_DATA_SAIDA(""),
    PREENCHIMENTO_CLASSIFICACAO_HOTEL("escolher se deseja filtrar por classificação"),

    ESCOLHA_HOTEL("selecionar um hotel"),
    ESCOLHA_QUARTO("selecionar um quarto"),
    CONFIRMAR_QUARTO("confirmar a escolha do quarto"),

    PREENCHIMENTO_HOSPEDES("selecionar um hóspede para preencher os dados"),
    PREENCHIMENTO_NOME(""),
    PREENCHIMENTO_CPF(""),
    PREENCHIMENTO_SEXO(("selecionar o sexo do hóspede")),
    PREENCHIMENTO_DATA_NASCIMENTO(""),

    MODIFICAR_DADOS_HOSPEDES("selecionar o dado que deseja alterar"),
    CONFIRMAR_DADOS_HOSPEDES("confirmar os dados do hóspedes"),
    ESCOLHA_RESPONSAVEL("selecionar o responsável pela reserva"),
    CONFIRMAR_INFORMACOES("confirmar a reserva");

    private String instrucao;

    PassoCorrente(String instrucao) {
        this.instrucao = instrucao;
    }

    public String getInstrucao() {
        return instrucao;
    }
}
