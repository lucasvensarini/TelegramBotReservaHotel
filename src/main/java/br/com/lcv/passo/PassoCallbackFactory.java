package br.com.lcv.passo;

import br.com.lcv.bot.KeyboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by lucas on 27/08/19.
 */
@Component
public class PassoCallbackFactory {

    private PassoCallbackConfirmarNomeHotel passoCallbackConfirmarNomeHotel;
    private PassoCallbackNaoSabeNomeHotel passoCallbackNaoSabeNomeHotel;
    private PassoCallbackQtdAdultos passoCallbackQtdAdultos;
    private PassoCallbackQtdCriancas passoCallbackQtdCriancas;
    private PassoCallbackQtdBebes passoCallbackQtdBebes;
    private PassoCallbackClassificacaoHotel passoCallbackClassificacaoHotel;
    private PassoCallbackHoteis passoCallbackHoteis;
    private PassoCallbackMaisInfoHotel passoCallbackMaisInfoHotel;
    private PassoCallbackQuartos passoCallbackQuartos;
    private PassoCallbackQuartoSelecionado passoCallbackQuartoSelecionado;
    private PassoCallbackMaisInfoQuarto passoCallbackMaisInfoQuarto;
    private PassoCallbackPreencherHospedes passoCallbackPreencherHospedes;
    private PassoCallbackSexoHospede passoCallbackSexoHospede;
    private PassoCallbackListarDadosParaAlteracao passoCallbackListarDadosParaAlteracao;
    private PassoCallbackModificarDadosHospedes passoCallbackModificarDadosHospedes;
    private PassoCallbackSairModificacaoDadosHospedes passoCallbackSairModificacaoDadosHospedes;
    private PassoCallbackConfirmacaoHospedes passoCallbackConfirmacaoHospedes;
    private PassoCallbackConfirmacaoInformacoesReserva passoCallbackConfirmacaoInformacoesReserva;
    private PassoCallbackIniciaCriacaoReserva passoCallbackIniciaCriacaoReserva;
    private PassoCallbackReexibeListaHoteis passoCallbackReexibeListaHoteis;
    private PassoCallbackReexibeDetalhesHotel passoCallbackReexibeDetalhesHotel;
    private PassoCallbackReexibeListaHospedesParaPreencherDados passoCallbackReexibeListaHospedesParaPreencherDados;
    private PassoCallbackReiniciaProcesso passoCallbackReiniciaProcesso;

    @Autowired
    public PassoCallbackFactory(PassoCallbackConfirmarNomeHotel passoCallbackConfirmarNomeHotel,
                                PassoCallbackNaoSabeNomeHotel passoCallbackNaoSabeNomeHotel,
                                PassoCallbackQtdAdultos passoCallbackQtdAdultos, PassoCallbackQtdCriancas passoCallbackQtdCriancas,
                                PassoCallbackQtdBebes passoCallbackQtdBebes,
                                PassoCallbackClassificacaoHotel passoCallbackClassificacaoHotel, PassoCallbackHoteis passoCallbackHoteis,
                                PassoCallbackMaisInfoHotel passoCallbackMaisInfoHotel, PassoCallbackQuartos passoCallbackQuartos,
                                PassoCallbackQuartoSelecionado passoCallbackQuartoSelecionado,
                                PassoCallbackMaisInfoQuarto passoCallbackMaisInfoQuarto,
                                PassoCallbackPreencherHospedes passoCallbackPreencherHospedes,
                                PassoCallbackSexoHospede passoCallbackSexoHospede,
                                PassoCallbackListarDadosParaAlteracao passoCallbackListarDadosParaAlteracao,
                                PassoCallbackModificarDadosHospedes passoCallbackModificarDadosHospedes,
                                PassoCallbackSairModificacaoDadosHospedes passoCallbackSairModificacaoDadosHospedes,
                                PassoCallbackConfirmacaoHospedes passoCallbackConfirmacaoHospedes,
                                PassoCallbackConfirmacaoInformacoesReserva passoCallbackConfirmacaoInformacoesReserva,
                                PassoCallbackIniciaCriacaoReserva passoCallbackIniciaCriacaoReserva,
                                PassoCallbackReexibeListaHoteis passoCallbackReexibeListaHoteis,
                                PassoCallbackReexibeDetalhesHotel passoCallbackReexibeDetalhesHotel,
                                PassoCallbackReexibeListaHospedesParaPreencherDados passoCallbackReexibeListaHospedesParaPreencherDados,
                                PassoCallbackReiniciaProcesso passoCallbackReiniciaProcesso) {
        this.passoCallbackConfirmarNomeHotel = passoCallbackConfirmarNomeHotel;
        this.passoCallbackNaoSabeNomeHotel = passoCallbackNaoSabeNomeHotel;
        this.passoCallbackQtdAdultos = passoCallbackQtdAdultos;
        this.passoCallbackQtdCriancas = passoCallbackQtdCriancas;
        this.passoCallbackQtdBebes = passoCallbackQtdBebes;
        this.passoCallbackClassificacaoHotel = passoCallbackClassificacaoHotel;
        this.passoCallbackHoteis = passoCallbackHoteis;
        this.passoCallbackMaisInfoHotel = passoCallbackMaisInfoHotel;
        this.passoCallbackQuartos = passoCallbackQuartos;
        this.passoCallbackQuartoSelecionado = passoCallbackQuartoSelecionado;
        this.passoCallbackMaisInfoQuarto = passoCallbackMaisInfoQuarto;
        this.passoCallbackPreencherHospedes = passoCallbackPreencherHospedes;
        this.passoCallbackSexoHospede = passoCallbackSexoHospede;
        this.passoCallbackListarDadosParaAlteracao = passoCallbackListarDadosParaAlteracao;
        this.passoCallbackModificarDadosHospedes = passoCallbackModificarDadosHospedes;
        this.passoCallbackSairModificacaoDadosHospedes = passoCallbackSairModificacaoDadosHospedes;
        this.passoCallbackConfirmacaoHospedes = passoCallbackConfirmacaoHospedes;
        this.passoCallbackConfirmacaoInformacoesReserva = passoCallbackConfirmacaoInformacoesReserva;
        this.passoCallbackIniciaCriacaoReserva = passoCallbackIniciaCriacaoReserva;
        this.passoCallbackReexibeListaHoteis = passoCallbackReexibeListaHoteis;
        this.passoCallbackReexibeDetalhesHotel = passoCallbackReexibeDetalhesHotel;
        this.passoCallbackReexibeListaHospedesParaPreencherDados = passoCallbackReexibeListaHospedesParaPreencherDados;
        this.passoCallbackReiniciaProcesso = passoCallbackReiniciaProcesso;
    }

