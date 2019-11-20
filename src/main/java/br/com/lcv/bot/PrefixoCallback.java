package br.com.lcv.bot;

/**
 * Created by lucas on 03/09/19.
 */
public enum PrefixoCallback {
    UNDEF("", ""),
    CONFIRMACAO_NOME_HOTEL("confirmacaoNomeHotel", "digitar o nome do hotel"),
    NAO_SABE_NOME_HOTEL("NaoSabeNomeHotel", ""),
    HOSPEDES_ADT("hospedesADT", "selecionar a quantidade de adultos"),
    HOSPEDES_CHD("hospedesCHD", "selecionar a quantidade de crianças"),
    HOSPEDES_INF("hospedesINF", "selecionar a quantidade de bebês"),
    CLASSIFICAO_HOTEL("classificacaoHotel", "selecionar se deseja filtar por classificação"),
    HOTEIS("hoteis", "selecionar um hotel"),
    MAIS_INFO_HOTEL("fotosHotel", ""),
    QUARTOS("quartos", "selecionar um quarto"),
    HOTEL_SELECIONADO("hotelSelecionado", ""),
    QUARTO_SELECIONADO("quartoSelecionado", ""),
    PREENCHER_HOSPEDES("preencherHospedes", "seelcionar o hóspede para preencher seus dados"),
    SEXO_HOSPEDE("sexoHospede", "selecionar o sexo do hóspede"),
    LISTAR_DADOS_PARA_ALTERACAO("listarDadosParaAlteracao", "selecionar um dado para alterar"),
    MODIFICAR_DADOS_HOSPEDES("modificarDadosHospedes", ""),
    SAIR_MODIFICACAO_DADOS_HOSPEDES("sairModificarDadosHospedes", ""),
    CONFIRMACAO_HOSPEDES("confirmacaoHospedes", "confirmar os dados dos hóspedes"),
    CONFIRMACAO_INFORMACOES_RESERVA("confirmacaoInformacoesReserva", "confirmar os dados da reserva"),
    CONFIRMACAO_RESPONSAVEL("confirmacaoResponsavelsReserva", "selecionar o responsável pela reserva"),
    VOLTAR_HOTEIS("voltarHoteis", ""),
    VOLTAR_QUARTOS("voltarQuartos", ""),
    VOLTAR_PREENCHER_HOSPEDES("voltarPreencherHospedes", ""),
    VOLTAR_INICIO("voltarInicio", "");

    private String id;
    private String instrucao;

    PrefixoCallback(String id, String instrucao) {
        this.id = id;
        this.instrucao = instrucao;
    }

    public static PrefixoCallback getPrefixoCallbackById(String id) {
        for (PrefixoCallback prefixoCallback : PrefixoCallback.values()) {
            if (prefixoCallback.getId().equalsIgnoreCase(id)) {
                return prefixoCallback;
            }
        }

        return UNDEF;
    }

    public String getId() {
        return id;
    }

    public String getInstrucao() {
        return instrucao;
    }

}
