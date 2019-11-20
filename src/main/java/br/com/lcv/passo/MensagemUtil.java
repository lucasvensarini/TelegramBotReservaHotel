package br.com.lcv.passo;

import br.com.lcv.bot.KeyboardService;
import br.com.lcv.hospede.Hospede;
import br.com.lcv.hospede.HospedeService;
import br.com.lcv.reserva.ReservaDTO;
import br.com.lcv.sessao.Sessao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMediaGroup;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 27/08/19.
 */
@Component
public class MensagemUtil {

    private static final String MENSAGEM_EXEMPLO_CPF = "Digite o número sem letras. Ex: 13271557888";
    private static final String MENSAGEM_EXEMPLO_DATA = "Digite a data no formato dd/mm/aaaa. Ex: 10/09/2019";

    private KeyboardService keyboardService;
    private HospedeService hospedeService;

    @Autowired
    public MensagemUtil(KeyboardService keyboardService, HospedeService hospedeService) {
        this.keyboardService = keyboardService;
        this.hospedeService = hospedeService;
    }

    public SendMessage enviaMensagemInicial(Long chatId) {
        String mensagem = "Utilize os seguintes comandos:" + "\n\n" + "/reservarhotel - Inicia processo de reserva de hotel.";
        return new SendMessage(chatId, mensagem);
    }

    SendMessage exibePreenchimentoCidade(Long chatId) {
        return new SendMessage(chatId, "Em qual cidade você gostaria de reservar um hotel?");
    }

    SendMessage exibePreenchimentoNome(Long chatId) {
        return new SendMessage(chatId, "Nome?");
    }

    SendMessage exibePreenchimentoCPF(Long chatId) {
        return new SendMessage(chatId, "CPF?" + "\n\n" + MENSAGEM_EXEMPLO_CPF);
    }

    SendMessage exibePreenchimentoSexo(Long chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = keyboardService.montaSexoHospedeKeyboard();
        return new SendMessage(chatId, "Sexo?").enableHtml(true).setReplyMarkup(inlineKeyboardMarkup);
    }

    SendMessage exibePreenchimentoDataNascimento(Long chatId) {
        return new SendMessage(chatId, "Data de nascimento?" + "\n\n" + MENSAGEM_EXEMPLO_DATA);
    }

    SendMessage exibeResumoInformacoesOuContnuaPreenchendoDados(Long chatId, Sessao sessao) {
        ReservaDTO reservaDTO = sessao.getReservaDTO();
        if (!hospedeService.isTodosDadosHospedesPreenchidos(reservaDTO.getHospedes())) {
            return continuaPreechimentoDadosHospedes(chatId, sessao);
        } else {
            sessao.adicionaAtributoPassoCorrente(PassoCorrente.CONFIRMAR_DADOS_HOSPEDES);
            return exibeResumoDadosHospedes(chatId, sessao);
        }
    }

    private SendMessage continuaPreechimentoDadosHospedes(Long chatId, Sessao sessao) {
        ReservaDTO reservaDTO = sessao.getReservaDTO();
        Hospede hospede = sessao.getHospedeCorrente();

        sessao.adicionaAtributoPassoCorrente(PassoCorrente.PREENCHIMENTO_HOSPEDES);

        String mensagem = "Dados do hóspede preenchidos!" + "\n\n" + hospede.listaDadosHospede() + "\n\n" + "Falta pouco agora...";
        return enviaListaHospedes(chatId, mensagem, reservaDTO.getHospedes());
    }

    private SendMessage enviaListaHospedes(Long chatId, String mensagem, List<Hospede> hospedes) {
        InlineKeyboardMarkup inlineKeyboardMarkup = keyboardService.montaHospedesKeyboard(hospedes);
        return new SendMessage(chatId, mensagem).enableHtml(true).setReplyMarkup(inlineKeyboardMarkup);
    }

    private SendMessage exibeResumoDadosHospedes(Long chatId, Sessao sessao) {
        ReservaDTO reservaDTO = sessao.getReservaDTO();

        String mensagem = "Tudo certinho, jovem?" + "\n\n" + hospedeService.dadosHospoedesToString(reservaDTO.getHospedes());
        InlineKeyboardMarkup inlineKeyboardMarkup = keyboardService.montaConfirmacaoHospedesKeyboard();

        return new SendMessage(chatId, mensagem).enableHtml(true).setReplyMarkup(inlineKeyboardMarkup);
    }

    SendMediaGroup criaMediaGroup(Long chatId, String urlThumbnail, List<String> urlFotos) {
        SendMediaGroup sendMediaGroup = new SendMediaGroup().setChatId(chatId).disableNotification();

        List<String> urls = new ArrayList<>();
        urls.add(urlThumbnail);
        urls.addAll(urlFotos);

        List<InputMedia> medias = new ArrayList<>();
        for (String urlFoto : urls) {
            medias.add(new InputMediaPhoto().setMedia(urlFoto));
        }

        sendMediaGroup.setMedia(medias);

        return sendMediaGroup;
    }

}