    public PassoCallback definePasso(String prefixo) {
        if (prefixo.equals(KeyboardService.PREFIXO_CALLBACK_CONFIRMACAO_NOME_HOTEL)) {
            return passoCallbackConfirmarNomeHotel;
        } else if (prefixo.equals(KeyboardService.PREFIXO_CALLBACK_NAO_SABE_NOME_HOTEL)) {
            return passoCallbackNaoSabeNomeHotel;
        } else if (prefixo.equals(KeyboardService.PREFIXO_CALLBACK_HOSPEDES_ADT)) {
            return passoCallbackQtdAdultos;
        } else if (prefixo.equals(KeyboardService.PREFIXO_CALLBACK_HOSPEDES_CHD)) {
            return passoCallbackQtdCriancas;
        } else if (prefixo.equals(KeyboardService.PREFIXO_CALLBACK_HOSPEDES_INF)) {
            return passoCallbackQtdBebes;
        } else if (prefixo.equals(KeyboardService.PREFIXO_CALLBACK_CLASSIFICAO_HOTEL)) {
            return passoCallbackClassificacaoHotel;
        } else if (prefixo.equals(KeyboardService.PREFIXO_CALLBACK_HOTEIS)) {
            return passoCallbackHoteis;
        } else if (prefixo.equals(KeyboardService.PREFIXO_CALLBACK_MAIS_INFO_HOTEL)) {
            return passoCallbackMaisInfoHotel;
        } else if (prefixo.equals(KeyboardService.PREFIXO_CALLBACK_QUARTOS)) {
            return passoCallbackQuartos;
        } else if (prefixo.equals(KeyboardService.PREFIXO_CALLBACK_QUARTO_SELECIONADO)) {
            return passoCallbackQuartoSelecionado;
        } else if (prefixo.equals(KeyboardService.PREFIXO_CALLBACK_MAIS_INFO_QUARTO)) {
            return passoCallbackMaisInfoQuarto;
        } else if (prefixo.equals(KeyboardService.PREFIXO_CALLBACK_PREENCHER_HOSPEDES)) {
            return passoCallbackPreencherHospedes;
        } else if (prefixo.equals(KeyboardService.PREFIXO_CALLBACK_SEXO_HOSPEDE)) {
            return passoCallbackSexoHospede;
        } else if (prefixo.equals(KeyboardService.PREFIXO_CALLBACK_LISTAR_DADOS_PARA_ALTERACAO)) {
            return passoCallbackListarDadosParaAlteracao;
        } else if (prefixo.equals(KeyboardService.PREFIXO_CALLBACK_MODIFICAR_DADOS_HOSPEDES)) {
            return passoCallbackModificarDadosHospedes;
        } else if (prefixo.equals(KeyboardService.PREFIXO_CALLBACK_SAIR_MODIFICACAO_DADOS_HOSPEDES)) {
            return passoCallbackSairModificacaoDadosHospedes;
        } else if (prefixo.equals(KeyboardService.PREFIXO_CALLBACK_CONFIRMACAO_HOSPEDES)) {
            return passoCallbackConfirmacaoHospedes;
        } else if (prefixo.equals(KeyboardService.PREFIXO_CALLBACK_CONFIRMACAO_INFORMACOES_RESERVA)) {
            return passoCallbackConfirmacaoInformacoesReserva;
        } else if (prefixo.equals(KeyboardService.PREFIXO_CALLBACK_CONFIRMACAO_RESPONSAVEL)) {
            return passoCallbackIniciaCriacaoReserva;
        } else if (prefixo.equals(KeyboardService.PREFIXO_CALLBACK_VOLTAR_HOTEIS)) {
            return passoCallbackReexibeListaHoteis;
        } else if (prefixo.equals(KeyboardService.PREFIXO_CALLBACK_VOLTAR_QUARTOS)) {
            return passoCallbackReexibeDetalhesHotel;
        } else if (prefixo.equals(KeyboardService.PREFIXO_CALLBACK_VOLTAR_PREENCHER_HOSPEDES)) {
            return passoCallbackReexibeListaHospedesParaPreencherDados;
        } else if (prefixo.equals(KeyboardService.PREFIXO_CALLBACK_VOLTAR_INICIO)) {
            return passoCallbackReiniciaProcesso;
        }

        return null;
    }

}
