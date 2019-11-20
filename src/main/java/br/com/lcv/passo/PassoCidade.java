package br.com.lcv.passo;

import br.com.lcv.bot.KeyboardService;
import br.com.lcv.bot.TextoUtil;
import br.com.lcv.sessao.Sessao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

@Component
public class PassoCidade implements Passo {

    private static final String MENSAGEM_DEFINICAO_ADULTO = "Adultos são pessoas com idade maior que 12 anos";

    private TextoUtil textoUtil;
    private KeyboardService keyboardService;

    @Autowired
    public PassoCidade(TextoUtil textoUtil, KeyboardService keyboardService) {
        this.textoUtil = textoUtil;
        this.keyboardService = keyboardService;
    }

    @Override
    public SendMessage executa(Long chatId, String texto, Sessao sessao) {
        String textoFormatado = textoUtil.normalizaTexto(texto);

        sessao.getReservaDTO().getDadosBusca().defineCidade(textoFormatado);
        sessao.adicionaAtributoPassoCorrente(PassoCorrente.PREENCHIMENTO_ADULTOS);

        String mensagem = "Massa! E quantos adultos vão se hospedar?" + "\n\n" + MENSAGEM_DEFINICAO_ADULTO;
        InlineKeyboardMarkup inlineKeyboardMarkup = keyboardService.montaQtdHospedesKeyboard(KeyboardService.PREFIXO_CALLBACK_HOSPEDES_ADT);

        return new SendMessage(chatId, mensagem).setReplyMarkup(inlineKeyboardMarkup);
    }

}
