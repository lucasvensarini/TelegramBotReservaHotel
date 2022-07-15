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

    private final KeyboardService keyboardService;
    private final HospedeService hospedeService;

    @Autowired
    public MensagemUtil(KeyboardService keyboardService, HospedeService hospedeService) {
        this.keyboardService = keyboardService;
        this.hospedeService = hospedeService;
    }

    public SendMessage enviaMensagemInicial(String chatId) {
        String mensagem = "Utilize os seguintes comandos:" + "\n\n" + "/reservarhotel - Inicia processo de reserva de hotel.";
        return new SendMessage(chatId, mensagem);
    }

    SendMessage exibePreenchimentoCidade(String chatId) {
        return new SendMessage(chatId, "Em qual cidade você gostaria de reservar um hotel?");
    }

    SendMessage exibePreenchimentoNome(String chatId) {
        return new SendMessage(chatId, "Nome?");
    }

    SendMessage exibePreenchimentoCPF(String chatId) {
        return new SendMessage(chatId, "CPF?" + "\n\n" + MENSAGEM_EXEMPLO_CPF);
    }

    SendMessage exibePreenchimentoSexo(String chatId) {
        InlineKeyboardMarkup inlineKeyboardMarkup = keyboardService.montaSexoHospedeKeyboard();
        return SendMessage.builder().chatId(chatId).text("Sexo?").replyMarkup(inlineKeyboardMarkup).build();
    }

    SendMessage exibePreenchimentoDataNascimento(String chatId) {
        return new SendMessage(chatId, "Data de nascimento?" + "\n\n" + MENSAGEM_EXEMPLO_DATA);
    }

    SendMessage exibeResumoInformacoesOuContnuaPreenchendoDados(String chatId, Sessao sessao) {
        ReservaDTO reservaDTO = sessao.getReservaDTO();
        if (!hospedeService.isTodosDadosHospedesPreenchidos(reservaDTO.getHospedes())) {
            return continuaPreechimentoDadosHospedes(chatId, sessao);
        } else {
            sessao.adicionaAtributoPassoCorrente(PassoCorrente.CONFIRMAR_DADOS_HOSPEDES);
            return exibeResumoDadosHospedes(chatId, sessao);
        }
    }

    private SendMessage continuaPreechimentoDadosHospedes(String chatId, Sessao sessao) {
        ReservaDTO reservaDTO = sessao.getReservaDTO();
        Hospede hospede = sessao.getHospedeCorrente();

        sessao.adicionaAtributoPassoCorrente(PassoCorrente.PREENCHIMENTO_HOSPEDES);

        String mensagem = "Dados do hóspede preenchidos!" + "\n\n" + hospede.listaDadosHospede() + "\n\n" + "Falta pouco agora...";
        return enviaListaHospedes(chatId, mensagem, reservaDTO.getHospedes());
    }

    private SendMessage enviaListaHospedes(String chatId, String mensagem, List<Hospede> hospedes) {
        InlineKeyboardMarkup inlineKeyboardMarkup = keyboardService.montaHospedesKeyboard(hospedes);
        return SendMessage.builder().chatId(chatId).text(mensagem).replyMarkup(inlineKeyboardMarkup).parseMode("html").build();
    }

    private SendMessage exibeResumoDadosHospedes(String chatId, Sessao sessao) {
        ReservaDTO reservaDTO = sessao.getReservaDTO();

        String mensagem = "Tudo certinho, jovem?" + "\n\n" + hospedeService.dadosHospoedesToString(reservaDTO.getHospedes());
        InlineKeyboardMarkup inlineKeyboardMarkup = keyboardService.montaConfirmacaoHospedesKeyboard();

        return SendMessage.builder().chatId(chatId).text(mensagem).replyMarkup(inlineKeyboardMarkup).parseMode("html").build();
    }

    SendMediaGroup criaMediaGroup(String chatId, String urlThumbnail, List<String> urlFotos) {
        List<String> urls = new ArrayList<>();
        urls.add(urlThumbnail);
        urls.add(urlThumbnail);

        urls.addAll(urlFotos);

        List<InputMedia> medias = new ArrayList<>();
        for (String urlFoto : urls) {
            medias.add(InputMediaPhoto.builder().media(urlFoto).build());
        }

        return SendMediaGroup.builder().chatId(chatId).medias(medias).disableNotification(true).build();
    }

}
